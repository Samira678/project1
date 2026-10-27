package com.example.myapplication.data.repository

import com.example.myapplication.data.models.Task
import com.example.myapplication.data.network.SupabaseClient.client
import io.github.jan.supabase.postgrest.from

class TaskRepository {

    suspend fun getTask(taskId: Int) : Task?{
        val task = client.from("Task").select{
            filter {
                gte("id",taskId)
            }
        }.decodeSingle<Task>()
        return task
    }

    suspend fun getAllTasks() : List<Task> {
        val tasks = client.from("Task").select().decodeList<Task>()
        return tasks
    }

    suspend fun addTask(task: Task){
        client.from("Task").insert(task)
    }
    suspend fun deleteTask(id: Int){
        client.from("Task").delete{
            filter {
                gte("id",id)
            }
        }
    }

    suspend fun updateTask(updateTask: Task){
        client.from("Task").update(updateTask){
            filter {
                eq("id",updateTask.id!!)
            }
        }
    }
}