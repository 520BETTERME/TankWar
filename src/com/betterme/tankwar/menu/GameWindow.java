package com.betterme.tankwar.menu;

/**
 * 存放游戏面板MapPanel的窗口
 */

import com.betterme.tankwar.game.MapPanel;
import com.betterme.tankwar.game.Recorder;

public class GameWindow extends FWindow implements Runnable{

    private MapPanel mapPanel;
    private boolean stopGame = false;

    public GameWindow(){
        super();
        MapPanel.setRunThread(true);
        MapPanel.setWinGame(false);
        MapPanel.setOverGame(false);
    }

    public GameWindow(int stage){

        this.mapPanel = new MapPanel(stage);
        MapPanel.setRunThread(true);
        MapPanel.setWinGame(false);
        MapPanel.setOverGame(false);
        //System.out.println(MapPanel.runThread);
        this.add(mapPanel);
        Thread thread = new Thread(mapPanel);
        thread.start();
        this.setVisible(true);
    }

    /**
     * 用于监听MapPanel游戏是否结束
     */
    @Override
    public void run() {

        while (!stopGame){
            //System.out.println(MapPanel.isRunThread());

            /**
             * 如果游戏结束(退出)，此面板消失，终止此线程;
             * 当按“esc”键退出游戏的时候，MapPanel对象还在内存中，
             * 它的runThread的值此时是false，所以，再次载入进度的时候，此处的runThread是上次创建的MapPanel的
             * runThread是false,导致此时载入进度会会退到chooseStage面板。
             * 解决办法：
             * 要么退出时把MapPanel所占的内存释放，因为Java的自动垃圾回收机制，这个不是很现实；
             * 在此类的构造方法中将MapPanel的runThread设为true。
             * 除此之外，还要对winGame,overGame进行初始化
             */
            if (MapPanel.isRunThread() == false){
                //this.remove(mapPanel);
                this.dispose();
                //this.mapPanel = null;       //对象置空，貌似会加快JVM的垃圾回收
                //System.out.println(MapPanel.isWinGame());
                //没有结束游戏，是按了esc键退出游戏
                if (MapPanel.isOverGame() == false)   {

                    //System.out.println("here");
                    new ChooseStage().init();
                }
                this.stopGame = true;
                Recorder.resetMap();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
