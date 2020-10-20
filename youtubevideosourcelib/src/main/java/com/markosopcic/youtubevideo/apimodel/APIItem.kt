package com.markosopcic.youtubevideo.apimodel

import com.squareup.moshi.Json

data class APIItem(
    @field:Json(name = "etag")
    val etag: String,
    @field:Json(name = "id")
    val id: APIId,
    @field:Json(name = "kind")
    val kind: String
)
