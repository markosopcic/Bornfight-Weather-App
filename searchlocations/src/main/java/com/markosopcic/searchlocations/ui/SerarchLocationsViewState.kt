package com.markosopcic.searchlocations.ui

sealed class SerarchLocationsViewState {

    class SavedLocationsViewState(val locations: List<LocationItemViewState>) : SerarchLocationsViewState()
}
