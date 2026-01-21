package top.cpjinan.akaribot.bot

import com.sun.net.httpserver.HttpServer
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.console
import taboolib.module.lang.sendLang
import top.cpjinan.akaribot.config.BotConfig
import java.net.InetSocketAddress

/**
 * AkariBot
 * top.cpjinan.akaribot.bot
 *
 * WebHook 服务器。
 *
 * @author 季楠
 * @since 2026/1/17 20:16
 */
object BotWebHookServer {
    @Awake(LifeCycle.ENABLE)
    fun onEnable() {
        if (!BotConfig.webHookEnable) return
        try {
            HttpServer.create(InetSocketAddress(BotConfig.webHookPort), 0).apply {
                createContext("/", BotHttpHandler())
                start()
            }
            console().sendLang("BotWebHookServerStarted", "http://127.0.0.1:${BotConfig.webHookPort}")
        } catch (e: Exception) {
            console().sendLang("BotWebHookServerStartFailed", "http://127.0.0.1:${BotConfig.webHookPort}")
            e.printStackTrace()
        }
    }
}