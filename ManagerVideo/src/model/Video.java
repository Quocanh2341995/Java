package model;

import java.time.LocalDate;

public class Video {
    private long id;
    private String title;
    private String description;
    private Category category;
    private long categoryId;
    private LocalDate dayAt;

    public Video() {
    }


    public Video(long id, String title, String description, Category category, long categoryId, LocalDate dayAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.categoryId = categoryId;
        this.dayAt = dayAt;
    }

    public Video(long id, String title, String description, long categoryId, LocalDate dayAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
        this.dayAt = dayAt;
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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDate getDayAt() {
        return dayAt;
    }

    public void setDayAt(LocalDate dayAt) {
        this.dayAt = dayAt;
    }

    @Override
    public String toString() {
        return id + title + description + categoryId + dayAt;
    }
}
