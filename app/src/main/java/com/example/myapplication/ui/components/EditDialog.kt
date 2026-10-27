package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.data.models.Task

@Composable
fun EditDialog(
    onDismiss:()-> Unit,
    onUpdate:(Task)-> Unit,
    updatedTask: Task
){
    var title by remember { mutableStateOf(updatedTask.title) }
    var text by remember { mutableStateOf(updatedTask.text) }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column() {
                Text("Добавить новую задачу?")

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Название задачи") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                )

                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Описание задачи") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    singleLine = false,
                    maxLines = 3
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Отмена")
                    }
                    TextButton(onClick = {
                        if(title.isNotBlank() and text.isNotBlank())
                            onUpdate(
                                Task(
                                    id = updatedTask.id,
                                    text = text,
                                    title = title,
                                    expanded = updatedTask.expanded
                                )
                            )
                    }) {
                        Text("Добавить")
                    }
                }
            }
        }
    }
}