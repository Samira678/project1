package com.example.myapplication.data.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("title")
    var title: String,
    @SerialName("text")
    var text: String,
    @Transient var expended: Boolean = false
){
    companion object{

    }
}
