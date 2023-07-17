package com.dante.taipeitour.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttractionResponse(
    @SerialName("total")
    val total: Int,
    @SerialName("data")
    val `data`: List<Attraction>
)