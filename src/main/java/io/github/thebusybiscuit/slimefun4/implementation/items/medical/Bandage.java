package io.github.thebusybiscuit.slimefun4.implementation.items.medical;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.cscorelib2.inventory.ItemUtils;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

/**
 * A {@link Bandage} or Rag is a medical supply which heals the {@link Player} and extinguishes
 * fire.
 * 
 * @author TheBusyBiscuit
 *
 */
public class Bandage extends SimpleSlimefunItem<ItemUseHandler> {

    private final int healingLevel;

    public Bandage(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput, int healingLevel) {
        super(category, item, recipeType, recipe, recipeOutput);

        this.healingLevel = healingLevel;
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Player p = e.getPlayer();

            // Player is neither burning nor injured
            if (p.getFireTicks() <= 0 && p.getHealth() >= p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
                return;
            }

            if (p.getGameMode() != GameMode.CREATIVE) {
                ItemUtils.consumeItem(e.getItem(), false);
            }

            p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, Material.WHITE_WOOL);
            p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, healingLevel));
            p.setFireTicks(0);

            e.cancel();
        };
    }

}
