package cn.j0n4than.mc.deathswap.actions;

import cn.j0n4than.mc.deathswap.DeathSwap;
import cn.j0n4than.mc.deathswap.interfaces.Action;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Life was like a box of chocolates
 * you never know what you're going to get.
 *
 * @author jonathan [admin@56fkj.cn]
 */
public class ReadyTime implements Action {

    private final DeathSwap plugin;

    public ReadyTime(DeathSwap plugin) {
        this.plugin = plugin;
    }

    public boolean run(CommandSender sender, Command command, String label, String[] args) {
        if (DeathSwap.isStart) {
            sender.sendMessage(plugin.lang("start_already_started"));
            return true;
        }

        if (args.length < 2) {
            plugin.usage(sender);
            return true;
        }

        try {
            int i = Integer.parseInt(args[1]);
            if (i < 1) {
                sender.sendMessage(plugin.lang("ready_time_sec_must_bigger_than_1"));
                return true;
            }
            DeathSwap.readyTime = i;
            sender.sendMessage(plugin.lang("ready_time_set").replace("%s%", String.valueOf(i)));
        } catch (NumberFormatException e) {
            sender.sendMessage(plugin.lang("ready_time_sec_must_be_int"));
            return true;
        }

        return true;
    }
}
