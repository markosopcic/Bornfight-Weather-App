package com.markosopcic.youtubevideo.service

import com.markosopcic.youtubevideo.apimodel.APIYoutubeVideoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface YouTubeService {

    @GET("/youtube/v3/search")
    fun listYoutubeVideos(@Query("q") query: String): Single<APIYoutubeVideoResponse>
}
