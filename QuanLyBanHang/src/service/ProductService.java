package service;

import model.Category;
import model.EPath;
import model.Product;
import model.Status;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductService implements BasicCRUD<Product> {
    List<Product> list = new ArrayList<>();
    public static List<Product> listProducts = new ArrayList<>();
    private static int currentId = 0;
    static {
        readData();
    }
    public ProductService(){
    }
    public static void save() {
        writeFile();
    }


    @Override
    public Product getByID(int id) {
        return listProducts.stream().filter(p->p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return listProducts;
    }

    @Override
    public void create(Product product) {
        listProducts.add(product);
    }

    @Override
    public void update(Product obj) {
        for (Product product : listProducts) {
            if (product.getId() == obj.getId()) {
                product = obj;
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        listProducts.removeAll(listProducts.stream().filter(p->p.getId() == id).toList());
        save();
        writeFile();

    }

    @Override
    public boolean isExist(int id) {
        Product product = listProducts.stream().filter(p-> Objects.equals(p.getId(),id)).findFirst().orElse(null);
        return product != null;
    }

    public void add(Product newproduct) {
        listProducts = getAll();
        listProducts.add(newproduct);
        save();
        writeFile();
    }
    public Product getProductDetail(long productId) {
        list = getAll();
        for (Product product: list){
            if (product.getId() == productId){
                return product;
            }
        }
        return null;
    }

    public void findNameProduct(String name) {
        for (Product product : listProducts) {
            if(product.getName().indexOf(name) >= 0) {
                System.out.println(String.format("| %-5s | %-20s | %-10s | %-10s | %-20s | %-10s |",
                        " Id", "Ten", "So Luong", "Gia", "Phan Loai", "Ngay nhap hang"));
            }
        }
    }

    public boolean checkProductStatus(long productId) {
        list = getAll();
        Product product = getProductDetail(productId);
        return product.getStatus() == Status.INSTOCK;
    }

    public void changeProductStatus (long productId) {
        list = getAll();
        Product product = getProductDetail(productId);
        if (product.getQuantity() == 0){
            if(product.getStatus() == Status.INSTOCK) {
                product.setStatus(Status.OUTOFSTOCK);
            }
        }
        save();
        writeFile();
    }
    private static void readData() {
        try{
            File fileWriter = new File("data/products.txt");
            FileReader fileReader = new FileReader(fileWriter);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null && !line.equals("")){
                String[] data = line.split(",");
                currentId = Integer.parseInt(data[0]);
                Product product = new Product(Integer.parseInt(data[0]),
                        data[1],
                        Integer.parseInt(data[2]),
                        Double.parseDouble(data[3]),
                        Category.valueOf(data[4]),
                        Date.valueOf(data[5]),
                        Status.valueOf(data[6]));
                listProducts.add(product);
                line = reader.readLine();


            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void writeFile(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/products.txt"));
            for (var product : listProducts) {
                writer.write(product.toString());
                writer.newLine();
            }

            writer.flush();
            writer.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
