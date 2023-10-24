class Circle {
    private double radius;
    private String color;

    public Circle() {
        radius = 1.0;
        color = "red";
    }

    public Circle(double radius) {
        this.radius = radius;
        color = "red";
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }
}

class TestCircle {
    public static void main(String[] args) {
        Circle circle = new Circle(2.5);
        System.out.println("Radius: " + circle.getRadius());
        System.out.println("Area: " + circle.getArea());
    }
}
