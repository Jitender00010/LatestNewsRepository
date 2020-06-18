package com.domain.entity

import com.google.gson.annotations.SerializedName

data class NewsResponse (
    @SerializedName("source")
    val source: source,
    @SerializedName("author")
    val author : String?,
@SerializedName("title")
    val title : String?,
@SerializedName("description")
    val description : String?,
@SerializedName("url")
    val url : String?,
@SerializedName("urlToImage")
    val urlToImage : String?,
@SerializedName("publishedAt")
    val publishedAt : String?,
@SerializedName("publishedAt")
    val publishedAt : String?
)


data class source(
    @SerializedName("id")
    val id : String?,
    @SerializedName("name")
    val name : String?
)