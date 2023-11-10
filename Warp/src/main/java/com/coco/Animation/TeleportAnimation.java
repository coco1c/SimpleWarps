package com.coco.Animation;


import com.coco.WarpMain;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class TeleportAnimation {
    WarpMain main;
    public TeleportAnimation(WarpMain main) {
        this.main = main;
    }
    public void spawnParticles(Player target) {
        if (!main.pluginConfig.isWarpInProgress() ||
                !main.pluginConfig.warpParticleEnabled() ||
                main.pluginConfig.warpParticle().equalsIgnoreCase("NONE") ||
                main.pluginConfig.warpParticleAmount() <= 0 ||
                main.pluginConfig.warpParticleAmount() > 100000 ||
                main.pluginConfig.warpParticle().isEmpty()) {
            main.pluginUtil.sendConsoleMessage("Unable to spawn particles: Invalid configuration");
            return;
        }

        Particle particleType = Particle.valueOf(main.pluginConfig.warpParticle());
        int Y = (int) (target.getLocation().getY() + main.pluginConfig.warpParticleLocationY());
        Location location = new Location(target.getWorld(), target.getLocation().getX(), Y, target.getLocation().getZ());
        World world = target.getWorld();
        world.spawnParticle(particleType, location, main.pluginConfig.warpParticleAmount());
    }

    public void playSound(Player target) {
        if (!main.pluginConfig.isWarpInProgress() ||
                !main.pluginConfig.warpSoundEnabled() ||
                main.pluginConfig.warpSound().equalsIgnoreCase("NONE") ||
                main.pluginConfig.warpSound().isEmpty()) {
            main.pluginUtil.sendConsoleMessage("Unable to play sound: Invalid configuration");
            return;
        }

        Sound soundType = Sound.valueOf(main.pluginConfig.warpSound());
        target.playSound(target.getLocation(), soundType, 1, 1);
    }

}
