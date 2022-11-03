package net.guizhanss.networks;

import com.bekvon.bukkit.residence.event.ResidenceChangedEvent;
import com.bekvon.bukkit.residence.event.ResidenceCreationEvent;
import com.bekvon.bukkit.residence.event.ResidenceDeleteEvent;
import com.bekvon.bukkit.residence.event.ResidenceFlagChangeEvent;
import com.bekvon.bukkit.residence.event.ResidenceSubzoneCreationEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class ResidenceListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        BlockCache.clearCache(e.getPlayer());
    }

    @EventHandler
    public void onResFlagChange(ResidenceFlagChangeEvent e) {
        BlockCache.clearCache();
    }

    @EventHandler
    public void onResCreate(ResidenceCreationEvent e) {
        BlockCache.clearCache();
    }

    @EventHandler
    public void onResSubCreate(ResidenceSubzoneCreationEvent e) {
        BlockCache.clearCache();
    }

    @EventHandler
    public void onResDelete(ResidenceDeleteEvent e) {
        BlockCache.clearCache();
    }

    @EventHandler
    public void onResChange(ResidenceChangedEvent e) {
        BlockCache.clearCache();
    }
}
