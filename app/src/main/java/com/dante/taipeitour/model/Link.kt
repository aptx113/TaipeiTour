package com.dante.taipeitour.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Link(
    @SerialName("src")
    val src: String,
    @SerialName("subject")
    val subject: String
) : java.io.Serializable
