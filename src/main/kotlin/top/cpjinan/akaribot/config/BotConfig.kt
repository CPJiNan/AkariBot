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
     * HTTP API 服务器地址。
     */
    val api: String by lazy {
        settings.getString("Bot.API", "http://127.0.0.1:3000/")!!
    }

    /**
     * HTTP 上报服务器端口。
     */
    val post: Int by lazy {
        settings.getInt("Bot.Post", 8080)
    }
}