package com.example.exptracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.exptracker.datastore.UserPreference
import com.example.exptracker.navigation.Navigation
import com.example.exptracker.ui.theme.ExpTrackerTheme
import com.example.exptracker.viewmodels.TransactionViewModel
import com.example.exptracker.viewmodels.UserDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpTrackerTheme {
                val context = LocalContext.current
                val dataStore = UserPreference(context)
                val userDetailViewModel = UserDetailViewModel(dataStore)
                val transactionViewModel = hiltViewModel<TransactionViewModel>()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation(userDetailViewModel, transactionViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExpTrackerTheme {
        Greeting("Android")
    }
}