/**
 * AkariBot
 *
 * Bot 群服消息互通。
 *
 * @author 季楠
 * @since 2026/1/25 21:51
 */

function onPluginEnable() {
    onBotPost();
    onAsyncPlayerChat();
}

var enable = false;
var groups = [];

function onBotPost() {
    if (!enable) return;
    new Listener(BotPostEvent.class)
        .setExecutor(
            function (event) {
                var json = JSON.parse(event.getJson());
                if (json.post_type !== "message" || json.message_type !== "group") return;

                var groupId = json.group_id;
                var message = json.raw_message.trim();
                if (groups.indexOf(groupId) === -1 || message.startsWith("/") || message.contains("[CQ:")) return;

                var sender = json.sender.card || json.sender.nickname || json.user_id;
                Bukkit.getServer().broadcastMessage("§8[§f群聊§8] §f" + sender + "§7: §f" + message);
            }
        ).register();
}

function onAsyncPlayerChat() {
    if (!enable) return;
    new Listener(org.bukkit.event.player.AsyncPlayerChatEvent.class)
        .setExecutor(
            function (event) {
                for (var i = 0; i < groups.length; i++) {
                    BotMessageAPI.sendGroupTextMsg(groups[i], "[服务器] " + event.getPlayer().getName() + ": " + event.getMessage());
                }
            }
        ).register();
}