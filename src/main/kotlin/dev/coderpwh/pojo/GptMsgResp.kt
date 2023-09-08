package dev.coderpwh.pojo

import kotlinx.serialization.*

@Serializable
data class GptMsgResp (
    val choices: List<Choice>,
    val created: Long,
    val id: String,

    @SerialName("object")
    val apifoxModelObject: String,

    val model:String,

    val usage: Usage
)

@Serializable
data class Choice (
    @SerialName("finish_reason")
    val finishReason: String? = null,

    val index: Long? = null,
    val message: Message? = null
)

@Serializable
data class Message (
    val content: String,
    val role: String
)

@Serializable
data class Usage (
    @SerialName("completion_tokens")
    val completionTokens: Long,

    @SerialName("prompt_tokens")
    val promptTokens: Long,

    @SerialName("total_tokens")
    val totalTokens: Long
)