package dev.coderpwh.pojo

import kotlinx.serialization.Serializable

/**
 * @Auther: pangwenhao
 * @Date: 2023/8/25 11:17
 * @Description:
 */
@Serializable
data class OwnMsgReq(
    val spoken:String,
    val appid:String,
    val userid:String? = null
)
