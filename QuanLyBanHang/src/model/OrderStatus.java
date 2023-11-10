package model;

public enum OrderStatus {
    PAID("Da thanh toan"), PROCESSING("Dang xu ly"), REFUND("Tra hang");
    private final String value;
    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
