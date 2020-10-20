package com.markosopcic.youtubevideo.apimodel

import com.squareup.moshi.Json

data class APIId(
    @field:Json(name = "kind")
    val kind: String,
    @field:Json(name = "videoId")
    val videoId: String
)
