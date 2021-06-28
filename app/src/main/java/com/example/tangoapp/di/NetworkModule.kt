package com.example.tangoapp.di

import android.content.Context
import android.util.Log
import com.example.tangoapp.data.ApiService
import com.example.tangoapp.utils.isOnline
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

@Module(includes = [AppModule::class])
@InstallIn(value = [SingletonComponent::class])
object NetworkModule {

    private const val BASE_URL = "https://api.spaceflightnewsapi.net/v3/"

    @Provides
    fun provideAPI(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideCache(cacheFile: File): Cache = Cache(cacheFile, 10 * 1024 * 1024)

    @Provides
    fun provideFile(@ApplicationContext context: Context): File = File(context.cacheDir, "okhttp_cache")

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.v("LoggingInterceptor", message)
            }
        })

        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor, cache: Cache, @ApplicationContext context: Context): OkHttpClient {
        val onlineInterceptor = Interceptor { chain ->
            var request = chain.request()
            request = if (isOnline(context)) {
                request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build()
            } else {
                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
                    .build()
            }
            chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(onlineInterceptor)
            .build()
    }
}