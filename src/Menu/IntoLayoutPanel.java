package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//放在各种布局里的面板，此面板里都采用流布局
public class IntoLayoutPanel extends JPanel {

    @Override
    public void setLayout(LayoutManager mgr) {
        super.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    public IntoLayoutPanel(String buttonText) {
        JButton button = new JButton(buttonText);
//            setLayout(new BorderLayout());
        button.setFont(new Font("黑体", Font.PLAIN, 16));
        add(button);
        button.setBackground(Color.WHITE);
//        button.setSize(35,15); 如果setLayouy(null)，可以修改大小
        button.addActionListener(new ChoiceActionListener(buttonText));
    }

    public IntoLayoutPanel(JLabel jLabel) {
        add(jLabel);
    }

    //面板里各种按钮的监听器
    private class ChoiceActionListener implements ActionListener{

        private String buttonText;
//        ExitConfirmDialog exitConfirmDialog = new ExitConfirmDialog();

        private ChoiceActionListener(String buttonText){
            this.buttonText = buttonText;
        }

        public void actionPerformed(ActionEvent event){
            if (buttonText.equals("退出游戏")){
                //            if(event.getSource().equals("退出游戏")){
//                exitConfirmDialog.exit();
                new ExitConfirmDialog();
            }else if(buttonText.equals("一对一")){
                //to do something
            }else if(buttonText.equals("一对多")){
                //to do something
            }else if(buttonText.equals("再来一局")){
                new StartMenu();
            }else {
                Exception e = new Exception();
                e.printStackTrace();
            }
        }
    }

}