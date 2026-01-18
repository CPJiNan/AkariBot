package top.cpjinan.akaribot.bot

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import top.cpjinan.akaribot.event.BotPostEvent

/**
 * AkariBot
 * top.cpjinan.akaribot.bot
 *
 * HTTP 处理器。
 *
 * @author 季楠
 * @since 2026/1/17 20:02
 */
class BotHttpHandler : HttpHandler {
    override fun handle(exchange: HttpExchange) {
        try {
            when (exchange.requestMethod) {
                "POST" -> {
                    BotPostEvent(exchange.requestBody.bufferedReader().readText()).call()
                    exchange.sendResponseHeaders(200, -1)
                    exchange.responseBody.close()
                }

                else -> exchange.sendResponseHeaders(405, -1)
            }
        } catch (_: Exception) {
            exchange.sendResponseHeaders(500, -1)
        } finally {
            exchange.close()
        }
    }
}