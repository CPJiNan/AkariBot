/**
 * AkariBot
 *
 * Bot 玩家列表命令。
 *
 * @author 季楠
 * @since 2026/1/17 20:48
 */

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
                if (message !== "/玩家列表") return;

                var players = Bukkit.getOnlinePlayers();
                var playerNames = [];
                for (var i = 0; i < players.length; i++) {
                    playerNames.push(players.get(i).getName());
                }

                var playerListMessage = "服务器当前有 " + players.length + " 个玩家在线：" + "\\n" + playerNames.join(", ");
                var emptyPlayerListMessage = "服务器当前有 0 个玩家在线。";

                if (json.message_type === "private") {
                    var userId = json.user_id;
                    if (players.length !== 0) BotMessageAPI.sendPrivateTextMsg(userId, playerListMessage);
                    else BotMessageAPI.sendPrivateTextMsg(userId, emptyPlayerListMessage);
                } else if (json.message_type === "group") {
                    var groupId = json.group_id;
                    if (players.length !== 0) BotMessageAPI.sendGroupTextMsg(groupId, playerListMessage);
                    else BotMessageAPI.sendGroupTextMsg(groupId, emptyPlayerListMessage);
                }
            }
        ).register();
}