package dev.stormwatch.vanillaspice.items;

import dev.stormwatch.vanillaspice.lib.Classes;
import dev.stormwatch.vanillaspice.util.XPUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class VisionItem extends Item {

    private final Classes type;
    private final int tier;

    public VisionItem(Item.Properties properties, Classes type, int tier) {
        super(properties);
        this.type = type;
        this.tier = tier;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        boolean success = false;
        if (!world.isClientSide()) {
            switch (this.type.ordinal()) {
                case 0:
                    success = XPUtil.increaseMeleeTier(player, this.tier);
                    break;
                case 1:
                    success = XPUtil.increaseArcheryTier(player, this.tier);
                    break;
                case 2:
                    success = XPUtil.increaseAlchemyTier(player, this.tier);
                    break;
            }
        }
        if (success) {
            player.getItemInHand(hand).shrink(1);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WITHER_SPAWN, SoundCategory.PLAYERS, 1F, 1F);
        }
        return ActionResult.success(player.getItemInHand(hand));
    }

}

