package com.example.myapplication.ui.taskAdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.Task
import com.example.myapplication.data.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskAddViewModel(
    private val repository: TaskRepository
): ViewModel() {

    fun addTask(newTask: Task){
        viewModelScope.launch {
            repository.addTask(newTask)
        }
    }
}