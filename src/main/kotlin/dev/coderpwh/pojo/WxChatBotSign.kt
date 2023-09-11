package dev.coderpwh.pojo

import kotlinx.serialization.Serializable

@Serializable
data class WxChatBotSign(
    val expiresIn: Int,
    val rid: String,
    val signature: String
)