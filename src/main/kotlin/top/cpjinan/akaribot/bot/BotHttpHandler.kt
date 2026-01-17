package top.cpjinan.akaribot.bot

import com.google.gson.Gson
import com.google.gson.JsonObject
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
    private val gson = Gson()

    override fun handle(exchange: HttpExchange) {
        try {
            when (exchange.requestMethod) {
                "POST" -> {
                    BotPostEvent(
                        gson.fromJson(
                            exchange.requestBody.bufferedReader().readText(),
                            JsonObject::class.java
                        )
                    ).call()
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