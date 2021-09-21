package dev.stormwatch.vanillaspice.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public class ModifierUtil {

    private static final UUID MODIFIER_ID_PLAYER_BASE_HEALTH = UUID.fromString("ec579d5e-c3aa-44fe-a7b8-5e50c5c478cb");
    private static final String MODIFIER_NAME_PLAYER_BASE_HEALTH = "vanillaspice_player_base_health";

    private static final UUID MODIFIER_ID_MAINLEVEL_HEALTH = UUID.fromString("0c16f844-a727-40e4-ad89-107fb2babfb5");
    private static final String MODIFIER_NAME_MAINLEVEL_HEALTH = "vanillaspice_mainlevel_health";
    private static final UUID MODIFIER_ID_MAINLEVEL_TOUGHNESS = UUID.fromString("04d1393b-8ef0-42bc-848d-8639869a5d1a");
    private static final String MODIFIER_NAME_MAINLEVEL_TOUGHNESS = "vanillaspice_mainlevel_toughness";

    public static final UUID MODIFIER_ID_HEALTH = UUID.fromString("b59043e4-f166-4ade-bc92-cad5f2d6dee4");
    public static final String MODIFIER_NAME_HEALTH = "vanillaspice_health";
    public static final UUID MODIFIER_ID_ARMOR = UUID.fromString("4d038472-e2bb-46f6-b424-ef33713997f1");
    public static final String MODIFIER_NAME_ARMOR = "vanillaspice_armor";
    public static final UUID MODIFIER_ID_ATTACK = UUID.fromString("a0c8c889-ae5b-468b-9535-3fe638e63bd2");
    public static final String MODIFIER_NAME_ATTACK = "vanillaspice_attack";
    public static final UUID MODIFIER_ID_SPEED = UUID.fromString("cc627a76-ef4b-466c-82a2-b2f197397983");
    public static final String MODIFIER_NAME_SPEED = "vanillaspice_speed";
    public static final UUID MODIFIER_ID_RANGE = UUID.fromString("721f214d-3f0f-4701-821a-27e7d4373513");
    public static final String MODIFIER_NAME_RANGE = "vanillaspice_range";
    public static final UUID MODIFIER_ID_REACH = UUID.fromString("447fedfe-238d-4b65-a9c7-20d3e1ffab36");
    public static final String MODIFIER_NAME_REACH = "vanillaspice_reach";
    public static final UUID MODIFIER_ID_SWIM = UUID.fromString("8bf6cbc4-d494-4828-9550-432cdcb20a04");
    public static final String MODIFIER_NAME_SWIM = "vanillaspice_swim";
    public static final UUID MODIFIER_ID_KNOCKBACK = UUID.fromString("f2680616-f69c-4e0c-b482-1849ca62be9d");
    public static final String MODIFIER_NAME_KNOCKBACK = "vanillaspice_knockback";
    public static final UUID MODIFIER_ID_KNOCKBACKRES = UUID.fromString("0c473d78-d677-47d7-a367-5dcdbf42d066");
    public static final String MODIFIER_NAME_KNOCKBACKRES = "vanillaspice_knockbackres";
    public static final UUID MODIFIER_ID_ATTACKSPEED = UUID.fromString("d8c8aae0-d965-415b-8355-f58c9a176ea7");
    public static final String MODIFIER_NAME_ATTACKSPEED = "vanillaspice_attackspeed";
    public static final UUID MODIFIER_ID_LUCK = UUID.fromString("be3de969-2f27-493b-bf51-5cfe5220b27d");
    public static final String MODIFIER_NAME_LUCK = "vanillaspice_luck";


    private static void setModifier(LivingEntity entity, Attribute attribute, UUID id, String name, double amount, AttributeModifier.Operation operation) {
        ModifiableAttributeInstance instance = entity.getAttribute(attribute);
        if (instance == null) { return; }
        AttributeModifier modifier = instance.getModifier(id);
        if (modifier != null) { instance.removeModifier(modifier); }
        instance.addPermanentModifier(new AttributeModifier(id, name, amount, operation));
    }

    private static void setMainLevelMaxHealth(LivingEntity entity, double amount) {
        setModifier(entity, Attributes.MAX_HEALTH, MODIFIER_ID_MAINLEVEL_HEALTH, MODIFIER_NAME_MAINLEVEL_HEALTH, amount, AttributeModifier.Operation.ADDITION);
    }

    private static void setMainLevelToughness(LivingEntity entity, double amount) {
        setModifier(entity, Attributes.ARMOR_TOUGHNESS, MODIFIER_ID_MAINLEVEL_TOUGHNESS, MODIFIER_NAME_MAINLEVEL_TOUGHNESS, amount, AttributeModifier.Operation.ADDITION);
    }

    public static void setPlayerBaseHealth(LivingEntity entity) {
        setModifier(entity, Attributes.MAX_HEALTH, MODIFIER_ID_PLAYER_BASE_HEALTH, MODIFIER_NAME_PLAYER_BASE_HEALTH, -0.6, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    public static void setMainLevelStats(LivingEntity entity, int playerLevel) {
        setMainLevelMaxHealth(entity, playerLevel);
        setMainLevelToughness(entity, playerLevel);
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

    public static void setKnockback(LivingEntity entity, double amount) {
        setModifier(entity, Attributes.ATTACK_KNOCKBACK, MODIFIER_ID_KNOCKBACK, MODIFIER_NAME_KNOCKBACK, amount, AttributeModifier.Operation.ADDITION);
    }

    public static void setAttackDamage(LivingEntity entity, double amount) {
        // Adds or subtracts given amount times the base value of the stat (0.6 will add 60%, -0.3 will subtract 30%)
        setModifier(entity, Attributes.ATTACK_DAMAGE, MODIFIER_ID_ATTACK, MODIFIER_NAME_ATTACK, amount, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    public static void setSpeed(LivingEntity entity, double amount) {
        setModifier(entity, Attributes.MOVEMENT_SPEED, MODIFIER_ID_SPEED, MODIFIER_NAME_SPEED, amount, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    public static void setRange(LivingEntity entity, double amount) {
        setModifier(entity, Attributes.FOLLOW_RANGE, MODIFIER_ID_RANGE, MODIFIER_NAME_RANGE, amount, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    public static void setReach(LivingEntity entity, double amount) {
        setModifier(entity, ForgeMod.REACH_DISTANCE.get(), MODIFIER_ID_REACH, MODIFIER_NAME_REACH, amount, AttributeModifier.Operation.MULTIPLY_BASE);
    }

}
