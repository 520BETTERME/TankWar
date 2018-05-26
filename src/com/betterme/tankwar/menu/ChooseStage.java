package com.betterme.tankwar.menu;

/**
 * 选择关卡的面板，可以开始全新的游戏，载入进度或者选择关卡
 */
import com.betterme.tankwar.game.Recorder;
import com.betterme.tankwar.game.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChooseStage extends FWindow implements ActionListener{

    Recorder recorder = new Recorder();

    public ChooseStage(){
        super();
    }
    public void init(){

        this.setLayout(new BorderLayout());
        //左边放置按钮的面板
        JButton button1 = new JButton("新游戏");
        JButton button2 = new JButton("载入进度");
        JButton button3 = new JButton("返回主菜单");
        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        ButtonPanel panL = new ButtonPanel(350,540, 40, 40, buttons);
        panL.setPanNSSize(350, 60 );
        panL.setPanWESize(40, 350);
        //panL.setPanEastSize(70, 350);
        panL.setButtonBackground(new Color(232,232,232));
        panL.setButtonTextFont(new Font("黑体", Font.PLAIN, 20));
        panL.setButtonBorder(null);
        //右边选择关卡的面板
        //ChooseStagePanel chooseStagePanelR= new ChooseStagePanel();
        JPanel chooseStagePanel = new JPanel();
        chooseStagePanel.setLayout(new BorderLayout());
        JPanel panNull = new JPanel();
        JPanel panNull1 = new JPanel();
        JPanel panNull2 = new JPanel();
        panNull.setPreferredSize(new Dimension(610,60));
        panNull1.setPreferredSize(new Dimension(40, 350));
        panNull2.setPreferredSize(new Dimension(610, 60));
        //存放关卡选择的滚动面板

        ImageIcon stage1Icon = new ImageIcon("sources/images/stage1.png");
        JButton buStage_1 = new JButton("", stage1Icon);
        ImageIcon stage2Icon = new ImageIcon("sources/images/stage2.png");
        JButton buStage_2 = new JButton("", stage2Icon);
        ImageIcon stage3Icon = new ImageIcon("sources/images/stage3.png");
        JButton buStage_3 = new JButton("", stage3Icon);
        ArrayList<JButton> buStages = new ArrayList<>();
        buStages.add(buStage_1);
        buStages.add(buStage_2);
        buStages.add(buStage_3);
        ButtonPanel stageBuPanel = new ButtonPanel(540, 951, 20, 20, buStages);
        stageBuPanel.setButtonBorder(null);
        stageBuPanel.setPanWestSize(40, 420);
        JScrollPane jspChooseStage = new JScrollPane(stageBuPanel);
        jspChooseStage.setBorder(null);
        for (int i = 0; i < buStages.size(); i++ ){
            JButton jButton = buStages.get(i);
            jButton.setActionCommand("第" + (i+1) + "关");
            //System.out.println(jButton.getActionCommand());
            jButton.addActionListener(this);
        }
        chooseStagePanel.add(panNull, BorderLayout.NORTH);
        chooseStagePanel.add(panNull1, BorderLayout.EAST);
        chooseStagePanel.add(panNull2, BorderLayout.SOUTH);
        chooseStagePanel.add(jspChooseStage, BorderLayout.CENTER);

        this.add(panL, BorderLayout.WEST);
        this.add(chooseStagePanel, BorderLayout.CENTER);

        this.setVisible(true);

        for (int i = 0; i < buttons.size(); i++ ){
            buttons.get(i).addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("返回主菜单")){
            new StartMenu().init();
            this.dispose();
        } else if (e.getActionCommand().equals("新游戏")){
            //System.out.println(MapPanel.runThread);
            JLabel sureText = new JLabel("当前操作会丢失游戏进度，确认继续？");
            sureText.setFont(new Font("黑体", Font.PLAIN, 17));
            sureText.setForeground(Color.RED);
            int option = JOptionPane.showConfirmDialog(this, sureText,"请确认",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (option == JOptionPane.YES_OPTION){
                Setting.initStage(1);
                GameWindow gw = new GameWindow(1);
                Thread thread = new Thread(gw);
                thread.start();
                this.dispose();
            }else {
                return;
            }
        } else if (e.getActionCommand().equals("载入进度")){
            //载入进度
//            System.out.println(recorder.getEnemyInfoFromJson());
//            System.out.println(recorder.getPassStage());
//            System.out.println(recorder.getPlayerInfoFromJson());
            if (recorder.getPassStage() && recorder.getEnemyInfoFromJson() && recorder.getPlayerInfoFromJson()){
                this.dispose();
                GameWindow gw = new GameWindow(Recorder.getStage());
                Thread thread = new Thread(gw);
                thread.start();
            }   //载入进度失败，跳出错误信息对话框
            else{
                JLabel errorText = new JLabel("载入进度出现错误，请重新开始游戏");
                errorText.setFont(new Font("黑体",Font.PLAIN, 17));
                errorText.setForeground(Color.RED);
                JOptionPane.showConfirmDialog(this, errorText, "错误！",
                        JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
            }
        } else {
            GameWindow gw = new GameWindow();
            if (e.getActionCommand().equals("第1关")) {
                Setting.initStage(1);
                gw = new GameWindow(1);
            } else if (e.getActionCommand().equals("第2关")) {
                Setting.initStage(2);
                gw = new GameWindow(2);
            } else if (e.getActionCommand().equals("第3关")) {
                Setting.initStage(3);
                gw = new GameWindow(3);
            }
            Thread thread = new Thread(gw);
            thread.start();
            this.dispose();
        }
    }

    /**
     * 选择管卡的面板
     */
//    private class ChooseStagePanel extends JPanel{
//
//        public ChooseStagePanel(){
//
//            ChooseStage chooseStage = new ChooseStage();
//            this.setLayout(new BorderLayout());
//            //this.setPreferredSize(new Dimension());
//            JPanel panNull = new JPanel();
//            JPanel panNull1 = new JPanel();
//            JPanel panNull2 = new JPanel();
//            panNull.setPreferredSize(new Dimension(710,60));
//            panNull1.setPreferredSize(new Dimension(40, 350));
//            panNull2.setPreferredSize(new Dimension(710, 60));
//            //存放关卡选择的滚动面板
//
//            ImageIcon stage1Icon = new ImageIcon("sources/images/stage1.png");
//            JButton buStage_1 = new JButton("", stage1Icon);
//            ImageIcon stage2Icon = new ImageIcon("sources/images/stage2.png");
//            JButton buStage_2 = new JButton("", stage2Icon);
//            ImageIcon stage3Icon = new ImageIcon("sources/images/stage3.png");
//            JButton buStage_3 = new JButton("", stage3Icon);
//            ArrayList<JButton> buStages = new ArrayList<>();
//            buStages.add(buStage_1);
//            buStages.add(buStage_2);
//            buStages.add(buStage_3);
//            ButtonPanel stageBuPanel = new ButtonPanel(540, 951, 20, 20, buStages);
//            stageBuPanel.setButtonBorder(null);
//            stageBuPanel.setPanWestSize(40, 420);
//            JScrollPane jspChooseStage = new JScrollPane(stageBuPanel);
//            jspChooseStage.setBorder(null);
//            for (int i = 0; i < buStages.size(); i++ ){
//                JButton jButton = buStages.get(i);
//                jButton.setActionCommand("第" + (i+1) + "关");
//                //System.out.println(jButton.getActionCommand());
//                jButton.addActionListener(chooseStage);
//            }
//            this.add(panNull, BorderLayout.NORTH);
//            this.add(panNull1, BorderLayout.EAST);
//            this.add(panNull2, BorderLayout.SOUTH);
//            this.add(jspChooseStage, BorderLayout.CENTER);
//        }
//    }
}



