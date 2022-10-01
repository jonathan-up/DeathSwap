package cn.j0n4than.mc.deathswap.commands;

import cn.j0n4than.mc.deathswap.DeathSwap;
import cn.j0n4than.mc.deathswap.actions.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Life was like a box of chocolates
 * you never know what you're going to get.
 *
 * @author jonathan [admin@56fkj.cn]
 */
public class Ds implements CommandExecutor, TabCompleter {

    private final DeathSwap plugin;
    private final List<String> root = Arrays.asList("p1", "p2", "countdown", "readytime", "check", "start", "stop");

    public Ds(DeathSwap plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length < 1 || !root.contains(args[0])) {
            plugin.usage(sender);
            return true;
        }

        DeathSwap.sender = sender;

        //p1
        if (args[0].equals(root.get(0))) {
            return new P1(plugin).run(sender, command, label, args);
        }
        //p2
        if (args[0].equals(root.get(1))) {
            return new P2(plugin).run(sender, command, label, args);
        }
        //countdown
        if (args[0].equals(root.get(2))) {
            return new Countdown(plugin).run(sender, command, label, args);
        }
        //readytime
        if (args[0].equals(root.get(3))) {
            return new ReadyTime(plugin).run(sender, command, label, args);
        }
        //check
        if (args[0].equals(root.get(4))) {
            return new Check(plugin).run(sender, command, label, args);
        }

        //start
        if (args[0].equals(root.get(5))) {
            return new Start(plugin).run(sender, command, label, args);
        }

        //stop
        if (args[0].equals(root.get(6))) {
            return new Stop(plugin).run(sender, command, label, args);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        switch (args.length) {
            case 1:
                return root;
            case 2:
                if (args[0].equals(root.get(2)) || args[0].equals(root.get(3))) {
                    return Collections.singletonList("[sec]");
                }
                return null;
            default:
                return null;
        }
    }
}
