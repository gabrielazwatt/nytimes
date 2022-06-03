package com.mostpopular.nytimes.data.repository

import androidx.lifecycle.LiveData
import com.mostpopular.nytimes.model.Article
import com.mostpopular.nytimes.utils.Resource

interface ArticleRepository {

    suspend fun fetchArticles(period: Int): Resource<List<Article>>

    fun observableArticleList(filter: String): LiveData<List<Article>>

    fun getArticleFromDB(id: Long): LiveData<Article>
}