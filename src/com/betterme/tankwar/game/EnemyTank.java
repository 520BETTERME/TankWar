package com.betterme.tankwar.game;

import java.awt.*;

public class EnemyTank extends Tank implements Runnable{

    /**
     * 构造函数，在地图MapPanel中创建敌人坦克对象调用
     * @param x
     * @param y
     */
    public EnemyTank(int x, int y){

        super(x, y);
        this.direction = (int)(Math.random() * 4);
        this.speed = Setting.getEnemySpeed();
        this.color = Color.YELLOW;
    }

    /**
     * 构造函数，载入进度调用
     * @param x
     * @param y
     * @param direction
     */
    public EnemyTank(int x, int y, int direction){
        super(x, y);
        this.direction = direction;
        //System.out.println(shells.size());
        // 载入进度时创建该对象时，还没有进入面板得到对应关卡坦克的速度，此句在此处无效
        //this.speed = Setting.getEnemySpeed();
        //System.out.println(speed);
        this.color = Color.YELLOW;
    }

    /**
     * 敌人的炮弹击中我
     * @param myTank
     * @return 返回是否击中
     */
    public boolean hitMe(MyTank myTank){

        boolean isHitMe = false;
        for (int j = 0; j < shells.size(); j++ ){
            Shell s = shells.get(j);
            if (s.hitTank(myTank)){
                Recorder.reduceScore();
                Recorder.reduceMylife();
                isHitMe = true;
            }
        }
        return isHitMe;
    }

    /**
     * 撞到障碍物
     * @return 返回不可以继续前进的方向
     */
    public int crashObstacle(){

        int stop = 4;
        for (int i = 0; i < MapPanel.enemyTanks.size(); i++ ) {

            EnemyTank et = MapPanel.enemyTanks.get(i);
            for (int j = 0; j < Obstacle.obstacles.size(); j++) {

                Obstacle obstacle = Obstacle.obstacles.get(j);
                if (obstacle instanceof Obstacle.Wall || obstacle instanceof Obstacle.Steel || obstacle instanceof Obstacle.Lake) {
                    Rectangle obRectangle = obstacle.getRectangle();
                    switch (et.direction) {
                        case 0:
                            //假设坦克向前前进一次
                            if (obRectangle.intersects(et.getNextRectangle()))
                                stop = 0;
                            break;
                        case 1:
                            if (obRectangle.intersects(et.getNextRectangle()))
                                stop = 1;
                            break;
                        case 2:
                            if (obRectangle.intersects(et.getNextRectangle()))
                                stop = 2;
                            break;
                        case 3:
                            if (obRectangle.intersects(et.getNextRectangle()))
                                stop = 3;
                            break;
                    }
                }
            }
        }
        return stop;
    }

    /**
     * 进程休眠
     */
    private void sleep(){
        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断地图上的坦克是否碰到其他坦克
     * @return 返回不可以继续前进的方向
     */
    private int crashOthers(){

        int dir = 4;
        for (int i = 0; i < MapPanel.enemyTanks.size(); i++ ){
            EnemyTank et = MapPanel.enemyTanks.get(i);
            //如果不是自己
            if (et != this){
                EnemyTank me = new EnemyTank(this.x, this.y);
                if (isCrashTank(me, et)){
                    dir = me.direction;
                }
            }
        }
        return dir;
    }

    @Override
    public void run() {

        //如果退出游戏或者敌人全部死亡，终止此线程
        while (MapPanel.enemyTanks.size() != 0 || !MapPanel.isRunThread()) {
            //System.out.println("the enemy thread is running...");
//        while (true){
            //不加这个进程休眠的化，会导致最终无法绘制敌人坦克
            sleep();
            //随机产生坦克前进的次数后转换方向,25~30
            int times = (int) (Math.random() * 80);
            /**
             *判断敌人坦克是否在地图里，如果放在此处，一旦在下面的语句中坦克进入外面的化，
             *就会导致相应的坦克的进程不会执行这里面的语句，导致这个坦克永远在地图外面徘徊
             *if (getX() < 960 && getY() < 540 && getX() > 0 && getY()> 0)
             */
            switch (direction) {
                case 0:
                    for (int i = 0; i < times; i++) {
                        sleep();
                        if (y > 0 && crashObstacle() != 0 && crashObstacle() != 0) {
                                moveUp();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < times; i++) {
                        sleep();
                        if (y + 30 < 540 && crashObstacle() != 1 && crashOthers() != 1) {
                            moveDown();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < times; i++) {
                        sleep();
                        if (x > 0 && crashOthers() != 2 && crashObstacle() != 2) {
                            moveLeft();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < times; i++) {
                        sleep();
                        if (x + 30 < 960 && y + 25 < 540 && crashObstacle() != 3 && crashOthers() != 3) {
                            moveRight();
                        }
                        break;
                    }
//                    fire();
//                    direction = (int) (Math.random() * 4);
//              System.out.println(isLive);
//                    if (isLive == false) {
//                        break;
//                    }
            }
            fire();
            direction = (int) (Math.random() * 4);
//            System.out.println(isLive);
            if (!isLive) {
                break;
            }
        }
    }

}
