package cn.j0n4than.mc.deathswap.task;

import cn.j0n4than.mc.deathswap.DeathSwap;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Life was like a box of chocolates
 * you never know what you're going to get.
 *
 * @author jonathan [admin@56fkj.cn]
 */
public class SwapTask extends BukkitRunnable {

    private final DeathSwap plugin;
    private int countdown;

    public SwapTask(DeathSwap plugin, int count) {
        this.plugin = plugin;
        this.countdown = count;
    }

    @Override
    public void run() {

        if (this.countdown <= 0) {

            DeathSwap.sender.sendMessage(plugin.lang("start_swap"));
            DeathSwap.playerOne.sendMessage(plugin.lang("start_swap"));
            DeathSwap.playerTwo.sendMessage(plugin.lang("start_swap"));

            Location playerOneLocation = DeathSwap.playerOne.getLocation();
            DeathSwap.playerOne.teleport(DeathSwap.playerTwo.getLocation());
            DeathSwap.playerTwo.teleport(playerOneLocation);

            this.countdown = DeathSwap.countdown;
        }

        DeathSwap.startBar.setProgress((double) countdown / DeathSwap.countdown);
        DeathSwap.startBar.setTitle(plugin.lang("bar_start_time").replace("%sec%", String.valueOf(this.countdown)));

        this.countdown--;
    }
}
