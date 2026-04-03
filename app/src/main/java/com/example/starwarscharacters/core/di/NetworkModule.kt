package com.example.starwarscharacters.core.di

import com.example.starwarscharacters.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	@Singleton
	fun provideOkHttpClient(): OkHttpClient {
		return OkHttpClient.Builder()
			.connectTimeout(30, TimeUnit.SECONDS)
			.readTimeout(30, TimeUnit.SECONDS)
			.addInterceptor(HttpLoggingInterceptor().apply {
				level = if (BuildConfig.DEBUG)
					HttpLoggingInterceptor.Level.BODY
				else
					HttpLoggingInterceptor.Level.NONE
			}).build()
	}
}