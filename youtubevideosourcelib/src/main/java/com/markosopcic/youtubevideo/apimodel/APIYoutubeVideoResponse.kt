package com.markosopcic.youtubevideo.apimodel

import com.squareup.moshi.Json

data class APIYoutubeVideoResponse(
    @field:Json(name = "etag")
    val etag: String,
    @field:Json(name = "items")
    val items: List<APIItem>,
    @field:Json(name = "kind")
    val kind: String,
    @field:Json(name = "nextPageToken")
    val nextPageToken: String,
    @field:Json(name = "pageInfo")
    val pageInfo: APIPageInfo,
    @field:Json(name = "regionCode")
    val regionCode: String
)
