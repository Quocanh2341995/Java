// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Fan fan1 = new Fan();
        fan1.setSpeed(3);
        fan1.setRadius(10.0);
        fan1.setColor("Yellow");
        fan1.setOn(true);

        Fan fan2 = new Fan();
        fan2.setSpeed(2);
        fan2.setRadius(5.0);
        fan2.setColor("Blue");
        fan2.setOn(false);

        System.out.println("fan1 : " + fan1.toString());
        System.out.println("fan2 : " + fan2.toString());
    }
}