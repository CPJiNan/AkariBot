package top.cpjinan.akaribot.database

import taboolib.module.database.Table
import top.cpjinan.akaribot.config.DatabaseConfig

/**
 * AkariBot
 * top.cpjinan.akaribot.database
 *
 * [Database] 接口的 MySQL 实现。
 *
 * @author 季楠
 * @since 2026/1/18 23:07
 */
@Suppress("DEPRECATION", "SqlNoDataSourceInspection")
class DatabaseMySQL() : Database {
    override val type = DatabaseType.MYSQL

    override val dataSource by lazy { DatabaseConfig.hostSQL.createDataSource() }

    override val tables = hashMapOf<String, Table<*, *>>()

    override fun contains(table: Table<*, *>, path: String): Boolean {
        return table.select(dataSource) {
            rows("key")
            where("key" eq path)
            limit(1)
        }.find()
    }

    override fun get(table: Table<*, *>, path: String): String? {
        return table.select(dataSource) {
            rows("key", "value")
            where("key" eq path)
            limit(1)
        }.firstOrNull {
            getString("value")
        }
    }

    override fun set(table: Table<*, *>, path: String, value: String?) {
        if (value == null) {
            table.delete(dataSource) {
                where { "key" eq path }
            }
            return
        }
        if (contains(table, path)) table.update(dataSource) {
            set("value", value)
            where("key" eq path)
        } else {
            table.insert(dataSource, "key", "value") {
                value(path, value)
            }
        }
    }
}