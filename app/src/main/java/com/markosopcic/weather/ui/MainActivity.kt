package com.markosopcic.weather.ui

import android.os.Bundle
import android.widget.Toast
import com.markosopcic.alertinglib.usecase.GetAlerts
import com.markosopcic.core.di.MAIN_SCHEDULER
import com.markosopcic.core.routing.RoutingActionSender
import com.markosopcic.core.ui.BaseActivity
import com.markosopcic.weather.R
import com.markosopcic.weather.backgroundworkers.BaseWorker
import com.markosopcic.weather.di.WORKERS
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val routingActionSender: RoutingActionSender by inject()

    private val disposables = CompositeDisposable()

    private val getAlerts: GetAlerts by inject()

    private val mainScheduler: Scheduler by inject(named(MAIN_SCHEDULER))

    private val workers: List<BaseWorker> by inject(named(WORKERS))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        routingHandler.setRoutingConsumer(this)
        workers.forEach(BaseWorker::startWork)
        disposables.add(
            getAlerts().observeOn(mainScheduler).subscribe {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        )
    }

    override fun onPause() {
        workers.forEach(BaseWorker::stopWork)
        disposables.clear()
        routingHandler.removeRoutingConsumer(this)
        super.onPause()
    }
}
