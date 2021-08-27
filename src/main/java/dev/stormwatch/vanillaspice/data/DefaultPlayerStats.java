package dev.stormwatch.vanillaspice.data;

import java.lang.Math;

public class DefaultPlayerStats implements IPlayerStats {

    // Level formula: 2 * currentLevel^3 / 5
    // One tier up every 10 levels, at max tiers level just keeps going past 10

    private int meleeXP;
    private int archeryXP;
    private int alchemyXP;

    private int meleeLevel;
    private int archeryLevel;
    private int alchemyLevel;

    private int meleeTier;
    private int archeryTier;
    private int alchemyTier;

    private int nextLevelThreshold(int currentLevel) {
        double threshold = ((2 * Math.pow(currentLevel, 3)) / 5) + 10;
        return Math.round((float) threshold);
    }

    @Override
    public boolean setMeleeXP(int XP) {
        this.meleeXP = XP;
        int nextlevelthreshold = nextLevelThreshold(this.meleeLevel);
        if (this.meleeXP >= nextlevelthreshold) {
            this.meleeXP -= nextlevelthreshold;
            this.meleeLevel++;
            return true;
        }
        return false;
    }
    @Override
    public int getMeleeXP() { return this.meleeXP; }

    @Override
    public boolean setArcheryXP(int XP) {
        this.archeryXP = XP;
        int nextlevelthreshold = nextLevelThreshold(this.archeryLevel);
        if (this.archeryXP >= nextlevelthreshold) {
            this.archeryXP -= nextlevelthreshold;
            this.archeryLevel++;
            return true;
        }
        return false;
    }
    @Override
    public int getArcheryXP() { return this.archeryXP; }

    @Override
    public boolean setAlchemyXP(int XP) {
        this.alchemyXP = XP;
        int nextlevelthreshold = nextLevelThreshold(this.alchemyLevel);
        if (this.alchemyXP >= nextlevelthreshold) {
            this.alchemyXP -= nextlevelthreshold;
            this.alchemyLevel++;
            return true;
        }
        return false;
    }
    @Override
    public int getAlchemyXP() { return this.alchemyXP; }


    @Override
    public void setMeleeLevel(int level) { this.meleeLevel = level; }
    @Override
    public int getMeleeLevel() { return this.meleeLevel; }

    @Override
    public void setArcheryLevel(int level) { this.archeryLevel = level; }
    @Override
    public int getArcheryLevel() { return this.archeryLevel; }

    @Override
    public void setAlchemyLevel(int level) { this.alchemyLevel = level; }
    @Override
    public int getAlchemyLevel() { return this.alchemyLevel; }


    @Override
    public void setMeleeTier(int tier) { this.meleeTier = tier; }
    @Override
    public int getMeleeTier() { return this.meleeTier; }

    @Override
    public void setArcheryTier(int tier) { this.archeryTier = tier; }
    @Override
    public int getArcheryTier() { return this.archeryTier; }

    @Override
    public void setAlchemyTier(int tier) { this.alchemyTier = tier; }
    @Override
    public int getAlchemyTier() { return this.alchemyTier; }

}