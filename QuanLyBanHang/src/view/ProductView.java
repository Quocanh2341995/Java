package view;

import model.Category;
import model.Product;
import model.Status;
import service.ProductService;
import utils.AppUtils;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static utils.AppUtils.sc;

public class ProductView {
    static ProductService productService = new ProductService();

    public static void productViewMenu() throws IOException {
        int choice;
        do {
            System.out.println("+------------------------------------------+");
            System.out.println("|                 Store                    |");
            System.out.println("+------------------------------------------+");
            System.out.println("| 1. Hien thi san pham trong cua hang      |");
            System.out.println("| 2. Them san pham                         |");
            System.out.println("| 3. Xoa san pham                          |");
            System.out.println("| 4. Cap nhat thong tin san pham           |");
            System.out.println("| 0. Exit                                  |");
            System.out.println("+------------------------------------------+");

            choice = AppUtils.getNumberMinMax("Vui long nhap lua chon:", 0, 4);
            switch (choice) {
                case 1: {
                    printProduct();
                    break;
                }
                case 2: {
                    addProduct();
                    break;
                }
                case 3: {
                    deleteProduct();
                    break;
                }
                case 4: {
                    editProduct();
                    break;
                }
            }
        }
        while (choice != 0);
    }

    private static void editProduct() {
        int category, quantity, price;
        printProduct();
        int id;
        boolean exists;

        do {
            System.out.println("Nhap ma san pham:");
            id = Integer.parseInt(sc.nextLine());
            exists = checkExistId(id);
            if (!exists) {
                System.out.println("Id khong dung! Vui long thu lai!");
            }
        } while (!exists);
        Product product = productService.getByID(id);
        System.out.println("Nhap ten moi cua san pham:");

        boolean flag;
        String namenew = sc.nextLine();
        do {
            flag = false;
            if (namenew.equals("")) {
                System.out.println("Loi du lieu! Vui long nhap lai ten moi!");
                flag = true;
                namenew = sc.nextLine();
            }
        } while (flag);
        product.setName(namenew);
        try {
            boolean checkCatory = false;
            do {
                System.out.println("Chon phan loai san pham:");
                System.out.println("[1] Den");
                System.out.println("[2] Quat");
                System.out.println("[3] Day Dien");
                System.out.println("[4] O Cam");
                String input = sc.nextLine();

                if (AppUtils.checkNumber(input)) {
                    category = Integer.parseInt(input);
                } else {
                    continue;
                }
                switch (category) {
                    case 1:
                        product.setCategory(Category.LAMP);
//                        checkCatory = true;
                    case 2:
                        product.setCategory(Category.FAN);
//                        checkCatory = true;
                    case 3:
                        product.setCategory(Category.WIRE);
//                        checkCatory = true;
                    case 4:
                        product.setCategory(Category.SOCKETS);
//                        checkCatory = true;
                    default:
//                        System.out.println("Vui long chon dung phan loai san pham!");
                        checkCatory = false;
                        break;
                }
            } while (checkCatory);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        product.setStatus(Status.INSTOCK);
        LocalDate date = LocalDate.now();
        product.setImportDate(date);

        try {
            System.out.println("Nhap so luong san pham:");
            quantity = Integer.parseInt(sc.nextLine());
            product.setQuantity(quantity);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Nhap gia san pham:");
            price = Integer.parseInt(sc.nextLine());
            product.setPrice(price);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        productService.save();
        System.out.println("Thong tin san pham da cap nhat thanh cong!");
    }

    private static void deleteProduct() {
        try {
            printProduct();
            int productId = AppUtils.getNumber("Nhap id san pham muon xoa");
            if (!productService.isExist(productId)) {
                System.out.printf("Khong tim thay %d.\n", productId);
                deleteProduct();
            }
            productService.delete(productId);
            System.out.println("Xoa san pham thanh cong!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkExistId(int idproduct) {
        List<Product> productList = productService.getAll();
        for (Product product : productList) {
            if (product.getId() == idproduct) {
                return true;
            }
        }
        return false;
    }

    private static void addProduct() {
        List<Product> productList = productService.getAll();
        int category, quantity;

        Product product = new Product();
        String productName, importDate;
        Double productPrice;
        Integer productQuantity;
        int id = 0;
        if (productList.size() == 0) {
            id = 1;
        } else id = productList.get(productList.size() - 1).getId() + 1;
        product.setId(id);

        do {
            System.out.println("Nhap ten san pham:");
            productName = sc.nextLine();
            product.setName(productName);
        } while (productName == "");

        try {
            boolean checkCategory = false;
            do {
                System.out.println("Chon loai san pham:");
                System.out.println("[1] Den");
                System.out.println("[2] Quat");
                System.out.println("[3] Day Dien");
                System.out.println("[4] O Cam");
                String input = sc.nextLine();

                if (AppUtils.checkNumber(input)) {
                    category = Integer.parseInt(input);
                } else {
                    continue;
                }
                switch (category) {
                    case 1: {
                        product.setCategory(Category.LAMP);
                        checkCategory = true;
                        break;
                    }
                    case 2: {
                        product.setCategory(Category.FAN);
                        checkCategory = true;
                        break;
                    }
                    case 3: {
                        product.setCategory(Category.WIRE);
                        checkCategory = true;
                        break;
                    }
                    case 4: {
                        product.setCategory(Category.SOCKETS);
                        checkCategory = true;
                        break;
                    }
                    default: {
                        System.out.println("Vui long chon dung loai san pham");
                        checkCategory = false;
                    }
                }
            } while (!checkCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean isContinue = false;
        do {
            try {
                System.out.println("Nhap gia san pham:");
                productPrice = Double.parseDouble(sc.nextLine());
                product.setPrice(productPrice);
                isContinue = false;
            } catch (Exception e) {
                isContinue = true;
            }
        } while (isContinue);

        boolean isExit = false;
        do {
            try {
                System.out.println("Nhap so luong san pham:");
                productQuantity = Integer.parseInt(sc.nextLine());
                product.setQuantity(productQuantity);
                isExit = false;
            } catch (Exception e) {
                isContinue = true;
            }
        } while (isExit);



        product.setStatus(Status.INSTOCK);
        LocalDate date = LocalDate.now();
        product.setImportDate(date);
        productService.add(product);
        System.out.println("Them san pham thanh cong!");
    }

    private static void printProduct() {
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

    public static boolean checkQuantity (int quantity, int productId) {
        Product product = productService.getByID(productId);
        if (quantity <= product.getQuantity()){
            return true;
        }
        return false;
    }
}
