package com.example.myapplication.ui.taskEdit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.Task
import com.example.myapplication.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: TaskRepository
): viewModel() {

    private val taskId = savedStateHandle.get<String>("taskId")?.toIntOrNull() ?: 0

    private val _task = MutableStateFlow<Task?>(null)
    val task: StateFlow<Task?> = _task
    fun loadTask(){
        viewModelScope.launch {
            val loadedTask = repository.getTask(taskId)
            if(loadedTask != null){
                _task.value = loadedTask
            }
        }
    }
    fun updateTask(newTask: Task) {
        viewModelScope.launch {
            repository.updateTask(newTask)
        }
    }

}