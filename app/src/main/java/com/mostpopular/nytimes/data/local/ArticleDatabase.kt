package com.mostpopular.nytimes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mostpopular.nytimes.model.Article
import com.mostpopular.nytimes.model.Media
import com.mostpopular.nytimes.model.MediaMetadata

@Database(
    version = 1,
    entities = [Article::class, Media::class, MediaMetadata::class],
    exportSchema = true
)
@TypeConverters(RoomConverters::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}