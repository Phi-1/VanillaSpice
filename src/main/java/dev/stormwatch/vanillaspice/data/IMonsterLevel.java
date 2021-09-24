package dev.stormwatch.vanillaspice.data;

public interface IMonsterLevel {

    void setLevel(int level);
    int getLevel();

    void setXPScalesWithLevel(boolean flag);
    long getXPDrop();

}
