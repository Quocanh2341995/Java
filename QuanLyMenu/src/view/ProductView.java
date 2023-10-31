package view;

import model.Product;
import service.ProductService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ProductView {
    static Scanner sc = new Scanner(System.in);
    static ProductService productService = new ProductService();

    public static void printMenu() {
        while (true) {
            System.out.println("-----Menu-----");
            System.out.println("1. Hien thi danh sach san pham.");
            System.out.println("2. Them moi san pham.");
            System.out.println("3. Cap nhat san pham theo ma san pham.");
            System.out.println("4. Xoa san pham theo ma san pham.");
            System.out.println("5. Xoa san pham theo ten san pham");
            System.out.println("6. Sap xep san pham theo gia giam dan.");
            System.out.println("7. Sap xep san pham theo thu tu chu cai.");
            System.out.println("8. Tim kiem san pham theo ten san pham");
            System.out.println("9. Lay ra so luong san pham trong menu.");
            System.out.println("0. Thoat.");
            int choice = getNumber("Nhap vao lua chon.");
            switch (choice) {
                case 1 -> {
                    productService.sortByIdProduct();
                    printProduct();
                }
                case 2 -> createProduct();
                case 3 -> updateProduct();
                case 4 -> deleteProductId();
                case 5 -> deleteProductName();
                case 6 -> {
                    productService.sortByPriceProduct();
                    printProduct();
                }
                case 7 -> {
                    productService.sortByNameProduct();
                    printProduct();
                }
                case 8 -> {
                    System.out.println("Nhap ten san pham muon tim: ");
                    String name = sc.nextLine();
                    System.out.println("Ket qua tim kiem: ");
                    productService.findNameProduct(name);

                }
                case 9 -> {
                    System.out.println("So luong san pham trong menu: "+ productService.countProduct());
                }
            }
        }
    }



    private static void deleteProductName() {
        printProduct();
        boolean isSuccess = productService.deleteProductName(getString("Nhap ten san pham muon xoa: "));
        if (isSuccess) {
            System.out.println("Xoa san pham thanh cong.");
        } else {
            System.out.println("Khong tim thay ten san pham.");
        }
    }

    private static void deleteProductId() {
        printProduct();
        boolean isSuccess = productService.deleteProductId(getNumber("Nhap Id san pham muon xoa: "));
        if (isSuccess) {
            System.out.println("Xoa san pham thanh cong.");
        } else {
            System.out.println("Khong tim thay Id san pham");
        }
    }

    private static void updateProduct() {
        printProduct();
        boolean isSuccess = productService.updateProduct(new Product(getNumber("Nhap Id san pham muon cap nhat: "), getString("Nhap ten moi: "), getString("Nhap mo ta moi: "), new BigDecimal(getNumber("Nhap gia moi: "))));
        if (isSuccess) {
            System.out.println("Cap nhat san pham thanh cong.");
        } else {
            System.out.println("Khong tim thay Id san pham.");
        }
    }

    private static void createProduct() {
        productService.createProduct(new Product(0, getString("Nhap ten san pham moi: "), getString("Nhap mo ta san pham moi: "), new BigDecimal(getNumber("Nhap gia san pham moi: "))));
    }

    private static void printProduct() {
        System.out.println("Id:     | Name:     | Descript:     | Price:     ");
        for (var product:productService.getProducts()) {
            System.out.printf("%x     | %s     | %s     |      %f%n", product.getId(), product.getName(), product.getDescript(), product.getPrice());
        }
    }

    // bat loi nhap du lieu kieu so
    public static int getNumber(String str) throws IndexOutOfBoundsException {
        System.out.println(str);
        int num;
        try {
            num = Integer.parseInt(sc.nextLine());
            return num;
        } catch (Exception e) {
            System.out.println("Du lieu nhap vao khong dung dinh dang.");
            return getNumber(str);
        }
    }
    public static String getString (String str) throws IndexOutOfBoundsException {
        System.out.println(str);
        return sc.nextLine();
    }


}
