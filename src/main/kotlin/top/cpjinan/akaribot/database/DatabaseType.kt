package top.cpjinan.akaribot.database

import top.cpjinan.akaribot.config.DatabaseConfig

/**
 * AkariBot
 * top.cpjinan.akaribot.database
 *
 * 数据库类型枚举。
 *
 * @author 季楠
 * @since 2026/1/18 23:07
 */
enum class DatabaseType {
    SQLITE,
    MYSQL;

    companion object {
        @JvmStatic
        val instance by lazy {
            try {
                valueOf(DatabaseConfig.type.uppercase())
            } catch (e: Exception) {
                e.printStackTrace()
                SQLITE
            }
        }
    }
}