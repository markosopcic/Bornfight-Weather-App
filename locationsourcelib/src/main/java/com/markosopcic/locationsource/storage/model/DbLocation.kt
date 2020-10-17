package com.markosopcic.locationsource.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbLocation(@PrimaryKey val id: String, val name: String, val longitude: Double, val latitude: Double)
