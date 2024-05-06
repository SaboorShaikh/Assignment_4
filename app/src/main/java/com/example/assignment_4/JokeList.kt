package com.example.assignment_4

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData

@Composable
fun JokeList(jokes: LiveData<List<Joke>>) {
    val jokeList by jokes.observeAsState(initial = emptyList())
    LazyColumn {
        items(jokeList) { joke ->
            Text(text = joke.content)
        }
    }
}
