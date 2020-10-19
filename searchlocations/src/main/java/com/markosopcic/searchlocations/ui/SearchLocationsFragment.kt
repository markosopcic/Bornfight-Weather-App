package com.markosopcic.searchlocations.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.markosopcic.core.ui.BaseFragment
import com.markosopcic.core.ui.addBasicDivider
import com.markosopcic.core.ui.setVisible
import com.markosopcic.searchlocations.R
import com.markosopcic.searchlocations.ui.SearchLocationsViewState.SavedLocationsViewState
import kotlinx.android.synthetic.main.fragment_search_locations.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

private const val PLACES_API_KEY = ""

class SearchLocationsFragment : BaseFragment<SearchLocationsViewState>(R.layout.fragment_search_locations) {

    companion object {
        private val PLACES_FIELDS = listOf(Place.Field.ID, Place.Field.ADDRESS, Place.Field.ADDRESS_COMPONENTS, Place.Field.LAT_LNG)
    }

    override val viewModel: SearchLocationsViewModel by viewModel()

    private val adapter: LocationsAdapter by inject { parametersOf(requireContext(), viewModel::savedLocationSelected) }

    override fun initialiseView() {
        initializePlacesFragment()
        search_location_recyclerView.adapter = adapter
        search_location_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addBasicDivider()
        }

        search_locations_userLocation.setOnClickListener { viewModel.getUsersLocation() }
    }

    private fun initializePlacesFragment() {
        Places.initialize(requireContext(), PLACES_API_KEY, resources.configuration.locale)
        val autocompleteFragment = childFragmentManager.findFragmentById(R.id.search_locations_searchFragment) as AutocompleteSupportFragment
        autocompleteFragment.run {
            setTypeFilter(TypeFilter.CITIES)
            setPlaceFields(PLACES_FIELDS)
            setOnPlaceSelectedListener(object : PlaceSelectionListener {

                override fun onPlaceSelected(place: Place): Unit = with(place.latLng!!) {
                    viewModel.locationSelected(place.address!!, latitude, longitude)
                }

                override fun onError(errorStatus: Status) = Timber.d(errorStatus.statusMessage)
            })
        }
    }

    override fun render(viewState: SearchLocationsViewState) = when (viewState) {
        is SavedLocationsViewState -> updateSavedLocations(viewState)
        is SearchLocationsViewState.Loading -> search_locations_spinner.setVisible(viewState.isLoading)
    }

    private fun updateSavedLocations(viewState: SavedLocationsViewState) {
        adapter.updateItems(viewState.locations)
        search_location_noSavedLocationsText.setVisible(viewState.locations.isEmpty())
    }
}
