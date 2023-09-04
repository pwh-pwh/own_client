package dev.coderpwh.client

import dev.coderpwh.pojo.MsgResp
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import java.io.File
import java.util.UUID

/**
 * @Auther: pangwenhao
 * @Date: 2023/9/4 15:44
 * @Description:
 */
class PollImgClient : ImgClient {
    val client: HttpClient

    companion object{
        const val BaseUrl = "https://image.pollinations.ai/prompt/%s?width=%d&height=%d"
    }

    init {
        client = HttpClient(CIO) {
            install(HttpTimeout) {
                requestTimeoutMillis = 10000
            }
            headers {
                append(HttpHeaders.UserAgent,"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36")
            }
        }
    }

    override suspend fun getImg(prompt: String, width:Int, height:Int): String {
        val resp:ByteArray = client.get(BaseUrl.format(prompt.quote(),width,height)).body()
        val pic_name = System.currentTimeMillis().toString().substring(5) + UUID.randomUUID().toString().substring(0,5)
        File("images").mkdir()
        val file = File("images/" + pic_name + ".jpg")
        file.writeBytes(resp)
        return file.absolutePath
    }
}

suspend fun main() {
    println(PollImgClient().getImg("高 有钱 很帅 很强壮"))
}