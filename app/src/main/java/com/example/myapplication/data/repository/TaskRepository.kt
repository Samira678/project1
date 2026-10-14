package com.example.myapplication.data.repository

import com.example.myapplication.data.models.Task

class TaskRepository {
    private var tasks = mutableListOf<Task>()
    private var currentId = 1

    init {
        tasks.addAll(
            listOf(
                Task(
                    id = currentId++,
                    title = "Заголовок1",
                    text = "Mного текста".repeat(20),
                    expended = true
                ),
                Task(
                    id = currentId++,
                    title = "Заголовок2",
                    text = "Очень много текста".repeat(20),
                    expended = false
                ),
                Task(
                    id = currentId++,
                    title = "Заголовок3",
                    text = "ОЧЕНЬ МНОГО ТЕКСТА!!!".repeat(20),
                    false
                )


            )
        )
    }

    fun getAllTasks() : List<Task> = tasks.toList()

    fun addTask(Task: Task) {
        tasks.add(Task.copy(id = currentId++))
    }
    fun deleteTask(id: Int){
        tasks.removeAll{it.id == id}
    }
    fun updateTask(updatedTask: Task){
        val index = tasks.indexOfFirst { it.id == updatedTask.id }
        if (index != -1)
            tasks[index] = updatedTask
    }
}