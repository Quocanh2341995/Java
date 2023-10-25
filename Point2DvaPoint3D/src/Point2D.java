public class Point2D {
    float x;
    float y;

    public Point2D() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Point2D (float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setXY (float x, float y) {
        this.x = x;
        this.y =y;
    }


    public float[] getXY() {
        float[] coordinates = new float[2];
        coordinates[0] = x;
        coordinates[1] = y;
        return coordinates;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
