package top.cpjinan.akaribot.cache

import top.cpjinan.akaribot.config.CacheConfig

/**
 * AkariBot
 * top.cpjinan.akaribot.cache
 *
 * 缓存类型枚举。
 *
 * @author 季楠
 * @since 2026/1/18 23:07
 */
enum class CacheType {
    LOCAL,
    REDIS;

    companion object {
        @JvmStatic
        val instance by lazy {
            try {
                valueOf(CacheConfig.type.uppercase())
            } catch (e: Exception) {
                e.printStackTrace()
                LOCAL
            }
        }
    }
}