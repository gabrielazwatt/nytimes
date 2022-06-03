package com.mostpopular.nytimes.data.repository

import androidx.lifecycle.LiveData
import com.mostpopular.nytimes.BaseApplication
import com.mostpopular.nytimes.R
import com.mostpopular.nytimes.data.local.ArticleDao
import com.mostpopular.nytimes.data.remote.ArticleListApi
import com.mostpopular.nytimes.model.Article
import com.mostpopular.nytimes.model.ArticleList
import com.mostpopular.nytimes.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NyArticleRepository @Inject constructor(
    private val articleDao: ArticleDao,
    private val articleListApi: ArticleListApi
) : ArticleRepository {
    override suspend fun fetchArticles(period: Int): Resource<List<Article>> {
        return try {
            withContext(Dispatchers.IO) {
                Resource.loading(null)
                val articleListResponse: Response<ArticleList> =
                    articleListApi.fetchArticles(period)
                if (articleListResponse.isSuccessful) {
                    articleListResponse.body()?.let {
                        it.let {
                            articleDao.insertArticleList(it.results)
                        }
                        return@let Resource.success(null)
                    } ?: Resource.error(BaseApplication.instance.ress.getString(R.string.unknown_error_occurred), null)
                } else {
                    Resource.error(BaseApplication.instance.ress.getString(R.string.unknown_error_occurred), null)
                }
            }
        } catch (e: Exception) {
            Resource.error(BaseApplication.instance.ress.getString(R.string.network_error_occurred), null)
        }
    }

    override fun observableArticleList(filter: String): LiveData<List<Article>> =
        articleDao.getAllArticles(filter)

    override fun getArticleFromDB(id: Long): LiveData<Article>  = articleDao.getArticleFromDb(id)

}