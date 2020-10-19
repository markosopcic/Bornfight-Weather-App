package com.markosopcic.youtubevideosource.apimodel

import com.squareup.moshi.Json

data class PageInfo(
    @field:Json(name = "resultsPerPage")
    val resultsPerPage: Int,
    @field:Json(name = "totalResults")
    val totalResults: Int
)
