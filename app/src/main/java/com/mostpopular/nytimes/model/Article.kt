package com.mostpopular.nytimes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity(tableName = "article")
@Parcelize
@JsonClass(generateAdapter = true)
data class Article(
    @PrimaryKey
    val id: Long,
    val url: String,
    val source: String,
    @Json(name = "published_date") val publishedDate: String,
    @Json(name = "updated") val updatedDate: String,
    val section: String,
    @Json(name = "subsection") val subSection: String,
    @Json(name = "byline") val byLine: String,
    val title: String,
    @Json(name = "des_facet") val desFacetList: List<String>,
    @Json(name = "media") val mediaList: List<Media>
) : Parcelable