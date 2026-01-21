package top.cpjinan.akaribot.config

import top.cpjinan.akaribot.config.SettingsConfig.settings

/**
 * AkariBot
 * top.cpjinan.akaribot.config
 *
 * Bot 配置。
 *
 * @author 季楠
 * @since 2026/1/18 22:34
 */
object BotConfig {
    /**
     * HTTP 服务器地址。
     */
    val httpUrl: String by lazy {
        settings.getString("Bot.HTTP", "http://127.0.0.1:3000")!!
    }

    /**
     * WebHook 是否启用。
     */
    val webHookEnable: Boolean by lazy {
        settings.getBoolean("Bot.WebHook.Enable", true)
    }

    /**
     * WebHook 端口。
     */
    val webHookPort: Int by lazy {
        settings.getInt("Bot.WebHook.Port", 5700)
    }
}