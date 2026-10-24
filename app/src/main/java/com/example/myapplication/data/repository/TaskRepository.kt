package com.example.myapplication.data.repository

import com.example.myapplication.data.models.Task
import com.example.myapplication.data.network.SupabaseClient.client
import io.github.jan.supabase.postgrest.from

class TaskRepository {
    private var tasks = mutableListOf<Task>()
    private var currentId = 1



    suspend fun getTask(taskId: Int): Task?{
        val task = client.from("Task").select{
            filter {
                Task::id gte taskId
                gte("id", taskId)
            }
        }.decodeSingle<Task>()
        return task
    }


    suspend fun getAllTasks() : List<Task> {
        val tasks = client.from("Task").select(

        ).decodeList<Task>()
        return tasks
    }

    suspend fun addTask(task: Task) {

        client.from("Task").insert(task)
    }
    suspend fun deleteTask(id: Int) {
        client.from("Task").delete {
            filter {
                Task::id gte id
                gte("Id", id)
            }
        }
        }
        suspend fun updateTask(updatedTask: Task) {
            client.from("Task").update(updatedTask){
                filter {
                    eq("Id", updatedTask.id!!)
                }
            }
        }

}