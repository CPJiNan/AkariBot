package top.cpjinan.akaribot.cache

import taboolib.expansion.LettuceRedisClient
import top.cpjinan.akaribot.config.CacheConfig

/**
 * AkariBot
 * top.cpjinan.akaribot.cache
 *
 * Redis 数据缓存。
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

    override fun get(table: String, path: String): String? {
        return client.useCommands {
            it.get("${table}:${path}")
        }
    }

    override fun set(table: String, path: String, value: String?) {
        if (value == null) {
            client.useCommands {
                it.del("${table}:${path}")
            }
        } else {
            client.useCommands {
                it.set("${table}:${path}", value)
            }
        }
    }

    override fun invalidate(table: String, path: String) {
        client.useCommands {
            it.del("${table}:${path}")
        }
    }
}