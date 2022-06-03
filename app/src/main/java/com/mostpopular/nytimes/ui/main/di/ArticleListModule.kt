package com.mostpopular.nytimes.ui.main.di

import com.mostpopular.nytimes.data.repository.ArticleRepository
import com.mostpopular.nytimes.data.repository.NyArticleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ArticleListModule {
    @ViewModelScoped
    @Binds
    abstract fun bindArticleRepository(repository: NyArticleRepository): ArticleRepository
}