package service;

import model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private static List<Category> categories = new ArrayList<>();

    static {
        categories.add(new Category(1, "Coffee"));
        categories.add(new Category(2, "Tra"));
        categories.add(new Category(3, "Nuoc ngot"));

    }

    public List<Category> findAll() {
        return categories;
    }

    public Category findById(int id) {
        return categories.stream().filter(e -> e.getId() == id).findFirst().orElse(new Category(5, "Nc chai"));
    }
}
