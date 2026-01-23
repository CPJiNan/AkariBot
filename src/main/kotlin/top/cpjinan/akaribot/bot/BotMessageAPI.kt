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
@Suppress("unused")
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
     * @param imageFile 图片路径。
     *
     * 图片路径支持本地路径、网络路径、base64 编码。
     * 1. file://C:/image.png
     * 2. http://akaribot.cpjinan.top/image.png
     * 3. base64://xxxxxxxx
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendPrivateImageMsg(userId: Long, imageFile: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "user_id": $userId,
                        "message": [
                            {
                                "type": "image",
                                "data": {
                                    "file": "$imageFile"
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送私聊文字 + 图片。
     *
     * @param userId 对方 QQ 号。
     * @param messageText 要发送的内容。
     * @param imageFile 图片路径。
     *
     * 图片路径支持本地路径、网络路径、base64 编码。
     * 1. file://C:/image.png
     * 2. http://akaribot.cpjinan.top/image.png
     * 3. base64://xxxxxxxx
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendPrivateTextImageMsg(userId: Long, messageText: String, imageFile: String): String {
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
                            },
                            {
                                "type": "image",
                                "data": {
                                    "file": "$imageFile"
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送私聊系统表情。
     *
     * @param userId 对方 QQ 号。
     * @param faceID 系统表情 ID。
     *
     * 系统表情 ID 参考：
     * https://bot.q.qq.com/wiki/develop/api-v2/openapi/emoji/model.html#EmojiType
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendPrivateFaceMsg(userId: Long, faceID: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "user_id": $userId,
                        "message": [
                            {
                                "type": "face",
                                "data": {
                                    "id": $faceID
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送私聊语音消息。
     *
     * @param userId 对方 QQ 号。
     * @param recordFile 语音路径。
     *
     * 语音路径支持本地路径、网络路径、base64 编码。
     * 1. file://C:/record.mp3
     * 2. http://akaribot.cpjinan.top/record.mp3
     * 3. base64://xxxxxxxx
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendPrivateRecordMsg(userId: Long, recordFile: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "user_id": $userId,
                        "message": [
                            {
                                "type": "record",
                                "data": {
                                    "file": "$recordFile"
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送私聊视频消息。
     *
     * @param userId 对方 QQ 号。
     * @param videoFile 视频路径。
     *
     * 视频路径支持本地路径、网络路径、base64 编码。
     * 1. file://C:/video.mp4
     * 2. http://akaribot.cpjinan.top/video.mp4
     * 3. base64://xxxxxxxx
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendPrivateVideoMsg(userId: Long, videoFile: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "user_id": $userId,
                        "message": [
                            {
                                "type": "record",
                                "data": {
                                    "file": "$videoFile"
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送私聊超级表情骰子。
     *
     * @param userId 对方 QQ 号。
     * @param diceResult 骰子点数。
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendPrivateDiceMsg(userId: Long, diceResult: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "user_id": $userId,
                        "message": [
                            {
                                "type": "dice",
                                "data": {
                                    "result": $diceResult
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送私聊超级表情猜拳。
     *
     * @param userId 对方 QQ 号。
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendPrivateRpsMsg(userId: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "user_id": $userId,
                        "message": [
                            {
                                "type": "rps",
                                "data": {
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送私聊 QQ 音乐卡片。
     *
     * @param userId 对方 QQ 号。
     * @param musicID QQ 音乐歌曲 ID。
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendPrivateQQMusicMsg(userId: Long, musicID: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "user_id": $userId,
                        "message": [
                            {
                                "type": "music",
                                "data": {
                                    "type": "qq",
                                    "id": $musicID
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送私聊网易云音乐卡片。
     *
     * @param userId 对方 QQ 号。
     * @param musicID 网易云音乐歌曲 ID。
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendPrivate163MusicMsg(userId: Long, musicID: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "user_id": $userId,
                        "message": [
                            {
                                "type": "music",
                                "data": {
                                    "type": "163",
                                    "id": $musicID
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送私聊自定义音乐卡片。
     *
     * @param userId 对方 QQ 号。
     * @param musicUrl 音乐路径。
     * @param musicAudio 音乐音频路径。
     * @param musicTitle 音乐标题。
     * @param musicImage 音乐封面路径。
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendPrivateCustomMusicMsg(
        userId: Long,
        musicUrl: String,
        musicAudio: String,
        musicTitle: String,
        musicImage: String
    ): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "user_id": $userId,
                        "message": [
                            {
                                "type": "music",
                                "data": {
                                    "type": "custom",
                                    "url": "$musicUrl",
                                    "audio": "$musicAudio",
                                    "title": "$musicTitle",
                                    "image": "$musicImage"
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送私聊卡片（json）消息。
     *
     * @param userId 对方 QQ 号。
     * @param json 要发送的内容。
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendPrivateJsonMsg(userId: Long, json: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "user_id": $userId,
                        "message": [
                            {
                                "type": "json",
                                "data": {
                                    "data": "$json"
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送私聊合并转发消息。
     *
     * @param userId 对方 QQ 号。
     * @param messageText 要发送的内容。
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendPrivateNodeMsg(userId: Long, messageText: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "user_id": "$userId",
                        "messages": [
                            {
                                "type": "node",
                                "data": {
                                    "content": [
                                        {
                                            "type": "text",
                                            "data": {
                                                "text": "$messageText"
                                            }
                                        }
                                    ]
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

    /**
     * 发送群聊回复消息。
     *
     * @param groupId 群号。
     * @param messageId 要回复的消息 ID。
     * @param messageText 要发送的内容。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendGroupReplyMsg(groupId: Long, messageId: Long, messageText: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_group_msg",
            """
                    {
                        "group_id": $groupId,
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
     * 发送群聊图片。
     *
     * @param groupId 群号。
     * @param imageFile 图片路径。
     *
     * 图片路径支持本地路径、网络路径、base64 编码。
     * 1. file://C:/image.png
     * 2. http://akaribot.cpjinan.top/image.png
     * 3. base64://xxxxxxxx
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendGroupImageMsg(groupId: Long, imageFile: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "group_id": $groupId,
                        "message": [
                            {
                                "type": "image",
                                "data": {
                                    "file": "$imageFile"
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送群聊文字 + 图片。
     *
     * @param groupId 群号。
     * @param messageText 要发送的内容。
     * @param imageFile 图片路径。
     *
     * 图片路径支持本地路径、网络路径、base64 编码。
     * 1. file://C:/image.png
     * 2. http://akaribot.cpjinan.top/image.png
     * 3. base64://xxxxxxxx
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendGroupTextImageMsg(groupId: Long, messageText: String, imageFile: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "group_id": $groupId,
                        "message": [
                            {
                                "type": "text",
                                "data": {
                                    "text": "$messageText"
                                }
                            },
                            {
                                "type": "image",
                                "data": {
                                    "file": "$imageFile"
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送群聊系统表情。
     *
     * @param groupId 群号。
     * @param faceID 系统表情 ID。
     *
     * 系统表情 ID 参考：
     * https://bot.q.qq.com/wiki/develop/api-v2/openapi/emoji/model.html#EmojiType
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendGroupFaceMsg(groupId: Long, faceID: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "group_id": $groupId,
                        "message": [
                            {
                                "type": "face",
                                "data": {
                                    "id": $faceID
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送群聊语音消息。
     *
     * @param groupId 群号。
     * @param recordFile 语音路径。
     *
     * 语音路径支持本地路径、网络路径、base64 编码。
     * 1. file://C:/record.mp3
     * 2. http://akaribot.cpjinan.top/record.mp3
     * 3. base64://xxxxxxxx
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendGroupRecordMsg(groupId: Long, recordFile: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "group_id": $groupId,
                        "message": [
                            {
                                "type": "record",
                                "data": {
                                    "file": "$recordFile"
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送群聊视频消息。
     *
     * @param groupId 群号。
     * @param videoFile 视频路径。
     *
     * 视频路径支持本地路径、网络路径、base64 编码。
     * 1. file://C:/video.mp4
     * 2. http://akaribot.cpjinan.top/video.mp4
     * 3. base64://xxxxxxxx
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendGroupVideoMsg(groupId: Long, videoFile: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "group_id": $groupId,
                        "message": [
                            {
                                "type": "record",
                                "data": {
                                    "file": "$videoFile"
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送群聊超级表情骰子。
     *
     * @param groupId 群号。
     * @param diceResult 骰子点数。
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendGroupDiceMsg(groupId: Long, diceResult: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "group_id": $groupId,
                        "message": [
                            {
                                "type": "dice",
                                "data": {
                                    "result": $diceResult
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送群聊超级表情猜拳。
     *
     * @param groupId 群号。
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendGroupRpsMsg(groupId: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "group_id": $groupId,
                        "message": [
                            {
                                "type": "rps",
                                "data": {
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送群聊 QQ 音乐卡片。
     *
     * @param groupId 群号。
     * @param musicID QQ 音乐歌曲 ID。
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendGroupQQMusicMsg(groupId: Long, musicID: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "group_id": $groupId,
                        "message": [
                            {
                                "type": "music",
                                "data": {
                                    "type": "qq",
                                    "id": $musicID
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送群聊网易云音乐卡片。
     *
     * @param groupId 群号。
     * @param musicID 网易云音乐歌曲 ID。
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendGroup163MusicMsg(groupId: Long, musicID: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "group_id": $groupId,
                        "message": [
                            {
                                "type": "music",
                                "data": {
                                    "type": "163",
                                    "id": $musicID
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送群聊自定义音乐卡片。
     *
     * @param groupId 群号。
     * @param musicUrl 音乐路径。
     * @param musicAudio 音乐音频路径。
     * @param musicTitle 音乐标题。
     * @param musicImage 音乐封面路径。
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendGroupCustomMusicMsg(
        groupId: Long,
        musicUrl: String,
        musicAudio: String,
        musicTitle: String,
        musicImage: String
    ): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "group_id": $groupId,
                        "message": [
                            {
                                "type": "music",
                                "data": {
                                    "type": "custom",
                                    "url": "$musicUrl",
                                    "audio": "$musicAudio",
                                    "title": "$musicTitle",
                                    "image": "$musicImage"
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送群聊卡片（json）消息。
     *
     * @param groupId 群号。
     * @param json 要发送的内容。
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendGroupJsonMsg(groupId: Long, json: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "group_id": $groupId,
                        "message": [
                            {
                                "type": "json",
                                "data": {
                                    "data": "$json"
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送群聊合并转发消息。
     *
     * @param groupId 群号。
     * @param messageText 要发送的内容。
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendGroupNodeMsg(groupId: Long, messageText: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_private_msg",
            """
                    {
                        "group_id": $groupId,
                        "messages": [
                            {
                                "type": "node",
                                "data": {
                                    "content": [
                                        {
                                            "type": "text",
                                            "data": {
                                                "text": "$messageText"
                                            }
                                        }
                                    ]
                                }
                            }
                        ]
                    }
                    """.trimIndent()
        )
    }

    /**
     * 转发单条好友消息。
     *
     * @param messageId 消息 ID。
     * @param userId 对方 QQ 号。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun forwardFriendSingleMsg(messageId: Long, userId: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/forward_friend_single_msg",
            """
                    {
                        "message_id": $messageId,
                        "user_id": $userId
                    }
                    """.trimIndent()
        )
    }

    /**
     * 转发单条群消息。
     *
     * @param messageId 消息 ID。
     * @param groupId 群号。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun forwardGroupSingleMsg(messageId: Long, groupId: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/forward_group_single_msg",
            """
                    {
                        "message_id": $messageId,
                        "group_id": $groupId
                    }
                    """.trimIndent()
        )
    }

    /**
     * 获取消息详情。
     *
     * @param messageId 消息 ID。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun getMsg(messageId: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/get_msg",
            """
                    {
                        "message_id": $messageId
                    }
                    """.trimIndent()
        )
    }

    /**
     * 撤回消息。
     *
     * @param messageId 消息 ID。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun deleteMsg(messageId: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/delete_msg",
            """
                    {
                        "message_id": $messageId
                    }
                    """.trimIndent()
        )
    }

    /**
     * 获取消息文件详情。
     *
     * @param file 收到的文件名。
     * @param download 是否下载文件到 QQ 目录。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun getFile(file: String, download: Boolean = true): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/get_file",
            """
                    {
                        "file": "$file",
                        "download": $download
                    }
                    """.trimIndent()
        )
    }

    /**
     * 获取消息图片详情。
     *
     * @param file 收到的图片文件名。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun getImage(file: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/get_image",
            """
                    {
                        "file": "$file"
                    }
                    """.trimIndent()
        )
    }

    /**
     * 获取消息语音详情。
     *
     * @param file 收到的语音文件名。
     * @param outFormat 要转换到的格式（mp3、amr、wma、m4a、spx、ogg、wav、flac）。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun getRecord(file: String, outFormat: String = "mp3"): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/get_record",
            """
                    {
                        "file": "$file",
                        "out_format": "$outFormat"
                    }
                    """.trimIndent()
        )
    }

    /**
     * 表情回应消息。
     *
     * @param messageId 消息 ID。
     * @param emojiId 表情 ID。
     *
     * 表情 ID 参考：
     * https://bot.q.qq.com/wiki/develop/api-v2/openapi/emoji/model.html#EmojiType
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun setMsgEmojiLike(messageId: Long, emojiId: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/set_msg_emoji_like",
            """
                    {
                        "message_id": $messageId,
                        "emoji_id": $emojiId
                    }
                    """.trimIndent()
        )
    }

    /**
     * 取消消息表情回应。
     *
     * @param messageId 消息 ID。
     * @param emojiId 表情 ID。
     *
     * 表情 ID 参考：
     * https://bot.q.qq.com/wiki/develop/api-v2/openapi/emoji/model.html#EmojiType
     *
     * @return 响应体字符串。
     */
    @JvmStatic
    fun unsetMsgEmojiLike(messageId: Long, emojiId: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/unset_msg_emoji_like",
            """
                    {
                        "message_id": $messageId,
                        "emoji_id": $emojiId
                    }
                    """.trimIndent()
        )
    }

    /**
     * 获取好友历史消息记录。
     *
     * @param userId QQ 号。
     * @param messageSeq 起始消息序号，填 0 表示从最新开始。
     * @param count 消息数量。
     * @param reverseOrder 是否逆序。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun getFriendMsgHistory(
        userId: Long,
        messageSeq: Long = 0,
        count: Long = 20,
        reverseOrder: Boolean = false
    ): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/get_friend_msg_history",
            """
                    {
                        "user_id": $userId,
                        "message_seq": $messageSeq,
                        "count": $count,
                        "reverseOrder": $reverseOrder
                    }
                    """.trimIndent()
        )
    }

    /**
     * 获取群历史消息。
     *
     * @param groupId 群号。
     * @param messageSeq 起始消息序号，填 0 表示从最新开始。
     * @param count 消息数量。
     * @param reverseOrder 是否逆序。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun getGroupMsgHistory(
        groupId: Long,
        messageSeq: Long = 0,
        count: Long = 20,
        reverseOrder: Boolean = false
    ): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/get_group_msg_history",
            """
                    {
                        "group_id": $groupId,
                        "message_seq": $messageSeq,
                        "count": $count,
                        "reverseOrder": $reverseOrder
                    }
                    """.trimIndent()
        )
    }

    /**
     * 获取转发消息详情。
     *
     * @param messageId 消息 ID。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun getForwardMsg(messageId: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/get_forward_msg",
            """
                    {
                        "message_id": $messageId
                    }
                    """.trimIndent()
        )
    }

    /**
     * 标记消息已读。
     *
     * @param messageId 消息 ID。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun markMsgAsRead(messageId: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/mark_msg_as_read",
            """
                    {
                        "message_id": $messageId
                    }
                    """.trimIndent()
        )
    }

    /**
     * 语音消息转文字。
     *
     * @param messageId 消息 ID。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun voiceMsgToText(messageId: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/voice_msg_to_text",
            """
                    {
                        "message_id": $messageId
                    }
                    """.trimIndent()
        )
    }

    /**
     * 发送群 AI 语音。
     *
     * @param character 语音声色。
     * @param groupId 群号。
     * @param text 语音文本。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun sendGroupAiRecord(character: String, groupId: Long, text: String): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/send_group_ai_record",
            """
                    {
                        "character": "$character",
                        "group_id": $groupId,
                        "text": "$text"
                    }
                    """.trimIndent()
        )
    }

    /**
     * 获取群 AI 语音可用声色列表。
     *
     * @param groupId 群号。
     * @return 响应体字符串。
     */
    @JvmStatic
    fun getAiCharacters(groupId: Long): String {
        return HttpUtils.sendPostRequest(
            "${BotConfig.httpUrl}/get_ai_characters",
            """
                    {
                        "group_id": $groupId,
                        "chat_type": 1
                    }
                    """.trimIndent()
        )
    }
}