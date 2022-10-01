package cn.j0n4than.mc.deathswap.listeners;

import cn.j0n4than.mc.deathswap.DeathSwap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Life was like a box of chocolates
 * you never know what you're going to get.
 *
 * @author jonathan [admin@56fkj.cn]
 */
public class PlayerListener implements Listener {

    private final DeathSwap plugin;

    public PlayerListener(DeathSwap plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerOneQuit(PlayerQuitEvent event) {
        // TODO: 2022/10/1 监听游戏中的玩家1退出
        Player player = event.getPlayer();
        String msg = plugin.lang("player1_select_quit");

        if (DeathSwap.playerOne != null && DeathSwap.playerOne.getName().equals(player.getName())) {
            //玩家1离开
            DeathSwap.playerOne = null;
            DeathSwap.sender.sendMessage(msg.replace("%p%", "1"));
        }
    }

    @EventHandler
    public void onPlayerTwoQuit(PlayerQuitEvent event) {
        // TODO: 2022/10/1 监听游戏中的玩家2退出
        Player player = event.getPlayer();
        String msg = plugin.lang("player1_select_quit");

        if (DeathSwap.playerTwo != null && DeathSwap.playerTwo.getName().equals(player.getName())) {
            //玩家2离开
            DeathSwap.playerTwo = null;
            DeathSwap.sender.sendMessage(msg.replace("%p%", "2"));
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        if (!DeathSwap.isStart) {
            return;
        }

        List<String> playerNames = Arrays.asList(DeathSwap.playerOne.getName(), DeathSwap.playerTwo.getName());

        Player player = event.getEntity();

        if (playerNames.contains(player.getName())) {
            //Game over

            DeathSwap.isStart = false;

            DeathSwap.readyBar.removeAll();
            DeathSwap.startBar.removeAll();

            DeathSwap.waitingBar.addPlayer(DeathSwap.playerOne);
            DeathSwap.waitingBar.addPlayer(DeathSwap.playerTwo);

            //player died in ready time
            if (!DeathSwap.readyTask.isCancelled()) {
                DeathSwap.readyTask.cancel();
            }

            if (!DeathSwap.swapTask.isCancelled()) {
                DeathSwap.swapTask.cancel();
            }

            List<Player> players = Arrays.asList(DeathSwap.playerOne, DeathSwap.playerTwo);

            int winner = 0;

            if (DeathSwap.playerOne.getName().equals(player.getName())) {
                //player two won
                winner = 1;
                DeathSwap.playerTwo.sendTitle(plugin.lang("game_over"), plugin.lang("you_win"), 10, 60, 10);
            } else {
                DeathSwap.playerOne.sendTitle(plugin.lang("game_over"), plugin.lang("you_win"), 10, 60, 10);
            }

            //sender不存在就发送给所有op
            if (DeathSwap.sender == null) {
                Collection<? extends Player> onlinePlayers = plugin.getServer().getOnlinePlayers();

                for (Player onlinePlayer : onlinePlayers) {
                    if (!onlinePlayer.isOp()) {
                        continue;
                    }

                    onlinePlayer.sendMessage(plugin.lang("game_over"));
                    onlinePlayer.sendMessage(plugin.lang("winner").replace("%player%", players.get(winner).getName()));
                }
            } else {
                DeathSwap.sender.sendMessage(plugin.lang("game_over"));
                DeathSwap.sender.sendMessage(plugin.lang("winner").replace("%player%", players.get(winner).getName()));
            }
        }
    }
}
