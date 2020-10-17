package com.markosopcic.searchlocations.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.markosopcic.searchlocations.R
import com.markosopcic.searchlocations.ui.SerarchLocationsViewState.SavedLocationsViewState
import com.markosopcic.core.ui.BaseFragment
import com.markosopcic.core.ui.addBasicDivider
import kotlinx.android.synthetic.main.fragment_search_locations.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

private const val PLACES_API_KEY = ""

class SearchLocationsFragment : BaseFragment<SerarchLocationsViewState>(R.layout.fragment_search_locations) {

    override val viewModel: SearchLocationsViewModel by viewModel()

    private val adapter: LocationsAdapter by inject { parametersOf(requireContext()) }

    override fun initialiseView() {
        initializePlacesFragment()
        add_location_recyclerView.adapter = adapter
        add_location_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addBasicDivider()
        }
    }

    private fun initializePlacesFragment() {
        Places.initialize(requireContext(), PLACES_API_KEY, resources.configuration.locale)
        val autocompleteFragment = childFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment
        autocompleteFragment.run {
            setTypeFilter(TypeFilter.CITIES)
            setPlaceFields(listOf(Place.Field.ID, Place.Field.ADDRESS, Place.Field.ADDRESS_COMPONENTS, Place.Field.LAT_LNG))
            setOnPlaceSelectedListener(object : PlaceSelectionListener {
                override fun onPlaceSelected(place: Place): Unit =
                    with(place) { viewModel.locationSelected(id!!, address!!, latLng?.longitude, latLng?.latitude) }

                override fun onError(errorStatus: Status) = Timber.d(errorStatus.statusMessage)
            })
        }
    }

    override fun render(viewState: SerarchLocationsViewState) = when (viewState) {
        is SavedLocationsViewState -> adapter.updateItems(viewState.locations)
    }
}
