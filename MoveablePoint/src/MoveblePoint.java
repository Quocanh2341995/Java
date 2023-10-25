public class MoveblePoint extends Point {
    float xSpeed;
    float ySpeed;

    public MoveblePoint() {
        xSpeed = 0.0f;
        ySpeed = 0.0f;
    }

    public MoveblePoint(float x, float y, float xSpeed, float ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public MoveblePoint (float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void setSpeed (float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public float[] getSpeed() {
        float[] toado = new float[2];
        toado[0] = xSpeed;
        toado[1] = ySpeed;
        return toado;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public void move() {
        setX(getX() + xSpeed);
        setY(getY() + ySpeed);
    }
}
