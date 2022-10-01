package cn.j0n4than.mc.deathswap.interfaces;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Life was like a box of chocolates
 * you never know what you're going to get.
 *
 * @author jonathan [admin@56fkj.cn]
 */
public interface Action {

    /**
     * Run
     *
     * @param sender  CommandSender
     * @param command command
     * @param label   label
     * @param args    args
     *
     * @return boole
     */
    boolean run(CommandSender sender, Command command, String label, String[] args);
}
