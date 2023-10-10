import java.util.Scanner;

public class MatrixSum {
    public static void main(String[] args) {
        // Thu thập mảng từ nhập liệu của người dùng
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số hàng và số cột của ma trận: ");
        int size = scanner.nextInt();

        // Tạo ma trận vuông với kích thước nhập từ người dùng
        int[][] matrix = new int[size][size];

        // Nhập giá trị cho từng phần tử trong ma trận
        System.out.println("Nhập các giá trị cho ma trận:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("Nhập giá trị cho phần tử thứ [%d][%d]: ", i, j);
                matrix[i][j] = scanner.nextInt();
            }
        }

        //  Tính tổng các phần tử của ma trận đã nhập
        // Tính tổng các phần tử có tọa độ hàng ngang và cột dọc bằng nhau
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += matrix[i][i];
        }

        // In ra kết quả tổng
        System.out.println("Tổng các phần tử có tọa độ hàng ngang và cột dọc bằng nhau là: " + sum);
    }
}
