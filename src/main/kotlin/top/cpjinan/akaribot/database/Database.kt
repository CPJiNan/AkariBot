package top.cpjinan.akaribot.database

import taboolib.module.database.Table
import javax.sql.DataSource

/**
 * AkariBot
 * top.cpjinan.akaribot.database
 *
 * 数据库接口。
 *
 * @author TabooLib, 季楠
 * @since 2026/1/18 23:07
 */
interface Database {
    companion object {
        @JvmStatic
        val instance by lazy {
            when (DatabaseType.instance) {
                DatabaseType.SQLITE -> DatabaseSQLite()
                DatabaseType.MYSQL -> DatabaseMySQL()
            }
        }
    }

    /**
     * 数据库类型。
     */
    val type: DatabaseType

    /**
     * 数据源。
     */
    val dataSource: DataSource

    /**
     * 数据表。
     */
    val tables: Map<String, Table<*, *>>

    /**
     * 创建数据表。
     *
     * @param name 数据表名称。
     * @return 请求的数据表。
     */
    fun createTable(name: String): Table<*, *>

    /**
     * 获取或创建数据表。
     *
     * 如果数据表不存在，则会创建一个新的。
     *
     * @param name 数据表名称。
     * @return 请求的数据表。
     */
    fun getOrCreateTable(name: String): Table<*, *>

    /**
     * 检查此数据表中是否包含给定路径。
     *
     * 如果请求路径的值不存在但已指定默认值，则此方法将返回 true。
     *
     * @param table 要操作的数据表名称。
     * @param path 要检查存在性的路径。
     * @return 如果此数据表包含请求的路径（通过默认值或已设置），则返回 true。
     */
    fun contains(table: String, path: String): Boolean

    /**
     * 通过路径获取请求的对象。
     *
     * 如果对象不存在，则返回 null。
     *
     * @param table 要操作的数据表名称。
     * @param path 要获取的对象的路径。
     * @return 请求的对象。
     */
    fun get(table: String, path: String): String?

    /**
     * 将指定路径设置为给定值。
     *
     * 如果值为 null，则会删除该条目。任何现有条目都将被替换，无论新值是什么。
     *
     * @param table 要操作的数据表名称。
     * @param path 要设置的对象的路径。
     * @param value 要设置的新值。
     */
    fun set(table: String, path: String, value: String?)
}