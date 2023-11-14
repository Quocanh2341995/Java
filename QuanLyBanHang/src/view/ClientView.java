package view;

import model.Order;
import model.Product;
import service.AuthService.LoginService;
import service.OrderService;
import service.ProductService;
import utils.AppUtils;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static view.LoginView.loginMenu;

public class ClientView {
    static Scanner sc = new Scanner(System.in);
    static ProductService productService = new ProductService();
    static OrderService orderService = new OrderService();
    public static void selectBuyerView() throws IOException {
        int choice = -1;
        do {
            System.out.println();

            System.out.println();
            System.out.println("\u001B[35m            ╔══════════════════════════════════════════════════════════╗\u001B[0m");
            System.out.println("\u001B[35m                                                             \u001B[0m");
            System.out.println("\u001B[35m                        [1]    Hien thi danh sach san pham              \u001B[0m");
            System.out.println("\u001B[35m                        [2]    Tim kiem san pham           \u001B[0m");
            System.out.println("\u001B[35m                        [3]    Mua san pham                 \u001B[0m");
            System.out.println("\u001B[35m                        [4]    Kien tra lich su mua hang           \u001B[0m");
            System.out.println("\u001B[35m                        [0]    Logout                      \u001B[0m");
            System.out.println("\u001B[35m                                                             \u001B[0m");
            System.out.println("\u001B[35m            ╚══════════════════════════════════════════════════════════╝\u001B[0m");
            System.out.println();
            System.out.println();

            choice = AppUtils.getNumberMinMax("Nhap lua chon", 0, 4);

            switch (choice) {
                case 1:
                    showProduct();
                    break;
                case 2:
                    searchProduct();
                    break;
                case 3:
                    buyProduct();
                    break;
                case 4:
                    showHistoryOrder();
                    break;
                case 0:
                    loginMenu();
                    break;
                default:
                    System.out.println("Vui long chon dung chuc nang!");
                    break;
            }
        } while (choice != 0);
    }

    private static void searchProduct() {
        showProduct();
        System.out.println("Nhap ten san pham:");
        String search = sc.next();
        List<Product> list = productService.getByProductName(search);
        System.out.println("╔-------------------------- Danh sach san pham lien qua -----------------------------------╗");
        System.out.println(String.format("| %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s |",
                " Id", "Ten", "So Luong", "Gia", "Phan Loai", "Ngay nhap hang", "Trang thai"));
        System.out.println("|-------*------------*------------*------------*------------*----------|");
        for (Product product : list) {
            System.out.println(product.toStringShow());
            System.out.println("|-------*------------*------------*------------*------------*----------|");
        }
    }

    private static void showHistoryOrder() {
        List<Order> orderList = orderService.showHistoryOrder(LoginService.currentUser);
        System.out.println("╔---------------------------- Danh sach don hang -----------------------------╗");
        String str = String.format("| %-5s | %-5s | %-7s | %-7s | %-7s | %-10s |",
                " Id order", "Client Id", "Product Id", "BuyDate", "Category", "Oder Status");
        System.out.println(str);
        System.out.println("|-------*---------------*---------------*------------*------------*------|");
        for (Order order : orderList) {
            System.out.println(order.toString());
            System.out.println("|-------*---------------*---------------*------------*------------*------|");
        }
    }

    private static void buyProduct() throws IOException {
        showProduct();
        int productId;
        int quantity;
        do {
            System.out.println("Nhap ma san pham:");
            try {
                productId = Integer.parseInt(sc.next());
            } catch (NumberFormatException e) {
                System.out.println("Ma san pham phai la so nguyen!");
                continue;
            }
            if (!ProductView.checkExistId(productId)) {
                System.out.println("Ma san pham khong dung");
                continue;
            }
            break;
        } while (true);

        if (!productService.checkProductStatus(productId)) {
            System.out.println("........");
            selectBuyerView();
        }

        do {
            System.out.println("Nhap so luong san pham muon mua:");
            quantity = Integer.parseInt(sc.next());
            if (ProductView.checkQuantity(quantity,productId)) {
                break;
            }
            System.out.println("So luong san pham mua khong hop le!");
        } while (true);
        orderService.buyProduct(Integer.parseInt(String.valueOf(productId)), LoginService.currentUser, Integer.parseInt(String.valueOf(quantity)));
    }

    private static void showProduct() {
        List<Product> productList = productService.getAll();
        System.out.println("╔----------------------------- Danh sach san pham --------------------------------╗");
        System.out.println(String.format("| %-5s | %-10s | %-10s | %-10s | %-10s | %-10s |",
                " Id", "Ten", "So Luong", "Gia", "Phan Loai", "Ngay nhap hang"));
        System.out.println("|-------*------------*------------*------------*------------*----------|");
        for (Product product : productList) {

            System.out.println(String.format("| %-5s | %-10s | %-10s | %-10s | %-10s | %-10s |",
                    product.getId(), product.getName(),product.getQuantity(),product.getPrice(), product.getCategory(), product.getImportDate()));
            System.out.println("|-------*---------------*---------------*------------*------------*------|");
        }

        }
    }

