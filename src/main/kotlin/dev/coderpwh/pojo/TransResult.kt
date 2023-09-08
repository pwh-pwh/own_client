package dev.coderpwh.pojo

import kotlinx.serialization.Serializable

@Serializable
data class TransResult(
    val dst: String,
    val src: String
)