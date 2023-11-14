package service;

import model.*;
import view.ClientView;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderService implements BasicCRUD<Order> {
    public static List<Order> listOrders = new ArrayList<>();
    List<Product> listProducts = new ArrayList<>();
    ProductService productService = new ProductService();
    ClientView clientView = new ClientView();
    private static int currentId = 0;

    static {
        readData();
    }

    public List<Order> getListOrders() {
        return listOrders;
    }
    public static void save() {
        writeFile();
    }

    @Override
    public Order getByID(int id) {
        return listOrders.stream().filter(b->b.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Order> getAll() {
        return listOrders;
    }

    @Override
    public void create(Order order) {
        listOrders.add(order);
    }

    @Override
    public void update(Order obj) {
        for (Order order : listOrders) {
            if(order.getId() == obj.getId()) {
                order = obj;
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        listOrders.removeAll(listOrders.stream().filter(b->b.getId() == id).toList());
    }

    @Override
    public boolean isExist(int id) {
        Order order = listOrders.stream().filter(b-> Objects.equals(b.getId(), id)).findFirst().orElse(null);
        return order != null;
    }

    public List<Order> getOrderProcessing() {
        List<Order> orderProcessingList = new ArrayList<>();
        for (Order order : listOrders) {
            if(order.getOrderStatus() == OrderStatus.PROCESSING){
                orderProcessingList.add(order);
            }
        }
        return orderProcessingList;
    }

    public List<Order> getOrderPaid() {
        List<Order> orderPaidList = new ArrayList<>();
        for (Order order : listOrders) {
            if(order.getOrderStatus() == OrderStatus.PAID){
                orderPaidList.add(order);
            }
        }
        return orderPaidList;
    }

    public void buyProduct(int productId, User user, int quantity) {
        listOrders = getListOrders();
        listProducts = productService.getAll();
        LocalDate date = LocalDate.now();
        if (productService.getByID(productId).getQuantity() < quantity) {
            System.out.println("San pham khong du so luong!");
        } else {
            for (Product product : listProducts) {
                if (productId == product.getId()) {
                    product.setQuantity(product.getQuantity() - quantity);
                }
            }
            System.out.println("Mua san pham thanh cong!");
        }
        Order order = new Order();
        int id = 0;
        if (listProducts.size() == 0){
            id = 1;
        } else id = listOrders.get(listOrders.size() - 1).getId() + 1;
        order.setId(id);
        order.setUserId(user.getId());
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setBuyDate(date);
        order.setOrderStatus(OrderStatus.PROCESSING);
        productService.changeProductStatus(productId);

        for(Order order1 : listOrders) {
            if(order1.getUserId() == order.getUserId() && order1.getBuyDate().equals(order.getBuyDate())) {
                order1.setQuantity(order1.getQuantity() + order.getQuantity());
                save();
                return;
            }
        }
        listOrders.add(order);
        save();

    }

    public List<Order> showHistoryOrder(User user) {
        List<Order> listOrderProductByUser = new ArrayList<>();
        listOrders = getListOrders();
        int userId = user.getId();
        for (Order order : listOrders) {
            if (userId == order.getUserId()) {
                listOrderProductByUser.add(order);
            }
        }
        return listOrderProductByUser;
    }
    private static void readData() {
        try{
            File fileWriter = new File("data/bill.txt");
            FileReader fileReader = new FileReader(fileWriter);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null && !line.equals("")){
                String[] data = line.split(",");
                currentId = Integer.parseInt(data[0]);
                Order order = new Order(Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]),
                        Integer.parseInt(data[2]),
                        Integer.parseInt(data[3]),
                        Date.valueOf(data[4]),
                        OrderStatus.valueOf(data[5]));

                listOrders.add(order);
                line = reader.readLine();


            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void writeFile(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/bill.txt"));
            for (var order : listOrders) {
                writer.write(order.toStringShow());
                writer.newLine();
            }

            writer.flush();
            writer.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
