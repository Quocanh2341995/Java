import java.util.Stack;

public class ReverseArrayUsingStack {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};

        // Tạo một stack và đẩy các phần tử của mảng vào stack
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < array.length; i++) {
            stack.push(array[i]);
        }

        // Lấy các phần tử từ stack để đảo ngược thứ tự trong mảng
        for (int i = 0; i < array.length; i++) {
            array[i] = stack.pop();
        }

        // In mảng đã đảo ngược
        System.out.print("Mảng đã đảo ngược: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
