package com.betterme.tankwar.game;

public class Bomb {

    private int x, y;
    private boolean isLive = true;
    private int time = 9;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

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

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void timeDown(){

        if (time > 0){
            time--;
        }else {
            isLive = false;
        }
    }
}
