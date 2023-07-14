package com.dante.taipeitour.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("src")
    val src: String,
    @SerialName("subject")
    val subject: String,
    @SerialName("ext")
    val ext: String
)