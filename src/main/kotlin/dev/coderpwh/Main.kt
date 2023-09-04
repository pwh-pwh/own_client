import dev.coderpwh.client.*
import kotlinx.coroutines.runBlocking
import java.util.Scanner
import kotlin.system.exitProcess


suspend fun main(args: Array<String>) {
    val qingyunkeClient = ClientFactory.getClient(ClientType.QingYunKeClient)
    val imgClient = PollImgClient()
    val sc = Scanner(System.`in`)
    while (true) {
        showMenu()
        val next = sc.next()
        when(next) {
            "1" -> doChat(sc,qingyunkeClient)
            "2" -> doImg(sc,imgClient)
            "3" ->  {
                println("bye")
                exitProcess(0)
            }
            else -> println("选择不存在，请重新选择")
        }
    }
    val msgResp = qingyunkeClient.sendMsg("你好啊，臭机器人")
    println(msgResp)
}

fun doImg(sc: Scanner, imgClient: ImgClient) {
    doAction(sc,::showImgMenu) {
        sc ->
        val next = sc.next()
        if(next.startsWith("1:")) {
            runBlocking {
                val message = imgClient.getImg(next)
                Runtime.getRuntime().exec(arrayOf("explorer",message))
                println("msg:$message")
            }
        } else {
            return
        }
    }
}

inline fun doAction(sc:Scanner,showMenu:()->Unit, clientAction:(Scanner)->Unit) {
    showMenu()
    while (true) {
        clientAction(sc)
    }
}

fun doChat(sc:Scanner,client: MsgClient) {
    doAction(sc,::showChatMenu) {
            sc ->
        val next = sc.next()
        if(next.startsWith("1:")) {
            runBlocking {
                val message = client.sendMsg(next.substring(2)).message
                println("msg:$message")
            }
        } else {
            return
        }
    }
}

fun showChatMenu() {
    println("1:you msg;")
    println("other to quit")
}

fun showImgMenu() {
    println("1:you prompt;")
    println("other to quit")
}

fun showMenu() {
    println("1:chat")
    println("2:img")
    println("3:quit")
}