import java.util.Scanner;
public class QuadraticEquation {
    private double a;
    private double b;
    private double c;

    public QuadraticEquation (double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA(){
        return a;
    }

    public double getB(){
        return b;
    }

    public double getC(){
        return c;
    }

    public double getDiscriminant() {
        return b * b - 4 * a * c;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập vào giá trị a: ");
        double a = scanner.nextDouble();
        System.out.print("Nhập vào giá trị b: ");
        double b = scanner.nextDouble();
        System.out.print("Nhập vào giá trị c: ");
        double c = scanner.nextDouble();

        QuadraticEquation equation = new QuadraticEquation(a, b, c);
        double discriminant = equation.getDiscriminant();

        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println("Phương trình có 2 nghiệm: " + root1 + " and " + root2);
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            System.out.println("Phương trình có 1 nghiệm: " + root);
        } else {
            System.out.println("Phương trình vô nghiệm");
        }
    }

}

