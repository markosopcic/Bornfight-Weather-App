package com.markosopcic.youtubevideosource.apimodel

import com.squareup.moshi.Json

data class APIYoutubeVideoResponse(
    @field:Json(name = "etag")
    val etag: String,
    @field:Json(name = "items")
    val items: List<Item>,
    @field:Json(name = "kind")
    val kind: String,
    @field:Json(name = "nextPageToken")
    val nextPageToken: String,
    @field:Json(name = "pageInfo")
    val pageInfo: PageInfo,
    @field:Json(name = "regionCode")
    val regionCode: String
)
