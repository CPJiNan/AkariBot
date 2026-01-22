package top.cpjinan.akaribot.event

import taboolib.platform.type.BukkitProxyEvent

/**
 * AkariBot
 * top.cpjinan.akaribot.event
 *
 * Bot 上报事件。
 *
 * @author 季楠
 * @since 2026/1/17 20:10
 */
class BotPostEvent(
    val json: String
) : BukkitProxyEvent()