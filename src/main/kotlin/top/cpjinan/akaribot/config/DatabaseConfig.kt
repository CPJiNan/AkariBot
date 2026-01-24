package top.cpjinan.akaribot.config

import taboolib.common.platform.function.getDataFolder
import taboolib.module.database.HostSQL
import taboolib.module.database.HostSQLite
import taboolib.module.database.getHost
import top.cpjinan.akaribot.config.SettingsConfig.settings
import java.io.File

/**
 * AkariBot
 * top.cpjinan.akaribot.config
 *
 * 数据库配置。
 *
 * @author 季楠
 * @since 2025/8/7 22:20
 */
object DatabaseConfig {
    val type: String by lazy {
        settings.getString("Database.Type") ?: "SQLITE"
    }

    val hostSQL: HostSQL by lazy {
        settings.getHost("Database.MySQL")
    }

    val file: String by lazy {
        settings.getString("Database.SQLite.file") ?: "sqlite.db"
    }

    val hostSQLite: HostSQLite by lazy {
        HostSQLite(
            File(getDataFolder(), file).apply {
                parentFile?.mkdirs()
                createNewFile()
            }
        )
    }
}