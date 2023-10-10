import java.util.Scanner;

public class FindMinInArray {
    public static void main(String[] args) {

        final int SIZE = 5;
        int[] array = new int[SIZE];

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập các giá trị cho mảng:");
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("Nhập giá trị cho phần tử thứ %d: ", i + 1);
            array[i] = scanner.nextInt();
        }

        int minElement = array[0];

        for (int i = 1; i < SIZE; i++) {
            if (array[i] < minElement) {
                minElement = array[i];
            }
        }

        System.out.println("Giá trị nhỏ nhất trong mảng là: " + minElement);
    }
}
