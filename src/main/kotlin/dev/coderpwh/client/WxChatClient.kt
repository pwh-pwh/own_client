package dev.coderpwh.client

import dev.coderpwh.pojo.WxChatBotSign
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * @Auther: pangwenhao
 * @Date: 2023/9/11 15:14
 * @Description:
 */
class WxChatClient(val token: String) {
    val client: HttpClient

    companion object {
        const val BASE_URL = "https://chatbot.weixin.qq.com/openapi"
    }

    init {
        client = HttpClient(CIO) {
            install(HttpTimeout) {
                requestTimeoutMillis = 3000
            }
        }
    }

    suspend fun getMsg(userId:String,query:String):String {
        val signature = getSign(userId).signature
        val map = mapOf("signature" to signature,"query" to query)
        var encodeToString = Json.encodeToString(map)
        val result = client.post("$BASE_URL/aibot/$token") {
            contentType(ContentType.Application.Json)
            setBody(encodeToString)
        }.body<String>()
        return Json.parseToJsonElement(result).jsonObject["answer"]!!.jsonPrimitive.content
    }

    suspend fun getSign(userId:String):WxChatBotSign {
        var map = mapOf("userid" to userId)
        var encodeToString = Json.encodeToString(map)
        val result = client.post("$BASE_URL/sign/$token") {
            contentType(ContentType.Application.Json)
            setBody(encodeToString)
        }.body<String>()
        return Json.decodeFromString(result)
    }
}