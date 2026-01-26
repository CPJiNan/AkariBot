package top.cpjinan.akaribot.cache

import taboolib.expansion.LettuceRedisClient
import top.cpjinan.akaribot.config.CacheConfig

/**
 * AkariBot
 * top.cpjinan.akaribot.cache
 *
 * Redis 缓存。
 *
 * @author 季楠
 * @since 2026/1/26 22:41
 */
class RedisCache : Cache {
    companion object {
        val client = LettuceRedisClient(CacheConfig.lettuceRedisConfig)

        init {
            client.start()
        }
    }

    override fun get(cache: String, path: String): String? {
        return client.useCommands { it.get("${cache}:${path}") }
    }

    override fun set(cache: String, path: String, value: String?) {
        if (value == null) client.useCommands { it.del("${cache}:${path}") }
        else client.useCommands { it.set("${cache}:${path}", value) }
    }
}