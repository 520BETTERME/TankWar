package com.betterme.tankwar.game;
/**
 * 坦克类，是我的坦克和敌人坦克的父类
 */

import java.awt.*;
import java.util.Vector;

public class Tank {

    protected int x;
    protected int y;
    protected Color color;
    protected int speed;
    //坦克的方向 0，1，2，3 分别表示上下左右
    protected int direction;
    protected Vector<Shell> shells = new Vector<>();
    protected Shell shell;
    protected boolean isLive = true;

    public Tank(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Tank(){

    }

//    public Tank(int x, int y, Color color) {
//        this.x = x;
//        this.y = y;
//        this.color = color;
//    }

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

    //坦克移动
    public void moveUp(){
        y = y - speed;
    }

    public void moveDown(){
        y = y + speed;
    }

    public void moveLeft(){
        x = x - speed;
    }

    public void moveRight(){
        x = x + speed;
    }

    /**
     * 坦克开火
     */
    public void fire(){

        switch (direction){
            case 0:
                shell = new Shell(x + 11, y - 6, 0);
                shells.add(shell);
                break;
            case 1:
                shell = new Shell(x + 11, y + 36,1);
                shells.add(shell);
                break;
            case 2:
                shell = new Shell(x - 5, y + 11, 2);
                shells.add(shell);
                break;
            case 3:
                shell = new Shell(x + 35, y + 11, 3);
                shells.add(shell);
                break;
        }
        Thread thread = new Thread(shell);
        thread.start();
    }

    /**
     * 得到坦克所处的矩形区域
     * @return
     */
    public Rectangle getRectangle(){

        Rectangle rectangle = null;
        switch (direction){
            case 0:
            case 1:
              rectangle = new Rectangle(x, y, 25, 30);
              break;
            case 2:
            case 3:
                rectangle = new Rectangle(x, y, 30, 25);
                break;
        }
        return rectangle;
    }

    /**
     * 得到坦克移动后的矩形区域
     * @return
     */
    public Rectangle getNextRectangle(){
        Rectangle rectangle = null;
        switch (direction){
            case 0:
                rectangle = new Rectangle(x, y-speed, 25, 30);
                break;
            case 1:
                rectangle = new Rectangle(x, y+speed, 25, 30);
                break;
            case 2:
                rectangle = new Rectangle(x-speed, y, 30, 25);
                break;
            case 3:
                rectangle = new Rectangle(x+speed, y, 30, 25);
                break;
        }
        return rectangle;
    }

    /**
     * 判断坦克是否撞到坦克
     * @param tank 坦克对象，enemyTank or MyTank
     * @param anotherTank 另一个坦克
     * @return 返回是否撞到
     */
    public boolean isCrashTank(Tank tank , Tank anotherTank) {

        boolean b = tank.getNextRectangle().intersects(anotherTank.getRectangle());
//        if (b)
//            System.out.println(b + " " + tank.direction + " " + anotherTank.direction);
        return (b);
    }

}
