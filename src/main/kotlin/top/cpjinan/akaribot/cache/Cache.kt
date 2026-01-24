package top.cpjinan.akaribot.cache

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.LoadingCache
import top.cpjinan.akaribot.database.Database

/**
 * AkariBot
 * top.cpjinan.akaribot.cache
 *
 * 数据缓存。
 *
 * @author 季楠
 * @since 2025/8/12 04:43
 */
object Cache {
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
    @JvmStatic
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
    @JvmStatic
    fun getOrCreateCache(table: String): LoadingCache<String, String> {
        return caches.getOrPut(table) { createCache(table) }
    }

    /**
     * 通过路径获取请求的对象。
     *
     * 如果对象不存在，则返回 null。
     *
     * @param table 要操作的数据表名称。
     * @param path 要获取的对象的路径。
     * @return 请求的对象。
     */
    @JvmStatic
    fun get(table: String, path: String): String? {
        return getOrCreateCache(table).get(path)
    }

    /**
     * 将指定路径设置为给定值。
     *
     * 如果值为 null，则会删除该条目。任何现有条目都将被替换，无论新值是什么。
     *
     * @param table 要操作的数据表名称。
     * @param path 要设置的对象的路径。
     * @param value 要设置的新值。
     */
    @JvmStatic
    fun set(table: String, path: String, value: String?) {
        if (value == null) getOrCreateCache(table).invalidate(path)
        else getOrCreateCache(table).put(path, value)
        Database.instance.set(table, path, value)
    }
}