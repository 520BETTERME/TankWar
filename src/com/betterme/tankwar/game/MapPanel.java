package com.betterme.tankwar.game;

/**
 * 游戏面板，坦克在该面板上移动
 */
import com.betterme.tankwar.menu.OverMenu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Vector;

public class MapPanel extends JPanel implements KeyListener, Runnable{

    private Music bgm;
    private Music winMusic;
    private Music failMusic;
    private Image img1, img2, img3;
    public static MyTank myTank;
    private static boolean runThread = true;    //是否继续运行线程
    private static boolean winGame = false;     //是否赢了游戏
    private static boolean overGame = false;    //是否结束游戏
    public static Vector<Bomb> bombs = new Vector<>();
    public static Vector<EnemyTank> enemyTanks = new Vector<>();
    private LinkedList<Music> bombMusics = new LinkedList<>();
    private LinkedList<Music> getFoodMusics = new LinkedList<>();
    private Obstacle obstacle = new Obstacle();
    private Setting setting = new Setting();

    /**
     * 构造函数
     * @param stage 关卡
     */
    public MapPanel(int stage){
        switch (stage){
            case 1:
                obstacle.init_1();
                break;
            case 2:
                obstacle.init_2();
                break;
            case 3:
                obstacle.init_3();
                break;
        }
        setting.setStage(stage);
        this.myTank = Setting.getMt();
        for (int i = 0; i < Setting.ets.size(); i++ ){
            EnemyTank et = Setting.ets.get(i);
            et.setSpeed(Setting.getEnemySpeed());  //设置坦克速度
            this.enemyTanks.add(et);
            Thread thread = new Thread(et);
            thread.start();
            //System.out.println(thread.getName());
        }
        Recorder.setEnemyNum(Setting.getEnemySize());
        Recorder.setScore(Setting.getScore());
        Recorder.setMyLife(Setting.getMyLife());
        Recorder.setMyShellNum(Setting.getShellNum());
        Setting.setMyTankColor(Color.RED);
        this.img1 = Toolkit.getDefaultToolkit().getImage("sources/images/boom1.png");
        this.img2 = Toolkit.getDefaultToolkit().getImage("sources/images/boom2.png");
        this.img3 = Toolkit.getDefaultToolkit().getImage("sources/images/boom3.png");
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(960, 540));
        this.setFocusable(true);    //设置键盘焦点，否则无法实现键盘监听
        this.addKeyListener(this);
//        this.myTank = new MyTank(920, 500, 5, Setting.getMyTankColor());
        if (Setting.isIsBgmOn()) {
            this.bgm = new Music("sources/media/bgm.wav");
            bgm.loopPlay();
        }
        if (Setting.isIsMusicFxOn()){
            this.winMusic = new Music("sources/media/win.wav");
            this.failMusic = new Music("sources/media/fail.wav");
        }
        this.setVisible(true);
    }

    public static boolean isRunThread() {
        return runThread;
    }

    public static void setRunThread(boolean runThread) {
        MapPanel.runThread = runThread;
    }

    public static boolean isWinGame() {
        return winGame;
    }

    public static void setWinGame(boolean b) {
        MapPanel.winGame = winGame;
    }

    public static boolean isOverGame() {
        return overGame;
    }

    public static void setOverGame(boolean overGame) {
        MapPanel.overGame = overGame;
    }

    /**
     * 绘制地图上的坦克，障碍物和爆炸效果
     * @param g
     */
    public void paint(Graphics g){

        super.paint(g);
        drawObstacle(g);
        if (myTank.shells.size() != 0) {
            for (int i = 0; i < myTank.shells.size(); i++ ){
                Shell shell = myTank.shells.get(i);
                if (shell.isLive == true){
                    drawShell(shell.getX() ,shell.getY(), myTank.getColor(), g);
//                    System.out.println(shell.getX() + " " + shell.getY());
//                    System.out.println("no shell died: " + myTank.shells.size());
                } else{
                    myTank.shells.remove(shell);
//                    System.out.println("a shell died: " + myTank.shells.size());
                }
            }
        }

        if (enemyTanks.size() != 0){
//                System.out.println("loaded here...");
            for (int i = 0; i < enemyTanks.size(); i++){
                EnemyTank et = enemyTanks.get(i);
                if (et.isLive){
                    this.drawTank(et.getX(), et.getY(), et,et.getDirection(), g);
//                    System.out.println("no et deid: " + enemyTanks.size());
//                    System.out.println("x: " + et.getX() + " y: " + et.getY());
                    for (int j = 0; j < et.shells.size(); j++ ){
                        Shell shell = et.shells.get(j);     //特别容易犯错，写成i
                        if (shell.isLive)
                            drawShell(shell.getX(), shell.getY(), shell.getColor(), g);
                        else
                            et.shells.remove(shell);
                    }
                }else
                    enemyTanks.remove(et);
            }
        }

        if (myTank.isLive){
            drawTank(myTank.getX(), myTank.getY(), myTank, myTank.getDirection(), g);
        }
        drawGrass(g);
        if (bombs.size() != 0 ){
            drawBomb(g);
        }
        drawInfo(g);

    }

    /**
     * 绘制坦克
     * @param x
     * @param y
     * @param tank 坦克种类
     * @param direction 坦克方向
     * @param g
     */
    private void drawTank(int x, int y, Tank tank, int direction, Graphics g){

//        super.paint(g); 添加此句会导致后画的坦克覆盖之前的坦克
        g.setColor(tank.getColor());

        switch (direction){
            case 0: //上
                //坦克左轮
                g.fill3DRect(x, y, 5, 30, false);
                //坦克中间
                g.fill3DRect(x + 5, y + 5, 15, 20, false);
                //坦克右轮
                g.fill3DRect(x + 19, y, 5, 30, false);
                //坦克中间圆
                g.drawOval(x + 7, y + 10, 10, 10);
                //坦克炮筒
                g.drawLine(x + 12, y + 15, x + 12, y - 5);
                break;
            case 1: //下
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 15, 20, false);
                g.fill3DRect(x + 19, y, 5, 30, false);
                g.drawOval(x + 7, y + 10, 10, 10);
                g.drawLine(x + 12, y + 15, x + 12, y + 35);
                break;
            case 2: //左
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 15, false);
                g.fill3DRect(x, y + 20, 30, 5, false);
                g.drawOval(x + 10, y + 7, 10, 10);
                g.drawLine(x + 15, y + 12, x - 5, y + 12);
                break;
            case 3: //右
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 15, false);
                g.fill3DRect(x, y + 20, 30, 5, false);
                g.drawOval(x + 10, y + 7, 10, 10);
                g.drawLine(x + 15, y + 12, x + 35, y + 12);
                break;
            default:
                break;
        }
    }

    /**
     * 绘制炮弹
     * @param x
     * @param y
     * @param shellColor 炮弹的颜色
     *
     */
    private void drawShell(int x, int y, Color shellColor, Graphics g){
        g.setColor(shellColor);
        g.drawOval(x, y, 2, 2);
    }

    /**
     * 绘制爆炸效果
     * @param g
     */
    private void drawBomb(Graphics g){

        for (int i = 0; i < bombs.size(); i++ ){
            Bomb bomb = bombs.get(i);
            int x = bomb.getX();
            int y = bomb.getY();
            if (bomb.getTime() > 6){
                g.drawImage(img1, x, y, 30, 30, this);
//                System.out.println(x + " " + y);
            }else if (bomb.getTime() > 3){
                g.drawImage(img2, x, y, 30, 30, this);
            }else if (bomb.getTime() > 0){
                g.drawImage(img3, x, y, 30, 30, this);
            }
            bomb.timeDown();
            if (bomb.getTime() <= 0){
                bombs.remove(bomb);
            }
        }

    }

    /**
     * 绘制除草以外的障碍物
     * @param g
     */
    private void drawObstacle(Graphics g){

        for (int i = 0; i < Obstacle.obstacles.size(); i++ ){
            Obstacle o = Obstacle.obstacles.get(i);
            g.drawImage(o.image, o.x, o.y, 30, 30, this);
        }
    }

    /**
     * 绘制游戏的信息
     * @param g
     */
    private void drawInfo(Graphics g){

        g.setFont(new Font("宋体", Font.PLAIN, 12));
        g.setColor(Color.RED);
        g.drawString("敌方坦克剩余数量：", 10, 13);
        g.drawString(Recorder.getEnemyNum()+"", 115, 13);
        g.drawString("关卡：", 135, 13);
        g.drawString(Recorder.getStage()+"", 167, 13);
        g.drawString("剩余炮弹数：", 690, 13);
        g.drawString(Recorder.getMyShellNum()+"", 760, 13);
        g.drawString("生命：", 790, 13);
        g.drawString(Recorder.getMyLife()+"", 824, 13);
        g.drawString("得分：", 850, 13);
        g.drawString(Recorder.getScore()+"", 884, 13);
    }

    /**
     * 创建心脏和额外的炮弹(食物)
     */
    private void createFood(){

        int flag = (int)(Math.random() * 2000);
        int x = (int)(Math.random() * 930);
        int y = (int)(Math.random() * 510);
        //nextInt()里面不能是0
//        System.out.println(new Random().nextInt(1) + 510);
        int king = (int)(Math.random() * 2);
        if (flag == 1000 && king == 0){
            Obstacle.obstacles.add(new Life(x,y));
        }else if (flag == 25 && king == 1){
            Obstacle.obstacles.add(new Exshell(x,y));
        }
    }

    /**
     * 绘制草
     * @param g
     */
    private void drawGrass(Graphics g){

        for (int i = 0; i < Obstacle.grasses.size(); i++ ){
            Grass grass = Obstacle.grasses.get(i);
            g.drawImage(grass.image, grass.x, grass.y, 30, 30, this);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//    System.out.println(myTank.getX() + " " + myTank.getY());
        switch (e.getKeyCode()){
            //上
            case KeyEvent.VK_W:
                myTank.setDirection(0);
                String s0 = myTank.crashObstacle(0);
                if ("12".indexOf(s0) >= 0 && Setting.isIsMusicFxOn())
                    getFoodMusics.add(new Music("sources/media/get.wav"));
                if (myTank.getY() > 0 && !s0.equals("0")){
                    myTank.moveUp();
                }

                break;
            //下
            case KeyEvent.VK_S:
                myTank.setDirection(1);
                String s1 = myTank.crashObstacle(1);
                if ("12".indexOf(s1) >= 0 && Setting.isIsMusicFxOn())
                    getFoodMusics.add(new Music("sources/media/get.wav"));
                if (myTank.getY()+30 < 540 && !s1.equals("0"))
                        myTank.moveDown();
                break;
            //左
            case KeyEvent.VK_A:
                myTank.setDirection(2);
                String s2 = myTank.crashObstacle(2);
                if ("12".indexOf(s2) >= 0 && Setting.isIsMusicFxOn())
                    getFoodMusics.add(new Music("sources/media/get.wav"));
                if (myTank.getX() > 0 && !s2.equals("0"))
                        myTank.moveLeft();
                break;
            //右
            case KeyEvent.VK_D:
                myTank.setDirection(3);
                String s3 = myTank.crashObstacle(3);
                if ("12".indexOf(s3) >= 0 && Setting.isIsMusicFxOn())
                    getFoodMusics.add(new Music("sources/media/get.wav"));
                if (myTank.getX()+30 < 960 && !s3.equals("0"))
                       myTank.moveRight();
                break;
        }

        //按I键开火
        if (e.getKeyCode() == KeyEvent.VK_I && Recorder.getMyShellNum()>0 ){
                myTank.fire();
                Recorder.reduceMyShellNum();
        }
        //按esc键退出
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            Recorder.savePassStage();
            Recorder.saveEnemyInfoTOJson();
            Recorder.savePlayerInfoToJson();
            MapPanel.runThread = false;
            if (Setting.isIsBgmOn())
                bgm.stopPlay();

        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * 进程休眠
     * @param millis 休眠时间
     */
    private void sleep(int millis){

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (runThread) {
            sleep(40);
            //初始化敌人坦克，若全死了，重新往地图上添加
            if (enemyTanks.size() == 0){
                //System.out.println("here");
                for (int i = 0; i < Setting.getEnemyNumOnMap(); i ++){
                    EnemyTank et = new EnemyTank((i+1)*45,i+480);
                    enemyTanks.add(et);
                    Thread thread = new Thread(et);
                    thread.start();
                }
            }
            //初始化我的坦克
            if (!myTank.isLive && Recorder.getMyLife() > 0){
                this.myTank = new MyTank(920, 500);
                //myTank.setSpeed(5);
            }


            for (int i = 0; i < enemyTanks.size(); i++ ){

                EnemyTank et = enemyTanks.get(i);
                //炮弹打击到了有生命的障碍物
                for (int j = 0; j < et.shells.size(); j++ ){

                    Shell ets = et.shells.get(j);
                    if (ets.hitObstacle(et) == 0 && Setting.isIsMusicFxOn()){
                        bombMusics.add(new Music("sources/media/bomb.wav"));
                    }
                }
//                if (Recorder.getMyLife() > 0) {
                    //敌人炮弹击中我
                    if (et.hitMe(myTank) && Setting.isIsMusicFxOn()){
                        bombMusics.add(new Music("sources/media/bomb.wav"));
                    }
                    //我的炮弹击中敌人
                    if (myTank.hitEnemy(et) && Setting.isIsMusicFxOn()){
                        bombMusics.add(new Music("sources/media/bomb.wav"));
//                    }
                }
            }

//            if (Recorder.getMyLife() > 0){
                //我的炮弹击中障碍物
                for (int i = 0; i < myTank.shells.size(); i++ ){

                    Shell ms = myTank.shells.get(i);
                    if (ms.hitObstacle(myTank) == 0 && Setting.isIsMusicFxOn()){
                        bombMusics.add(new Music("sources/media/bomb.wav"));
                    }

//                }
            }

            createFood();

            //播放爆炸声音数组里的对象
            for (int i = 0; i < bombMusics.size(); i++ ){
                Music bm = bombMusics.get(i);
                bm.play();
                bombMusics.remove(bombMusics.get(i));
            }

            //播放得到声音数组里的对象
            for (int i = 0; i < getFoodMusics.size(); i++ ){
                Music gfm = getFoodMusics.get(i);
                gfm.play();
                getFoodMusics.remove(gfm);
            }

            if (Recorder.getMyLife() == 0){
                if (Setting.isIsMusicFxOn()){
                    failMusic.play();
                }
                if (Setting.isIsBgmOn()){
                    bgm.stopPlay();
                }
                sleep(500);
                new OverMenu().init("sources/images/fail.png");
                runThread = false;
                overGame = true;
                break;
            }else if (Recorder.getEnemyNum() == 0){
                if(Setting.isIsMusicFxOn()){
                    winMusic.play();
                }
                if (Setting.isIsBgmOn()){
                    bgm.stopPlay();
                }
                sleep(500);
                new OverMenu().init("sources/images/win.png");
                runThread = false;
                winGame = true;
                overGame = true;
                break;
            }
            this.repaint();
        }
    }

}
