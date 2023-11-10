package model;

public enum Status {
    INSTOCK("Con hang"),
    OUTOFSTOCK("Het hang");

    private final String value;

    private Status(String value) {
        this.value = value;
    }
}
