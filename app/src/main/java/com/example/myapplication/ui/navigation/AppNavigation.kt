package com.example.myapplication.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.repository.AuthorizationRepository
import com.example.myapplication.data.repository.TaskRepository
import com.example.myapplication.ui.signInUser.SignInUserScreen
import com.example.myapplication.ui.signInUser.SignInViewModel
import com.example.myapplication.ui.signUpUser.SignUpUserScreen
import com.example.myapplication.ui.signUpUser.SignUpViewModel
import com.example.myapplication.ui.splashScreen.SplashScreen
import com.example.myapplication.ui.splashScreen.SplashScreenViewModel
import com.example.myapplication.ui.taskAdd.TaskAddScreen
import com.example.myapplication.ui.taskAdd.TaskAddViewModel
import com.example.myapplication.ui.taskEdit.TaskEditScreen
import com.example.myapplication.ui.taskEdit.TaskEditViewModel
import com.example.myapplication.ui.taskList.TaskListScreen
import com.example.myapplication.ui.taskList.TaskListViewModel

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavigation() {

    val repository = remember { TaskRepository() }
    val navController = rememberNavController()
    val authorizationRepository = remember { AuthorizationRepository() }


    NavHost(
        navController = navController,
        startDestination = "Splash"
    ) {
        composable("TaskList") {
            TaskListScreen(
                navController,
                TaskListViewModel(repository)
            )
        }
        composable("TaskAdd") {
            TaskAddScreen(
                navController,
                TaskAddViewModel(repository)
            )
        }
        composable("TaskEdit/{taskId}") { backStackEntry->
            TaskEditScreen(
                navController,
                TaskEditViewModel(backStackEntry.savedStateHandle,repository)
            )
        }
        composable("Splash") {
            SplashScreen(
                navController = navController,
                viewModel = viewModel { SplashScreenViewModel(authorizationRepository) })
        }
        composable("SignUpScreen") {
            SignUpUserScreen(
                navController = navController,
                viewModel = viewModel { SignUpViewModel(authorizationRepository) }
            )
        }
        composable("SignInScreen") {
            SignInUserScreen(
                navController = navController,
                viewModel = viewModel { SignInViewModel(authorizationRepository) }
            )
        }
    }
}