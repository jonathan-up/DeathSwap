package cn.j0n4than.mc.deathswap.actions;

import cn.j0n4than.mc.deathswap.DeathSwap;
import cn.j0n4than.mc.deathswap.interfaces.Action;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Life was like a box of chocolates
 * you never know what you're going to get.
 *
 * @author jonathan [admin@56fkj.cn]
 */
public class P2 implements Action {

    private final DeathSwap plugin;

    public P2(DeathSwap plugin) {
        this.plugin = plugin;
    }

    public boolean run(CommandSender sender, Command command, String label, String[] args) {
        if (DeathSwap.isStart) {
            sender.sendMessage(plugin.lang("start_already_started"));
            return true;
        }

        Player player = Bukkit.getPlayer(args[1]);

        if (player == null || !player.isOnline()) {
            sender.sendMessage(plugin.lang("start_player_not_found"));
            return true;
        }

        if (DeathSwap.playerTwo != null && DeathSwap.playerTwo.getName().equals(player.getName())) {
            DeathSwap.waitingBar.removePlayer(player);
            DeathSwap.playerTwo = null;
            sender.sendMessage(plugin.lang("unselect_p2"));
            return true;
        }

        if (DeathSwap.playerOne != null && player.getName().equals(DeathSwap.playerOne.getName())) {
            sender.sendMessage(plugin.lang("player_can_not_be_same_one"));
            return true;
        }

        if (DeathSwap.playerTwo != null) {
            DeathSwap.waitingBar.removePlayer(DeathSwap.playerTwo);
        }
        DeathSwap.playerTwo = player;
        DeathSwap.waitingBar.addPlayer(player);

        sender.sendMessage(plugin.lang("set_p2").replace("%player%", player.getName()));
        return true;
    }
}
