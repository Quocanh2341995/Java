package S;

import model.*;
import service.OrderService;
import service.ProductService;
import service.UserService;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static service.ProductService.writeFile;

//import static service.ProductService.writeFile;

public class Startup {
    private  static List<User> listUsers = new ArrayList<>();
    private static final List<Product> listProducts = new ArrayList<>();
    private static List<Order> list0rders = new ArrayList<>();
    public static void init() {
        if (ProductService.listProducts.isEmpty()) {
            initProduct();
        }
        if (UserService.listUsers.isEmpty()){
            intiUsers();
        }
        if (OrderService.listOrders.isEmpty()){
            initOrder();
        }

    }

    private static void initOrder() {
        Order order1 = new Order(1, 1, 1, 3, Date.valueOf("2023-10-01"), OrderStatus.PROCESSING);
        Order order2 = new Order(2, 3, 2, 4, Date.valueOf("2023-10-01"), OrderStatus.PROCESSING);
        Order order3 = new Order(3, 2, 3, 2, Date.valueOf("2023-10-01"), OrderStatus.PAID);

        list0rders.add(order1);
        list0rders.add(order2);
        list0rders.add(order3);
        OrderService.listOrders = list0rders;

        writeFile();
    }

    private static void intiUsers() {
        User user1 = new User(1, "Quoc Anh", "Hue", "0777444644", "123456", Role.ADMIN, UserStatus.ACTIVE);
        User user2 = new User(2, "Bao Ngan", "Hue", "0901234986", "111111", Role.CLIENT, UserStatus.ACTIVE);
        User user3 = new User(3, "Ronaldo", "Portugal", "0914249704", "222222", Role.CLIENT, UserStatus.ACTIVE);

        listUsers.add(user1);
        listUsers.add(user2);
        listUsers.add(user3);
        UserService.listUsers = listUsers;
        writeFile();

    }

    private static void initProduct() {
        Product product1 = new Product(1, "Den led", 10, 50000, Category.LAMP, Date.valueOf("2023-10-01"), Status.INSTOCK);
        Product product2 = new Product(2, "Den tron", 10, 30000, Category.LAMP, Date.valueOf("2023-10-01"), Status.INSTOCK);
        Product product3 = new Product(3, "Quat cay", 10, 250000, Category.FAN, Date.valueOf("2023-10-01"), Status.INSTOCK);
        Product product4 = new Product(4, "Quat tran", 10, 650000, Category.FAN, Date.valueOf("2023-10-01"), Status.INSTOCK);
        Product product5 = new Product(5, "Day 1 loi", 10, 200000, Category.WIRE, Date.valueOf("2023-10-01"), Status.INSTOCK);
        Product product6 = new Product(6, "Den 2 loi", 10, 250000, Category.WIRE, Date.valueOf("2023-10-01"), Status.INSTOCK);
        Product product7 = new Product(7, "O cam 2", 10, 10000, Category.SOCKETS, Date.valueOf("2023-10-01"), Status.INSTOCK);

        listProducts.add(product1);
        listProducts.add(product2);
        listProducts.add(product3);
        listProducts.add(product4);
        listProducts.add(product5);
        listProducts.add(product6);
        listProducts.add(product7);
        ProductService.listProducts = listProducts;

        writeFile();
    }
}

