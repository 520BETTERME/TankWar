package Menu;

import javax.swing.*;
import java.awt.*;

//让图片自适应面板的大小
public class ImgPanel extends JPanel{

//    private Screen thisScreen = new Screen();
//    private int width = (int)thisScreen.getScreenWidth()*(477/1920);
//    private int height = (int)thisScreen.getScreenHeight()*(469/1080);
    private int width = 477;
    private int height = 469;
    private JLabel jLabel = new JLabel();

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public ImgPanel(String imgName){
        ImageIcon image = new ImageIcon(imgName);
        Image img = image.getImage();
//        Image img = new ImageIcon(imgName).getImage();
        img = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
        image.setImage(img);
        jLabel.setIcon(image);

        setLayout(new FlowLayout(FlowLayout.CENTER));
        jLabel.setSize(getWidth(), getHeight());
        add(jLabel);
        setVisible(true);
//        System.out.println(jLabel.getWidth());
    }

//    public static void main(String[] args){
//        ImgPanel imgPanel = new ImgPanel("sources/start");
//
//    }
}
