package com.markosopcic.weather

import android.os.Bundle
import com.markosopcic.core.routing.Router
import com.markosopcic.core.routing.RoutingActionSender
import com.markosopcic.core.ui.BaseActivity
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val routingActionSender: RoutingActionSender by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        routingHandler.setRoutingConsumer(this)
    }

    override fun onPause() {
        routingHandler.removeRoutingConsumer(this)
        super.onPause()
    }
}
