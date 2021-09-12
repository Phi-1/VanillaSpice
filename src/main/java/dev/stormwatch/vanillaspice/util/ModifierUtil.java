package dev.stormwatch.vanillaspice.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class ModifierUtil {

    private static final UUID MODIFIER_ID_HEALTH = UUID.fromString("b59043e4-f166-4ade-bc92-cad5f2d6dee4");
    private static final String MODIFIER_NAME_HEALTH = "vanillaspice_health";
    private static final UUID MODIFIER_ID_ARMOR = UUID.fromString("4d038472-e2bb-46f6-b424-ef33713997f1");
    private static final String MODIFIER_NAME_ARMOR = "vanillaspice_armor";
    private static final UUID MODIFIER_ID_ATTACK = UUID.fromString("a0c8c889-ae5b-468b-9535-3fe638e63bd2");
    private static final String MODIFIER_NAME_ATTACK = "vanillaspice_attack";
    private static final UUID MODIFIER_ID_SPEED = UUID.fromString("cc627a76-ef4b-466c-82a2-b2f197397983");
    private static final String MODIFIER_NAME_SPEED = "vanillaspice_speed";
    private static final UUID MODIFIER_ID_RANGE = UUID.fromString("721f214d-3f0f-4701-821a-27e7d4373513");
    private static final String MODIFIER_NAME_RANGE = "vanillaspice_range";


    private static void setModifier(LivingEntity entity, Attribute attribute, UUID id, String name, double amount, AttributeModifier.Operation operation) {
        ModifiableAttributeInstance instance = entity.getAttribute(attribute);
        if (instance == null) { return; }
        AttributeModifier modifier = instance.getModifier(id);
        if (modifier != null) { instance.removeModifier(modifier); }
        instance.addPermanentModifier(new AttributeModifier(id, name, amount, operation));
    }

    public static void setMaxHealth(LivingEntity entity, double amount) {
        // Adds or subtracts given amount times the base value of the stat (0.6 will add 60%, -0.3 will subtract 30%)
        setModifier(entity, Attributes.MAX_HEALTH, MODIFIER_ID_HEALTH, MODIFIER_NAME_HEALTH, amount, AttributeModifier.Operation.MULTIPLY_BASE);
        float modifiedMaxHP = entity.getMaxHealth();
        // Players aren't healed if their new max hp is greater than their initial
        if (entity instanceof PlayerEntity) {
            if (entity.getHealth() > modifiedMaxHP) { entity.setHealth(modifiedMaxHP); }
        } else {
            entity.setHealth(modifiedMaxHP);
        }
    }

    public static void setArmor(LivingEntity entity, double amount) {
        // 1 per extra half armor point
        setModifier(entity, Attributes.ARMOR, MODIFIER_ID_ARMOR, MODIFIER_NAME_ARMOR, amount, AttributeModifier.Operation.ADDITION);
    }

    public static void setAttackDamage(LivingEntity entity, double amount) {
        // Adds or subtracts given amount times the base value of the stat (0.6 will add 60%, -0.3 will subtract 30%)
        setModifier(entity, Attributes.ATTACK_DAMAGE, MODIFIER_ID_ATTACK, MODIFIER_NAME_ATTACK, amount, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    public static void setSpeed(LivingEntity entity, double amount) {
        setModifier(entity, Attributes.MOVEMENT_SPEED, MODIFIER_ID_SPEED, MODIFIER_NAME_SPEED, amount, AttributeModifier.Operation.MULTIPLY_BASE);
    }

}
