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
                if (obstacle instanceof Wall || obstacle instanceof Steel || obstacle instanceof Lake) {
                    Rectangle obRectangle = new Rectangle(obstacle.x, obstacle.y, 30, 30);
                    int x = et.getX();
                    int y = et.getY();
                    switch (et.getDirection()) {
                        case 0:
                            //假设坦克向前前进一次,得到坦克前进后的矩形
                            Rectangle tankRectangle0 = new Rectangle(x, y - et.getSpeed(), 25, 30);
                            if (obRectangle.intersects(tankRectangle0)) {
                                stop = 0;
                                //System.out.println("here0");
                            }
                            break;
                        case 1:
                            Rectangle tankRectangle1 = new Rectangle(x, y + et.getSpeed(), 25, 30);
                            if (obRectangle.intersects(tankRectangle1)) {
                                stop = 1;
                                //System.out.println("here1");
                            }
                            break;
                        case 2:
                            Rectangle tankRectangle2 = new Rectangle(x - et.getSpeed(), y, 30, 25);
                            if (obRectangle.intersects(tankRectangle2)) {
                                stop = 2;
                                //System.out.println("here2");
                            }
                            break;
                        case 3:
                            Rectangle tankRectangle3 = new Rectangle(x + et.getSpeed(), y, 30, 25);
                            if (obRectangle.intersects(tankRectangle3)) {
                                stop = 3;
                                //System.out.println("here3");
                            }
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

    //判断地图上的坦克是否碰到其他坦克
//    private boolean isCrashOthers(){
//
//        boolean b = false;
//        for (int i = 0; i < etsOnMap.size(); i++ ){
//            EnemyTank et = etsOnMap.get(i);
//            //如果不是自己
//            if (et != this){
////                switch (direction){
////                    case 0:
////                    case 1:
////                        if (et.direction == 0 || et.direction == 1){
////                            if (x >= et.x && x <= et.x+25 && y >= et.y && y <= et.y+30)    //左上角
////                                b = true;
////                            else if (x+25 >= et.x && x+25 <= et.x+25 && y >= et.y && y <= et.y+30)    //右上角
////                                b = true;
////                            else if (x >= et.x && x <= et.x+25 && y+30 >= et.y && y+30 <= et.y+30)    //左下角
////                                b = true;
////                            else if (x+25 >= et.x && x+25 <= et.x+25 && y+30 >= et.y && y+30 <= et.y+30)    //右下角
////                                b = true;
////                        }else if (et.direction == 2 || et.direction == 3){
////                            if (x >= et.x && x <= et.x+30 && y >= et.y && y <= et.y+25)
////                                b = true;
////                            else if (x+25 >= et.x && x+25 <= et.x+30 && y >= et.y && y <= et.y+25)
////                                b = true;
////                            else if (x >= et.x && x <= et.x+30 && y+30 >= et.y && y+30 <= et.y+25)
////                                b = true;
////                            else if (x+25 >= et.x && x+25 <= et.x+30 && y+30 >= et.y && y+30 <= et.y+25)
////                                b = true;
////                        }
////                        break;
////                    case 2:
////                    case 3:
////                        if (et.direction == 0 || et.direction == 1){
////                            if (x >= et.x && x <= et.x+25 && y >= et.y && y <= et.y+30)
////                                b = true;
////                            else if (x+30 >= et.x && x+30 <= et.x+25 && y >= et.y && y <= et.y+30)
////                                b = true;
////                            else if (x >= et.x && x <= et.x+25 && y+25 >= et.y && y+25 <= et.y+30)
////                                b = true;
////                            else if (x+30 >= et.x && x+30 <= et.x+25 && y+25 >= et.y && y+25 <= et.y+30)
////                                b = true;
////                        }else if (et.direction == 2 || et.direction == 3){
////                            if (x >= et.x && x <= et.x+30 && y >= et.y && y <= et.y+25)
////                                b = true;
////                            else if (x+30 >= et.x && x+30 <= et.x+30 && y >= et.y && y <= et.y+25)
////                                b = true;
////                            else if (x >= et.x && x <= et.x+30 && y+25 >= et.y && y+25 <= et.y+25)
////                                b = true;
////                            else if (x+30 >= et.x && x+30 <= et.x+30 && y+25 >= et.y && y+25 <= et.y+25)
////                                b = true;
////                        }
////                        break;
////                }
//                switch(this.direction) {
//                    case 0:
//                        if(et.direction==0||et.direction==2) {
//                            //左点
//                            if(this.x>=et.x&&this.x<=et.x+25&&this.y>=et.y&&this.y<=et.y+30) {
//                                b = true;
//                                }
//                                if(this.x+25>=et.x&&this.x+25<=et.x+25&&this.y>=et.y&&this.y<=et.y+30) {
//                                b = true;
//                                }
//                        }
//                        if(et.direction==3||et.direction==1) {
//                            if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+25) {
//                                b = true;
//                                }
//                                if(this.x+25>=et.x&&this.x+25<=et.x+30&&this.y>=et.y&&this.y<=et.y+25) {
//                                b = true;
//                                }
//                        }
//                        break;
//                    case 1:
//                        if(et.direction==0||et.direction==2) {
//                            //上点
//                            if(this.x+30>=et.x&&this.x+30<=et.x+25&&this.y>=et.y&&this.y<=et.y+30) {
//                                b = true;
//                            }
//                            //下点
//                            if(this.x+30>=et.x&&this.x+30<=et.x+25&&this.y+25>=et.y&&this.y+25<=et.y+30) {
//                                b = true;
//                            }
//                        }
//                        if(et.direction==3||et.direction==1) {
//                            if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y>=et.y&&this.y<=et.y+25) {
//                                b = true;
//                            }
//                            if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y+25>=et.y&&this.y+25<=et.y+25) {
//                                b = true;
//                            }
//                        }
//                        break;
//                    case 2:
//                        if(et.direction==0||et.direction==2) {
//                            //我的左点
//                            if(this.x>=et.x&&this.x<=et.x+25&&this.y+30>=et.y&&this.y+30<=et.y+30) {
//                                b = true;
//                            }
//                            //我的右点
//                            if(this.x+25>=et.x&&this.x+25<=et.x+25&&this.y+30>=et.y&&this.y+30<=et.y+30) {
//                                b = true;
//                            }
//                        }
//                        if(et.direction==3||et.direction==1) {
//                            if(this.x>=et.x&&this.x<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+25) {
//                                b = true;
//                            }
//                            if(this.x+25>=et.x&&this.x+25<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+25) {
//                                b = true;
//                            }
//                        }
//                        break;
//                    case 3:
//                        if(et.direction==0||et.direction==2) {
//                            //我的上一点
//                            if(this.x>=et.x&&this.x<=et.x+25&&this.y>=et.y&&this.y<=et.y+30) {
//                                b = true;
//                            }
//                            //下一点
//                            if(this.x>=et.x&&this.x<=et.x+25&&this.y+25>=et.y&&this.y+25<=et.y+30) {
//                                b = true;
//                            }
//                        }
//                        if(et.direction==3||et.direction==1) {
//                            //上一点
//                            if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+25) {
//                                b = true;
//                            }
//                            if(this.x>=et.x&&this.x<=et.x+30&&this.y+25>=et.y&&this.y+25<=et.y+25) {
//                                b = true;
//                            }
//                        }
//                        break;
//                }
//            }
//        }
//        return b;
//    }

    //是否碰到地图上障碍物

    //碰到地图上的障碍物
//    private void crashObstacle(){
//
//        for (int i = 0; i < obstacles.size(); i++ ){
//            Obstacle obstacle = obstacles.get(i);
//            if (obstacle instanceof Wall || obstacle instanceof Steel || obstacle instanceof Lake){
//                //Rectangle obRectangle = new Rectangle(obstacle.x, obstacle.y, 30, 30);
//                for (int j = 0; j < etsOnMap.size(); j++ ){
//                    EnemyTank et = etsOnMap.get(j);
//                    switch (direction){
//                        case 0:
//                            if ((et.getX() >= obstacle.x && et.getX() <= obstacle.x+30 && et.getY() >= obstacle.y && et.getY() <= obstacle.y+30) ||
//                                    et.getX()+25 >= obstacle.x && et.getX()+25 <= obstacle.x+30 && et.getY() >= obstacle.y && et.getY() <= obstacle.y+30){
//                                controlDir[0] = false;
//                                System.out.println("here0");
//                            }
//                            break;
//                        case 1:
//                            if ((et.getX() >= obstacle.x && et.getX() <= obstacle.x+30 && et.getY()+30 >= obstacle.y && et.getY()+30 <= obstacle.y+30) ||
//                                    et.getX()+25 >= obstacle.x && et.getX()+25 <= obstacle.x+30 && et.getY()+30 >= obstacle.y && et.getY()+30 <= obstacle.y+30){
//                                controlDir[1] = false;
//                                System.out.println("here1");
//                            }
//                            break;
//                        case 2:
//                            if ((et.getX() >= obstacle.x && et.getX() <= obstacle.x+30 && et.getY() >= obstacle.y && et.getY() <= obstacle.y+30) ||
//                                    et.getX() >= obstacle.x && et.getX() <= obstacle.x+30 && et.getY()+25 >= obstacle.y && et.getY()+25 <= obstacle.y+30){
//                                controlDir[2] = false;
//                                System.out.println("here2");
//                            }
//                            break;
//                        case 3:
//                            if ((et.getX()+30 >= obstacle.x && et.getX()+30 <= obstacle.x+30 && et.getY() >= obstacle.y && et.getY() <= obstacle.y+30) ||
//                                    et.getX()+30 >= obstacle.x && et.getX()+30 <= obstacle.x+30 && et.getY()+25 >= obstacle.y && et.getY()+25 <= obstacle.y+30){
//                                controlDir[3] = false;
//                                System.out.println("here3");
//                            }
//                            break;
//                    }
//                }
//            }
//        }
//    }

    @Override
    public void run() {

        //若果退出游戏或者敌人全部死亡，终止此线程
        while (MapPanel.enemyTanks.size() != 0 || MapPanel.isRunThread() == true){
            //System.out.println("the enemy thread is running...");
//        while (true){
            //不加这个进程休眠的化，会导致最终无法绘制敌人坦克
            sleep();
            //随机产生坦克前进的次数后转换方向,25~30
            int times = (int)(Math.random() * 80);
            /**
             *判断敌人坦克是否在地图里，如果放在此处，一旦在下面的语句中坦克进入外面的化，
             *就会导致相应的坦克的进程不会执行这里面的语句，导致这个坦克永远在地图外面徘徊
             *if (getX() < 960 && getY() < 540 && getX() > 0 && getY()> 0)
             */
            switch (direction){
                case 0:
                    for (int i = 0; i < times; i++ ){
                        sleep();
                        if (y > 0 && crashObstacle() != 0){
                            moveUp();
                            //System.out.println("up ");
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < times; i++ ){
                        sleep();
                        if (y+30 < 540 && crashObstacle() != 1){
                            moveDown();
                            //System.out.println("down ");
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < times; i++ ){
                        sleep();
                        if (x > 0 && crashObstacle() != 2){
                            moveLeft();
                            //System.out.println("left ");
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < times; i++ ){
                        sleep();
                        if (x+30 < 960 && y+25 < 540 && crashObstacle() != 3){
                            moveRight();
                            //System.out.println("right ");
                        }
                    }
                    break;
            }
            fire();
            setDirection((int)(Math.random() * 4));
//            System.out.println(isLive);
            if (isLive == false){
                break;
            }
        }
    }

}
