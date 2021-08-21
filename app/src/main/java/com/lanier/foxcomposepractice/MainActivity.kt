package com.lanier.foxcomposepractice

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lanier.foxcomposepractice.model.PokemonInfoModel
import com.lanier.foxcomposepractice.model.PokemonListModel
import com.lanier.foxcomposepractice.ui.theme.FoxComposePracticeTheme
import com.lanier.foxcomposepractice.ui.theme.screen.MainInfo
import com.lanier.foxcomposepractice.ui.theme.screen.MainView

class MainActivity : ComponentActivity() {

    private val mainViewModel: PokemonListModel by viewModels()
    private val infoViewModel: PokemonInfoModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            FoxComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                ProvideWindowInsets {
                    rememberSystemUiController().setStatusBarColor(Color.Transparent)
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "index"
                    ){
                        composable("index"){
                            MainView(navController, mainViewModel)
                        }
                        composable("detail?id={pkid}",
                            arguments = listOf(
                                navArgument("id"){
                                    type = NavType.StringType
                                    defaultValue = "1"
                                }
                            )
                        ){
                            MainInfo(controller = navController, it.arguments?.getString("pkid")!!, infoViewModel)
                        }
                    }
                }
            }
        }
    }
}
