package Menu;

import javax.swing.*;
import java.awt.*;
//整个程序所有窗口的夫类
public class FWindow extends JFrame{

//    Screen thisScreen = new Screen();
    private String gameName = "TankWar";
    private String gameIconUri = "sources/icon.png";
    private Image gameIcon;
    private int width = 960;
    private int height = 540;

    public FWindow(){

        this.gameIcon = new ImageIcon(gameIconUri).getImage();
//        this.width = thisScreen.getScreenWidth();
//        this.height = thisScreen.getScreenHeight();

        this.setTitle(gameName);
        this.setIconImage(gameIcon);
        this.setSize(width, height);
        this.setResizable(false);
        //默认居中显示
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
