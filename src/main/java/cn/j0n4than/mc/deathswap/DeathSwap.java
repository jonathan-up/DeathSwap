package cn.j0n4than.mc.deathswap;

import cn.j0n4than.mc.deathswap.commands.Ds;
import cn.j0n4than.mc.deathswap.listeners.PlayerListener;
import cn.j0n4than.mc.deathswap.task.ReadyTask;
import cn.j0n4than.mc.deathswap.task.SwapTask;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

/**
 * Life was like a box of chocolates
 * you never know what you're going to get.
 *
 * @author jonathan [admin@56fkj.cn]
 */
public class DeathSwap extends JavaPlugin {

    /**
     * is game start?
     */
    public static boolean isStart = false;

    /**
     * p1
     */
    public static Player playerOne = null;

    /**
     * p2
     */
    public static Player playerTwo = null;

    /**
     * sender, person last used ds command
     */
    public static CommandSender sender = null;

    /**
     * countdown
     */
    public static int countdown = 300;

    /**
     * ready time
     */
    public static int readyTime = 1;

    /**
     * waiting boss bar
     */
    public static BossBar waitingBar;

    /**
     * ready boss bar
     */
    public static BossBar readyBar;

    /**
     * start boss bar
     */
    public static BossBar startBar;

    /**
     * ready task
     */
    public static ReadyTask readyTask;

    /**
     * swap task
     */
    public static SwapTask swapTask;

    @Override
    public void onEnable() {
        this.getLogger().info("A game for spigot server");
        this.getLogger().info("Made by Jonathan with love");
        this.saveDefaultConfig();

        DeathSwap.waitingBar = Bukkit.createBossBar(this.lang("bar_waiting_start"), BarColor.GREEN, BarStyle.SEGMENTED_20);
        DeathSwap.readyBar = Bukkit.createBossBar(this.lang("bar_ready"), BarColor.PINK, BarStyle.SEGMENTED_20);
        DeathSwap.startBar = Bukkit.createBossBar(this.lang("bar_start"), BarColor.RED, BarStyle.SEGMENTED_20);

        Objects.requireNonNull(this.getCommand("ds")).setExecutor(new Ds(this));
        this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    @Override
    public void onDisable() {

        DeathSwap.isStart = false;

        DeathSwap.waitingBar.removeAll();
        DeathSwap.readyBar.removeAll();
        DeathSwap.startBar.removeAll();
    }

    /**
     * helper
     *
     * @param sender sender
     */
    public void usage(CommandSender sender) {
        sender.sendMessage(this.lang("usage"));
        sender.sendMessage("/ds p1 [player] " + this.lang("usage_ds_p1"));
        sender.sendMessage("/ds p2 [player] " + this.lang("usage_ds_p1"));
        sender.sendMessage("/ds readytime [sec] " + this.lang("usage_ds_ready_time"));
        sender.sendMessage("/ds countdown [sec] " + this.lang("usage_ds_countdown"));
        sender.sendMessage("/ds check " + this.lang("usage_ds_check"));
        sender.sendMessage("/ds start " + this.lang("usage_ds_start"));
        sender.sendMessage("/ds stop " + this.lang("usage_ds_start"));
    }

    /**
     * language
     *
     * @param path path
     *
     * @return String
     */
    public String lang(String path) {
        return this.getConfig().getString("lang." + path) == null ? path
                : this.getConfig().getString("lang." + path);
    }
}
