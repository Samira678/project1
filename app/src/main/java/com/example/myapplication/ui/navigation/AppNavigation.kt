package com.example.myapplication.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.repository.TaskRepository
import com.example.myapplication.ui.signInUser.SignInUserScreen
import com.example.myapplication.ui.signInUser.SignInViewModel
import com.example.myapplication.ui.signUpUser.SignUpUserScreen
import com.example.myapplication.ui.signUpUser.SignUpViewModel
import com.example.myapplication.ui.taskAdd.TaskAddScreen
import com.example.myapplication.ui.taskAdd.TaskAddViewModel
import com.example.myapplication.ui.taskEdit.TaskEditScreen
import com.example.myapplication.ui.taskEdit.TaskEditViewModel
import com.example.myapplication.ui.taskList.TaskListScreen
import com.example.myapplication.ui.taskList.TaskListViewModel

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavigation() {
    val repository = remember{ TaskRepository() }
    val  navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination ="SignInScreen"
    ){
        composable("TaskList") {
            TaskListScreen(
                TaskListViewModel(repository)
            )
        }
        composable("TaskAddScreen"){
            TaskAddScreen(
                navController,
                TaskAddViewModel(repository)
            )

        }
        composable("TaskEditScreen/{taskId}"){ backStackEntry ->
            TaskEditScreen(
                navController,
                TaskEditViewModel(backStackEntry.savedStateHandle, repository)
            )

        }
        composable("SignUpScreen") {
            SignUpUserScreen(
                navController = navController,
                viewModel = viewModel { SignUpViewModel() }
            )
        }
        composable ("SignInScreen") {
            SignInUserScreen(
                navController = navController,
                viewModel = viewModel { SignInViewModel() }
            )
        }
    }

}