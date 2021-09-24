package dev.stormwatch.vanillaspice.data;

public class DefaultMonsterLevel implements IMonsterLevel {

    private int level;
    boolean xpScalesWithLevel = true;

    @Override
    public void setLevel(int level) { this.level = level; }
    @Override
    public int getLevel() { return this.level; }

    @Override
    public void setXPScalesWithLevel(boolean flag) { this.xpScalesWithLevel = flag; }

    @Override
    public long getXPDrop() {
        if (!this.xpScalesWithLevel) { return (long) this.level; }
        long xpGain = 0;
        int ring = (int) Math.ceil((double) this.level / 4);
        int rarity = this.level % 4; // 1, 2, 3, 0
        if (rarity == 1) {
            if (ring == 1) { xpGain = 10; }
            else { xpGain = 10 * (long) Math.pow(ring + 1, 2); }
        } else if (rarity == 2) {
            if (ring == 1) { xpGain = 20; }
            else { xpGain = 20 * (long) Math.pow(ring + 1, 2); }
        } else if (rarity == 3) {
            if (ring == 1) { xpGain = 80; }
            else { xpGain = 80 * (long) Math.pow(ring + 1, 2); }
        } else if (rarity == 0 && this.level > 0) {
            if (ring == 1) { xpGain = 160; }
            else { xpGain = 160 * (long) Math.pow(ring + 1, 2); }
        } else {
            xpGain = 10 * (long) Math.pow(ring + 1, 2);
        }

        return xpGain;
    }

}
