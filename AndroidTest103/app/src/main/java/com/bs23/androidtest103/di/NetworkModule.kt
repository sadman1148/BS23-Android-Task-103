package com.bs23.androidtest103.di

import android.app.Application
import com.bs23.androidtest103.data.remote.ApiService
import com.bs23.androidtest103.data.remote.ApiUrl
import com.bs23.androidtest103.utils.Utility
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideAPIService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(ApiUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttp(context: Application) : OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, (5 * 1024 * 1024).toLong()))
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (Utility.hasInternet(context))
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .build()
        return okHttpClient
    }
}