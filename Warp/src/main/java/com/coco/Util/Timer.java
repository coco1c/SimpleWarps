package com.coco.Util;

import com.coco.WarpMain;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {
    private WarpMain plugin;
    BukkitRunnable task = null;

    public Timer(WarpMain plugin) {
        this.plugin = plugin;
    }

    public void startTimer(int cooldown, Runnable oneTimeAction, Runnable everySecondAction) {
        task = new BukkitRunnable() {
            int secondsLeft = cooldown;

            @Override
            public void run() {
                if (secondsLeft <= 0) {
                    oneTimeAction.run();
                    this.cancel();
                    return;
                }
                everySecondAction.run();
                secondsLeft--;
            }
        };

        task.runTaskTimer(plugin, 0L, 20L);
    }

    public void startTimer(int delayInSeconds, Runnable action) {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                action.run();
            }
        };

        task.runTaskLater(plugin, delayInSeconds * 20);
    }
    public void cancelTimer() {
        if (task != null && !task.isCancelled()) {
            task.cancel();
        }
    }
}
