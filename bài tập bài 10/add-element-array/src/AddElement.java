import java.util.Scanner;
public class AddElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số phần tử trong mảng : ");

        int N = scanner.nextInt();
        int [] array = new int[N];
        for ( int i = 0; i < N; i++ ) {
            System.out.printf("Nhập phần tử thứ %d : ", i + 1);
            array[i] = scanner.nextInt();
        }
        System.out.print("Nhập vào số cần chèn");
        int X = scanner.nextInt();

        System.out.print("Nhập vị trí index cần chèn :");
        int index = scanner.nextInt();

        if ( index < 0 || index > array.length -1) {
            System.out.println("Không chèn được phần tử vào mảng.");
        } else {
            int [] newArray = new int[array.length + 1];
            for ( int i = 0; i < index; i++ ) {
                newArray[i] = array[i];
            }

            newArray[index] = X;
            for ( int i = index + 1; i < newArray.length; i++) {
                newArray[i] = array[i -1];
            }

            System.out.print("Mảng in ra sau khi chèn phần tử x là");
                    for ( int i = 0; i < newArray.length; i++) {
                        System.out.print(newArray[i] + "");
                    }

        }
    }
}
