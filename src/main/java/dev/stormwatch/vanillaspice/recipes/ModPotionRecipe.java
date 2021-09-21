package dev.stormwatch.vanillaspice.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

public class ModPotionRecipe implements IBrewingRecipe {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Item ingredient;
    private final Potion input;
    private final Potion output;

    public ModPotionRecipe(Item ingredient, Potion input, Potion output) {
        this.ingredient = ingredient;
        this.input = input;
        this.output = output;
    }

    @Override
    public boolean isInput(@Nonnull ItemStack input) {
        return PotionUtils.getPotion(input) == this.input;
    }

    @Override
    public boolean isIngredient(@Nonnull ItemStack ingredient) {
        return ingredient.getItem() == this.ingredient;
    }

    @Nonnull
    @Override
    public ItemStack getOutput(@Nonnull ItemStack input, @Nonnull ItemStack ingredient) {
        if (isInput(input) && isIngredient(ingredient)) {
            ItemStack output = PotionUtils.setPotion(new ItemStack(Items.POTION), this.output);
            return output;
        }
        return ItemStack.EMPTY;
    }
}
