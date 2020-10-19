package com.markosopcic.youtubevideosource

import com.markosopcic.youtubevideosource.model.YouTubeVideo
import io.reactivex.Single

interface YouTubeVideoSource {

    fun searchYoutubeVideos(query: String): Single<List<YouTubeVideo>>
}
