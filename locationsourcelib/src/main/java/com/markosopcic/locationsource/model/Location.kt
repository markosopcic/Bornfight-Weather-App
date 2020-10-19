package com.markosopcic.locationsource.model

data class Location(val id: Int?, val name: String, val longitude: Double, val latitude: Double) {

    companion object {
        val EMPTY = Location(null, "", 0.toDouble(), 0.toDouble())
    }
}
