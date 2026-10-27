package com.example.myapplication.data.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("Title")
    var title: String,
    @SerialName("Text")
    var text : String,
    @Transient var expanded: Boolean = false
)
