package net.guizhanss.networks;

import dev.sefiraat.netheopoiesis.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;

/**
 * 方块缓存
 *
 * @author ybw0014
 */
public final class BlockCache {
    private static final HashMap<OfflinePlayer, HashMap<BlockPosition, Boolean>> queryCache = new HashMap<>();
    private static boolean disableCache = false;

    /**
     * 查询玩家是否拥有方块交互权限
     *
     * @param player   玩家
     * @param location 方块位置
     *
     * @return 是否拥有权限
     */
    @ParametersAreNonnullByDefault
    public static boolean canInteract(OfflinePlayer player, Location location) {
        if (disableCache || player.isOnline()) {
            // 玩家在线，直接调用保护查询结果
            return Slimefun.getProtectionManager().hasPermission(player, location, Interaction.INTERACT_BLOCK);
        } else {
            // 玩家离线，查询缓存
            BlockPosition pos = new BlockPosition(location);
            HashMap<BlockPosition, Boolean> records = queryCache.get(player);

            if (records == null) {
                records = new HashMap<>();
                queryCache.put(player, records);
            } else if (records.containsKey(pos)) {
                return records.get(pos);
            }

            boolean result = Slimefun.getProtectionManager().hasPermission(player, location, Interaction.INTERACT_BLOCK);
            records.put(pos, result);
            return result;
        }
    }

    /**
     * 设置是否启用缓存
     * @param enable 是否启用缓存
     */
    static void setCache(boolean enable) {
        disableCache = !enable;
    }

    /**
     * 清除玩家对某个方块权限的缓存
     *
     * @param player   玩家
     * @param location 方块位置
     */
    @ParametersAreNonnullByDefault
    static void clearCache(OfflinePlayer player, Location location) {
        HashMap<BlockPosition, Boolean> records = queryCache.get(player);

        if (records != null) {
            records.remove(new BlockPosition(location));
        }
    }

    /**
     * 清除玩家的所有权限缓存
     *
     * @param player 玩家
     */
    @ParametersAreNonnullByDefault
    static void clearCache(OfflinePlayer player) {
        queryCache.remove(player);
    }

    /**
     * 清除所有玩家的权限缓存
     */
    @ParametersAreNonnullByDefault
    static void clearCache() {
        queryCache.clear();
    }
}
