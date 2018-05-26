package com.betterme.tankwar.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//整个程序所有窗口的夫类
public class FWindow extends JFrame{

//    Screen thisScreen = new Screen();
    private String gameName = "TankWar";
    private String gameIconUri = "sources/images/icon.png";
    private Image gameIcon;
    private int width = 960;
    private int height = 575;

    public FWindow(){

        this.gameIcon = new ImageIcon(gameIconUri).getImage();

        this.setTitle(gameName);
        this.setIconImage(gameIcon);
        this.setSize(width, height);
        this.setResizable(false);
        //默认居中显示
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new ExitConfirmDialog();
            }
        });
    }
}
