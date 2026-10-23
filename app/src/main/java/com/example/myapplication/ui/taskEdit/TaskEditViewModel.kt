package com.example.myapplication.ui.taskEdit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.models.Task
import com.example.myapplication.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositpry: TaskRepository
): ViewModel {

    private val taskId = savedStateHandle.get<String>("taskId")?.toIntOrNull() ?: 0

    private val _task = MutableStateFlow<Task?>(null)
    val task: StateFlow<Task?> = _task
    fun loadTask(){
        viewModelScope.launch {
            val loadedTask = repositpry.getTask(taskId)
            if(loadedTask != null){
                _task.value = loadedTask
            }
        }
    }
    fun updateTask(newTask: Task) {
        viewModelScope.launch {
            repositpry.updateTask(newTask)
        }
    }

}