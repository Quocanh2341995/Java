package view;

import utils.AppUtils;

import java.io.IOException;
import java.util.Scanner;

import static view.LoginView.loginMenu;
import static view.ProductView.productViewMenu;
import static view.UserView.userMenu;

public class AdminView {
    static Scanner sc = new Scanner(System.in);

    public static void selectAdminView() throws IOException {
        int choice = -1;
        do {
            System.out.println();
            System.out.println("\u001B[34m                    MENU FOR ADMIN        \u001B[34m");
            System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
            System.out.println("⣿                                                   ⣿");
            System.out.println("⣿                                                   ⣿");
            System.out.println("⣿            1.   Quan ly khach hang                ⣿");
            System.out.println("⣿            2.   Quan ly san pham                  ⣿");
            System.out.println("⣿            0.   Dang xuat                         ⣿");
            System.out.println("⣿                                                   ⣿");
            System.out.println("⣿                                                   ⣿");
            System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
            choice = AppUtils.getNumberMinMax("Nhap lua chon: ", 0, 2);
            switch (choice) {
                case 1:
                    userMenu();
                    break;
                case 2:
                    productViewMenu();
                    break;
                case 3:
                    System.out.println("Tro lai man hinh dang nhap!");
                    loginMenu();
                    break;
            }
        }
        while (choice != 0);
    }
}
