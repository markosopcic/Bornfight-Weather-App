package com.markosopcic.weatherservicelib.di

import com.markosopcic.weatherservicelib.usecase.GetWeatherForLocation
import com.markosopcic.weatherservicelib.usecase.GetWeatherForSavedLocations
import com.markosopcic.weatherservicelib.weatherrepository.WeatherRepository
import com.markosopcic.weatherservicelib.weatherrepository.WeatherRepositoryImpl
import com.markosopcic.weatherservicelib.weatherservice.WeatherService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private const val API_KEY = "940f1d33cdc8aa5815a5307279123be4"
private const val API_KEY_QUERY_NAME = "appid"
private const val BASE_URL = "https://api.openweathermap.org"

fun weatherServiceModule() = module {

    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    factory {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    single {
        get<Retrofit.Builder>()
            .client(get())
            .build()
            .create(WeatherService::class.java)
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(::addApiKeyInterceptor)
            .build()
    }

    single { GetWeatherForLocation(get()) }

    single { GetWeatherForSavedLocations(get(), get()) }

}

private fun addApiKeyInterceptor(chain: Interceptor.Chain): Response {
    val request = chain.request()
    val url = request.url().newBuilder().addQueryParameter(API_KEY_QUERY_NAME, API_KEY).build()
    return chain.proceed(request.newBuilder().url(url).build())
}


