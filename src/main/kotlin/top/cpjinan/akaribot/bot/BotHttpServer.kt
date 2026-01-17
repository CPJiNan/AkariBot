package top.cpjinan.akaribot.bot

import com.sun.net.httpserver.HttpServer
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import java.net.InetSocketAddress

/**
 * AkariBot
 * top.cpjinan.akaribot.bot
 *
 * HTTP 服务器。
 *
 * @author 季楠
 * @since 2026/1/17 20:16
 */
object BotHttpServer {
    @Awake(LifeCycle.ENABLE)
    fun onEnable() {
        try {
            HttpServer.create(InetSocketAddress(8080), 0).apply {
                createContext("/", BotHttpHandler())
                start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}