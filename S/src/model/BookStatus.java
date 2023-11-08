package model;

public enum BookStatus {
    INSTOCK("INSTOCK"),
    OUTOFSTOCK("OUTOFSTOCK");

    private final String value;

    private BookStatus(String value) {
        this.value = value;
    }
}
