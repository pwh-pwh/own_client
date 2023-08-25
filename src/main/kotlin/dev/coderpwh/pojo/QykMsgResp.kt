package dev.coderpwh.pojo

import kotlinx.serialization.Serializable

/**
 * @Auther: pangwenhao
 * @Date: 2023/8/25 15:18
 * @Description:
 */
@Serializable
data class QykMsgResp(
    val result:Int,
    val content:String
):MsgResp {
    override val message: String
        get() = content
}
