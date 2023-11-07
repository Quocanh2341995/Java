package view;

import model.Product;
import service.CategoryService;
import service.ProductService;
import utils.AppUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Scanner;
import static utils.AppUtils.*;


public class ProductView {
    static Scanner sc = new Scanner(System.in);
    static ProductService productService = new ProductService();
    static CategoryService categoryService = new CategoryService();

    public static void printMenu() {
        while (true) {
            System.out.println("+------------------------------------------+");
            System.out.println("|               MenuManager                |");
            System.out.println("+------------------------------------------+");
            System.out.println("| 1. Hien thi danh sach san pham.          |");
            System.out.println("| 2. Them moi san pham.                    |");
            System.out.println("| 3. Cap nhat san pham theo ma san pham.   |");
            System.out.println("| 4. Xoa san pham.                         |");
            System.out.println("| 5. Sap xep san pham.                     |");
            System.out.println("| 6. Tim kiem san pham theo ten san pham   |");
            System.out.println("| 7. Lay ra so luong san pham trong menu   |");
            System.out.println("| 7. Lam rong danh sach san pham.          |");
            System.out.println("| 0. Exit                                  |");
            System.out.println("+------------------------------------------+");
            int choice =getNumber("Nhap vao lua chon: ");
            switch (choice) {
                case 1 -> {
                    printProduct();
                }
                case 2 -> {
                    createProduct();
                }
                case 3 -> {
                    updateProduct();
                }
                case 4 -> {
                    deleteProduct();
                }
                case 5 -> {
                    sortProduct();
                }
                case 6 -> {
                    System.out.println("Nhap ten san pham muon tim: ");
                    String name = sc.nextLine();
                    System.out.println("Ket qua tim thay: ");
                    productService.findByNameProduct(name);
                }
                case 7 -> {
                    System.out.println("So luong san pham co trong menu :" + productService.countProduct());
                }
                case 8 -> {
                    productService.removeProduct();
                }
            }

        }
    }

    private static void sortProduct() {
        System.out.println("1. Sap xep theo gia tang dan.");
        System.out.println("2. Sap xep theo gia giam dan.");
        System.out.println("3. Sap xep theo thu tu chu cai.");
        int choosee = getNumber("Nhap vao lua chon sap xep:", 1, 3);
        switch (choosee) {
            case 1 -> {
                productService.sortByPriceIncreaseProduct();
                printProduct();
            }
            case 2 -> {
                productService.sortByPriceDecreaseProduct();
                printProduct();
            }
            case 3 -> {
                productService.sortByNameProduct();
                printProduct();
            }
        }
    }

    private static void deleteProduct() {
        System.out.println("1. Xoa theo Id san pham.");
        System.out.println("2. Xoa theo ten san pham.");
        int choosee = getNumber("Nhap vao lua chon kieu xoa:", 1, 2);
        switch (choosee) {
            case 1 -> {
                printProduct();
                boolean isSuccess = productService.deleteProductId(getNumber("Nhap Id san pham muon xoa: "));
                if (isSuccess) {
                    System.out.println("Xoa san pham thanh cong.");
                } else {
                    System.out.println("Khong tim thay Id san pham");
                }
            }
            case 2 -> {
                printProduct();
                boolean isSuccess = productService.deleteProductName(getString("Nhap ten san pham muon xoa: "));
                if (isSuccess) {
                    System.out.println("Xoa san pham thanh cong.");
                } else {
                    System.out.println("Khong tim thay ten san pham.");
                }
            }

        }


    }

    private static void updateProduct() {
        printProduct();
        boolean isSuccess = productService.updateProduct(new Product(getNumber("Nhap Id san pham muon cap nhat thong tin:"),
                getString("Nhap ten moi cua san pham:"),
                getString("Nhap mo ta moi cua san pham:"),
                new BigDecimal(getNumber("Nhap gia moi cua san pham:")),
                getCategoryId("Nhap phan loai moi cua san pham:"),
                getDay("Nhap ngay nhap hang moi cua san pham:")));
    }

    private static void createProduct() {
        productService.createProduct(new Product(0, getString("Nhap ten san pham moi:"),
                getString("Nhap mo ta san pham moi:"),
                new BigDecimal(getNumber("Nhap gia san pham moi:")),
                getCategoryId("Nhap phan loai san pham:"),
                getDay("Nhap ngay nhap hang:")
        ));
    }

    private static void printProduct() {
        System.out.println("╔------------------------------------------ List Product ----------------------------------------╗");
        System.out.println(String.format("| %-7s | %-13s | %-15s | %-20s | %-10s | %-10s ",
                "ID", "Name", "Descript", "Price", "Category", "Date"));
        for (var product : productService.getProducts()) {
            System.out.printf("| %-7s | %-13s | %-15s | %-20s | %-10s | %-10s%n ",
                    product.getId(),
                    product.getName(),
                    product.getDescript(),
                    product.getPrice(),
                    product.getCategory().getName(),
                    product.getImportDate().toString());
                    System.out.println("╚---------*------------*--------------*-----------------*------------*------------*-------------╝");

        }
    }
    public static int getCategoryId(String str) {
        System.out.println("Danh sach phan loai:");
        for (var category : categoryService.findAll()) {
            System.out.println(category.getId() + ". " + category.getName());
        }
        return getNumber(str);
    }

}
