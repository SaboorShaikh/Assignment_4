package com.example.assignment_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.wear.compose.material.MaterialTheme
import com.example.assignment_4.ui.theme.Assignment_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment_4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val jokeDao = (application as App).db.jokeDao()
                    JokeList(jokes = jokeDao.getAll())
                }
            }
        }
    }
}

@Composable
fun JokeList(jokes: LiveData<List<Joke>>) {
    val jokeList by jokes.observeAsState(initial = emptyList())
    LazyColumn {
        items(jokeList) { joke ->
            Text(text = joke.content)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Assignment_4Theme {
        JokeList(jokes = MockData.jokesLiveData) // MockData.jokesLiveData is for preview purposes
    }
}
