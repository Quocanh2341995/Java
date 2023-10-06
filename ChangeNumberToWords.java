import java.util.Scanner;

public class ChangeNumberToWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập một số: ");
        int number = scanner.nextInt();

        System.out.println("Số bạn đã nhập là:" + number);

        switch (number) {
            case 0:
                System.out.println("Số bạn đã nhập là: Zero");
                break;
            case 1:
                System.out.println("Số bạn đã nhập là: One");
                break;
            case 2:
                System.out.println("Số bạn đã nhập là: Two");
                break;
            case 3:
                System.out.println("Số bạn đã nhập là: Three");
                break;
            case 4:
                System.out.println("Số bạn đã nhập là: Four");
                break;
            case 5:
                System.out.println("Số bạn đã nhập là: Five");
                break;
            case 6:
                System.out.println("Số bạn đã nhập là: Six");
                break;
            case 7:
                System.out.println("Số bạn đã nhập là: Seven");
                break;
            case 8:
                System.out.println("Số bạn đã nhập là: Eight");
                break;
            case 9:
                System.out.println("Số bạn đã nhập là: Nine");
                break;
//            default:
//                System.out.println("Out of Ability");
//                break;
        }

        if ( number >= 10 && number < 20) {
            int ones = number % 10;
            switch (ones) {
                case 0:
                    System.out.println("Số bạn đã nhập là: Ten");
                    break;
                case 1:
                    System.out.println("Số bạn đã nhập là: Eleven");
                    break;
                case 2:
                    System.out.println("Số bạn đã nhập là: Twelve");
                    break;
                case 3:
                    System.out.println("Số bạn đã nhập là: Thirteen");
                    break;
                case 4:
                    System.out.println("Số bạn đã nhập là: Fourteen");
                    break;
                case 5:
                    System.out.println("Số bạn đã nhập là: Fifteen");
                    break;
                case 6:
                    System.out.println("Số bạn đã nhập là: Sixteen");
                    break;
                case 7:
                    System.out.println("Số bạn đã nhập là: Seventeen");
                    break;
                case 8:
                    System.out.println("Số bạn đã nhập là: Eighteen");
                    break;
                case 9:
                    System.out.println("Số bạn đã nhập là: Nineteen");
                    break;
            }

        }

        if ( number >= 20 && number < 100) {
            int tens = number / 10;
            int ones = number % 10;

            String tensString = "";
            switch (tens) {
                case 2:
                    tensString = "Twenty";
                    break;
                case 3:
                    tensString = "Thirty";
                    break;
                case 4:
                    tensString = "Fourty";
                    break;
                case 5:
                    tensString = "Fifty";
                    break;
                case 6:
                    tensString = "Sixty";
                    break;
                case 7:
                    tensString = "Seventy";
                    break;
                case 8:
                    tensString = "Eightty";
                    break;
                case 9:
                    tensString = "Ninety";
                    break;
            }

            String onesString = "";
            switch (ones) {
                case 1:
                    onesString = "One";
                    break;
                case 2:
                    onesString = "Two";
                    break;
                case 3:
                    onesString = "Three";
                    break;
                case 4:
                    onesString = "Four";
                    break;
                case 5:
                    onesString = "Five";
                    break;
                case 6:
                    onesString = "Six";
                    break;
                case 7:
                    onesString = "Seven";
                    break;
                case 8:
                    onesString = "Eight";
                    break;
                case 9:
                    onesString = "Nine";
                    break;
            }

            String numbersString = tensString + " " + onesString;
            System.out.println("Số bạn đã nhập là: " + numbersString);
        }

        if ( number >= 100 && number < 1000) {
            int hundreds = number / 100;
            int tens = number / 10;
            int ones = number % 10;

            String hundredsString = "";
            switch (hundreds) {
                case 1:
                    hundredsString = "One hundred";
                    break;
                case 2:
                    hundredsString = "Two hundred";
                    break;
                case 3:
                    hundredsString = "Three hundred";
                    break;
                case 4:
                    hundredsString = "Four hundred";
                    break;
                case 5:
                    hundredsString = "Five hundred";
                    break;
                case 6:
                    hundredsString = "Six hundred";
                    break;
                case 7:
                    hundredsString = "Seven hundred";
                    break;
                case 8:
                    hundredsString = "Eight hundred";
                    break;
                case 9:
                    hundredsString = "Nine hundred";
                    break;
            }

            String tensString = "";
            switch (tens) {
                case 2:
                    tensString = "Twenty";
                    break;
                case 3:
                    tensString = "Thirty";
                    break;
                case 4:
                    tensString = "Fourty";
                    break;
                case 5:
                    tensString = "Fifty";
                    break;
                case 6:
                    tensString = "Sixty";
                    break;
                case 7:
                    tensString = "Seventy";
                    break;
                case 8:
                    tensString = "Eightty";
                    break;
                case 9:
                    tensString = "Ninety";
                    break;
            }

            String onesString = "";
            switch (ones) {
                case 1:
                    onesString = "One";
                    break;
                case 2:
                    onesString = "Two";
                    break;
                case 3:
                    onesString = "Three";
                    break;
                case 4:
                    onesString = "Four";
                    break;
                case 5:
                    onesString = "Five";
                    break;
                case 6:
                    onesString = "Six";
                    break;
                case 7:
                    onesString = "Seven";
                    break;
                case 8:
                    onesString = "Eight";
                    break;
                case 9:
                    onesString = "Nine";
                    break;
            }
            String numbersString = hundredsString + " " + tensString + " " + onesString;
            System.out.println("Số bạn đã nhập là: " + numbersString);
        } else {
            System.out.println("Out of ability");
        }

    }
}
