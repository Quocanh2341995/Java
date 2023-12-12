package models;

public enum Category {
    HAI("Tiểu thuyết"), KINHDI("Truyện ngắn "), LANGMAN("Trinh thám"),HANHDONG("Trinh thám");
    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
