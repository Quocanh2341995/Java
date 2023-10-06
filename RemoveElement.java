import java.util.Scanner;

public class RemoveElement {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập số lượng phần tử trong mảng: ");
        int N = scanner.nextInt();

        int [] array = new int [N];
        System.out.println("Nhập các phần tử của mảng: ");

        for (int i = 0; i < N; i++) {
            System.out.print("Phần tử thứ " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        System.out.print("Nhập phần tử cần xoá (X): ");
        int X = scanner.nextInt();

        int index_del = -1;
        for (int i = 0; i < N; i++) {
            if (array[i] == X) {
                index_del = i;
                break;
            }
        }

        if (index_del != -1) {
            for (int i = index_del; i < N - 1; i++) {
                array[i] = array[i + 1];
            }
            N--; // Giảm số lượng phần tử trong mảng đi 1
            System.out.println("Phần tử " + X + " đã được xoá khỏi mảng.");
        } else {
            System.out.println("Phần tử " + X + " không xuất hiện trong mảng.");
        }

        System.out.println("Mảng sau khi xoá phần tử X:");
        for (int i = 0; i < N; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
