package top.cpjinan.akaribot.cache

/**
 * AkariBot
 * top.cpjinan.akaribot.cache
 *
 * 缓存接口。
 *
 * @author 季楠
 * @since 2026/1/26 22:02
 */
interface Cache {
    companion object {
        @JvmStatic
        val instance by lazy {
            when (CacheType.instance) {
                CacheType.LOCAL -> LocalCache()
                CacheType.REDIS -> RedisCache()
            }
        }
    }

    /**
     * 通过路径获取请求的对象。
     *
     * 如果对象不存在，则返回 null。
     *
     * @param cache 要操作的缓存表名称。
     * @param path 要获取的对象的路径。
     * @return 请求的对象。
     */
    fun get(cache: String, path: String): String?

    /**
     * 将指定路径设置为给定值。
     *
     * 如果值为 null，则会删除该条目。任何现有条目都将被替换，无论新值是什么。
     *
     * @param cache 要操作的缓存表名称。
     * @param path 要设置的对象的路径。
     * @param value 要设置的新值。
     */
    fun set(cache: String, path: String, value: String?)
}