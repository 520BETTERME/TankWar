package com.betterme.tankwar.game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

/**
 * 记录游戏中敌人坦克剩余数量我方坦克生命数，剩余炮弹数，得分；
 * 将结果写入文件保存;
 * 游戏开始时读取上次记录信息
 */
public class Recorder {

    private static int enemyNum;
    private static int myShellNum;
    private static int myLife;
    private static int score;
    private static int stage;

    public static int getEnemyNum() {
        return enemyNum;
    }

    public static void setEnemyNum(int enemyNum) {
        Recorder.enemyNum = enemyNum;
    }

    public static int getMyShellNum() {
        return myShellNum;
    }

    public static void setMyShellNum(int myShellNum) {
        Recorder.myShellNum = myShellNum;
    }

    public static int getMyLife() {
        return myLife;
    }

    public static void setMyLife(int myLife) {
        Recorder.myLife = myLife;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Recorder.score = score;
    }

    public static int getStage() {
        return stage;
    }

    public static void setStage(int stage) {
        Recorder.stage = stage;
    }

    public static void reduceEnemyNum(){
        enemyNum--;
    }

    public static void increaseMylife(){
        myLife++;
    }

    public static void reduceMylife(){
        myLife--;
    }

    public static void increaseMyShellNum(){
        int num = (int)(Math.random() * 10);
        myShellNum = myShellNum + num;
    }

    public static void reduceMyShellNum(){
        myShellNum--;
    }

    public static void increaseScore(){
        score = score + 50;
    }

    public static void reduceScore(){
        score = score + 20;
        if (score < 0)
            score = 0;
    }

    /**
     * 将map里的地图数组和敌人坦克数组置空，按esc键，进行下一关的时候调用
     * 因为它们是静态的，一直存在内存中，一旦它们被初始化后，如果不置空，
     * 再次创建新的MapPanel对象时，MapPanel里的这2个数组已经有元素在里面了
     */
    public static void resetMap(){

        Obstacle.obstacles.clear();
        Obstacle.grasses.clear();
        MapPanel.enemyTanks.clear();
        MapPanel.myTank = new MyTank();
    }

    /**
     * 从json文件中获得上次敌人坦克的信息,并返回是否成功
     */
    public boolean getEnemyInfoFromJson(){

        boolean success = false;
        try {
            FileReader fr = new FileReader("sources/recorder/json/enemyTank.json");
            BufferedReader br = new BufferedReader(fr);
            String json = null;
            while ((json = br.readLine()) != null){
            success = true;
                try {
                    JSONObject enemyTank = new JSONObject(json);
                    Setting.setEnemySize(enemyTank.getInt("enemyReNum"));
                    //Setting.setEnemySpeed(enemyTank.getInt("speed"));
                    JSONArray enemyTanks = enemyTank.getJSONArray("enemyTanks");
                    for (int i = 0; i < enemyTanks.length(); i++ ){
                        JSONObject et = enemyTanks.getJSONObject(i);
                        int x = et.getInt("x");
                        int y = et.getInt("y");
                        int direction = et.getInt("direction");
                        EnemyTank lastEt = new EnemyTank(x, y, direction);
                        Setting.ets.add(lastEt);
                    }
                }catch (JSONException jsone){
                    jsone.printStackTrace();
                    return false;
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return success;
    }

    /**
     * 从json文件中获得上次游戏中玩家的信息
     * @return 返回是否读取成功
     */
    public boolean getPlayerInfoFromJson(){

        boolean success = false;
        try{
            FileReader fr = new FileReader("sources/recorder/json/myTank.json");
            //FileInputStream fis = new FileInputStream("sources/recorder/json/myTank.json");
            BufferedReader br = new BufferedReader(fr);
            String json = null;

            while ((json = br.readLine()) != null){     //如果文件为空，不会执行while循环里面的部分
            success = true;
                try{
                    JSONObject myTank = new JSONObject(json);
                    Setting.setMyLife(myTank.getInt("myLife"));
                    MyTank mt = new MyTank(myTank.getInt("x"), myTank.getInt("y"), myTank.getInt("direction"));
                    Setting.setMt(mt);
                    //System.out.println(mt.getX() + " " + mt.getY() + " " + mt.getDirection() + " " + mt.shells.size());
                    Setting.setScore(myTank.getInt("score"));
                    Setting.setShellNum(myTank.getInt("shellNum"));
                }catch (JSONException e){
                    e.printStackTrace();
                    return false;
                }
            }
            br.close();
            fr.close();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return success;
    }

    /**
     * 从文件中读取玩家的闯关信息
     * @return 返回是否读取成功
     */
    public boolean getPassStage(){

        boolean success = false;
        try {
            FileReader fr = new FileReader("sources/recorder/stage");
            BufferedReader br = new BufferedReader(fr);
            String stage = null;
            while ((stage = br.readLine()) != null){
                success = true;
                Recorder.setStage(Integer.valueOf(stage));
            }
            br.close();
            fr.close();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return success;
    }

    /**
     * 将本次游戏敌人坦克信息写入json
     */
    public static void saveEnemyInfoTOJson(){

        StringBuffer etInfo = new StringBuffer();
        etInfo.append("{\"enemyReNum\":" + Recorder.getEnemyNum() + ", \"enemyTanks\": [");
        for (int i = 0; i < MapPanel.enemyTanks.size(); i++ ){
            EnemyTank thisEt = MapPanel.enemyTanks.get(i);
            etInfo.append("{\"x\":" + thisEt.getX() + ", \"y\":" + thisEt.getY() +
                    ", \"direction\":" + thisEt.getDirection() + "},");
        }
        //删除最后一个“,”
        etInfo.deleteCharAt(etInfo.length()-1);
        etInfo.append("]}");
        try{
            FileWriter fw = new FileWriter("sources/recorder/json/enemyTank.json",false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(etInfo.toString());

            bw.close();
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 将本次游戏玩家信息写入json文件
     */
    public static void savePlayerInfoToJson(){

        StringBuffer playerInfo = new StringBuffer();
        playerInfo.append("{\"myLife\":" + Recorder.getMyLife() +
                ", \"x\":" + MapPanel.myTank.getX() +
                ", \"y\":" + MapPanel.myTank.getY() +
                ", \"direction\":" + MapPanel.myTank.getDirection() +
                ", \"score\":" + Recorder.getScore() +
                ", \"shellNum\":" + Recorder.getMyShellNum() + "}");
        try{
            FileWriter fw = new FileWriter("sources/recorder/json/myTank.json",false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(playerInfo.toString());

            bw.close();
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 将本次闯关记录写入到文件中
     */
    public static void savePassStage(){
        try{
            FileWriter fw = new FileWriter("sources/recorder/stage");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf(Recorder.getStage()));

            bw.close();
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
