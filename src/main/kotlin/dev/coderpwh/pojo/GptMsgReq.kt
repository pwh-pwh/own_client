package dev.coderpwh.pojo

import kotlinx.serialization.Serializable

/**
 * @Auther: pangwenhao
 * @Date: 2023/9/8 16:27
 * @Description:
 */
@Serializable
data class GptMsgReq(val content:String) {
    val model:String
    val messages:List<GptMessage>
    init {
        messages = listOf(GptMessage("user",content))
        model = "gpt-3.5-turbo"
    }
}

@Serializable
data class GptMessage(val role:String,val content:String)



