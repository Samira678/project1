package com.example.myapplication.ui.taskEdit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.data.models.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditScreen(
    navController: NavController,
    viewModel: TaskEditViewModel
) {
    val task by viewModel.task.collectAsState()

    var title by remember{mutableStateOf("")}
    var text by remember{mutableStateOf("")}

    LaunchedEffect(task) {
        task?.let {
            text = it.text
            title = it.title
        }
    }
    Scaffold(
        topbar = {
            TopAppBar(
                title = {Text("Добавьте новую задачу")}
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text("Добавитьновую задачу")
            OutlinedTextField(
                value = title,
                onValueChange = {title = it},
                label = {Text("Название задачи")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            OutlinedTextField(
                value = text,
                onValueChange = {text = it},
                label = {Text("Описание задачи")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                singleLine = false,
                maxLines = 10

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center

            ){
                TextButton(onClick = {navController.popBackStack()}) {
                    Text("Отмена")
                }
                TextButton(onClick = {
                    if(title.isNotBlank() and text.isNotBlank())
                        viewModel.updateTask(
                            Task(
                                id = task!!.id,
                                text,
                                title,
                                expended = false
                            )
                        )
                    navController.popBackStack()

                }) {
                    Text("Добавить")
                }
            }
        }
    }
}