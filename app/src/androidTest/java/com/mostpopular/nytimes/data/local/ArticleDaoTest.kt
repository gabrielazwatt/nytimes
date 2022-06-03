package com.mostpopular.nytimes.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.mostpopular.nytimes.model.Article
import com.mostpopular.nytimes.utils.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ArticleDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var dao: ArticleDao

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun insertArticles_checkIfAllValuesArePresentInDb() = runBlockingTest {
        val articleList = FakeArticlesData.articles

        dao.insertArticleList(articleList)

        val insertedArticles: List<Article> = dao.getAllArticles("%%").getOrAwaitValue()

        Truth.assertThat(insertedArticles.first().id).isEqualTo(100000008369475)
    }
}