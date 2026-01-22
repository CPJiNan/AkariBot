/**
 * AkariBot
 *
 * Bot 玩家列表命令。
 *
 * @author 季楠
 * @since 2025/9/19 23:42
 */

function onPluginEnable() {
    onBotPostEvent();
}

function onBotPostEvent() {
    new Listener(BotPostEvent.class)
        .setExecutor(
            function (event) {
                var json = JSON.parse(event.getJson());
                if (json.post_type !== "message" || json.message_type !== "group") return;

                var message = json.raw_message;
                if (message === ".玩家列表") {
                    var groupId = json.group_id;
                    var players = Bukkit.getOnlinePlayers();
                    var playerNames = [];
                    for (var i = 0; i < players.length; i++) {
                        playerNames.push(players.get(i).getName());
                    }
                    if (players.length !== 0) {
                        BotMessageAPI.sendGroupMsg(groupId, "服务器当前有 " + players.length + " 个玩家在线:\\n" + playerNames.join(", "));
                    } else {
                        BotMessageAPI.sendGroupMsg(groupId, "服务器当前有 0 个玩家在线");
                    }
                }
            }
        ).register();
}