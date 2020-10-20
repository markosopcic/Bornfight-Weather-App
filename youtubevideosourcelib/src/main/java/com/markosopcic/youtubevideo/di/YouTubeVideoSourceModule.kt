package com.markosopcic.youtubevideo.di

import com.markosopcic.youtubevideo.service.YouTubeService
import com.markosopcic.youtubevideo.source.YouTubeVideoSource
import com.markosopcic.youtubevideo.source.YoutubeVideoSourceImpl
import com.markosopcic.youtubevideo.usecase.SearchYoutubeVideos
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit

private const val BASE_URL = "https://www.googleapis.com"
private const val API_KEY_QUERY_NAME = "key"
private const val API_KEY = ""

fun youtubeVideoSourceModule() = module {


    single {
        get<Retrofit.Builder>()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().addInterceptor(::addApiKeyInterceptor).build())
            .build()
            .create(YouTubeService::class.java)
    }

    single<YouTubeVideoSource> { YoutubeVideoSourceImpl(get()) }

    single { SearchYoutubeVideos(get()) }
}


private fun addApiKeyInterceptor(chain: Interceptor.Chain): Response {
    val request = chain.request()
    val url = request.url().newBuilder().addQueryParameter(API_KEY_QUERY_NAME, API_KEY).build()
    return chain.proceed(request.newBuilder().url(url).build())
}
