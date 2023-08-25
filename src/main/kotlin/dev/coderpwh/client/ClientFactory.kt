package dev.coderpwh.client

/**
 * @Auther: pangwenhao
 * @Date: 2023/8/25 15:45
 * @Description:
 */
object ClientFactory {
    fun getClient(ct:ClientType):Client {
        return when(ct) {
            ClientType.OWNClient -> OwnClient.getDefault()
            ClientType.QingYunKeClient -> QingyunkeClient.getDefault()
        }
    }
}