package com.markosopcic.youtubevideo.usecase

import com.markosopcic.core.usecase.QueryUseCaseWithParam
import com.markosopcic.youtubevideo.source.YouTubeVideoSource
import com.markosopcic.youtubevideo.model.YouTubeVideo
import io.reactivex.Flowable

class SearchYoutubeVideos(private val youTubeVideoSource: YouTubeVideoSource) : QueryUseCaseWithParam<String, List<YouTubeVideo>> {

    override fun invoke(param: String): Flowable<List<YouTubeVideo>> = youTubeVideoSource.searchYoutubeVideos(param).toFlowable()

}
