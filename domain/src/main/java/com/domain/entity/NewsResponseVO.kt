package com.domain.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponseVO (
    @SerializedName("source")
    val source: source? = null,
    @SerializedName("author")
    val author : String? = null,
    @SerializedName("title")
    val title : String? = null,
    @SerializedName("description")
    val description : String? = null,
    @SerializedName("url")
    val url : String? = null,
    @SerializedName("urlToImage")
    val urlToImage : String? = null,
    @SerializedName("publishedAt")
    val publishedAt : String? = null,
    @SerializedName("content")
    val content : String? = null
) : Serializable


data class source(
    @SerializedName("id")
    val id : String? = null,
    @SerializedName("name")
    val name : String? = null
)