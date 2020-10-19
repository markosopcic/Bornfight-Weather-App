package com.markosopcic.youtubevideosource.usecase

import com.markosopcic.core.usecase.QueryUseCaseWithParam
import com.markosopcic.youtubevideosource.YouTubeVideoSource
import com.markosopcic.youtubevideosource.model.YouTubeVideo
import io.reactivex.Flowable

class SearchYoutubeVideos(private val youTubeVideoSource: YouTubeVideoSource) : QueryUseCaseWithParam<String, List<YouTubeVideo>> {

    override fun invoke(param: String): Flowable<List<YouTubeVideo>> = youTubeVideoSource.searchYoutubeVideos(param).toFlowable()

}
