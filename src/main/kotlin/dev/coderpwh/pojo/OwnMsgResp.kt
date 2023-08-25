package dev.coderpwh.pojo

import kotlinx.serialization.Serializable

/**
 * @Auther: pangwenhao
 * @Date: 2023/8/25 11:14
 * @Description:
 */
@Serializable
data class OwnMsgResp(
    override val message:String,
    val data: MsgData):MsgResp

@Serializable
data class MsgData(
    val type:Int,
    val info:MsgInfo
)
@Serializable
data class MsgInfo (
    val text:String
)