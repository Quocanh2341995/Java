package model;

public enum Category {
    LAMP("Den"),FAN("Quat"),WIRE("Day Dien"),SOCKETS("O Cam");
    private final String value;
    Category (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
