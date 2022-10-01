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
public class P1 implements Action {

    private final DeathSwap plugin;

    public P1(DeathSwap plugin) {
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

        if (DeathSwap.playerOne != null && DeathSwap.playerOne.getName().equals(player.getName())) {
            DeathSwap.waitingBar.removePlayer(player);
            DeathSwap.playerOne = null;
            sender.sendMessage(plugin.lang("unselect_p1"));
            return true;
        }

        if (DeathSwap.playerTwo != null && player.getName().equals(DeathSwap.playerTwo.getName())) {
            sender.sendMessage(plugin.lang("player_can_not_be_same_one"));
            return true;
        }

        if (DeathSwap.playerOne != null) {
            DeathSwap.waitingBar.removePlayer(DeathSwap.playerOne);
        }
        DeathSwap.playerOne = player;
        DeathSwap.waitingBar.addPlayer(player);

        sender.sendMessage(plugin.lang("set_p1").replace("%player%", player.getName()));
        return true;
    }
}
