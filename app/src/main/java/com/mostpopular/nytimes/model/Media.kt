package com.mostpopular.nytimes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity(tableName = "media")
@Parcelize
@JsonClass(generateAdapter = true)
data class Media(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val type: String,
    @Json(name = "subtype") val subType: String,
    val caption: String,
    val copyright: String,
    @Json(name = "media-metadata") val mediaMetadataList: List<MediaMetadata>
) : Parcelable
