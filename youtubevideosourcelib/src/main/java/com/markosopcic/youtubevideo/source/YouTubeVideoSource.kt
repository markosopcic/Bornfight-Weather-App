package com.markosopcic.youtubevideo.source

import com.markosopcic.youtubevideo.model.YouTubeVideo
import io.reactivex.Single

interface YouTubeVideoSource {

    fun searchYoutubeVideos(query: String): Single<List<YouTubeVideo>>
}
