package Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;

//确认退出游戏对话框
public class ExitConfirmDialog extends JDialog{

//    private ActionEvent event = new ActionEvent();

    ExitConfirmDialog(){
//        this.event = event;
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        this.setDefaultCloseOperation(HIDE_ON_CLOSE);

        int value = JOptionPane.showConfirmDialog(ExitConfirmDialog.this, "确认退出游戏？", "请确认",
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

        if (value == JOptionPane.YES_OPTION){
            System.exit(0);
        }else {
            return;
        }
    }

}

