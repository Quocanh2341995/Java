package models;

public enum Status {

    INSTOCK("INSTOCK"),
    OUTOFSTOCK("OUTOFSTOCK");

    private final String value;

    private Status(String value) {
        this.value = value;
    }

}
