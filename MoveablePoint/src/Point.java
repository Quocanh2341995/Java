public class Point {
    float x;
    float y;

    public Point () {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Point(float x, float y) {
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
        this.y = y;
    }

    public float[] getXY(){
        float[] toado = new float[2];
        toado[0] = x;
        toado[1] = y;
        return toado;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
