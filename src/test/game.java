package test;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class game extends JFrame{
    public JLabel jp = new JLabel("心随你动！！！");
    private int x = 0 , y = 0;

    public game(int x, int y, String title){
        this.x = x;
        this.y = y;
        jp.setBounds(100, 100, 100, 100);
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(jp);

    }

    public void lauchFrame() {
        this.setLocation(200, 100);
        this.setSize(500, 600);
        /*
         * 鼠標監聽的幾大分類
         *         MouseListener, MouseMotionListener,
         *      MouseWheelListener, EventListener
         *
         * MouseAdapter 对象实现 MouseListener 接口
         */
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                jp.setLocation(e.getPoint());
                x = e.getX();
                y = e.getY();
                repaint();
            }
        });

        this.setVisible(true);
    }



    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.drawOval(x,  y, 60, 70);
        g.drawOval(15 + x, 15 + y, 10, 10);
        g.drawOval(35 + x, 15 + y, 10, 10);

        /*
         * 畫三角形的三步
         */
        int[] xPoints=new int[]{25 + x, 30 + x, 35 + x};//所有点的x坐标
        int nPoints = 3;    //点数
        int[] yPoints=new int[]{35 + y, 40 + y, 35 + y};//所有点的y坐标
        g.drawPolygon(xPoints, yPoints, nPoints);
        g.drawOval(20 + x, 50 + y, 20, 10);
        g.setColor(c);
    }

    public static void main(String[] args) {
        /*
         * 新建game對象
         * 調用lauchFrame()方法
         *         注意：窗口的實現在lauchFrame()裏面，笑臉的構造在類的構造方法裏面！
         *
         */
        game gf = new game(0, 0, "SmileFace-Game");
        gf.lauchFrame();

    }
}
