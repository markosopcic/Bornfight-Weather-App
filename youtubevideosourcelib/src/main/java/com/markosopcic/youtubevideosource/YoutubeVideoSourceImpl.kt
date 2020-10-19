package com.markosopcic.youtubevideosource

import com.markosopcic.core.retryOnNetworkFailure
import com.markosopcic.youtubevideosource.apimodel.APIYoutubeVideoResponse
import com.markosopcic.youtubevideosource.model.YouTubeVideo
import io.reactivex.Single

class YoutubeVideoSourceImpl(private val youTubeService: YouTubeService) : YouTubeVideoSource {
    override fun searchYoutubeVideos(query: String): Single<List<YouTubeVideo>> = youTubeService.listYoutubeVideos(query)
        .retryOnNetworkFailure()
        .map { it.toDomainMovies() }

    fun APIYoutubeVideoResponse.toDomainMovies() = items.map { YouTubeVideo(it.kind, it.id.videoId) }

}
