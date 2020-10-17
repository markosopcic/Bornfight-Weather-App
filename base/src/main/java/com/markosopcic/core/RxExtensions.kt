package com.markosopcic.core

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

fun Completable.subscribeWithOnError(onError: (Throwable) -> Unit) = this.subscribe({}, onError)

fun <T> Single<T>.retryOnNetworkFailure(retryDelaySeconds: Long = 5L) = retryWhen {
    it.flatMapSingle { error ->
        when (error) {
            is SocketTimeoutException -> {
                Timber.d("Retrying network error:")
                Timber.d(error)
                Single.timer(retryDelaySeconds, TimeUnit.SECONDS)
            }
            else -> Single.error(error)
        }
    }
}

fun <T> Flowable<T>.retryOnNetworkFailure(retryDelaySeconds: Long = 5L) = retryWhen {
    it.flatMapSingle { error ->
        when (error) {
            is SocketTimeoutException -> {
                Timber.d("Retrying network error:")
                Timber.d(error)
                Single.timer(retryDelaySeconds, TimeUnit.SECONDS)
            }
            else -> Single.error(error)
        }
    }
}
