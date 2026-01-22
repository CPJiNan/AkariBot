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
     * @param messageText 要发送的内容。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendPrivateTextMsg(userId: Long, messageText: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "user_id": $userId,
                        "message": [
                            {
                                "type": "text",
                                "data": {
                                    "text": "$messageText"
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送私聊回复消息。
     *
     * @param userId 对方 QQ 号。
     * @param messageId 要回复的消息 ID。
     * @param messageText 要发送的内容。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendPrivateReplyMsg(userId: Long, messageId: Long, messageText: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "user_id": $userId,
                        "message": [
                            {
                                "type": "reply",
                                "data": {
                                    "id": $messageId
                                }
                            },
                            {
                                "type": "text",
                                "data": {
                                    "text": "$messageText"
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送私聊图片。
     *
     * @param userId 对方 QQ 号。
     * @param file 图片路径。
     *
     * 支持本地路径、网络路径、base64 编码。
     * 1. file://C:/image.png
     * 2. http://akaribot.cpjinan.top/image.png
     * 3. base64://xxxxxxxx
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendPrivateImageMsg(userId: Long, file: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "user_id": $userId,
                        "message": [
                            {
                                "type": "image",
                                "data": {
                                    "file": "$file"
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
     * @param messageText 要发送的内容。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendGroupTextMsg(groupId: Long, messageText: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_group_msg",
            """
                    {
                        "group_id": $groupId,
                        "message": [
                            {
                                "type": "text",
                                "data": {
                                    "text": "$messageText"
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }
}