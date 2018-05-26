package com.betterme.tankwar.menu;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * 按钮面板，采用边界布局，用于方便设置不同界面左边按钮的布局
 */
public class ButtonPanel extends JPanel{

    private JPanel panNorth;
    private JPanel panWest;
    private JPanel panSouth;
    private JPanel panEast;
    private ArrayList<JButton> buttons;

    /**
     * 构造方法
     * @param width 面板的高
     * @param height 面板的高
     * @param hgap 面板里按钮的水平间距
     * @param vgap 面板里按钮的垂直间距
     * @param buttons 按钮对象数组
     */
    public ButtonPanel(int width, int height, int hgap, int vgap, ArrayList<JButton> buttons){

        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        this.panNorth = new JPanel();
        this.panWest = new JPanel();
        this.panSouth = new JPanel();
        this.panEast = new JPanel();
        JPanel panCenter = new JPanel();
        this.buttons = buttons;
        panCenter.setLayout(new GridLayout(buttons.size(), 1, hgap, vgap));
        for (int i = 0; i < buttons.size(); i++ ){
            panCenter.add(buttons.get(i));
        }
        this.add(panNorth, BorderLayout.NORTH);
        this.add(panWest, BorderLayout.WEST);
        this.add(panSouth, BorderLayout.SOUTH);
        this.add(panEast, BorderLayout.EAST);
        this.add(panCenter, BorderLayout.CENTER);

    }

    /**
     * 设置面板中北面空白面板的宽高
     * @param width
     * @param height
     */
    public void setPanNorthSize(int width, int height){
        panNorth.setPreferredSize(new Dimension(width, height));
    }

    public void setPanWestSize(int width, int height){
        panWest.setPreferredSize(new Dimension(width, height));
    }

    public void setPanSouthSize(int width, int height){
        panSouth.setPreferredSize(new Dimension(width, height));
    }

    public void setPanEastSize(int width, int height){
        panEast.setPreferredSize(new Dimension(width, height));
    }

    /**
     * 统一设置北面和南面面板的大小
     * @param width
     * @param height
     */
    public void setPanNSSize(int width, int height){
        panNorth.setPreferredSize(new Dimension(width, height));
        panSouth.setPreferredSize(new Dimension(width, height));
    }

    public void setPanWESize(int width, int height){
        panWest.setPreferredSize(new Dimension(width, height));
        panEast.setPreferredSize(new Dimension(width, height));
    }

    public void setButtonBackground(Color color){
        for (int i = 0; i < buttons.size(); i++ ){
            buttons.get(i).setBackground(color);
        }
    }

    /**
     * 设置按钮的字体
     * @param font 字体种类
     */
    public void setButtonTextFont(Font font){
        for (int i = 0; i < buttons.size(); i++ ){
             buttons.get(i).setFont(font);
        }
    }

    /**
     * 设置按钮的边框
     * @param border
     */
    public void setButtonBorder(Border border){
        for (int i = 0; i < buttons.size(); i++ ){
            buttons.get(i).setBorder(border);
        }
    }

}
