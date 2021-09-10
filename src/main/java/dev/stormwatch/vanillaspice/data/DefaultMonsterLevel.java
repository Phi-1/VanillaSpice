package dev.stormwatch.vanillaspice.data;

public class DefaultMonsterLevel implements IMonsterLevel {

    private int level;

    @Override
    public void setLevel(int level) { this.level = level; }
    @Override
    public int getLevel() { return this.level; }

}
