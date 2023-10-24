public class RectangleTest {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        System.out.println(rectangle);

        rectangle = new Rectangle(2, 6);
        System.out.println(rectangle);

        rectangle = new Rectangle(2.5, 4.5, "orange", true);
        System.out.println(rectangle);
    }
}
