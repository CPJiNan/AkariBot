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
    /**
     * 发送私聊文本消息。
     *
     * @param userId 对方 QQ 号。
     * @param message 要发送的内容。
     */
    @JvmStatic
    fun sendPrivateMsg(userId: Int, message: String) {
        OkHttpClient().newCall(
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
        ).execute()
    }
}