package cn.j0n4than.mc.deathswap.task;

import cn.j0n4than.mc.deathswap.DeathSwap;
import cn.j0n4than.mc.deathswap.actions.Start;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Life was like a box of chocolates
 * you never know what you're going to get.
 *
 * @author jonathan [admin@56fkj.cn]
 */
public class ReadyTask extends BukkitRunnable {

    private final DeathSwap plugin;

    private int countdown;

    public ReadyTask(DeathSwap plugin, int countdown) {
        this.plugin = plugin;
        this.countdown = countdown;

        DeathSwap.readyBar.addPlayer(DeathSwap.playerOne);
        DeathSwap.readyBar.addPlayer(DeathSwap.playerTwo);
    }

    @Override
    public void run() {
        if (this.countdown <= 0) {

            this.cancel();

            if (this.isCancelled()) {
                //start game

                DeathSwap.readyBar.removeAll();

                DeathSwap.playerOne.sendTitle(plugin.lang("bar_start"), null, 10, 20, 10);
                DeathSwap.playerTwo.sendTitle(plugin.lang("bar_start"), null, 10, 20, 10);

                DeathSwap.startBar.addPlayer(DeathSwap.playerOne);
                DeathSwap.startBar.addPlayer(DeathSwap.playerTwo);

                DeathSwap.swapTask = new SwapTask(this.plugin, DeathSwap.countdown);
                DeathSwap.swapTask.runTaskTimer(this.plugin, 0, 20);
            }

        }

        DeathSwap.readyBar.setProgress((double) countdown / DeathSwap.readyTime);
        DeathSwap.readyBar.setTitle(plugin.lang("bar_ready_time").replace("%sec%", String.valueOf(this.countdown)));
        this.countdown--;
    }
}
