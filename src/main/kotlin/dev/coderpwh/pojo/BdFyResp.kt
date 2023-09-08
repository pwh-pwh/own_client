package dev.coderpwh.pojo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BdFyResp(
    val from: String,
    val to: String,
    @SerialName("trans_result")
    val transResult: List<TransResult>
)