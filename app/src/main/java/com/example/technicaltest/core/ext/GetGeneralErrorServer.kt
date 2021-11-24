package com.example.technicaltest.core.ext

import com.example.technicaltest.core.data.response.GeneralErrorResponse
import com.example.technicaltest.core.exception.Failure
import com.google.gson.Gson
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException

fun Throwable.getGeneralErrorServer(): Failure {
    Timber.e(this)
    return when (this) {
        is HttpException -> {
            try {
                val response = Gson().fromJson(
                    this.response()?.errorBody()?.charStream(),
                    GeneralErrorResponse::class.java
                )
                if (this.code() >= 500) {
                    return Failure.ServerError("Terjadi kesalahan server, mohon coba kembali nanti")
                }
                if (this.code() == 401) {
                    return Failure.ExpiredSession
                }
                Failure.ServerError((response.statusMessage ?: "").toString())
            } catch (e: Exception) {
                Failure.ServerError("Terjadi kesalahan, mohon coba kembali nanti")
            }
        }
        is SocketTimeoutException -> Failure.ServerError("Waktu tunggu berakhir, mohon coba kembali")
        is IOException -> Failure.NetworkConnection
        else -> Failure.ServerError("Terjadi kesalahan, mohon coba kembali nanti")
    }
}