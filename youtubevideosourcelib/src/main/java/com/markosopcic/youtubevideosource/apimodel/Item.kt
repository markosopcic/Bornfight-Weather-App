package com.markosopcic.youtubevideosource.apimodel

import com.squareup.moshi.Json

data class Item(
    @field:Json(name = "etag")
    val etag: String,
    @field:Json(name = "id")
    val id: Id,
    @field:Json(name = "kind")
    val kind: String
)
