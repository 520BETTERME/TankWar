package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends FWindow {

    Music bgm = new Music("sources/bgm.wav");
    Music musicFx = new Music("sources/music.wav");

    public StartMenu(){

        super();
        String author = "By BetterMe";
        JCheckBox bgmChBox = new JCheckBox("背景音乐");
        JCheckBox musChBox = new JCheckBox("游戏音效");
        JButton tipsButton = new JButton("游戏提示");
        ImageIcon northBarImg = new ImageIcon("sources/northBar");
        JLabel authorInfoLabel = new JLabel(author);
        JLabel northBarLabel = new JLabel();
        northBarLabel.setIcon(northBarImg);

        tipsButton.setBackground(Color.WHITE);
        bgmChBox.setBackground(Color.WHITE);
        musChBox.setBackground(Color.WHITE);

        //左上角面板，采用流式布局
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3,1));
        JPanel choicePanel1 = new IntoLayoutPanel("一对一");
        JPanel choicePanel2 = new IntoLayoutPanel("一对多");
        JPanel choicePanel3 = new IntoLayoutPanel("退出游戏");
        panel1.add(choicePanel1);
        panel1.add(choicePanel2);
        panel1.add(choicePanel3);
        panel1.setVisible(true);

        //右上角面板
//        JLabel startImgLabel = new JLabel(startImg);
//        IntoLayoutPanel panel2 = new IntoLayoutPanel(startImgLabel);
        ImgPanel panel2 = new ImgPanel("sources/start.png");

        //左下角面板，采用流式布局
        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel3.add(bgmChBox);
        panel3.add(musChBox);
        panel3.add(tipsButton);


        //右下角面板
        IntoLayoutPanel panel4 = new IntoLayoutPanel(authorInfoLabel);

        //边界布局中间面板，采用网格布局
        JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayout(1,2));
        panel5.add(panel1);
        panel5.add(panel2);

        //边界布局南方面板，采用网格布局
        JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayout(1,2));
        panel6.add(panel3);
        panel6.add(panel4);

        //边界布局北方面板，以增大第一个按钮与窗口顶端的距离
        JPanel panel7 = new JPanel();
        panel7.add(northBarLabel);

        this.setLayout( new BorderLayout());
        this.add(panel5, BorderLayout.CENTER);
        this.add(panel6, BorderLayout.SOUTH);
        //网格布局北方的白条
        this.add(panel7, BorderLayout.NORTH);
        //关键
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(true);

        tipsButton.addActionListener(new TipsActionListener());
        bgmChBox.addActionListener(new ChBoxActionListener(bgmChBox));
        bgmChBox.setActionCommand("bgmChBox");
        musChBox.addActionListener(new ChBoxActionListener(musChBox));
        musChBox.setActionCommand("musChBox");
        addWindowListener(new WindowClose());

    }

    //游戏提示按钮监听器
    private class TipsActionListener implements ActionListener{

        public void actionPerformed(ActionEvent event){
            new TipsDialog();
        }
    }

    //游戏提示对话框布局
    private class TipsDialog extends JDialog{

        TipsDialog(){

            Screen thisScreen = new Screen();
            int width = thisScreen.getScreenWidth();
            int height = thisScreen.getScreenHeight();
            Image gameIcon = new ImageIcon("sources/icon").getImage();
            this.setTitle("游戏提示");
            this.setSize(width/2, height/2);
            this.setLocationByPlatform(true);
            this.setResizable(false);
            this.setIconImage(gameIcon);
            //模态对话框
            this.setModal(true);

            ImgPanel tipsImgPanel = new ImgPanel("sources/tips.png");

            this.add(tipsImgPanel);
            this.setVisible(true);
        }
    }

    //复选框监听器
    private class ChBoxActionListener implements ActionListener{

        private JCheckBox checkBox = new JCheckBox();

        public ChBoxActionListener(JCheckBox checkBox){
            this.checkBox = checkBox;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            boolean start;
//            System.out.println(checkBox.isSelected());
            if (checkBox.getActionCommand().equals("bgmChBox")){

                if (checkBox.isSelected() == true){
//                    System.out.println("bofang");
                    start = true;
                    bgm.Play(start);
                }else {
//                    System.out.println("tingzhi");
                    start = false;
                    bgm.Play(start);
                }

            }else {
                if (checkBox.isSelected() == true){
                    start = true;
                }else {
                    start = false;
                }
                musicFx.Play(start);
            }
        }

    }

    public static void main(String[] args){
        new StartMenu();
    }

}
