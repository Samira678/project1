package com.example.myapplication.ui.splashScreen

import android.window.SplashScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.myapplication.data.repository.AuthorizationRepository
import com.example.myapplication.ui.splashScreen.SplashScreenViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (viewModel.checkToken(context)){
            delay(2000)
            navController.navigate("TaskList"){
                popUpTo("Splash"){inclusive = true}
            }
        }else{
            delay(2000)
            navController.navigate("SignUpScreen"){
                popUpTo("Splash"){inclusive = true}
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}