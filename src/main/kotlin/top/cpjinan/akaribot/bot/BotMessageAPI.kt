package top.cpjinan.akaribot.bot

import top.cpjinan.akaribot.config.BotConfig
import top.cpjinan.akaribot.utils.HttpUtils

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
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendPrivateMsg(userId: Long, message: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
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
                    """.trimIndent()
        )
    }

    /**
     * 发送群聊文本消息。
     *
     * @param groupId 群号。
     * @param message 要发送的内容。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendGroupMsg(groupId: Long, message: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_group_msg",
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
                    """.trimIndent()
        )
    }
}