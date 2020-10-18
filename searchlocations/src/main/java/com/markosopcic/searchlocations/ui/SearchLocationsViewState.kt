package com.markosopcic.searchlocations.ui

sealed class SearchLocationsViewState {

    class SavedLocationsViewState(val locations: List<LocationItemViewState>) : SearchLocationsViewState()
}
