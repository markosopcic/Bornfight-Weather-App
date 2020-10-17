package com.markosopcic.weather.routing

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.markosopcic.searchlocations.ui.SearchLocationsFragment
import com.markosopcic.core.routing.BaseRouter
import com.markosopcic.core.routing.Router
import com.markosopcic.core.routing.addFragment
import com.markosopcic.weather.R

class RouterImpl(private val activity: AppCompatActivity, private val fragmentManager: FragmentManager) : BaseRouter(activity, fragmentManager), Router {

    override fun showSearchLocationsScreen() = fragmentManager.addFragment(R.id.fragment_container, SearchLocationsFragment())

}
