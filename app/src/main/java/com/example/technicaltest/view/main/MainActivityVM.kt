package com.example.technicaltest.view.main

import com.example.technicaltest.BuildConfig
import com.example.technicaltest.core.base.BaseViewModel
import com.example.technicaltest.core.domain.usecase.GetDiscoverMovieUseCase
import com.example.technicaltest.core.domain.model.DiscoverMovie
import com.example.technicaltest.core.exception.Failure
import com.example.technicaltest.core.exception.NotFoundFailure
import com.example.technicaltest.core.ext.TagInjection
import com.example.technicaltest.core.ext.disposedBy
import com.example.technicaltest.core.ext.getGeneralErrorServer
import com.example.technicaltest.core.helper.SingleLiveEvent
import com.example.technicaltest.core.helper.util.NetworkHandler
import io.reactivex.Scheduler
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Named

class MainActivityVM @Inject constructor(
    @Named(TagInjection.UI_Scheduler) uiSchedulers: Scheduler,
    @Named(TagInjection.IO_Scheduler) ioScheduler: Scheduler,
    networkHandler: NetworkHandler,
    private val getDiscoverMovieUseCase: GetDiscoverMovieUseCase
) : BaseViewModel(uiSchedulers, ioScheduler, networkHandler) {

    val discoverMovieEvent = SingleLiveEvent<DiscoverMovie>()
    val discoverMovieLoadMoreEvent = SingleLiveEvent<DiscoverMovie>()
    val loadingLoadMoreEvent = SingleLiveEvent<Boolean>()


    fun getMovies(page : Int){
        executeJob {
            getDiscoverMovieUseCase.getMovies(BuildConfig.BASE_API_KEY,page)
                .compose(applySchedulers())
                .doOnSubscribe { isLoadingLiveData.value = true }
                .doOnTerminate { isLoadingLiveData.value = false }
                .subscribe({
                    discoverMovieEvent.value = it
                },{
                    handleFailure(it.getGeneralErrorServer())
                    if (it is HttpException && it.code() == 422) {
                        handleFailure(NotFoundFailure.EmptyListLoadMore())
                        return@subscribe
                    }
                    handleFailure(Failure.ServerError(it.message ?: ""))
                }).disposedBy(disposable)
        }
    }


    fun loadMore(page: Int){
        executeJob {
            getDiscoverMovieUseCase.getMovies(BuildConfig.BASE_API_KEY,page)
                .compose(applySchedulers())
                .doOnSubscribe { loadingLoadMoreEvent.value = true }
                .doOnTerminate { loadingLoadMoreEvent.value = false }
                .subscribe({
                    discoverMovieLoadMoreEvent.value = it
                },{
                    handleFailure(it.getGeneralErrorServer())
                    if (it is HttpException && it.code() == 422) {
                        handleFailure(NotFoundFailure.EmptyListLoadMore())
                        return@subscribe
                    }
                    handleFailure(Failure.ServerError(it.message ?: ""))
                }).disposedBy(disposable)
        }
    }
}