package io.github.sefiraat.networks.slimefun.tools;

import dev.sefiraat.sefilib.string.TextUtils;
import dev.sefiraat.sefilib.string.Theme;
import io.github.sefiraat.networks.Networks;
import io.github.sefiraat.networks.network.stackcaches.BlueprintInstance;
import io.github.sefiraat.networks.utils.Keys;
import io.github.sefiraat.networks.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.networks.utils.datatypes.PersistentCraftingBlueprintType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import net.guizhanss.guizhanlib.minecraft.helper.inventory.ItemStackHelper;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

public class CraftingBlueprint extends UnplaceableBlock {

    public CraftingBlueprint(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @ParametersAreNonnullByDefault
    public static void setBlueprint(ItemStack blueprint, ItemStack[] recipe, ItemStack output) {
        final ItemMeta itemMeta = blueprint.getItemMeta();
        final ItemMeta outputMeta = output.getItemMeta();
        DataTypeMethods.setCustom(
            itemMeta,
            Keys.BLUEPRINT_INSTANCE,
            PersistentCraftingBlueprintType.TYPE,
            new BlueprintInstance(recipe, output)
        );
        List<String> lore = new ArrayList<>();

        lore.add(Networks.getLanguageManager().getString("misc.assigned-recipe", Theme.CLICK_INFO));

        for (ItemStack item : recipe) {
            if (item == null) {
                lore.add(Networks.getLanguageManager().getString("misc.nothing", Theme.PASSIVE));
                continue;
            }
            lore.add(Theme.PASSIVE + ChatColor.stripColor(ItemStackHelper.getDisplayName(item)));
        }

        lore.add("");
        lore.add(Networks.getLanguageManager().getString("misc.outputting", Theme.CLICK_INFO));

        lore.add(Theme.PASSIVE + ChatColor.stripColor(ItemStackHelper.getDisplayName(output)));

        itemMeta.setLore(lore);

        blueprint.setItemMeta(itemMeta);
    }

}
