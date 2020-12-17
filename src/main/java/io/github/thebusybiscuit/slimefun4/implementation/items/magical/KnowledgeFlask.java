package io.github.thebusybiscuit.slimefun4.implementation.items.magical;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.inventory.ItemUtils;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

/**
 * The {@link KnowledgeFlask} is a magical {@link SlimefunItem} which allows you to store
 * experience levels in a bottle when you right click.
 * 
 * @author TheBusyBiscuit
 *
 */
public class KnowledgeFlask extends SimpleSlimefunItem<ItemUseHandler> {

    public KnowledgeFlask(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
        super(category, item, recipeType, recipe, recipeOutput);
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Player p = e.getPlayer();

            if (p.getLevel() >= 1 && (!e.getClickedBlock().isPresent() || !(e.getClickedBlock().get().getType().isInteractable()))) {
                p.setLevel(p.getLevel() - 1);
                p.getInventory().addItem(SlimefunItems.FILLED_FLASK_OF_KNOWLEDGE.clone());

                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 0.5F);

                ItemUtils.consumeItem(e.getItem(), false);
                e.cancel();
            }
        };
    }

}
