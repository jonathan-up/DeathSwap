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
public class Countdown implements Action {

    private final DeathSwap plugin;

    public Countdown(DeathSwap plugin) {
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
            if (i < 5) {
                sender.sendMessage(plugin.lang("countdown_sec_must_bigger_than_5"));
                return true;
            }
            DeathSwap.countdown = i;
            sender.sendMessage(plugin.lang("countdown_set").replace("%s%", String.valueOf(i)));
        } catch (NumberFormatException e) {
            sender.sendMessage(plugin.lang("countdown_sec_must_be_int"));
            return true;
        }

        return true;
    }
}
