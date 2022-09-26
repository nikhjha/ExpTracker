package com.example.exptracker.pages

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.exptracker.ui.theme.ExpTrackerTheme

@Composable
fun HomePage(){
    Text(text = "Hi")
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview(){
    ExpTrackerTheme {
        HomePage()
    }
}