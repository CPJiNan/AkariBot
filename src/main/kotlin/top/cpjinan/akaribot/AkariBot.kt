package top.cpjinan.akaribot

import taboolib.common.platform.Platform
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.console
import taboolib.module.chat.colored
import taboolib.module.lang.sendLang
import taboolib.module.metrics.Metrics
import taboolib.platform.util.bukkitPlugin
import top.cpjinan.akaribot.config.SettingsConfig
import top.cpjinan.akaribot.script.ScriptHandler.Companion.classLoader

/**
 * AkariBot
 * top.cpjinan.akaribot
 *
 * 插件主类。
 *
 * @author 季楠
 * @since 2025/8/7 22:04
 */
object AkariBot : Plugin() {
    /**
     * 插件加载事件。
     */
    override fun onLoad() {
        console().sendLang("PluginLoading", bukkitPlugin.description.version)
        // bStats 统计。
        if (SettingsConfig.sendMetrics) Metrics(
            18992,
            bukkitPlugin.description.version,
            Platform.BUKKIT
        )
    }

    /**
     * 插件启用事件。
     */
    override fun onEnable() {
        console().sendMessage("")
        console().sendMessage("&o _    _              _ ____        _ ".colored())
        console().sendMessage("&o / \\  | | ____ _ _ __(_) __ )  ___ | |_ ".colored())
        console().sendMessage("&o / _ \\ | |/ / _` | '__| |  _ \\ / _ \\| __| ".colored())
        console().sendMessage("&o / ___ \\|   < (_| | |  | | |_) | (_) | |_ ".colored())
        console().sendMessage("&o /_/   \\_\\_|\\_\\__,_|_|  |_|____/ \\___/ \\__| ".colored())
        console().sendMessage("")

        // 为脚本引擎提供类加载器。
        classLoader = this.javaClass.classLoader

        console().sendLang("PluginEnabled")
    }

    /**
     * 插件卸载事件。
     */
    override fun onDisable() {
        console().sendLang("PluginDisabled")
    }
}