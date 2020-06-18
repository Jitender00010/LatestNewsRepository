package com.latestnews.data.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Jitender
 */
@Entity
data class StringEntity(
        @PrimaryKey
        var queryKey: String,
        var value: String
)
