package com.example.myapplication.ui.taskList

import android.R.attr.text
import android.R.attr.title
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.models.Task
import com.example.myapplication.ui.components.AddDialog
import com.example.myapplication.ui.components.DeleteDialog
import com.example.myapplication.ui.components.EditDialog

@Composable
fun TaskListScreen(viewModel: TaskListViewModel){
    val tasks by viewModel.tasks.collectAsState()

    val showAddDialog by viewModel.showAddDialog.collectAsState()
    val showDeleteDialog by viewModel.showDeleteDialog.collectAsState()
    val showUpdateDialog by viewModel.showUpdateDialog.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {viewModel.showAddDialog()}) {
                Icon(Icons.Default.Add, contentDescription = "add")

            }
        }
    ){ ip->
        LazyColumn(
            modifier= Modifier
                .padding(ip)
                .padding(12.dp)
                .fillMaxSize()
        ) {
            items(items = tasks){it ->
                ElevatedCard(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                    onClick = {
                        viewModel.switchExpend(it)
                    }
                ) {
                    Column(modifier = Modifier.padding(10.dp)){
                        Box(modifier = Modifier.fillMaxSize()){
                            Text(modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.TopStart),
                                text = it.title
                            )
                            Row(Modifier
                                .align (Alignment.TopEnd)){
                                IconButton(
                                    onClick = {
                                        viewModel.showDeleteDialog()
                                        viewModel.selectTask(it)
                                }) {
                                    Icon(Icons.Filled.Delete, contentDescription = "Delete")
                                }
                                IconButton(onClick = {
                                    viewModel.showUpdateDialog()
                                    viewModel.selectTask(it)
                                }) {
                                    Icon(Icons.Filled.Delete, contentDescription = "Edit")
                                }
                            }
                        }
                        AnimatedVisibility(
                            visible = it.expended,
                            enter = fadeIn() + expandVertically (),
                            exit = fadeOut() + shrinkVertically ()
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(),
                                text = it.text
                            )
                        }
                    }

                }
            }
        }

    }
    if(showDeleteDialog){
        DeleteDialog(
            onDismiss ={viewModel.hideDeletedDialog()},
            onDelete = {viewModel.deleteTask()}
        )

    }
    if(showUpdateDialog){
        EditDialog(
            {viewModel.hideUpdateDialog()},
            {updatedTask->
                viewModel.editTask(updatedTask)},
            task!!

        )

    }
    if(showAddDialog){
        AddDialog(
            {viewModel.hideAddDialog()},
            { title, text ->
                val newTask = Task(
                    id = 0,
                    title = title,
                    text = text,
                    expended = true
                )
                viewModel.addTask(newTask)
            }
        )
    }


}