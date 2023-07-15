package com.dante.taipeitour.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Target(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)