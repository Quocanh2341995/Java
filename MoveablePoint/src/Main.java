// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Point point = new Point (2.5f, 8.5f);
        System.out.println(point.toString());

       MoveblePoint point1 = new MoveblePoint(4.5f, 6.5f, 9.5f, 8.5f);
        System.out.println(point1.toString());
        point1.move();
        System.out.println("vi tri moi : " + point1.toString());
    }
}