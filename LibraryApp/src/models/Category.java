package models;

public enum Category {
    NOVEL("Tiểu thuyết"), SHORT("Truyện ngắn "), DETECTIVE("Trinh thám"), COMICS("Truyện cười"), HORROR ("Kinh dị");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
