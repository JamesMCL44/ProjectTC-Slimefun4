package io.github.thebusybiscuit.slimefun4.implementation.items.backpacks;

import org.bukkit.Sound;
import org.bukkit.block.EnderChest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

/**
 * The {@link EnderBackpack} is a pretty simple {@link SlimefunItem} which opens your
 * {@link EnderChest} upon right clicking.
 * 
 * @author TheBusyBiscuit
 *
 */
public class EnderBackpack extends SimpleSlimefunItem<ItemUseHandler> implements NotPlaceable {

    public EnderBackpack(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Player p = e.getPlayer();
            p.openInventory(p.getEnderChest());
            p.playSound(p.getEyeLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 1F);
            e.cancel();
        };
    }
}
