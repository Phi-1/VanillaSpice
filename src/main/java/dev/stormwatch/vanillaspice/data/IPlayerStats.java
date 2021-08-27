package dev.stormwatch.vanillaspice.data;

public interface IPlayerStats {

    boolean setMeleeXP(int XP);
    int getMeleeXP();

    boolean setArcheryXP(int XP);
    int getArcheryXP();

    boolean setAlchemyXP(int XP);
    int getAlchemyXP();

    void setMeleeLevel(int level);
    int getMeleeLevel();

    void setArcheryLevel(int level);
    int getArcheryLevel();

    void setAlchemyLevel(int level);
    int getAlchemyLevel();

    void setMeleeTier(int tier);
    int getMeleeTier();

    void setArcheryTier(int tier);
    int getArcheryTier();

    void setAlchemyTier(int tier);
    int getAlchemyTier();

}