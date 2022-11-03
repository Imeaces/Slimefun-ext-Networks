package net.guizhanss.networks;

import io.github.sefiraat.networks.Networks;
import org.bukkit.event.Listener;

import javax.annotation.ParametersAreNonnullByDefault;

public final class ResidenceHook {

    private final Networks plugin;

    @ParametersAreNonnullByDefault
    public ResidenceHook(Networks plugin) {
        this.plugin = plugin;
        if (plugin.getServer().getPluginManager().isPluginEnabled("Residence")) {
            BlockCache.setCache(true);
            plugin.getLogger().info("Residence 已启用，将开启离线玩家权限缓存");
            addListener(new ResidenceListener());
        } else {
            BlockCache.setCache(false);
            plugin.getLogger().info("Residence 未启用，将不会启用相关功能");
        }
    }

    @ParametersAreNonnullByDefault
    private void addListener(Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }
}
