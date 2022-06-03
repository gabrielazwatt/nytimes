package com.mostpopular.nytimes.di

import com.mostpopular.nytimes.data.remote.ArticleListApi
import com.mostpopular.nytimes.utils.BASE_API_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun moshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun retrofit(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_API_URL).build()

    @Singleton
    @Provides
    fun provideArticleListApi(retrofit: Retrofit): ArticleListApi =
        retrofit.create(ArticleListApi::class.java)
}