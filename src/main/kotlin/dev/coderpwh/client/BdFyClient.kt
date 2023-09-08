package dev.coderpwh.client

import dev.coderpwh.pojo.BdFyResp
import dev.coderpwh.pojo.TransResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import java.security.MessageDigest
import kotlin.random.Random

/**
 * @Auther: pangwenhao
 * @Date: 2023/9/8 11:42
 * @Description:
 */
class BdFyClient(val appid: String, val secret: String) {
    val client: HttpClient

    companion object {
        const val BASE_URL = "https://fanyi-api.baidu.com/api/trans/vip/translate?q=%s&from=auto&to=%s&appid=%s&salt=%s&sign=%s"
    }

    init {
        client = HttpClient(CIO) {
            install(HttpTimeout) {
                requestTimeoutMillis = 2000
            }
        }
    }

    suspend fun getTranslate(query:String,to:String): BdFyResp {
        val saltNum = getSaltNum()
        val result = client.post(BASE_URL.format(query,to,appid,saltNum.toString(),getSign(query, saltNum))) {
            contentType(ContentType.Application.FormUrlEncoded)
        }.call.body<String>()
        return Json.decodeFromString<BdFyResp>(result)
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun getSign(query:String,saltNum:Int):String {
        var digest = MessageDigest.getInstance("md5").digest("$appid$query$saltNum$secret".toByteArray())
        return digest.toHexString()
    }

    private fun getSaltNum() = Random(1).nextInt()

}