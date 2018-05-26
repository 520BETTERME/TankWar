package com.betterme.tankwar.menu;
/**
 * 开始菜单类；
 * 包含关于，游戏提示，开始退出游戏的按钮
 */

import com.betterme.tankwar.StartGame;
import com.betterme.tankwar.game.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StartMenu extends FWindow implements ActionListener{

    public StartMenu(){
        super();
    }

    //初始化开始菜单
    public void init(){

        String author = "By 赵卫华 李则贤 王鹏涛";
        this.setLayout( new BorderLayout());
        //关键
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        //存放所有组件的面板（不包括南北空白面板），这个面板放在中间,采用边界布局
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        //panel.setPreferredSize(new Dimension(960,500));

        //中间面板，采用1*2网格布局,包括左上角面板和右上角标签
        JPanel panCenter = new JPanel();
        panCenter.setLayout(new GridLayout(1,2));
        //左上角面板
        ArrayList<JButton> buChoices = new ArrayList<>();
        JButton buChoice1 = new JButton("人机对战");
        JButton buChoice2 = new JButton("联机对战");
        JButton buChoice3 = new JButton("退出游戏");
        buChoices.add(buChoice1);
        buChoices.add(buChoice2);
        buChoices.add(buChoice3);
        for (int i = 0; i < buChoices.size(); i++ ){
            buChoices.get(i).addActionListener(this);
        }
        ButtonPanel panelLU = new ButtonPanel(460,460,25,35, buChoices);
        panelLU.setPanNSSize(400, 35);
        panelLU.setPanWESize(60, 350);
        panelLU.setButtonBackground(new Color(232,232,232));
        panelLU.setButtonTextFont(new Font("黑体", Font.PLAIN, 20));
        panelLU.setButtonBorder(null);
        //右上角面板（启动图片标签，设置面板，关于面板,根据不同的按钮让对应的组件显示）
        JLabel labStartImgRU = new JLabel();
        ImageIcon startImg = new ImageIcon("sources/images/start.png");
        labStartImgRU.setIcon(startImg);

        panCenter.add(panelLU);
        panCenter.add(labStartImgRU);

        //南方面板，采用1*2网格布局，包括左下角面板和右下角标签
        JPanel panSouth = new JPanel();
        panSouth.setLayout(new GridLayout(1,2));
        panCenter.setPreferredSize(new Dimension(960, 40));

        //左下角面板（几个按钮）
        JPanel panelLD = new JPanel();
        panelLD.setLayout(new FlowLayout(FlowLayout.CENTER));
        //panelLD.setPreferredSize(new Dimension(960,30));
        JCheckBox bgmChBox = new JCheckBox("背景音乐");
        bgmChBox.setFont(new Font("黑体",Font.PLAIN,16));
        bgmChBox.setBackground(Color.WHITE);
        JCheckBox musChBox = new JCheckBox("游戏音效");
        musChBox.setFont(new Font("黑体", Font.PLAIN, 16));
        musChBox.setBackground(Color.WHITE);
        JButton tipsBu = new JButton("游戏提示");
        tipsBu.setFont(new Font("黑体", Font.PLAIN, 16));
        tipsBu.setBackground(Color.WHITE);
        JButton settingBu = new JButton("设置");
        settingBu.setFont(new Font("黑体", Font.PLAIN ,16));
        settingBu.setBackground(Color.WHITE);
        JButton aboutBu = new JButton("关于");
        aboutBu.setFont(new Font("黑体", Font.PLAIN, 16));
        aboutBu.setBackground(Color.WHITE);
        panelLD.add(bgmChBox);
        panelLD.add(musChBox);
        panelLD.add(tipsBu);
        //panelLD.add(settingBu);
        panelLD.add(aboutBu);
        //右下角作者信息标签
        JLabel authorInfoLabRD = new JLabel(author,SwingConstants.CENTER);
        authorInfoLabRD.setFont(new Font("黑体", Font.PLAIN, 16));

        panSouth.add(panelLD);
        panSouth.add(authorInfoLabRD);

        panel.add(panCenter, BorderLayout.CENTER);
        panel.add(panSouth, BorderLayout.SOUTH);

        //南方空白面板,占位
        JPanel nullPanSouth = new JPanel();
        nullPanSouth.setPreferredSize(new Dimension(960, 20));

        this.add(nullPanSouth, BorderLayout.SOUTH);
        this.add(panel, BorderLayout.CENTER);

        bgmChBox.addActionListener(new ChBoxActionListener(bgmChBox));
        bgmChBox.setActionCommand("bgmChBox");
        musChBox.addActionListener(new ChBoxActionListener(musChBox));
        musChBox.setActionCommand("musChBox");

        tipsBu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TipsDialog();
            }
        });
        settingBu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new SettingDialog();
            }
        });
        aboutBu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new EasyDialog("关于", "sources/about.png");
                new AboutDialog();
            }
        });
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("人机对战")){
            //to do something
            StartGame.chooseStage.init();
            this.dispose();
        }else if(e.getActionCommand().equals("联机对战")){
            //to do something
            JLabel message = new JLabel("功能尚未完善。。。",SwingConstants.CENTER);
            message.setFont(new Font("黑体", Font.PLAIN, 16));
            JOptionPane.showMessageDialog(this, message,"提示",JOptionPane.CLOSED_OPTION);
        }else {
            new ExitConfirmDialog();
        }
    }


    /**
     * 复选框监听器
     */
    private class ChBoxActionListener implements ActionListener{

        private JCheckBox checkBox;

        public ChBoxActionListener(JCheckBox checkBox){
            this.checkBox = checkBox;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

//            System.out.println(checkBox.isSelected());
            if (checkBox.getActionCommand().equals("bgmChBox")){

                if (checkBox.isSelected() == true){
                    Setting.setIsBgmOn(true);

                }else {
                    Setting.setIsBgmOn(false);
                }
            }else {
                if (checkBox.isSelected() == true){
                    Setting.setIsMusicFxOn(true);
                }else {
                    Setting.setIsMusicFxOn(false);
                }
            }
        }
    }

    /**
     * 游戏提示对话框
     */
    private class TipsDialog extends JDialog{

        public TipsDialog(){

            this.setSize(960, 580);
            this.setTitle("游戏提示");
            Image icon = new ImageIcon("sources/images/icon.png").getImage();
            this.setIconImage(icon);
            this.setLocationByPlatform(true);
            this.setModal(true);
            ImageIcon lableIcon = new ImageIcon("sources/images/ips.png");
            JLabel imgLable = new JLabel();
            imgLable.setIcon(lableIcon);
            this.add(imgLable);
            this.setVisible(true);
        }
    }

    /**
     * 游戏关于对话框
     */
    private class AboutDialog extends JDialog{

        public AboutDialog(){

            this.setTitle("关于");
            Image icon = new ImageIcon("sources/images/icon.png").getImage();
            this.setIconImage(icon);
            this.setSize(960, 540);
            this.setModal(true);
            this.setLocationByPlatform(true);
            TextArea aboutArea = new TextArea();
            //aboutArea.setBounds(null);
            aboutArea.setFont(new Font("黑体", Font.PLAIN, 20));
            try{
                FileReader fr = new FileReader("sources/updateLog");
                BufferedReader br = new BufferedReader(fr);
                String aboutStr = null;
                while ((aboutStr = br.readLine()) != null){
                aboutArea.append(aboutStr + "\n");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            this.add(aboutArea);
            this.setVisible(true);
        }
    }
    /**
     * 游戏设置对话框
     */
    private class SettingDialog extends JDialog{

        public SettingDialog(){

            this.setTitle("游戏设置");
            this.setSize(480, 400);
            this.setLocationRelativeTo(null);
            this.setModal(true);
            this.setLayout(new BorderLayout());
            JPanel panSetMyTank = new JPanel();
            panSetMyTank.setPreferredSize(new Dimension(480, 36));
            panSetMyTank.setBackground(Color.WHITE);
            panSetMyTank.setLayout(new FlowLayout(FlowLayout.LEFT));
            JLabel labMyTank = new JLabel(" 我方坦克：颜色 ");
            labMyTank.setFont(new Font("黑体", Font.PLAIN,16));
            JComboBox color = new JComboBox();
            color.setBackground(Color.WHITE);
            color.setBorder(null);
            color.setFont(new Font("黑体", Font.PLAIN, 15));
            color.addItem("红色");
            color.addItem("绿色");
            color.addItem("蓝色");
            color.addItem("白色");
            color.addItem("紫色");
            //System.out.println(color.getSelectedItem());
            panSetMyTank.add(labMyTank);
//            panSetMyTank.add(butChooseColor);
            panSetMyTank.add(color);

            JPanel panSetEnemy = new JPanel();
            panSetEnemy.setBackground(Color.GREEN);
            JPanel panSure = new JPanel();
            panSure.setPreferredSize(new Dimension(480, 50));
            panSure.setBackground(Color.lightGray);

            this.add(panSetMyTank, BorderLayout.NORTH);
            this.add(panSetEnemy, BorderLayout.CENTER);
            this.add(panSure, BorderLayout.SOUTH);
            this.setVisible(true);
            color.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (color.getSelectedItem().equals("红色"))
                        Setting.setMyTankColor(Color.RED);
                    else if (color.getSelectedItem().equals("绿色"))
                        Setting.setMyTankColor(Color.GREEN);
                    else if (color.getSelectedItem().equals("蓝色"))
                        Setting.setMyTankColor(Color.BLUE);
                    else if (color.getSelectedItem().equals("白色")){
                        Setting.setMyTankColor(Color.WHITE);
                        System.out.println(Setting.getMyTankColor());
                    }


                    else Setting.setMyTankColor(Color.magenta);
                }
            });
        }

//        private class ColorLable extends JLabel{
//
//            private Color color;
//
//            public ColorLable(Color color, String item){
//
//                super(item);
//                this.color = color;
//                this.setFont(new Font("黑体", Font.PLAIN, 12));
//                this.setBackground(color);
//                this.setSize(35, 20);
//            }
//        }
    }

//    public static void main(String[] args){
//        StartMenu startMenu = new StartMenu();
//        startMenu.init();
//    }
}
