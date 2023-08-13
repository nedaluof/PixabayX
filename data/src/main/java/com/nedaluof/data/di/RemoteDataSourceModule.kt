package com.nedaluof.data.di

import com.nedaluof.data.BuildConfig
import com.nedaluof.data.datasource.remote.api.PixabayXApiServices
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created By NedaluOf - 7/22/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

  @Singleton
  @Provides
  fun provideMoshi(): Moshi = Moshi.Builder().build()

  @Singleton
  @Provides
  fun provideInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().also {
    it.level = HttpLoggingInterceptor.Level.BODY
  }

  @Singleton
  @Provides
  fun provideOkHttpClient(
    interceptor: HttpLoggingInterceptor
  ): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
    .build()

  @Singleton
  @Provides
  fun provideRetrofitClient(
    okHttpClient: OkHttpClient,
    moshi: Moshi
  ): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

  @Singleton
  @Provides
  fun providePixabayXApiServices(
    retrofitClient: Retrofit
  ): PixabayXApiServices = retrofitClient.create(PixabayXApiServices::class.java)

  private const val TIME_OUT = 180L
}