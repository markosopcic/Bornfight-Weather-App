package com.markosopcic.core

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.processors.FlowableProcessor
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

fun Completable.subscribeWithOnError(onError: (Throwable) -> Unit) = this.subscribe({}, onError)

fun <T> Single<T>.retryOnNetworkFailure(retryDelaySeconds: Long = 5L) = retryWhen {
    it.flatMapSingle { error ->
        if (error is SocketTimeoutException || error is UnknownHostException) {
            Timber.d("Retrying network error:")
            Timber.d(error)
            Single.timer(retryDelaySeconds, TimeUnit.SECONDS)
        } else {
            Single.error(error)
        }
    }
}

fun <T> Flowable<T>.retryOnNetworkFailure(retryDelaySeconds: Long = 5L) = retryWhen {
    it.flatMapSingle { error ->
        if (error is SocketTimeoutException || error is UnknownHostException) {
            Timber.d("Retrying network error:")
            Timber.d(error)
            Single.timer(retryDelaySeconds, TimeUnit.SECONDS)
        } else {
            Single.error(error)
        }
    }
}

fun Completable.publishProgress(progressPublisher: FlowableProcessor<Boolean>): Completable = this
    .doOnSubscribe { progressPublisher.onNext(true) }
    .doOnTerminate { progressPublisher.onNext(false) }

fun <T> Flowable<T>.publishProgress(progressPublisher: FlowableProcessor<Boolean>): Flowable<T> =
    this.doOnSubscribe { progressPublisher.onNext(true) }
        .doOnNext { progressPublisher.onNext(false) }
