package service;

import model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductService {
    private static final ArrayList<Product> products = new ArrayList<>();
    private static int currentId = 0;

    static {
        products.add(new Product(++currentId, "Coca", "Ngon", new BigDecimal(15000)));
        products.add(new Product(++currentId, "Pepsi", "Ngonnn", new BigDecimal(15000)));
        products.add(new Product(++currentId, "Bo huc", "Chua", new BigDecimal(25000)));
        products.add(new Product(++currentId, "Sting", "Bthuong", new BigDecimal(10000)));
    }

    public List<Product> getProducts(){
        return products;
    }

    public void createProduct(Product product) {
        product.setId(++currentId);
        products.add(product);
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
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProductName(String name) {
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getName().equals(name)) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public void sortByPriceProduct() {
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

    public void sortByIdProduct() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                return product1.getId() - product2.getId();
            }
        });
    }


    public void findNameProduct(String name) {
        for (Product product:products) {
            if(product.getName().indexOf(name) >= 0) {
                System.out.printf("%x     | %s     | %s     |      %f%n", product.getId(), product.getName(), product.getDescript(), product.getPrice());
            }
        }
    }

    public int countProduct() {
        return products.size();
    }
}
