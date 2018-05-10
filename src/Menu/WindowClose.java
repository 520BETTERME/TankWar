package Menu;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//关闭窗口的监听器
public class WindowClose extends WindowAdapter{

//    public WindowClose(){
//        new ExitConfirmDialog();
//    }

    @Override
    public void windowClosing(WindowEvent e) {
        new ExitConfirmDialog();
        //int option = JOptionPane.showConfirmDialog(jFrame.this, "确定退出游戏？","请确认？", JOptionPane.YES_NO_OPTION);
    }
}
