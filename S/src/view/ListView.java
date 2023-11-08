package view;

import java.util.Arrays;
import java.util.List;

public class ListView {
    public static List<String> loginMenuList = Arrays.asList(" Welcome to TT Library", "1. Login", "2. Register");

    public static void printMenu(List<String> menu) {

        printMenuDecoration("open");
        for (String str : menu) {
            System.out.printf(" ║ %-40s ║%n", str);
        }

        printMenuDecoration("close");
    }

    public static void printMenuDecoration(String open) {
        switch (open) {
            case "open":
                System.out.println("\u001B[35m ╔══════════════════════════════════════════╗ \u001b[35m");
                break;
            default:
                System.out.println("\u001b[35m ╚══════════════════════════════════════════╝ \u001b[35m");
                break;
        }
    }
}
