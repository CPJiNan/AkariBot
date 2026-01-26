package top.cpjinan.akaribot.cache

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.LoadingCache
import top.cpjinan.akaribot.database.Database

/**
 * AkariBot
 * top.cpjinan.akaribot.cache
 *
 * 本地数据缓存。
 *
 * @author 季楠
 * @since 2025/8/12 04:43
 */
class LocalCache : Cache {
    /**
     * 数据表缓存。
     */
    val caches = hashMapOf<String, LoadingCache<String, String>>()

    /**
     * 创建数据表缓存。
     *
     * @param table 数据表名称。
     * @return 请求的数据表缓存。
     */
    fun createCache(table: String): LoadingCache<String, String> {
        val cache = caches.computeIfAbsent(table) {
            Caffeine.newBuilder()
                .build { key ->
                    Database.instance.get(table, key) ?: ""
                }
        }
        return cache
    }

    /**
     * 获取或创建数据表缓存。
     *
     * 如果数据表缓存不存在，则会创建一个新的。
     *
     * @param table 数据表名称。
     * @return 请求的数据表缓存。
     */
    fun getOrCreateCache(table: String): LoadingCache<String, String> {
        return caches.getOrPut(table) { createCache(table) }
    }

    override fun get(table: String, path: String): String? {
        return getOrCreateCache(table).get(path)
    }

    override fun set(table: String, path: String, value: String?) {
        if (value == null) getOrCreateCache(table).invalidate(path)
        else getOrCreateCache(table).put(path, value)
    }

    override fun invalidate(table: String, path: String) {
        getOrCreateCache(table).invalidate(path)
    }
}