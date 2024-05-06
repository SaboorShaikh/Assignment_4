package com.example.assignment_4

import android.content.ContentValues
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jokes")
data class Joke(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "content") val content: String
) {
    companion object {
        fun fromContentValues(values: ContentValues): Joke {
            val id = if (values.containsKey("id")) values.getAsLong("id") else 0
            val content = values.getAsString("content") ?: ""
            return Joke(id, content)
        }
    }
}
