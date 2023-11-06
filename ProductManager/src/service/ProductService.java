package service;

import model.Product;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

public class ProductService {
    private static List<Product> products = new ArrayList<>();

    private static CategoryService categoryService = new CategoryService();
    private static int currentId = 0;

    static {
        readData();
    }


    public List<Product> getProducts() {
        return products;
    }


    public void createProduct(Product product) {
        product.setId(++currentId);
        product.setCategory(categoryService.findById(product.getCategoryId()));
        products.add(product);
        writeFile();
    }

    public boolean updateProduct(Product product) {
        for (var item:products) {
            if (item.getId() == product.getId()) {
                item.setName(product.getName());
                item.setDescript(product.getDescript());
                item.setPrice(product.getPrice());
                return true;
            }
        }
        return false;
    }

    public boolean deleteProductId(int id) {
        var sizeOld = products.size();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                writeFile();
                return sizeOld != products.size();
            }
        }
        return false;

    }

    public boolean deleteProductName(String name) {
        var sizeOld = products.size();
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getName().equals(name)) {
                products.remove(i);
                writeFile();
                return sizeOld != products.size();
            }
        }
        return false;
    }

    public void sortByPriceIncreaseProduct() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                return product1.getPrice().compareTo(product2.getPrice());
//                return product1.getPrice().subtract(product2.getPrice()).intValue()*-1; // de doi tu tang dan -> giam dan

            }
        });
    }

    public void sortByPriceDecreaseProduct() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                return product2.getPrice().compareTo(product1.getPrice());
//                return product1.getPrice().subtract(product2.getPrice()).intValue()*-1; // de doi tu tang dan -> giam dan

            }
        });
    }

    public void sortByNameProduct() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                return product1.getName().compareTo(product2.getName());
            }
        });
    }

    public void findByNameProduct(String name) {
        for (Product product:products) {
            if(product.getName().indexOf(name) >= 0) {
                System.out.printf("%x   | %s   | %s   | %f   | %s   |      %s%n",
                        product.getId(),
                        product.getName(),
                        product.getDescript(),
                        product.getPrice(),
                        product.getCategory().getName(),
                        product.getImportDate().toString());
            }
        }
    }

    public int countProduct() {
        return products.size();
    }

    private static void readData() {
        try {
            File fileWriter = new File("data/data.txt");
            FileReader fileReader = new FileReader(fileWriter);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null && !line.equals(" ")) {
                String [] data = line.split(",");
                currentId = Integer.parseInt(data[0]);
                Product product = new Product(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        new BigDecimal(data[3].trim()),
                        Integer.parseInt(data[4]),
                        Date.valueOf(data[5])
                );
                product.setCategory(categoryService.findById(product.getCategoryId()));
                products.add(product);

                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/data.txt"));
            for (var product: products) {
                writer.write(product.toString());
                writer.newLine();
            }

            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeProduct() {
        products.removeAll(products);
    }
}
