package com.mostpopular.nytimes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mostpopular.nytimes.model.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticleList(articleList: List<Article>)

    @Query("SELECT * FROM article WHERE article.title LIKE :filter")
    fun getAllArticles(filter: String): LiveData<List<Article>>

    @Query("SELECT * FROM article WHERE id =:articleId")
    fun getArticleFromDb(articleId:Long): LiveData<Article>
}