package com.dante.taipeitour.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Service(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
) : java.io.Serializable
