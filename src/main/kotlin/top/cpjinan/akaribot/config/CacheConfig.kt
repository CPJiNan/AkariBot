package top.cpjinan.akaribot.config

import taboolib.expansion.LettuceRedisConfig
import top.cpjinan.akaribot.config.SettingsConfig.settings

/**
 * AkariBot
 * top.cpjinan.akaribot.config
 *
 * 缓存配置。
 *
 * @author 季楠
 * @since 2025/8/7 22:20
 */
object CacheConfig {
    val type: String by lazy {
        settings.getString("Cache.Type") ?: "LOCAL"
    }

    val lettuceRedisConfig: LettuceRedisConfig by lazy {
        LettuceRedisConfig(settings.getConfigurationSection("Cache.Redis")!!)
    }
}