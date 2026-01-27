package top.cpjinan.akaribot.cache

import java.util.concurrent.ConcurrentHashMap

/**
 * AkariBot
 * top.cpjinan.akaribot.cache
 *
 * 本地缓存。
 *
 * @author 季楠
 * @since 2025/8/12 04:43
 */
class LocalCache : Cache {
    /**
     * 缓存表。
     */
    val caches = hashMapOf<String, MutableMap<String, String>>()

    /**
     * 创建缓存表。
     *
     * @param name 缓存表名称。
     * @return 请求的缓存表。
     */
    fun createCache(name: String): MutableMap<String, String> {
        val cache = caches.computeIfAbsent(name) { ConcurrentHashMap() }
        return cache
    }

    /**
     * 获取或创建缓存表。
     *
     * 如果缓存表不存在，则会创建一个新的。
     *
     * @param name 缓存表名称。
     * @return 请求的缓存表。
     */
    fun getOrCreateCache(name: String): MutableMap<String, String> {
        return caches.getOrPut(name) { createCache(name) }
    }

    override val type = CacheType.LOCAL

    override fun contains(cache: String, path: String): Boolean {
        return path in getOrCreateCache(cache)
    }

    override fun get(cache: String, path: String): String? {
        return getOrCreateCache(cache).get(path)
    }

    override fun set(cache: String, path: String, value: String?) {
        if (value == null) getOrCreateCache(cache).remove(path)
        else getOrCreateCache(cache)[path] = value
    }
}