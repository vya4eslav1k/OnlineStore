package enums;

public enum PromoCodes {
    WELCOME10(10),
    VIP50(50);

    private final double discount;

    PromoCodes(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
