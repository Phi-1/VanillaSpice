package dev.stormwatch.vanillaspice.events;

import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

public class onItemToolTipEvent {

    private static void addPotionChargesTooltip(ItemStack stack, List<ITextComponent> tooltip) {
        CompoundNBT tag = stack.getTag();
        if (tag == null) { return; }
        int charges = tag.getInt("vs_charges");
        if (charges > 0) { tooltip.add(new TranslationTextComponent("vanillaspice.tooltip.potioncharges", charges)); }
    }

    private static void addEffectDescriptions(ItemStack stack, List<ITextComponent> tooltip) {
    }

    public static void event(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof PotionItem) {
            addPotionChargesTooltip(stack, event.getToolTip());
        }
    }

}
