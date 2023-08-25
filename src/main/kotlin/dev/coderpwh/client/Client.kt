package dev.coderpwh.client

import dev.coderpwh.pojo.MsgResp

/**
 * @Auther: pangwenhao
 * @Date: 2023/8/25 15:39
 * @Description:
 */
interface Client {
    suspend fun sendMsg(msg:String):MsgResp
}