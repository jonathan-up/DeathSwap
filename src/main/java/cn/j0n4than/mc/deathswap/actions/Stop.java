package cn.j0n4than.mc.deathswap.actions;

import cn.j0n4than.mc.deathswap.DeathSwap;
import cn.j0n4than.mc.deathswap.interfaces.Action;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Life was like a box of chocolates
 * you never know what you're going to get.
 *
 * @author jonathan [admin@56fkj.cn]
 */
public class Stop implements Action {

    private final DeathSwap plugin;

    public Stop(DeathSwap plugin) {
        this.plugin = plugin;
    }

    public boolean run(CommandSender sender, Command command, String label, String[] args) {
        if (!DeathSwap.isStart) {
            sender.sendMessage(plugin.lang("start_has_not_started"));
            return true;
        }

        DeathSwap.readyBar.removeAll();
        DeathSwap.startBar.removeAll();

        if (!DeathSwap.readyTask.isCancelled()) {
            DeathSwap.readyTask.cancel();
        }

        if (!DeathSwap.swapTask.isCancelled()) {
            DeathSwap.swapTask.cancel();
        }

        DeathSwap.waitingBar.addPlayer(DeathSwap.playerOne);
        DeathSwap.waitingBar.addPlayer(DeathSwap.playerTwo);

        DeathSwap.playerOne.sendTitle(ChatColor.RED + plugin.lang("game_over_forced"), null, 10, 60, 10);
        DeathSwap.playerTwo.sendTitle(ChatColor.RED + plugin.lang("game_over_forced"), null, 10, 60, 10);

        DeathSwap.isStart = false;

        sender.sendMessage(plugin.lang("game_over_forced"));
        return true;
    }
}
