import java.util.Map;
import java.util.TreeMap;

public class CountWord {
    public static void main(String[] args) {
        String str = "Chương trình sử dụng một TreeMap để lưu trữ một mục bao gồm một từ và số lượng của nó.";

        // Tạo một TreeMap để lưu trữ từ và số lượng của nó
        Map<String, Integer> wordCountMap = new TreeMap<>();

        // Duyệt qua từng từ trong chuỗi
        String[] words = str.split("\\s+"); // Tách chuỗi thành các từ dựa trên khoảng trắng
        for (String word : words) {
            // Chuyển đổi từ thành chữ hoa hoặc chữ thường
            String formattedWord = word.toLowerCase(); // Chuyển toàn bộ về chữ thường

            // Kiểm tra xem từ đã có trong map hay chưa
            if (wordCountMap.containsKey(formattedWord)) {
                // Tăng giá trị số lượng từ lên 1 nếu từ đã tồn tại trong map
                int count = wordCountMap.get(formattedWord);
                wordCountMap.put(formattedWord, count + 1);
            } else {
                // Thêm từ vào map với giá trị mặc định là 1 nếu từ chưa có trong map
                wordCountMap.put(formattedWord, 1);
            }
        }

        // Hiển thị map gồm từ và số lượng của từ
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            System.out.println(word + ": " + count);
        }
    }
}
