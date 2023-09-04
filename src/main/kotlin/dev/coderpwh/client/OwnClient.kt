package dev.coderpwh.client

import dev.coderpwh.pojo.OwnMsgReq
import dev.coderpwh.pojo.OwnMsgResp
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

/**
 * @Auther: pangwenhao
 * @Date: 2023/8/25 11:18
 * @Description:
 */
class OwnClient(var appid: String, var userid: String? = null):MsgClient {
    val client: HttpClient

    companion object {
        const val BaseUrl = "https://api.ownthink.com/bot"
        fun getDefault():MsgClient {
            return OwnClient("")
        }
    }

    init {
        client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(contentType = ContentType("text","json"))
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 10000
            }
        }
    }

    override suspend fun sendMsg(spoken: String): OwnMsgResp {
        return client.post(BaseUrl) {
            contentType(ContentType("text","json"))
            setBody(OwnMsgReq(spoken, appid, userid))
        }.body<OwnMsgResp>()
    }
}