import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        double exchangeRate = 23000;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập giá trị tiền $ : ");
        double usdAmount = scanner.nextDouble();

        double vndAmount = usdAmount * exchangeRate;
        System.out.println("Giá trị tương ứng tiền Việt Nam" + vndAmount + "vnd");
    }
}
