package com.mostpopular.nytimes.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.mostpopular.nytimes.BuildConfig
import com.mostpopular.nytimes.model.ArticleList
import com.mostpopular.nytimes.utils.CoroutineTestRule
import com.mostpopular.nytimes.utils.MockResponseFileReader
import com.mostpopular.nytimes.utils.SUCCESS_RESPONSE_FILENAME
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class ArticleListApiTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private var mockWebServer = MockWebServer()
    private lateinit var articleListApi: ArticleListApi

    @Before
    fun setUp() {
        mockWebServer.start()
        articleListApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
            .create(ArticleListApi::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetch articles response is successful`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader(SUCCESS_RESPONSE_FILENAME).content)

        mockWebServer.enqueue(response)
        runBlocking(coroutineTestRule.testDispatcher) {
            val articleListResponse: Response<ArticleList> = articleListApi.fetchArticles(7,  "")
            Truth.assertThat(articleListResponse.isSuccessful).isTrue()
        }
    }

    @Test
    fun `fetch articles response body has desired num_results`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader(SUCCESS_RESPONSE_FILENAME).content)

        mockWebServer.enqueue(response)
        runBlocking(coroutineTestRule.testDispatcher) {
            val articleListResponse: Response<ArticleList> = articleListApi.fetchArticles(7, "")
            Truth.assertThat(articleListResponse.body()?.numResults).isEqualTo(20)
        }
    }
}