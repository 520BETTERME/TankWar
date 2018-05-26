package com.betterme.tankwar.menu;

import javax.swing.*;
import java.awt.*;

//确认退出游戏对话框
public class ExitConfirmDialog extends JDialog{

    ExitConfirmDialog(){

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        JLabel sureText = new JLabel("确认退出游戏？");
        sureText.setFont(new Font("黑体", Font.PLAIN, 16));
        int value = JOptionPane.showConfirmDialog(ExitConfirmDialog.this, sureText, "请确认",
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

        if (value == JOptionPane.YES_OPTION){
            System.exit(0);
        }else {
            return;
        }
    }

}

