package com.markosopcic.weather.backgroundworkers

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseWorker {

    protected val disposables = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) = disposables.add(disposables)

    abstract fun startWork()

    abstract fun stopWork()
}
