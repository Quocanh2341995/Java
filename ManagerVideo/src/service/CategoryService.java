package service;

import model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private static List<Category> categories = new ArrayList<>();

    static {
        categories.add(new Category(1, "Hai"));
        categories.add(new Category(2, "Kinh di"));
        categories.add(new Category(3, "Lang man"));
        categories.add(new Category(4, "Hanh dong"));

    }

    public List<Category> findAll() {
        return categories;
    }

    public Category findById(int id) {
        return categories.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }
}
