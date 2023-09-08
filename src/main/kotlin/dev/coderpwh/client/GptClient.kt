package dev.coderpwh.client

import dev.coderpwh.pojo.GptBotType
import dev.coderpwh.pojo.GptMsgReq
import dev.coderpwh.pojo.GptMsgResp
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * @Auther: pangwenhao
 * @Date: 2023/9/8 16:33
 * @Description:
 */
class GptClient(val token:String) {
    val client: HttpClient
    var type:GptBotType

    companion object {
        const val BASE_URL = "https://api.chatanywhere.com.cn/v1/chat/completions"
    }

    init {
        client = HttpClient(CIO) {
            install(HttpTimeout) {
                requestTimeoutMillis = 1000 * 10
            }
        }
        type = GptBotType.Empty
    }

    suspend fun getGpt(content:String):GptMsgResp {
        val body = client.post(BASE_URL) {
            contentType(ContentType.Application.Json)
            bearerAuth(token)
            val data = Json.encodeToString(GptMsgReq(content,type))
            setBody(data)
        }.body<String>()
        println(body)
        return Json.decodeFromString<GptMsgResp>(body)
    }
}