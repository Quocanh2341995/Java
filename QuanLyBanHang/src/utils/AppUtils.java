package utils;

import model.EPattern;

import java.sql.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AppUtils {
    public static Scanner sc = new Scanner(System.in);
    private static final String REGEX_NUMBER = "\\d+";
    private static final String REGEX_PHONE = "^0\\d{9}$";

    public static String getString(String str) {
        try {
            System.out.println(str);
            String data = sc.nextLine();
            if (data.equals("")) {
                throw new Exception();
            }
            return data;
        } catch (Exception e) {
            System.out.println("Du lieu trong--Vui long nhap lai:");
            return getString(str);
        }
    }

    public static int getNumber(String str) {
        try {
            return Integer.parseInt(getString(str));
        } catch (Exception e) {
            System.out.println("Du lieu nhap vao khong dung!");
            return getNumber(str);
        }
    }

    public static int getNumberMinMax(String str, int min, int max) throws IndexOutOfBoundsException {
        System.out.println(str);
        int num;
        try {
            num = Integer.parseInt(sc.nextLine());
            if(num < min || num > max) {
                System.err.println("Chọn từ khoảng " + min + " và " + max);
                return getNumberMinMax(str,min,max);
            }
            return num;
        } catch (Exception e) {
            System.err.println("Khong dung dinh dang");
            return getNumberMinMax(str,min,max);
        }
    }

    public static Date getDate(String str) {
        try {
            System.out.println("Vui long nhap ngay theo mau : yyyy-mm-dd");
            return Date.valueOf(getString(str));
        } catch (Exception e) {
            System.out.println("Du lieu nhap vao khong dung!");
            return getDate(str);
        }
    }

    public static boolean checkNumber(String str) {
        if (str.matches(REGEX_NUMBER)) {
            return true;
        } else {
            System.out.println("");
            System.out.println("Nhap vao khong dung! Vui long nhap lai:");
            return false;
        }
    }
    public static String getStringWithPattern(EPattern ePattern) {
        String result = getString(ePattern.getMessage());
        if (!Pattern.compile(ePattern.getPattern()).matcher(result).matches()) {
            System.out.println(ePattern.getErrorMsg());
            return getStringWithPattern(ePattern);
        }
        return result;
    }
}
