package com.betterme.tankwar.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 障碍物类
 */
public class Obstacle {

    int x, y;
    Image image;
    ArrayList<Wall> walls = new ArrayList<>();
    ArrayList<Steel> steels = new ArrayList<>();
    public static LinkedList<Grass> grasses = new LinkedList<>();
    public static ArrayList<Obstacle> obstacles = new ArrayList<>();

    public Obstacle(){ }

    public Obstacle(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     *初始化第一关障碍物
     */
    public void init_1() {

        int wallNum = 52;
        int steelNum = 32;
        int grassNum = 12;
        int lakeNum = 9;

        for (int i = 0; i < wallNum; i++ ){
           if (i < 12)
               obstacles.add(new Wall(100,100+30*i));
           else if (i >= 12 && i < 17)
               obstacles.add(new Wall(220+30*(i-12),100));
           else if (i >= 17 && i < 23)
               obstacles.add(new Wall(220+30*(i-17),430));
           else if (i >= 23 && i < 32)
               obstacles.add(new Wall(580+30*(i-23), 100));
           else if (i >=32 && i < 38)
               obstacles.add(new Wall(820, 190+30*(i-32)));
           else if (i >= 38 && i < 47)
               obstacles.add(new Wall(580+30*(i-38), 430));
           else obstacles.add(new Wall(460,130+60*(i-47)));
        }
        for (int i = 0; i < steelNum; i++ ){
            if (i < 3)
                obstacles.add(new Steel(130+30*i,100));
            else if (i >= 3 && i < 10)
                obstacles.add(new Steel(370+30*(i-3),100));
            else if (i >= 10 && i < 13)
                obstacles.add(new Steel(130+30*(i-10),430));
            else if (i >= 13 && i < 19)
                obstacles.add(new Steel(400+30*(i-13),430));
            else if (i >= 19 && i < 21)
                obstacles.add(new Steel(820,130+30*(i-19)));
            else if (i >= 21 && i < 23)
                obstacles.add(new Steel(820,370+30*(i-21)));
            else if (i >= 23 && i < 26)
                obstacles.add(new Steel(460,10+30*(i-23)));
            else if (i >= 26 && i < 30)
                obstacles.add(new Steel(460, 160+60*(i-26)));
            else obstacles.add(new Steel(460, 460+30*(i-30)));
        }
        for (int i = 0; i < grassNum; i++ ){
            if (i < 4)
                grasses.add(new Grass(220+30*i,230));
            else if (i >=4 && i < 8)
                grasses.add(new Grass(220+30*(i-4),260));
            else
                grasses.add(new Grass(220+30*(i-8),290));
        }
        for (int i = 0; i < lakeNum; i++ ){
            if (i < 3)
                obstacles.add(new Lake(580+30*i,230));
            else if (i >= 3 && i < 6)
                obstacles.add(new Lake(580+30*(i-3),260));
            else
                obstacles.add(new Lake(580+30*(i-6),290));
        }
    }

    public void init_2(){

        int wallNum = 77;
        int steelNum = 19;
        int grassNum = 32;
        int lakeNum = 8;
        for (int i = 0; i < wallNum; i++ ){
            if (i < 3)
                obstacles.add(new Wall(210, 0+30*i));
            else if (i >= 3 && i < 6)
                obstacles.add(new Wall(450,0+30*(i-3)));
            else if (i >= 6 && i < 9)
                obstacles.add(new Wall(690,0+30*(i-6)));
            else if (i >= 9 && i < 13)
                obstacles.add(new Wall(90, 120+30*(i-9)));
            else if (i >= 13 && i < 17)
                obstacles.add(new Wall(330, 120+30*(i-13)));
            else if (i >= 17 && i < 21)
                obstacles.add(new Wall(570, 120+30*(i-17)));
            else if (i >= 21 && i < 25)
                obstacles.add(new Wall(810, 120+30*(i-21)));
            else if (i >= 25 && i < 28)
                obstacles.add(new Wall(0+30*(i-25) ,240));
            else if (i >= 28 && i < 31)
                obstacles.add(new Wall(120+30*(i-28), 240));
            else if (i >= 31 && i < 34)
                obstacles.add(new Wall(240+30*(i-31), 240));
            else if (i >= 34 && i < 37)
                obstacles.add(new Wall(360+30*(i-34), 240));
            else if (i >= 37 && i < 40)
                obstacles.add(new Wall(480+30*(i-37), 240));
            else if (i >= 40 && i < 43)
                obstacles.add(new Wall(600+30*(i-40), 240));
            else if (i >= 43 && i < 46)
                obstacles.add(new Wall(720+30*(i-43), 240));
            else if (i >= 46 && i < 49)
                obstacles.add(new Wall(840+30*(i-46), 240));
            else if (i >= 49 && i < 53)
                obstacles.add(new Wall(210, 270+30*(i-49)));
            else if (i >= 53 && i < 57)
                obstacles.add(new Wall(450, 270+30*(i-53)));
            else if (i >= 57 && i < 61)
                obstacles.add(new Wall(690, 270+30*(i-57)));
            else if (i >= 61 && i < 65)
                obstacles.add(new Wall(90, 420+30*(i-61)));
            else if (i >= 65 && i < 69)
                obstacles.add(new Wall(330, 420+30*(i-65)));
            else if (i >= 69 && i < 73)
                obstacles.add(new Wall(570, 420+30*(i-69)));
            else if (i >= 73 && i < 77)
                obstacles.add(new Wall(810, 420+30*(i-73)));
        }
        for (int i = 0;i < steelNum; i++){
            if (i < 3)
                obstacles.add(new Steel(90, 0+30*(i-0)));
            else if (i >= 3 && i < 6)
                obstacles.add(new Steel(330, 0+30*(i-3)));
            else if (i >= 6 && i < 9)
                obstacles.add(new Steel(570, 0+30*(i-6)));
            else if (i >= 9 && i < 12)
                obstacles.add(new Steel(810, 0+30*(i-9)));
            else if (i >= 12 && i < 19)
                obstacles.add(new Steel(90+120*(i-12), 240));
        }
        for (int i = 0; i < grassNum; i++ ){
            grasses.add(new Grass(0+30*(i-0), 390));
        }
        for (int i = 0; i < lakeNum; i++ ){
            if (i < 7)
                obstacles.add(new Lake(90+120*(i-0), 90));
            else
                obstacles.add(new Lake(930, 240));
        }
    }

    public void init_3(){

        int wallNum = 58;
        int steelNum = 12;
        int grassNum = 73;
        int lakeNum = 26;

        for (int i = 0; i < wallNum; i++ ){
            if (i < 8)
                obstacles.add(new Wall(0+30*(i-0), 30+30*(i-0)));
            else if (i >= 8 && i < 16)
                obstacles.add(new Wall(0+30*(i-8), 480-30*(i-8)));
            else if (i >= 16 && i < 23)
                obstacles.add(new Wall(270, 0+30*(i-16)));
            else if (i >= 23 && i < 30)
                obstacles.add(new Wall(270, 330+30*(i-23)));
            else if (i >= 30 && i < 36)
                obstacles.add(new Wall(450, 0+30*(i-30)));
            else if (i >= 36 && i < 42)
                obstacles.add(new Wall(450, 360+30*(i-36)));
            else if (i >= 42 && i < 50)
                obstacles.add(new Wall(930-30*(i-42), 30+30*(i-42)));
            else if (i >= 50 && i < 58)
                obstacles.add(new Wall(930-30*(i-50), 480-30*(i-50)));
        }
        for (int i = 0; i < steelNum; i++ ){
            if (i < 6)
                obstacles.add(new Steel(120, 180+30*(i-0)));
            else if (i >= 6 && i < 12)
                obstacles.add(new Steel(810, 180+30*(i-6)));
        }
        for (int i = 0; i < grassNum; i++ ) {
            if (i < 8)
                grasses.add(new Grass(210 + 30 * (i - 0), 210));
            else if (i >= 8 && i < 15)
                grasses.add(new Grass(240 + 30 * (i - 8), 240));
            else if (i >= 15 && i < 22)
                grasses.add(new Grass(240 + 30 * (i - 15), 270));
            else if (i >= 22 && i < 30)
                grasses.add(new Grass(210 + 30 * (i - 22), 300));
            else if (i >= 30 && i < 36)
                grasses.add(new Grass(420, 0 + 30 * (i - 30)));
            else if (i >= 36 && i < 43)
                grasses.add(new Grass(420, 330 + 30 * (i - 36)));
            else if (i >= 43 && i < 51)
                grasses.add(new Grass(510 + 30 * (i - 43), 210));
            else if (i >= 51 && i < 58)
                grasses.add(new Grass(510 + 30 * (i - 51), 240));
            else if (i >= 58 && i < 65)
                grasses.add(new Grass(510 + 30 * (i - 58), 270));
            else if (i >= 65 && i < 73)
                grasses.add(new Grass(510 + 30 * (i - 65), 300));
        }
        for (int i = 0; i < lakeNum; i++ ){
            if (i < 2)
                obstacles.add(new Lake(420+30*(i-0), 180));
            else if (i >= 2 && i < 7)
                obstacles.add(new Lake(450, 210+30*(i-2)));
            else if (i >= 7 && i < 12)
                obstacles.add(new Lake(480, 210+30*(i-7)));
            else if (i >= 12 && i < 14)
                obstacles.add(new Lake(510+30*(i-12), 330));
            else if (i >= 14 && i < 18)
                obstacles.add(new Lake(540+30*(i-14), 360));
            else if (i >= 18 && i < 22)
                obstacles.add(new Lake(630+30*(i-18), 390));
            else if (i >= 22 && i <26)
                obstacles.add(new Lake(720, 420+30*(i-22)));
        }
    }

    /**
     * 判断障碍物的种类,Wall,Steel,Lake返回0，Life返回1，ExSteel返回2
     * @param obstacle 障碍物对象
     * @return 返回障碍物种类
     */
    public String judgeObstacle(Obstacle obstacle){

        String kind = "4";
        //不可继续通过
        if (obstacle instanceof Wall || obstacle instanceof Steel || obstacle instanceof Lake) kind = "0";
            //可继续通过
        else if (obstacle instanceof Life){
            kind = "1";
            Recorder.increaseMylife();
            Life life = (Life)obstacle;
            life.isLive = false;
            Obstacle.obstacles.remove(life);
        }
        else if (obstacle instanceof Exshell){
            kind = "2";
            Recorder.increaseMyShellNum();
            Exshell shell = (Exshell)obstacle;
            shell.isLive = false;
            Obstacle.obstacles.remove(shell);
        }
        return kind;
    }

    /**
     * 得到障碍物所在矩形
     * @return
     */
    public Rectangle getRectangle(){

        return(new Rectangle(x, y, 30, 30));
    }
    /**
     * 钢铁，不可以被炮弹打掉
     */
    class Steel extends Obstacle{

        public Steel(int x, int y) {
            super(x, y);
            this.image = Toolkit.getDefaultToolkit().getImage("sources/images/steel.png");
        }
    }

    /**
     * 草，坦克可以隐藏到里面
     */
    class Grass extends Obstacle{

        public Grass(int x, int y) {
            super(x, y);
            this.image = Toolkit.getDefaultToolkit().getImage("sources/images/grass.png");
        }
    }

    /**
     * 水，炮弹课以穿过，坦克不可以
     */
    class Lake extends Obstacle{

        public Lake(int x, int y) {
            super(x, y);
            this.image = Toolkit.getDefaultToolkit().getImage("sources/images/lake.png");
        }
    }

    class ObstacleWithLife extends Obstacle{

        boolean isLive = true;

        public ObstacleWithLife(int x, int y){
            super(x, y);
        }
    }
    /**
     * 墙，可以被炮弹打掉
     */
    class Wall extends ObstacleWithLife{


        public Wall(int x, int y){
            super(x, y);
            this.isLive = true;
            this.image = Toolkit.getDefaultToolkit().getImage("sources/images/wall.png");
        }
    }
    /**
     * 生命，+1
     */
    class Life extends ObstacleWithLife{

        public Life(int x, int y){
            super(x, y);
            this.isLive = true;
            this.image = Toolkit.getDefaultToolkit().getImage("sources/images/life.png");
        }
    }

    /**
     * 炮弹，随机加
     */
    class Exshell extends ObstacleWithLife{

        public Exshell(int x, int y){
            super(x, y);
            this.isLive = true;
            this.image = Toolkit.getDefaultToolkit().getImage("sources/images/shell.png");
        }
    }

}

