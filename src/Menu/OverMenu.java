package Menu;

import javax.swing.*;
import java.awt.*;
//结束菜单
public class OverMenu extends FWindow {

    public OverMenu(String overImgName){

        super();
        String author = "By BetterMe";
        JLabel authorInfoLabel = new JLabel(author);

        this.setLayout(new GridLayout());

        IntoLayoutPanel continueButtonPanel = new IntoLayoutPanel("继续游戏");
        IntoLayoutPanel exitButtonPanel = new IntoLayoutPanel("退出游戏");
        ImgPanel overImg = new ImgPanel(overImgName);
        IntoLayoutPanel authorPanel = new IntoLayoutPanel(authorInfoLabel);

        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new GridLayout(2,1));
        panelLeft.add(continueButtonPanel);
        panelLeft.add(exitButtonPanel);

        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BorderLayout());

        panelRight.add(overImg, BorderLayout.CENTER);
        panelRight.add(authorPanel, BorderLayout.SOUTH);

        this.add(panelLeft);
        this.add(panelRight);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowClose());
    }

    public static void main(String[] args){
        new OverMenu("sources/win.png");
        new OverMenu("sources/fail.png");
    }

}
