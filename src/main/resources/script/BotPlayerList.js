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
                var sender = json.sender.user_id;
                var message = json.raw_message;

                if (message === ".list") {
                    var players = Bukkit.getOnlinePlayers();
                    var playerNames = new java.util.ArrayList();
                    for (var i = 0; i < players.size(); i++) {
                        playerNames.add(players.get(i).getName());
                    }
                    BotMessageAPI.sendPrivateMsg(sender, "服务器当前有" + players.size() + "个玩家在线:"
                        + playerNames.join(", "));
                }
            }
        ).register();
}