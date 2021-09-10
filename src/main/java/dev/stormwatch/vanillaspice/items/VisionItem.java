package dev.stormwatch.vanillaspice.items;

import dev.stormwatch.vanillaspice.lib.Visions;
import dev.stormwatch.vanillaspice.util.XPUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class VisionItem extends Item {

    private final String type;

    public VisionItem(Item.Properties properties, String type) {
        super(properties);
        this.type = type;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClientSide()) {
            XPUtil.handleVisionUse(player, this.type);
        }
        return ActionResult.success(player.getItemInHand(hand));
    }

}

