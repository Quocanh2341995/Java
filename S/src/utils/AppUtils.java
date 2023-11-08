package utils;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AppUtils {
    private static Scanner sc;
    private static final String REGEX_NUMBER = "\\d+";
    private static final String REGEX_PHONE = "^0\\d{9}$";

    static {
        sc = new Scanner(System.in);
    }

    public static double round(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String roundedNumber = decimalFormat.format(d).replace(",", ".");
        System.out.println(roundedNumber);
        return Double.parseDouble(roundedNumber);
    }

    public static String getString(String str) {
        try {
            System.out.println(str);
            String data = sc.nextLine();
            if (data.equals("")) {
                throw new Exception();
            }
            return data;
        } catch (Exception e) {
            System.out.println("Empty data. Input again!");
            return getString(str);
        }
    }


    public static int getInt(String str) {
        try {
            return Integer.parseInt(getString(str));
        } catch (Exception e) {
            System.out.println("Input invalid");
            return getInt(str);
        }
    }

    public static boolean checkInt(String str) {
        if(str.matches(REGEX_NUMBER)){
            return true;
        } else  {
            System.out.println("");
            System.out.println("Invalid select! Please try again!!");
            return false;
        }
    }


    public static int getIntWithBound(String str, int begin, int end) {
        try {
            int number = getInt(str);
            if (number < begin || number > end) {
                throw new NumberFormatException(String.format("Please input number between %d and %d", begin, end));
            }
            return number;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getIntWithBound(str, begin, end);
        }
    }

    public static Date getDate(String str) {
        try {
            System.out.println("Please enter date with format YYYY-MM-DD");
            return Date.valueOf(getString(str));
        } catch (Exception e) {
            System.out.println("Invalid Date Format");
            return getDate(str);
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
