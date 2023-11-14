package model;

public enum Role {
    ADMIN("Admin"), CLIENT("Khach hang");
    private final String value;
    Role (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static boolean fromValue(String value) {
        Role[] values = values();
        for (Role type : values) {
            if (type.value.equals(value))
                return true;
        }
        return false;
    }
}
