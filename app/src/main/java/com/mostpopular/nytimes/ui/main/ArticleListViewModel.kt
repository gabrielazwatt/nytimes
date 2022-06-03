package com.mostpopular.nytimes.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mostpopular.nytimes.data.repository.ArticleRepository
import com.mostpopular.nytimes.model.Article
import com.mostpopular.nytimes.utils.Resource
import com.mostpopular.nytimes.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(private val articleRepository: ArticleRepository) :
    ViewModel() {

    private val _networkErrorState = MutableLiveData<Boolean?>()
    val networkErrorState: LiveData<Boolean?>
        get() = _networkErrorState

    private val _isLoading = MutableLiveData<Boolean?>()
    val isLoading: LiveData<Boolean?>
        get() = _isLoading

    fun articleList(filter: String = "%%") = articleRepository.observableArticleList(filter)

    init {
        getArticleList()
    }

    private fun getArticleList() {
        viewModelScope.launch {
            val fetchArticles: Resource<List<Article>> = articleRepository.fetchArticles(7)
            _networkErrorState.postValue(
                when (fetchArticles.status) {
                    Status.ERROR -> {
                        articleList().value.isNullOrEmpty()
                    }
                    else -> false
                }
            )
        }
    }

    fun refreshArticles() {
        _isLoading.postValue(false)
        getArticleList()
    }

}