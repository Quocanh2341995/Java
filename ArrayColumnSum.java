import java.util.Scanner;

public class ArrayColumnSum {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số hàng của mảng: ");
        int rows = scanner.nextInt();
        System.out.print("Nhập số cột của mảng: ");
        int columns = scanner.nextInt();

        int[][] array = new int[rows][columns];

        System.out.println("Nhập các giá trị cho mảng:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.printf("Nhập giá trị cho phần tử thứ [%d][%d]: ", i, j);
                array[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Nhập số cột muốn tính tổng: ");
        int column = scanner.nextInt();

        int sum = 0;
        for (int i = 0; i < rows; i++) {
            sum += array[i][column];
        }

        System.out.println("Tổng của cột " + column + " là: " + sum);
    }
}
