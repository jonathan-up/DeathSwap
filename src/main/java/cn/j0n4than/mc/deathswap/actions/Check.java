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
public class Check implements Action {

    private final DeathSwap plugin;

    public Check(DeathSwap plugin) {
        this.plugin = plugin;
    }

    public boolean run(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage(plugin.lang("check_player_in_game"));
        sender.sendMessage("    " +
                plugin.lang("check_player_one")
                        .replace("%player%", DeathSwap.playerOne == null ? plugin.lang("check_null") : DeathSwap.playerOne.getName())
        );

        sender.sendMessage("    " +
                plugin.lang("check_player_two")
                        .replace("%player%", DeathSwap.playerTwo == null ? plugin.lang("check_null") : DeathSwap.playerTwo.getName())
        );

        sender.sendMessage(plugin.lang("check_game_status") + (DeathSwap.isStart ? plugin.lang("check_status_started") : plugin.lang("check_status_waiting")));

        sender.sendMessage(plugin.lang("check_ready_time").replace("%s%", String.valueOf(DeathSwap.readyTime)));
        sender.sendMessage(plugin.lang("check_countdown").replace("%s%", String.valueOf(DeathSwap.countdown)));
        return true;
    }
}
