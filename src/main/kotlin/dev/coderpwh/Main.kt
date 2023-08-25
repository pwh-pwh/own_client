import dev.coderpwh.client.Client
import dev.coderpwh.client.ClientFactory
import dev.coderpwh.client.ClientType
import dev.coderpwh.client.QingyunkeClient
import kotlinx.coroutines.runBlocking
import java.util.Scanner
import kotlin.system.exitProcess


suspend fun main(args: Array<String>) {
    val qingyunkeClient = ClientFactory.getClient(ClientType.QingYunKeClient)
    val sc = Scanner(System.`in`)
    while (true) {
        showMenu()
        val next = sc.next()
        when(next) {
            "1" -> doChat(sc,qingyunkeClient)
            "2" ->  {
                println("bye")
                exitProcess(0)
            }

            else -> println("选择不存在，请重新选择")
        }
    }
    val msgResp = qingyunkeClient.sendMsg("你好啊，臭机器人")
    println(msgResp)
}
fun doChat(sc:Scanner,client: Client) {
    showChatMenu()
    while (true) {
        val next = sc.next()
        if(next.startsWith("1:")) {
            runBlocking {
                val message = client.sendMsg(next.substring(2)).message
                println("msg:$message")
            }
        } else {
            break
        }
    }

}

fun showChatMenu() {
    println("1:you msg;")
    println("other to quit")
}

fun showMenu() {
    println("1:chat")
    println("2:quit")
}