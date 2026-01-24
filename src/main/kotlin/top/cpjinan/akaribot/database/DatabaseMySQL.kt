package top.cpjinan.akaribot.database

import taboolib.module.database.ColumnOptionSQL
import taboolib.module.database.ColumnTypeSQL
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

    override fun createTable(name: String): Table<*, *> {
        val table = tables.computeIfAbsent(name) {
            Table(name, DatabaseConfig.hostSQL) {
                add("key") {
                    type(ColumnTypeSQL.VARCHAR, 64) {
                        options(ColumnOptionSQL.PRIMARY_KEY)
                    }
                }
                add("value") {
                    type(ColumnTypeSQL.TEXT)
                }
            }
        }
        table.createTable(dataSource)
        return table
    }

    override fun getOrCreateTable(name: String): Table<*, *> {
        return tables.getOrPut(name) { createTable(name) }
    }

    override fun contains(table: String, path: String): Boolean {
        return getOrCreateTable(table).select(dataSource) {
            rows("key")
            where("key" eq path)
            limit(1)
        }.find()
    }

    override fun get(table: String, path: String): String? {
        return getOrCreateTable(table).select(dataSource) {
            rows("key", "value")
            where("key" eq path)
            limit(1)
        }.firstOrNull {
            getString("value")
        }
    }

    override fun set(table: String, path: String, value: String?) {
        if (value == null) {
            getOrCreateTable(table).delete(dataSource) {
                where { "key" eq path }
            }
            return
        }
        if (contains(table, path)) getOrCreateTable(table).update(dataSource) {
            set("value", value)
            where("key" eq path)
        } else {
            getOrCreateTable(table).insert(dataSource, "key", "value") {
                value(path, value)
            }
        }
    }
}