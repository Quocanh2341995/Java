package view;

import model.Order;
import model.User;
import service.OrderService;
import service.ProductService;
import service.UserService;
import utils.AppUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import static view.AdminView.sc;
import static view.AdminView.selectAdminView;

public class UserView {
    static UserService userService = new UserService();
    static OrderService orderService = new OrderService();
    static ProductService productService = new ProductService();

    public static void userMenu() {
        try {
            int choice;
            do {
                System.out.println("+------------------------------------------+");
                System.out.println("|        Quan Ly Don Hang Khach Hang       |");
                System.out.println("+------------------------------------------+");
                System.out.println("| 1. Hien thi tai khoan khach hang         |");
                System.out.println("| 2. Hien thi danh sach don hang           |");
                System.out.println("| 3. Hien thi don hang chua thanh toan     |");
                System.out.println("| 4. Hien thi doanh thu trong ngay         |");
                System.out.println("| 5. Hien thi tong doanh thu               |");
                System.out.println("| 0. Exit                                  |");
                System.out.println("+------------------------------------------+");
                choice = AppUtils.getNumberMinMax("Chon lua chon:", 0, 5);
                switch (choice) {
                    case 1:
                        showUserAccount();
                        break;
                    case 2:
                        showOrderList();
                        break;
                    case 3:
                        showOrderProcessingList();
                        break;
                    case 4:
                        showRevenueOfDay();
                        break;
                    case 5:
                        showTotalRevenue();
                        break;
                    case 0:
                        selectAdminView();
                }
            } while (choice != 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void showTotalRevenue() {
        List<Order> orderList = orderService.getOrderPaid();
        double total = 0;
        for (int i = 0; i < orderList.size(); i++) {
            int productId = orderList.get(i).getProductId();
            double price = productService.getProductDetail(productId).getPrice();
            total += price/5 * orderList.get(i).getQuantity();
        }
        System.out.println("Tong doanh thu: " + total + "vnd");
    }

    public static boolean isValidLocalDate(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate.parse(dateString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static void showRevenueOfDay() {
        System.out.println("Nhap ngay muon hien thi doanh thu:");
        String s;
        do {
            String inputDay = sc.nextLine();
            if(isValidLocalDate(inputDay)) {
                s = inputDay;
                break;
            }
            System.out.println("Vui long nhap ngay theo dinh dang: yyyy-mm-dd");
        } while (true);

        LocalDate now = LocalDate.parse(s);
        List<Order> orderList = orderService.getAll();
        double total = 0;
        for (int i = 0; i < orderList.size(); i++) {
            if (now.compareTo(orderList.get(i).getBuyDate()) == 0) {
                int productId = orderList.get(i).getProductId();
                double price = productService.getProductDetail(productId).getPrice();
                total += price * orderList.get(i).getQuantity();
            }
        }
        System.out.println("Tong doanh thu trong ngay: " + total + "vnd");

    }

    private static void showOrderProcessingList() {
        System.out.println("╔------------------------ Danh sach don hang chua thanh toan ------------------------╗");
        String str = String.format("| %-7s | %-7s | %-13s | %-8s | %-15s |",
                "Id", "Book Id", "BorrowDate", "Quantity", "Borrower Status");
        System.out.println(str);
        System.out.println("|---------*--------*---------*-----------*----------*----------*-----------|");
        List<Order> orderProcessingList = orderService.getOrderProcessing();
        for (Order ord : orderProcessingList) {
            System.out.println(ord.toString());
            System.out.println("|---------*--------*---------*-----------*----------*----------*-----------|");
        }
    }

    private static void showOrderList() {
        List<Order> orderList = orderService.getAll();
        System.out.println("╔----------------------- Danh sach don hang  ------------------------------╗");
        System.out.println(String.format("| %-7s | %-7s | %-7s | %-13s | %-8s | %-13s |",
                "UserId", "OrderID", "ProductId", "BuyDate", "Quantity", "OrderStatus"));
        System.out.println("|---------*--------*---------*-----------*----------*----------*-----------|");
        for (Order order : orderList) {
            System.out.println(order.toString());
            System.out.println("|---------*--------*---------*-----------*----------*----------*-----------|");
        }


    }

    private static void showUserAccount() {
        List<User> userList = userService.getAll();
        System.out.println("╔---------------------------- LIST USER -------------------------------╗");
        System.out.println(String.format("| %-7s | %-13s | %-15s | %-20s | %-10s | %-10s | %-15s |",
                "ID", "Name", "Address", "Phone", "Password", "Role", "UserStatus"));
        System.out.println("|---------*---------*----------*--------*----------*---------*---------|");
        for (User user : userList) {
            System.out.println(user.toString());
            System.out.println("|---------*---------*----------*--------*----------*---------*---------|");
        }
    }

}
