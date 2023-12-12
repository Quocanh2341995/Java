package Utils;

import java.util.Scanner;

public class AppUtils {
    public static Scanner sc = new Scanner(System.in);


    public static String getString(String str) throws IndexOutOfBoundsException {
        System.out.println(str);
        return sc.nextLine();
    }

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


    public static int getNumberMinMax(String str, int min, int max) throws IndexOutOfBoundsException {
        System.out.println(str);
        int num;
        try {
            num = Integer.parseInt(sc.nextLine());
            if (num < min || num > max) {
                System.err.println("Chọn từ khoảng " + min + " và " + max);
                return getNumberMinMax(str, min, max);
            }
            return num;
        } catch (Exception e) {
            System.err.println("Khong dung dinh dang");
            return getNumberMinMax(str, min, max);
        }
    }


}
