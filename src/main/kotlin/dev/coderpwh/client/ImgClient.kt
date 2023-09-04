package dev.coderpwh.client

/**
 * @Auther: pangwenhao
 * @Date: 2023/9/4 15:43
 * @Description:
 */
interface ImgClient {
    suspend fun getImg(prompt:String,width:Int = 1024,height:Int = 1024):String
}