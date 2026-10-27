package com.example.myapplication.ui.taskAdd

import android.R.attr.onClick
import android.service.autofill.Validators.and
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
fun TaskAddScreen(
    navController: NavController,
    viewModel: TaskAddViewModel
) {

    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Добавить новую задачу") }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Заголовок") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Текст") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                singleLine = true
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(onClick = {
                    navController.popBackStack()
                }){
                    Text("Отмена")
                }
                TextButton(onClick = {
                    if (title.isNotBlank() and text.isNotBlank())
                        viewModel.addTask(
                            Task(
                                title = title,
                                text = text,
                                expanded = false
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


