package com.betterme.tankwar.game;

import java.awt.*;
import java.util.Vector;

/**
 * 设置游戏的音乐开启与否，每一关敌人坦克数量，移动速度及炮弹速度
 */
public class Setting {

    private static boolean isBgmOn;
    private static boolean isMusicFxOn;
    //单局敌人坦克的总数
    private static int enemySize ;
    private static int enemySpeed;
    //一次在地图上出现的敌人坦克数量
    private static int enemyNumOnMap;
    private static Color myTankColor;
    private static int myLife = 4;
    private static MyTank mt;
    private static int score;
    private static int shellNum;
    private static int shellSpeed;
    public static Vector<EnemyTank> ets = new Vector<>();

    public static boolean isIsBgmOn() {
        return isBgmOn;
    }

    public static void setIsBgmOn(boolean isBgmOn) {
        Setting.isBgmOn = isBgmOn;
    }

    public static boolean isIsMusicFxOn() {
        return isMusicFxOn;
    }

    public static void setIsMusicFxOn(boolean isMusicFxOn) {
        Setting.isMusicFxOn = isMusicFxOn;
    }

    public static int getEnemySize() {
        return enemySize;
    }

    public static void setEnemySize(int enemySize) {
        Setting.enemySize = enemySize;
    }

    public static int getEnemySpeed() {
        return enemySpeed;
    }

    public static void setEnemySpeed(int enemySpeed) {
        Setting.enemySpeed = enemySpeed;
    }

    public static int getEnemyNumOnMap() {
        return enemyNumOnMap;
    }

    public static void setEnemyNumOnMap(int enemyNumOnMap) {
        Setting.enemyNumOnMap = enemyNumOnMap;
    }

    public static Color getMyTankColor() {
        return myTankColor;
    }

    public static void setMyTankColor(Color myTankColor) {
        Setting.myTankColor = myTankColor;
    }

    public static int getMyLife() {
        return myLife;
    }

    public static void setMyLife(int myLife) {
        Setting.myLife = myLife;
    }

    public static MyTank getMt() {
        return mt;
    }

    public static void setMt(MyTank mt) {
        Setting.mt = mt;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Setting.score = score;
    }

    public static int getShellNum() {
        return shellNum;
    }

    public static void setShellNum(int shellNum) {
        Setting.shellNum = shellNum;
    }

    public static int getShellSpeed() {
        return shellSpeed;
    }

    public static void setShellSpeed(int shellSpeed) {
        Setting.shellSpeed = shellSpeed;
    }

    /**
     * 初始化关卡的敌人数量，我的炮弹数量和我的位置
     * @param stage 关卡
     */
    public static void initStage(int stage){

        switch (stage){
            case 1:
                enemySize = 40;
                shellNum = 60;

                break;
            case 2:
                enemySize = 50;
                shellNum = 70;
                break;
            case 3:
                enemySize = 60;
                shellNum = 80;
                break;
                default:
                    break;
        }
        mt = new MyTank(900, 500);
    }

    /**
     * 设置关卡的敌人速度，在地图上出现的敌人数量和炮弹的速度
     * @param stage 关卡
     */
    public static void setStage(int stage){

        switch (stage){

            case 1:
                enemySpeed = 4;
                enemyNumOnMap = 4;
                shellSpeed = 4;
                break;
            case 2:
                enemySpeed = 5;
                enemyNumOnMap = 5;
                shellSpeed = 5;
                break;
            case 3:
                enemySpeed = 6;
                enemyNumOnMap = 6;
                shellSpeed = 6;
                break;
                default:
                    break;
        }
        Recorder.setStage(stage);
    }


    /**
     * 初始化各个关卡中敌人在地图中的位置
     * @param stage 关卡
     */
    public void initEnemyTankLocatin(int stage){

        switch (stage){
            case 1:
                MapPanel.enemyTanks.add(new EnemyTank(100, 50));
                MapPanel.enemyTanks.add(new EnemyTank(300, 180));
                MapPanel.enemyTanks.add(new EnemyTank(200, 350));
                MapPanel.enemyTanks.add(new EnemyTank(300, 500));
                break;
            case 2:
                MapPanel.enemyTanks.add(new EnemyTank(10, 150));
                MapPanel.enemyTanks.add(new EnemyTank(100, 300));
                MapPanel.enemyTanks.add(new EnemyTank(200, 450));
                MapPanel.enemyTanks.add(new EnemyTank(320, 320));
                MapPanel.enemyTanks.add(new EnemyTank(10, 450));
                break;
            case 3:
                MapPanel.enemyTanks.add(new EnemyTank(150, 30));
                MapPanel.enemyTanks.add(new EnemyTank(30, 200));
                MapPanel.enemyTanks.add(new EnemyTank(150, 450));
                MapPanel.enemyTanks.add(new EnemyTank(270, 250));
                MapPanel.enemyTanks.add(new EnemyTank(350, 100));
                MapPanel.enemyTanks.add(new EnemyTank(350, 400));
                break;
        }
        for (int i = 0; i < MapPanel.enemyTanks.size(); i++ ){
            Thread thread = new Thread(MapPanel.enemyTanks.get(i));
            thread.start();
        }
    }
}
