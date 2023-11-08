package model;

public enum BorrowerStatus {
    PAY("Pay"),
    BORRWED("Borrowed"),
    OUTOFDATE("Out of date");
    private final String value;

    BorrowerStatus(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
