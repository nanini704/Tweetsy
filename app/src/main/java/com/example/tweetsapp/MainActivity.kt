package com.example.tweetsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRowDefaults.contentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweetsapp.api.TweetsyAPI
import com.example.tweetsapp.screens.CategoryScreen
import com.example.tweetsapp.screens.DetailScreen
import com.example.tweetsapp.ui.theme.TweetsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            TweetsAppTheme {
                Scaffold(
                    topBar = {
                       TopAppBar(
                           title = {
                               Text(text = "Tweetsy")
                           },
                           Modifier.background(Color.Black)
                       )
                    }
                ) {
                    Box( modifier = Modifier.padding(it)) {
                        App()
                    }
                }
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
     NavHost(navController = navController, startDestination = "category") {
         composable("category") {
             CategoryScreen {
                 navController.navigate("detail/{it}")
             }
         }
         composable("detail/{category}",
             arguments = listOf(
                 navArgument("category") {
                     type = NavType.StringType
                 }
             )
         ) {

              DetailScreen()
         }
     }
}

