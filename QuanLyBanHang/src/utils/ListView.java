package utils;

import java.util.Arrays;
import java.util.List;

public class ListView {
    public static List<String> loginMenuList = Arrays.asList("Chao mung ban den cua hang!!!", "1. Dang nhap", "2. Dang ky");

    public static void printMenu(List<String> menu) {
        printMenuDecoration("open");
        for (String str : menu) {
            System.out.printf(" ║ %-40s ║%n", str);
        }

        printMenuDecoration("close");
    }

    private static void printMenuDecoration(String open) {
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
