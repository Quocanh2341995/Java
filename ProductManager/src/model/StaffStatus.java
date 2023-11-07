package model;

public enum StaffStatus {
    Active("Hoat dong"), Off("Off");
    private final String value;
    StaffStatus(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
