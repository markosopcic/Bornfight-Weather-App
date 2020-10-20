package com.markosopcic.youtubevideo.apimodel

import com.squareup.moshi.Json

data class APIPageInfo(
    @field:Json(name = "resultsPerPage")
    val resultsPerPage: Int,
    @field:Json(name = "totalResults")
    val totalResults: Int
)
