package com.betterme.tankwar;

import com.betterme.tankwar.menu.ChooseStage;
import com.betterme.tankwar.menu.OverMenu;
import com.betterme.tankwar.menu.StartMenu;

public class StartGame {

    public static StartMenu startMenu;
    public static ChooseStage chooseStage;
    public static OverMenu overMenu;
    /**
     * 将GameWindow设为静态后，当第一次按esc键后，MapPanel里的runThread为false，再次进入游戏时，
     * 由于gameWindow是静态的，没有对runThread进行修改，导致相关线程无法运行
     */
    //public static GameWindow gameWindow;

    public StartGame (){

        startMenu = new StartMenu();
        chooseStage = new ChooseStage();
        overMenu = new OverMenu();
    }

    public static void main(String[]args){

        new StartGame();
        StartGame.startMenu.init();
    }
}
