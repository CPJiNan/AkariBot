/**
 * AkariBot
 *
 * Bot 执行命令。
 *
 * @author 季楠
 * @since 2026/1/24 09:31
 */

var users = [];
var groups = [704109949, 983034842];

function onPluginEnable() {
    onBotPost();
}

function onBotPost() {
    new Listener(BotPostEvent.class)
        .setExecutor(
            function (event) {
                var json = JSON.parse(event.getJson());
                if (json.post_type !== "message") return;

                var message = json.raw_message;
                var userId = json.user_id;

                if (!message.startsWith("/执行命令 ")) return;
                var command = message.substring(6).trim();

                var noPermissionMessage = "无权限执行命令。";
                var successMessage = "命令执行成功。";
                var failMessage = "命令执行失败。";

                if (json.message_type === "group") {
                    var groupId = json.group_id;
                    if (groups.indexOf(groupId) === -1) return;
                    if (users.indexOf(userId) === -1) {
                        BotMessageAPI.sendGroupTextMsg(groupId, noPermissionMessage);
                        return;
                    }
                } else if (json.message_type === "private") {
                    if (users.indexOf(userId) === -1) {
                        BotMessageAPI.sendPrivateTextMsg(userId, noPermissionMessage);
                        return;
                    }
                }

                var success = Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                if (success) {
                    if (json.message_type === "private") BotMessageAPI.sendPrivateTextMsg(userId, successMessage);
                    else if (json.message_type === "group") BotMessageAPI.sendGroupTextMsg(groupId, successMessage);
                } else {
                    if (json.message_type === "private") BotMessageAPI.sendPrivateTextMsg(userId, failMessage);
                    else if (json.message_type === "group") BotMessageAPI.sendGroupTextMsg(groupId, failMessage);
                }
            }
        ).register();
}