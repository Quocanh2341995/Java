import java.util.Scanner;

public class FindMaxInMatrix {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số hàng của ma trận: ");
        int rows = scanner.nextInt();
        System.out.print("Nhập số cột của ma trận: ");
        int columns = scanner.nextInt();

        int[][] matrix = new int[rows][columns];

        System.out.println("Nhập các giá trị cho ma trận:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.printf("Nhập giá trị cho phần tử thứ [%d][%d]: ", i, j);
                matrix[i][j] = scanner.nextInt();
            }
        }

        int maxElement = matrix[0][0];
        int maxRow = 0;
        int maxColumn = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] > maxElement) {
                    maxElement = matrix[i][j];
                    maxRow = i;
                    maxColumn = j;
                }
            }
        }

        System.out.printf("Phần tử lớn nhất trong ma trận là %d, nằm tại vị trí [%d][%d].\n",
                maxElement, maxRow, maxColumn);
    }
}
