package top.cpjinan.akaribot.command

import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.PermissionDefault
import taboolib.common.platform.command.subCommand
import taboolib.module.lang.Language
import taboolib.module.lang.sendLang
import top.cpjinan.akaribot.config.SettingsConfig
import top.cpjinan.akaribot.event.PluginReloadEvent
import top.cpjinan.akaribot.script.ScriptHandler

/**
 * AkariBot
 * top.cpjinan.akaribot.command
 *
 * 插件主命令。
 *
 * @author 季楠
 * @since 2026/1/17 21:37
 */
@CommandHeader(
    name = "akaribot",
    aliases = ["abot"],
    permission = "AkariBot.command.use",
    permissionDefault = PermissionDefault.OP
)
object MainCommand {
    @CommandBody(
        permission = "AkariBot.command.reload.use",
        permissionDefault = PermissionDefault.OP
    )
    val reload = subCommand {
        execute<ProxyCommandSender> { sender, _, _ ->
            PluginReloadEvent.Pre().call()

            // 重载配置文件。
            SettingsConfig.settings.reload()

            // 重载语言文件。
            Language.reload()

            // 重载脚本。
            ScriptHandler.reload()

            PluginReloadEvent.Post().call()
            sender.sendLang("PluginReloaded")
        }
    }
}