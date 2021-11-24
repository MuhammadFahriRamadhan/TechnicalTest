package com.example.technicaltest.view.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.technicaltest.R
import com.example.technicaltest.core.base.BaseActivityVM
import com.example.technicaltest.core.domain.model.DetailMovie
import com.example.technicaltest.core.domain.model.Review
import com.example.technicaltest.core.domain.model.Trailer
import com.example.technicaltest.core.ext.loadImage
import com.example.technicaltest.core.ext.observe
import com.example.technicaltest.databinding.ActivityDetailMovieBinding
import com.example.technicaltest.view.detail.item.ReviewExanpandable
import com.example.technicaltest.view.detail.item.ReviewItem
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.expandable.getExpandableExtension
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_detail_movie.view.*
import kotlin.math.abs


class DetailActivity : BaseActivityVM<ActivityDetailMovieBinding,DetailActivityVM>() {

    private val idMovie by lazy {
        intent.getStringExtra("IDMOVIE")
    }
    private val ItemExpandableReview : ItemAdapter<ReviewExanpandable> by lazy {
        ItemAdapter()
    }
    private  var trailer : Trailer? =null
    private val router =  DetailActivityRouter()
    override fun enableBackButton(): Boolean = true

    override fun bindToolbar(): Toolbar? = viewBinding?.toolbar

    override fun getUiBinding(): ActivityDetailMovieBinding {
        return ActivityDetailMovieBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        viewBinding?.run {
            val toolbarParams =
                toolbar.layoutParams as CollapsingToolbarLayout.LayoutParams
            toolbarParams.collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
            toolbar.layoutParams = toolbarParams

            detailMovieRcvReview.run {
                layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.VERTICAL, false)
                adapter = FastAdapter.with(ItemExpandableReview).also {
                    it.getExpandableExtension()
                }
            }
        }

        baseViewModel?.getDetailMovie(idMovie ?: "")
        baseViewModel?.getTrailerMovie(idMovie ?: "")
        baseViewModel?.getReviewMovie(idMovie ?: "")
    }

    override fun initUiListener() {
        viewBinding?.run {
            detailMoviePlayTrailer?.setOnClickListener { view ->
                router.openYoutube(this@DetailActivity,trailer?.resultTrailerResponses?.firstOrNull()?.key)
            }
        }
    }

    override fun observeViewModel(viewModel: DetailActivityVM) {
        observe(viewModel.failureLiveData, ::handleFailure)
        observe(viewModel.isLoadingLiveData, ::handleLoading)
        observe(viewModel.detailMovieEvent, ::handleDetailMovie)
        observe(viewModel.trailerMovieEvent, ::handleTrailerMovie)
        observe(viewModel.reviewMovieEvent, ::handleReviewMovie)

    }

    private fun handleReviewMovie(review: Review?) {
        review.let {
            if (it?.results?.isNotEmpty() == true) {
                ItemExpandableReview.add(
                    review?.results?.map {
                        ReviewExanpandable(it.author.toString()).also { item ->
                            item.subItems = mutableListOf(ReviewItem(it.content.toString()))
                        }
                    } ?: emptyList()
                )
                viewEmptyReview.isVisible = false
            }else{
                viewEmptyReview.isVisible = true
            }
        }
    }

    private fun handleTrailerMovie(trailer: Trailer?) {
        trailer?.let {
            this.trailer = it
        }
    }

    private fun handleDetailMovie(detailMovie: DetailMovie?) {
        viewBinding?.run {
            detailMovie?.let {
               this.detailMovieToolbarTxtName.text = it.title
                setAppBarText(it.title)
                this.detailMovieBackDropImage.loadImage(it.backdropPath.toString() ?: "")
                this.detailMovieImgPoster.loadImage(it.posterPath.toString() ?: "")
                this.detailMovieTextTitle.text = it.title ?: ""
                this.detailMovieTextGenre.text = it.genreResponses?.firstOrNull()?.name ?: ""
                this.detailMovieTextRelaseDate.text = it.releaseDate
                this.detailMovieTextAge.text = if (it.adult == true) "R 13+" else "R 18+"
                this.detailMovieTextRating.text = it.voteAverage.toString()
                this.detailMovieRatingBar.rating  = it.voteAverage?.toFloat() ?: 0.0F

            }
        }
    }

    private fun setAppBarText(title : String?) {
        viewBinding?.run {
            appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
                appBar.post {
                    try {
                        if (abs(verticalOffset) >= appBar.totalScrollRange) {
                            detailMovieToolbarTxtName.text = title
                            bindToolbar()?.let {
                                supportActionBar?.apply {
                                    setHomeAsUpIndicator(R.drawable.ic_arrow_back_black)
                                }
                            }
                        } else {
                            bindToolbar()?.let {
                                supportActionBar?.apply {
                                    setHomeAsUpIndicator(R.drawable.ic_arrow_back)
                                }
                            }
                            detailMovieToolbarTxtName.text = ""
                        }
                    } catch (ex: IllegalStateException) {
                        println(ex.localizedMessage)
                    }
                }
            })

        }
    }

    override fun bindViewModel(): DetailActivityVM {
        return ViewModelProvider(this, viewModelFactory)[DetailActivityVM::class.java]
    }

    companion object {
        fun createIntent(caller: Context,idMovie : String): Intent {
            return Intent(caller, DetailActivity::class.java).apply {
                putExtra("IDMOVIE",idMovie)
            }
        }
    }
}