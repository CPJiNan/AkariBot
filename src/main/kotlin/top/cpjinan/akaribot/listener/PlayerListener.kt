package top.cpjinan.akaribot.listener

import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.lang.event.PlayerSelectLocaleEvent
import taboolib.module.lang.event.SystemSelectLocaleEvent
import top.cpjinan.akaribot.config.SettingsConfig

/**
 * AkariBot
 * top.cpjinan.akaribot.listener
 *
 * 玩家监听器。
 *
 * @author 季楠
 * @since 2026/1/17 19:44
 */
object PlayerListener {
    /**
     * 玩家获取语言文本事件。
     */
    @SubscribeEvent
    fun onPlayerSelectLocale(event: PlayerSelectLocaleEvent) {
        event.locale = SettingsConfig.language
    }

    /**
     * 系统获取语言文本事件。
     */
    @SubscribeEvent
    fun onSystemSelectLocale(event: SystemSelectLocaleEvent) {
        event.locale = SettingsConfig.language
    }
}