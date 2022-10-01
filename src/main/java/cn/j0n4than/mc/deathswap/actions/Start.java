package cn.j0n4than.mc.deathswap.actions;

import cn.j0n4than.mc.deathswap.DeathSwap;
import cn.j0n4than.mc.deathswap.interfaces.Action;
import cn.j0n4than.mc.deathswap.task.ReadyTask;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Life was like a box of chocolates
 * you never know what you're going to get.
 *
 * @author jonathan [admin@56fkj.cn]
 */
public class Start implements Action {

    private final DeathSwap plugin;

    public Start(DeathSwap plugin) {
        this.plugin = plugin;
    }

    public boolean run(CommandSender sender, Command command, String label, String[] args) {

        if (DeathSwap.isStart) {
            sender.sendMessage(plugin.lang("start_already_started"));
            return true;
        }

        DeathSwap.isStart = true;

        if (DeathSwap.playerOne == null || DeathSwap.playerTwo == null) {
            sender.sendMessage(plugin.lang("start_cannot_start_not_enough_players"));
            return true;
        }

        //remove waitingBar for all player
        DeathSwap.waitingBar.removeAll();

        DeathSwap.playerOne.sendTitle(plugin.lang("bar_ready"), null, 10, 20, 10);
        DeathSwap.playerTwo.sendTitle(plugin.lang("bar_ready"), null, 10, 20, 10);

        DeathSwap.readyTask = new ReadyTask(plugin, DeathSwap.readyTime);
        DeathSwap.readyTask.runTaskTimer(plugin, 0, 20);
        return true;
    }
}
