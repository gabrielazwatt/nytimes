package com.mostpopular.nytimes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity(tableName = "media_metadata")
@Parcelize
@JsonClass(generateAdapter = true)
data class MediaMetadata(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val url: String,
    val format: String,
    val height: Int,
    val width: Int
) : Parcelable