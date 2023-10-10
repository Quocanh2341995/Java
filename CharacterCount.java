import java.util.Scanner;

public class CharacterCount {
    public static void main(String[] args) {
        // Khai báo một chuỗi và gán cho nó một giá trị
        String str = "Hello World";

        // Khai báo một biến ký tự và gán hoặc nhập từ bàn phím một giá trị
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập một ký tự: ");
        char targetChar = scanner.next().charAt(0);

        // Khai báo biến count và gán giá trị 0, để lưu số ký tự đếm được trong chuỗi
        int count = 0;

        // Sử dụng vòng lặp duyệt từng ký tự trong chuỗi và tăng biến đếm nếu ký tự trùng khớp
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == targetChar) {
                count++;
            }
        }

        // In ra giá trị biến đếm
        System.out.println("Số lần xuất hiện của ký tự " + targetChar + " trong chuỗi là: " + count);
    }
}
