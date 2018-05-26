package com.betterme.tankwar.menu;

import com.betterme.tankwar.game.MapPanel;
import com.betterme.tankwar.game.Recorder;
import com.betterme.tankwar.game.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 结束菜单，包含继续游戏和退出游戏还有输赢对应图片标签
 */
public class OverMenu extends FWindow implements ActionListener{

    public OverMenu(){

        super();
    }

    public void init(String overImgUrl){

        ImageIcon barImg = new ImageIcon("sources/bar.png");
        this.setLayout(new BorderLayout());

        //北方图片标签，用白色图片留白
        JLabel barLabNorth = new JLabel();
        barLabNorth.setIcon(barImg);
        barLabNorth.setPreferredSize(new Dimension(960,40));

        //中间面板
        JPanel panCenter = new JPanel();
        panCenter.setLayout(new GridLayout(1,2));

        ArrayList<JButton> buChoices = new ArrayList<>();
        JButton buChoice1 = new JButton("返回主菜单");
        JButton buChoice2 = new JButton("继续本关卡");
        JButton buChoice3 = new JButton("进行下一关");
        JButton buChoice4 = new JButton("退出游戏");
        buChoices.add(buChoice1);
        buChoices.add(buChoice2);
        buChoices.add(buChoice3);
        buChoices.add(buChoice4);
        ButtonPanel panL = new ButtonPanel(400,460,25,25, buChoices);
        panL.setPanNorthSize(400, 40);
        panL.setPanSouthSize(400, 80);
        panL.setPanWESize(100, 350);
        panL.setButtonBackground(new Color(232,232,232));
        panL.setButtonTextFont(new Font("黑体", Font.PLAIN, 25));
        panL.setButtonBorder(null);
        //右边面板，采用边界布局
        JLabel overImgLab = new JLabel();
        ImageIcon overImg = new ImageIcon(overImgUrl);
        overImgLab.setIcon(overImg);

        panCenter.add(panL);
        panCenter.add(overImgLab);

        this.add(panCenter);
        this.add(barLabNorth, BorderLayout.NORTH);
        this.add(panCenter, BorderLayout.CENTER);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        for (int i = 0; i < buChoices.size(); i++ ){
            buChoices.get(i).addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("返回主菜单")){
            new StartMenu().init();
            this.dispose();
        }else if (e.getActionCommand().equals("退出游戏")){
            new ExitConfirmDialog();
        }else if (e.getActionCommand().equals("进行下一关")){
            if(MapPanel.isWinGame() == false){
                JLabel jLabel = new JLabel("请先赢得本关卡游戏！", SwingConstants.CENTER);
                jLabel.setFont(new Font("黑体", Font.PLAIN, 16));
                JOptionPane.showMessageDialog(this, jLabel, "提示", JOptionPane.CLOSED_OPTION);
            }else {
                if (Recorder.getStage() < 3) {
                    int stage = Recorder.getStage() + 1;
                    Setting.initStage(stage);
                    new GameWindow(stage);
                    this.dispose();
                } else {
                    JLabel jLabel = new JLabel("菜鸟作者正在努力添加下一关中。。。", SwingConstants.CENTER);
                    jLabel.setFont(new Font("黑体", Font.PLAIN, 16));
                    JOptionPane.showMessageDialog(this, jLabel, "提示", JOptionPane.CLOSED_OPTION);
                }
            }
        }else if (e.getActionCommand().equals("继续本关卡")){
            int stage = Recorder.getStage();
            Setting.initStage(stage);
            new GameWindow(stage);
            this.dispose();
        }
    }


}
