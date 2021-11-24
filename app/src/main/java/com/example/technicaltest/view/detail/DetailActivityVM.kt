package com.example.technicaltest.view.detail

import com.example.technicaltest.BuildConfig
import com.example.technicaltest.core.base.BaseViewModel
import com.example.technicaltest.core.domain.model.DetailMovie
import com.example.technicaltest.core.domain.model.DiscoverMovie
import com.example.technicaltest.core.domain.model.Review
import com.example.technicaltest.core.domain.model.Trailer
import com.example.technicaltest.core.domain.usecase.GetDetailMovieUseCase
import com.example.technicaltest.core.domain.usecase.GetDiscoverMovieUseCase
import com.example.technicaltest.core.domain.usecase.GetReviewUseCase
import com.example.technicaltest.core.domain.usecase.GetTrailerMovieUseCase
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

class DetailActivityVM @Inject constructor(
    @Named(TagInjection.UI_Scheduler) uiSchedulers: Scheduler,
    @Named(TagInjection.IO_Scheduler) ioScheduler: Scheduler,
    networkHandler: NetworkHandler,
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val getTrailerMovieUseCase: GetTrailerMovieUseCase,
    private val getReviewUseCase: GetReviewUseCase
) : BaseViewModel(uiSchedulers, ioScheduler, networkHandler) {

    val detailMovieEvent = SingleLiveEvent<DetailMovie>()
    val trailerMovieEvent = SingleLiveEvent<Trailer>()
    val reviewMovieEvent = SingleLiveEvent<Review>()

    fun getDetailMovie(movieId : String){
         getDetailMovieUseCase
            .getDetailMovie(movieId,BuildConfig.BASE_API_KEY)
            .compose(applySchedulers())
            .doOnSubscribe { isLoadingLiveData.value = true }
            .doOnTerminate { isLoadingLiveData.value = false }
            .subscribe({
                detailMovieEvent.value = it
            },{
                it.getGeneralErrorServer()
            }).disposedBy(disposable)
    }

    fun getTrailerMovie(movieId: String){
        getTrailerMovieUseCase
            .getTrailerMovie(movieId,BuildConfig.BASE_API_KEY)
            .compose(applySchedulers())
            .filter { it.resultTrailerResponses?.map { it.type == "Trailer" }?.firstOrNull() == true }
            .subscribe({
                  trailerMovieEvent.value = it
            },{
                it.getGeneralErrorServer()
            }).disposedBy(disposable)
    }

    fun getReviewMovie(movieId: String){
        getReviewUseCase
            .getReviewMovie(movieId,BuildConfig.BASE_API_KEY)
            .compose(applySchedulers())
            .subscribe({
                   reviewMovieEvent.value = it
            },{
                it.getGeneralErrorServer()
            }).disposedBy(disposable)
    }
}