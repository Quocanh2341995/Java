public class Cylinder extends Circle {
    private double height;

    public Cylinder () {
    }
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getVolume() {
        return getArea() * height;
    }

    @Override
    public String toString() {
        return "A Cylinder with radius = " + getRadius() + " and height = " + height;
    }


}
