package com.latestnews.data.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "NewsTable")
class NewsEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "source")
    var source: String? = null

    @ColumnInfo(name = "author")
    var author: String? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "description")
    var description: String? = null

    @ColumnInfo(name = "url")
    var url: String? = null

    @ColumnInfo(name = "urlToImage")
    var urlToImage: String? = null

    @ColumnInfo(name = "publishedAt")
    var publishedAt: String? = null

    @ColumnInfo(name = "content")
    var content: String? = null

    @ColumnInfo(name = "time")
    var time: String? = null

    @ColumnInfo(name = "pageNumber")
    var pageNumber: Int = -1


}