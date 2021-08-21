package com.lanier.foxcomposepractice

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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

@Composable
fun Test4(){
    var v1 by remember {
        mutableStateOf(0f)
    }
    var v2 by remember {
        mutableStateOf(0f)
    }
    var v3 by remember {
        mutableStateOf(0f)
    }
    val state1 = animateFloatAsState(targetValue = v1, animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec)
    val state2 = animateFloatAsState(targetValue = v2, animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec)
    val state3 = animateFloatAsState(targetValue = v3, animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec)
    Column(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = {
            v1 = 0.9f
            v2 = 0.4f
            v3 = 0.7f
        }) {
            Text(text = "test")
        }
        Button(onClick = {
            v1 = 0f
            v2 = 0f
            v3 = 0f
        }) {
            Text(text = "reset")
        }
        LinearProgressIndicator(
            progress = state1.value, color = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
        LinearProgressIndicator(
            progress = state2.value, color = Color.Green,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
        LinearProgressIndicator(
            progress = state3.value, color = Color.Magenta,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
    }
}
