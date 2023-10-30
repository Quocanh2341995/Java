package Test;

import Main.MenuProduct;
import Main.Product;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MenuProduct menu = new MenuProduct();
        int choice = 0;
        do {
            System.out.println("----------Menu----------");
            System.out.println(
                            "1. Them moi san pham.\n"
                           + "2. Hien thi danh sach san pham.\n"
                           + "3. Cap nhat san pham.\n"
                           + "4. Xoa san pham theo ten.\n"
                           + "5. Sap xep theo gia tu cao den thap.\n"
                           + "6. Tim kiem theo ten san pham.\n"
                           + "0. Thoat.");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                // 1. them san pham moi vao menu
                System.out.println("Nhap ma san pham moi: ");
                int numbersOfProduct = sc.nextInt();
                sc.nextLine();
                System.out.println("Nhap ten san pham moi: ");
                String name = sc.nextLine();
                System.out.println("Nhap descrip san pham moi:");
                String descrip = sc.nextLine();
                System.out.println("Nhap gia san pham moi:");
                double price = sc.nextDouble();
                Product pr = new Product(numbersOfProduct, name, descrip, price);
                menu.addProduct(pr);

            } else if (choice == 2) {
                // 2. in menu ra man hinh
                menu.printProduct();

            } else if (choice == 3) {
                
            } else if (choice == 4) {
                // 4. xoa san pham dua tren ten san pham
                System.out.println("nhap ten san pham: ");
                String name = sc.nextLine();
                Product pr = new Product(name);
                System.out.println("xoa san pham trong danh sach: "+ menu.remove(pr));

            } else if (choice == 5) {
                // 5. Sap xep theo gia tu cao den thap.
                menu.arrange();
                menu.printProduct();
                
            } else if (choice == 6) {
                // 6. Tim kiem theo ten san pham.
                System.out.println("nhap ten san pham: ");
                String name = sc.nextLine();
                System.out.println("ket qua tim kiem: ");
                menu.find(name);

            } else if (choice == 0) {
                
            }
        } while (choice != 0);
    }
}
