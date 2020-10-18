package com.markosopcic.weatherdetails.ui

import com.markosopcic.core.routing.RoutingActionSender
import com.markosopcic.core.ui.BaseViewModel
import io.reactivex.Scheduler

class WeatherDetailsViewModel(
    backgroundScheduler: Scheduler,
    mainScheduler: Scheduler,
    routingActionSender: RoutingActionSender
) : BaseViewModel<WeatherDetailsViewState>(backgroundScheduler, mainScheduler, routingActionSender) {

}
