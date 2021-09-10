package dev.stormwatch.vanillaspice.data;

public class DefaultPotionCharges implements IPotionCharges {

    private int charges;

    @Override
    public void setCharges(int amount) {
        this.charges = amount;
    }

    @Override
    public int getCharges() {
        return this.charges;
    }

}
