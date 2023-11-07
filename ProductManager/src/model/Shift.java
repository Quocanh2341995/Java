package model;

public enum Shift {
    Morning("Buoi sang"), Afternoon("Buoi chieu"), Night("Buoi toi");
    private final String value;
    Shift(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
