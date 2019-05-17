package com.aquidigital.synchronosstech.repository.remote

import android.app.Application
import com.aquidigital.synchronosstech.R
import com.aquidigital.synchronosstech.repository.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
internal object ApiModule {

    @JvmStatic
    @Provides
    fun okHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
        return okHttpClient.build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideApi(okHttpClient: OkHttpClient,
                   context: Application): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.api_url))
            .client(okHttpClient)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}