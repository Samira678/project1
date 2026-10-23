package com.example.myapplication.ui.taskAdd

import com.example.myapplication.data.models.Task
import com.example.myapplication.data.repository.TaskRepository

class TaskAddViewModel {
    private val repository: TaskRepository
}: ViewModel() {
    fun addTask(addTask: Task){
        viewModeScope.launch{
            repository.addTask(newTask)
        }
    }
}