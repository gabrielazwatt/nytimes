package com.mostpopular.nytimes.data.remote

import com.mostpopular.nytimes.BuildConfig
import com.mostpopular.nytimes.model.ArticleList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleListApi {
    @GET("all-sections/{period}.json")
    suspend fun fetchArticles(
        @Path("period") period: Int = 7, // weekly
        @Query("api-key") apiKey: String = BuildConfig.API_KEY
    ): Response<ArticleList>
}

