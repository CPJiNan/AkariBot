package top.cpjinan.akaribot.bot

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * AkariBot
 * top.cpjinan.akaribot.bot
 *
 * Bot 消息 API。
 *
 * @author 季楠
 * @since 2026/1/17 21:48
 */
object BotMessageAPI {
    private val client = OkHttpClient()

    /**
     * 发送私聊文本消息。
     *
     * @param userId 对方 QQ 号。
     * @param message 要发送的内容。
     */
    @JvmStatic
    fun sendPrivateMsg(userId: Long, message: String) {
        client.newCall(
            Request.Builder()
                .url("http://127.0.0.1:3000/send_private_msg")
                .post(
                    """
                        {
                            "user_id": $userId,
                            "message": [
                                {
                                    "type": "text",
                                    "data": {
                                        "text": "$message"
                                    }
                                }
                            ]
                        }
                    """.trimIndent().toRequestBody("application/json".toMediaType())
                ).build()
        ).execute().use { response ->
            response.body.close()
        }
    }

    /**
     * 发送群聊文本消息。
     *
     * @param groupId 群号。
     * @param message 要发送的内容。
     */
    @JvmStatic
    fun sendGroupMsg(groupId: Long, message: String) {
        client.newCall(
            Request.Builder()
                .url("http://127.0.0.1:3000/send_group_msg")
                .post(
                    """
                        {
                            "group_id": $groupId,
                            "message": [
                                {
                                    "type": "text",
                                    "data": {
                                        "text": "$message"
                                    }
                                }
                            ]
                        }
                    """.trimIndent().toRequestBody("application/json".toMediaType())
                ).build()
        ).execute().use { response ->
            response.body.close()
        }
    }
}