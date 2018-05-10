package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MapPanel extends JPanel implements KeyListener{

    Tank tank;
    MyTank myTank;
    EnemyTank enemyTank;
//    int x, y;

    public MapPanel(){
        this.setBackground(Color.BLACK);
        this.myTank = new MyTank(100, 100, Color.RED);
        this.enemyTank = new EnemyTank(200, 200, Color.YELLOW);
//        this.x = myTank.getX();
//        this.y = myTank.getY();
//        this.addKeyListener(new KeyActionListener(myTank.getX(), myTank.getY(), myTank, this));
    }

    public void paint(Graphics g){

        super.paint(g);

        drawTank(myTank.getX(), myTank.getY(), myTank, myTank.getDirection(), g);
        drawTank(enemyTank.getX(), enemyTank.getY(), enemyTank, enemyTank.getDirection(), g);
    }

    public void drawTank(int x, int y, Tank tank, int direction, Graphics g){

//        super.paint(g); 添加此句会导致后画的坦克覆盖之前的坦克
        g.setColor(tank.getColor());

        switch (direction){
            case 0: //上
                //坦克左轮
                g.fill3DRect(x, y, 5, 30, false);
                //坦克中间
                g.fill3DRect(x + 5, y + 5, 15, 20, false);
                //坦克右轮
                g.fill3DRect(x + 19, y, 5, 30, false);
                //坦克中间圆
                g.drawOval(x + 7, y + 10, 10, 10);
                //坦克炮筒
                g.drawLine(x + 12, y + 15, x + 12, y - 5);
                break;
            case 1: //下
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 15, 20, false);
                g.fill3DRect(x + 19, y, 5, 30, false);
                g.drawOval(x + 7, y + 10, 10, 10);
                g.drawLine(x + 12, y + 15, x + 12, y + 35);
                break;
            case 2: //左
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 15, false);
                g.fill3DRect(x, y + 20, 30, 5, false);
                g.drawOval(x + 10, y + 7, 10, 10);
                g.drawLine(x + 15, y + 12, x - 5, y + 12);
                break;
            case 3: //右
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 15, false);
                g.fill3DRect(x, y + 20, 30, 5, false);
                g.drawOval(x + 10, y + 7, 10, 10);
                g.drawLine(x + 15, y + 12, x + 35, y + 12);
                break;
            default:
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()){
            //上
            case KeyEvent.VK_W:
                myTank.setDirection(0);
                myTank.moveUp();
                break;
            //下
            case KeyEvent.VK_S:
                myTank.setDirection(1);
                myTank.moveDown();
                break;
            //左
            case KeyEvent.VK_A:
                myTank.setDirection(2);
                myTank.moveLeft();
                break;
            //右
            case KeyEvent.VK_D:
                myTank.setDirection(3);
                myTank.moveRight();
            default:
                break;
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

//    private class KeyActionListener implements KeyListener{
//
//        int x;
//        int y;
//        Tank tank;
//        Component component;
//
//        public KeyActionListener(int x, int y, Tank tank, Component component) {
//            this.x = x;
//            this.y = y;
//            this.tank = tank;
//            this.component = component;
//        }
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//            System.out.println("KeyActionListener被调用了");
//           switch (e.getKeyCode()){
//               case KeyEvent.VK_UP:
//                   y --;
//                   component.repaint();
//                   break;
//               case KeyEvent.VK_DOWN:
//                   y ++;
//                   component.repaint();
//                   break;
//               case KeyEvent.VK_LEFT:
//                   x --;
//                   component.repaint();
//                   break;
//               case KeyEvent.VK_RIGHT:
//                   x ++;
//                   component.repaint();
//                   default:
//                       break;
//           }
//        }
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//
//        }
//
//        @Override
//        public void keyTyped(KeyEvent e) {
//
//        }
//    }
}
