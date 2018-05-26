package com.betterme.tankwar.game;

import java.awt.*;

public class Shell implements Runnable{

    private int x;
    private int y;
    private int direction;
    private int speed = Setting.getEnemySpeed();
    boolean isLive = true;
    private Color color;

//    public Shell(int x, int y, int direction, int speed, Color color) {
//        this.x = x;
//        this.y = y;
//        this.direction = direction;
//        this.speed = speed;
//        this.color = color;
//    }

    public Shell(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    /**
     * 炮弹是否击中坦克
     * @param tank 坦克对象，敌人坦克我的坦克都可以
     * @return 返回是否击中
     */
    public boolean hitTank(Tank tank) {

        boolean isHit = false;
        int sx = getX(), sy = getY(), tx = tank.getX(), ty = tank.getY();
        switch (tank.getDirection()) {
            case 0:
            case 1:
                if (sx >= tx && sx <= tx + 25 && sy >= ty && sy <= ty + 30) {
                    tank.isLive = false;
                    isLive = false;
                    MapPanel.bombs.add(new Bomb(tx, ty));
                    isHit = true;
                }
                break;
            case 2:
            case 3:
                if (sx >= tx && sx <= tx + 30 && sy >= ty && sy <= ty + 25) {
                    tank.isLive = false;
                    isLive = false;
                    MapPanel.bombs.add(new Bomb(tx, ty));
                    isHit = true;
                    break;
                }
        }
        return isHit;
    }

    /**
     * 判断炮弹击中障碍物种类，带有生命返回0，不带有生命(Steel)返回1
     * @param tank 坦克种类（对象）
     * @return 返回撞到的障碍物的种类
     */
    public int hitObstacle(Tank tank){

        int kind = 2;
        for (int i = 0; i < Obstacle.obstacles.size(); i++ ) {
            Obstacle o = Obstacle.obstacles.get(i);
            if (getX() >= o.x && getX() <= o.x + 30 && getY() >= o.y && getY() <= o.y + 30) {
                if (o instanceof ObstacleWithLive) {
                    isLive = false;
                    tank.shells.remove(this);
                    ObstacleWithLive owl = (ObstacleWithLive) o;
                    owl.isLive = false;
                    Obstacle.obstacles.remove(owl);
                    kind = 0;
                    MapPanel.bombs.add(new Bomb(o.x, o.y));
                } else if (o instanceof Steel) {
                    isLive = false;
                    tank.shells.remove(this);
                    kind = 1;
                    MapPanel.bombs.add(new Bomb(o.x, o.y));
                }
            }
        }
        return kind;
    }

    @Override
    public void run() {

        while (MapPanel.isRunThread() == true) {
            try {
                Thread.sleep(40);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
            switch (direction) {
                case 0:
                    y = y - speed;
                    break;
                case 1:
                    y = y + speed;
                    break;
                case 2:
                    x = x - speed;
                    break;
                case 3:
                    x = x + speed;
            }
            //炮弹跑出地图外
            if (x > 960 || y >540 || x < 0 || y < 0){
                isLive = false;
                break;
            }
        }
    }

}
