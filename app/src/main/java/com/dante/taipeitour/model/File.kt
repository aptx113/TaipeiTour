package com.dante.taipeitour.model

import kotlinx.serialization.Serializable

@Serializable
data class File(val src: String, val subject: String, val ext: String)
