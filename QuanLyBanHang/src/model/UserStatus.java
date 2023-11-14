package model;

public enum UserStatus {
    ACTIVE("Hoat dong"), OFF("Khong hoat dong");

    private final String value;
    UserStatus (String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

}
