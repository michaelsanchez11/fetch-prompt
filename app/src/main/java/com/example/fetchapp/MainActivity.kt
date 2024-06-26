package com.example.fetchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fetchapp.data_models.ItemData
import com.example.fetchapp.ui.theme.FetchAppTheme
import com.example.fetchapp.viewmodels.DataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchAppTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.fillMaxSize()) {
        composable("home") { Home() }
    }
}

@Composable
fun Home() {
    val viewModel: DataViewModel = hiltViewModel()
    val items = viewModel.data.collectAsState().value

    viewModel.getItemDataList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 24.dp,
                bottom = 32.dp)
    ) {
        items?.let { itemList ->
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(itemList) { item ->
                    ListItem(item)
                    Divider(color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun ListItem(item: ItemData) {
    Text(
        text = item.toString(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}
