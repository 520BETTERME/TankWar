package Menu;

import java.awt.*;

//获取不同设备屏幕长宽
public class Screen {

    public static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static Dimension screenSize = toolkit.getScreenSize();
    static int screenWidth = screenSize.width;
    static int screenHeight = screenSize.height;

    public int getScreenWidth(){
        return screenWidth;
    }

    public int getScreenHeight(){
        return screenHeight;
    }

}
