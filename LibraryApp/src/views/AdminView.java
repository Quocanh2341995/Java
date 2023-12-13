package views;

import utils.AppUtils;

import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

import static views.BookView.bookViewMenu;
import static views.LoginView.loginMenu;
import static views.UserView.userMenu;

public class AdminView {
    static Scanner scanner = new Scanner(System.in);

    public static void selectAdminView() throws IOException {
        int choice = -1;
        do {
            System.out.println();
            System.out.println("\u001B[34m                    MENU FOR ADMIN        \u001B[34m");
            System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
            System.out.println("⣿                                                                                                               ⣿");
            System.out.println("⣿                                                                                                               ⣿");
            System.out.println("⣿            1.   Customer Management                                                        ⣿");
            System.out.println("⣿            2.   Book Management                                                               ⣿");
            System.out.println("⣿            0.   Logout                                                                                  ⣿");
            System.out.println("⣿                                                                                                               ⣿");
            System.out.println("⣿                                                                                                               ⣿");
            System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
            choice = AppUtils.getIntWithBound("Enter choice : ", 0, 2);
            switch (choice) {
                case 1:
                    userMenu();
                    break;
                case 2:
                    bookViewMenu();
                    break;
                case 0:
                    System.out.println("Back to Login menu");
                    loginMenu();
                    break;
            }
        }
        while (choice != 0);
    }
}

