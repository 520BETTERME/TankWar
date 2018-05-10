package Game;

import java.awt.*;

public class Tank {

    int x;
    int y;
    Color color;
    int speed = 5;
    //坦克的方向 0，1，2，3 分别表示上下左右
    int direction = 2;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Tank(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
}
