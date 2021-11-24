package com.example.technicaltest.view.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.technicaltest.R
import com.example.technicaltest.core.base.BaseActivityVM
import com.example.technicaltest.core.domain.model.DiscoverMovie
import com.example.technicaltest.core.exception.Failure
import com.example.technicaltest.core.exception.NotFoundFailure
import com.example.technicaltest.core.ext.observe
import com.example.technicaltest.databinding.ActivityMainBinding
import com.example.technicaltest.view.common.item.ProgressBarItem
import com.example.technicaltest.view.main.item.MovieEmptyItem
import com.example.technicaltest.view.main.item.MovieItem
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.scroll.EndlessRecyclerOnScrollListener

class MainActivity : BaseActivityVM<ActivityMainBinding,MainActivityVM>() {
    private lateinit var itemAdapter   : GenericItemAdapter
    private lateinit var fastAdapter   : GenericFastItemAdapter
    private lateinit var endlessScroll : EndlessRecyclerOnScrollListener
    private val mainRouter = MainRouter()
    override fun enableBackButton(): Boolean  = false

    override fun bindToolbar(): Toolbar? = null

    override fun getUiBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        baseViewModel?.getMovies(1)
        initRecyclerView()
    }

    override fun initUiListener() {
        viewBinding?.mainSwipeRefresh?.setOnRefreshListener {
            baseViewModel?.getMovies(1)
        }

        fastAdapter.onClickListener = { _,_,item,_ ->
            when (item){
                is MovieItem ->{
                    mainRouter.gotoDetailActivity(this@MainActivity,item.discoverMovie.id.toString())
                }
            }
            true
        }
    }

    private fun initRecyclerView(){
        viewBinding?.run {
            fastAdapter = FastItemAdapter()
            itemAdapter = ItemAdapter.items()
            fastAdapter.addAdapter(1,itemAdapter)

            endlessScroll = object  : EndlessRecyclerOnScrollListener(itemAdapter){
                override fun onLoadMore(currentPage: Int) {
                    mainRecylrView.post {
                        itemAdapter.clear()
                    }
                    baseViewModel?.loadMore(currentPage +1)
                }

            }

            mainRecylrView.let {
                it.layoutManager = GridLayoutManager(this@MainActivity,2)
                it.adapter = fastAdapter
                it.addOnScrollListener(endlessScroll)
            }
        }
    }

    override fun observeViewModel(viewModel: MainActivityVM) {
        observe(viewModel.failureLiveData,::handleFailure)
        observe(viewModel.discoverMovieEvent,::handleMovie)
        observe(viewModel.isLoadingLiveData, ::handleLodingInitialValue)
        observe(viewModel.loadingLoadMoreEvent, ::handleLoadingLoadMore)
        observe(viewModel.discoverMovieLoadMoreEvent,:: handleLoadMoreMovie)
    }

    private fun handleLodingInitialValue(showLoading : Boolean?) {
        viewBinding?.mainSwipeRefresh?.isRefreshing = showLoading == true
    }

    private fun handleLoadMoreMovie(discoverMovie: DiscoverMovie?) {
        discoverMovie?.let {
            it.resultDataRespons?.map {
                fastAdapter.add(MovieItem(it))
            }
        }
    }

    private fun handleMovie(discoverMovie: DiscoverMovie?) {
        discoverMovie?.let {
            fastAdapter.clear()
            it.resultDataRespons?.map { result -> fastAdapter.add(MovieItem(result)) }
            endlessScroll.run {
                enable()
                resetPageCount(1)
            }
        }
    }


    private fun handleLoadingLoadMore(showLoading: Boolean?) {
        viewBinding?.mainRecylrView?.post {
            if (showLoading == true && fastAdapter.itemCount > 0) {
                itemAdapter.add(ProgressBarItem())
            } else {
                itemAdapter.clear()
            }
        }
    }


    override fun handleFailure(failure: Failure?) {
        when (failure) {
            is NotFoundFailure.DataNotExist -> {
                fastAdapter.clear()
                fastAdapter.add(MovieEmptyItem())
            }
            is NotFoundFailure.EmptyListLoadMore -> endlessScroll.disable()
            else -> super.handleFailure(failure)
        }
    }

    override fun bindViewModel(): MainActivityVM {
        return ViewModelProvider(this, viewModelFactory)[MainActivityVM::class.java]

    }

    companion object {
        fun createIntent(caller: Context): Intent {
            return Intent(caller, MainActivity::class.java)
        }
    }

}