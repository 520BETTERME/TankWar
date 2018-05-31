package com.betterme.tankwar.game;

import java.awt.*;

public class MyTank extends Tank {

    //    int x;
    //    int y;
    //此处加上x y，会导致坦克的坐标不变。

    public MyTank(){

        super();
    }

    public MyTank(int x, int y, int direction){
        super(x, y);
        this.direction = direction;
        this.color = Color.RED;
        this.speed = 5;
        //System.out.println(this.shells.size());
    }

    public MyTank(int x, int y){
        super(x, y);
        this.color = Color.RED;
        this.speed = 5;
    }

    /**
     * 我的炮弹击中敌人坦克
     * @param et 敌人坦克对象
     * @return 返回是否击中
     */
    public boolean hitEnemy(EnemyTank et){

        boolean isHitEnemy = false;
        if (shells.size() != 0){
            for (int i = 0; i < shells.size(); i++ ){
                Shell ms = shells.get(i);
                    if(ms.hitTank(et)){
                        Recorder.increaseScore();
                        Recorder.reduceEnemyNum();
                        isHitEnemy = true;
                    }
                }
            }
        return isHitEnemy;
    }

    /**
     * myTank碰到城墙，钢铁,水/心/炮弹，分别返回012
     * @return 返回撞到的障碍物的种类
     */
    public String crashObstacle(){

        String kind = "3";
        for (int i = 0; i < Obstacle.obstacles.size(); i++ ) {
            Obstacle obstacle = Obstacle.obstacles.get(i);
            if (getNextRectangle().intersects(obstacle.getRectangle()))
                kind = obstacle.judgeObstacle(obstacle);
        }
        return kind;
    }

    /**
     * 判断是否撞到敌人坦克
     * @return 返回是否
     */
    public boolean isCrashEnemy(){

        boolean crashEnemy = false;
        for (int i = 0; i < MapPanel.enemyTanks.size(); i++ ){
            EnemyTank et = MapPanel.enemyTanks.get(i);
            if (getNextRectangle().intersects(et.getRectangle()))
                crashEnemy = true;
        }
        return crashEnemy;
    }

}
