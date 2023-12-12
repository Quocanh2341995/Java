package models;

import java.time.LocalDate;

public class Video {
    private long id;
    private String title;
    private String description;
    private Category category;
    private LocalDate dayat;

    public Video(long id, String title, String description, Category category, LocalDate dayat) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.dayat = dayat;
    }

    public Video() {
    }

    public Video(long id, String title, String description, LocalDate dayat) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dayat = dayat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDayat() {
        return dayat;
    }

    public void setDayat(LocalDate dayat) {
        this.dayat = dayat;
    }

    @Override
    public String toString() {
        return id + title + description + category + dayat;
    }
}
