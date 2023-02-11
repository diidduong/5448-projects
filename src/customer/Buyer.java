package customer;

public class Buyer {
    public enum BuyingType {
        JUST_LOOKING(0.1), WANTS_ONE(0.4), NEEDS_ONE(0.7);

        private final double probability;

        BuyingType(double probability) {
            this.probability = probability;
        }

        public double getProbability() {
            return probability;
        }
    }

    private BuyingType buyingType;
    private String wantedType;

    public BuyingType getBuyingType() {
        return buyingType;
    }

    public void setBuyingType(BuyingType buyingType) {
        this.buyingType = buyingType;
    }

    public String getWantedType() {
        return wantedType;
    }

    public void setWantedType(String wantedType) {
        this.wantedType = wantedType;
    }
}
