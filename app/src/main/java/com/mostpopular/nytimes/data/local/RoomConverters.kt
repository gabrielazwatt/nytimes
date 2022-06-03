package com.mostpopular.nytimes.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.mostpopular.nytimes.model.Media
import com.mostpopular.nytimes.model.MediaMetadata
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ProvidedTypeConverter
class RoomConverters @Inject constructor(val moshi: Moshi) {
    private fun <T> getAdapter(classs: Class<T>): JsonAdapter<List<T>> {
        val listType = Types.newParameterizedType(List::class.java, classs)
        return moshi.adapter(listType)
    }

    @TypeConverter
    fun fromMediaList(data: List<Media>): String {
        return getAdapter(Media::class.java).toJson(data)
    }

    @TypeConverter
    fun toMediaList(json: String): List<Media>? {
        return getAdapter(Media::class.java).fromJson(json)
    }

    @TypeConverter
    fun fromMediaMetadataList(data: List<MediaMetadata>): String {
        return getAdapter(MediaMetadata::class.java).toJson(data)
    }

    @TypeConverter
    fun toMediaMetadataList(json: String): List<MediaMetadata>? {
        return getAdapter(MediaMetadata::class.java).fromJson(json)
    }

    @TypeConverter
    fun fromDesFacetList(data: List<String>): String {
        return getAdapter(String::class.java).toJson(data)
    }

    @TypeConverter
    fun toDesFacetList(json: String): List<String>? {
        return getAdapter(String::class.java).fromJson(json)
    }
}