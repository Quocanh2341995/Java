package service;

import model.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private static List<Category> categories = new ArrayList<>();
    private static int currentId = 0;

    static {
        categories.add(new Category(1, "Coffee"));
        categories.add(new Category(2, "Tra"));
        categories.add(new Category(3, "Nuoc ngot"));

        writeFile();

    }

    public List<Category> findAll() {

        return categories;
    }

    public Category findById(int id) {
        return categories.stream().filter(e -> e.getId() == id).findFirst().orElse(new Category(5, "Nuoc chai"));
    }
    private static void readData() {
        try{
            File fileWriter = new File("data/category.txt");
            FileReader fileReader = new FileReader(fileWriter);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null && !line.equals("")){
                String[] data = line.split(",");
                currentId = Integer.parseInt(data[0]);
                Category category = new Category(
                        Integer.parseInt(data[0]),
                        data[1]
                );
                categories.add(category);

                line = reader.readLine();
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    private static void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/category.txt"));
            for (var category: categories) {
                writer.write(category.toString());
                writer.newLine();
            }

            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
