package dev.coderpwh.client

import dev.coderpwh.pojo.QykMsgResp
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
 * @Date: 2023/8/25 15:18
 * @Description:
 */
class QingyunkeClient(var key: String = "free"):MsgClient {
    val client: HttpClient
    companion object {
        const val BaseUrl = "http://api.qingyunke.com/api.php?key=%s&appid=0&msg=%s"
        fun getDefault():MsgClient {
            return QingyunkeClient()
        }
    }
    init {
        client = HttpClient(CIO) {
            install(HttpTimeout) {
                requestTimeoutMillis = 5000
            }
            install(ContentNegotiation) {
                json(contentType = ContentType.Text.Html)
            }
        }
    }

    override suspend fun sendMsg(msg:String):QykMsgResp {
        return client.get(String.format(BaseUrl,key,msg.encodeURLPath())).body<QykMsgResp>()
    }
}

