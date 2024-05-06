package com.example.assignment_4

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room

class JokeContentProvider : ContentProvider() {
    private lateinit var jokeDao: JokeDao

    companion object {
        private const val JOKES = 1
        private const val JOKE_ID = 2
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI("com.example.assignment_4", "jokes", JOKES)
            uriMatcher.addURI("com.example.assignment_4", "jokes/#", JOKE_ID)
        }
    }

    override fun onCreate(): Boolean {
        val context = context ?: return false
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "jokes.db").build()
        jokeDao = db.jokeDao()
        return true
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        return when (uriMatcher.match(uri)) {
            JOKES -> jokeDao.getAllJokesCursor()
            JOKE_ID -> jokeDao.getJokeCursorById(uri.lastPathSegment!!.toLong())
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            JOKES -> "vnd.android.cursor.dir/vnd.com.example.jokes"
            JOKE_ID -> "vnd.android.cursor.item/vnd.com.example.joke"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = jokeDao.insert(Joke.fromContentValues(values!!))
        return Uri.parse("content://com.example.assignment_4/jokes/$id")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return when (uriMatcher.match(uri)) {
            JOKE_ID -> jokeDao.deleteJokeById(uri.lastPathSegment!!.toLong())
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        return when (uriMatcher.match(uri)) {
            JOKE_ID -> jokeDao.update(Joke.fromContentValues(values!!))
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }
}
