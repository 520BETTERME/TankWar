package Game;
import Menu.WindowClose;
import Menu.FWindow;
import java.awt.*;

public class GameWindow extends FWindow {

    private GamerStateBar gamerStateBar;
    private MapPanel mapPanel;
//    private SetButtonPanel setButtonPanel;

    public GameWindow(){

        //默认打开窗口时最大化
//        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.mapPanel = new MapPanel();
//        this.setButtonPanel = new SetButtonPanel();
        this.gamerStateBar = new GamerStateBar();

        this.add(mapPanel, BorderLayout.CENTER);
        this.add(gamerStateBar, BorderLayout.SOUTH);

        this.setVisible(true);

        this.addKeyListener(mapPanel);
        this.addWindowListener(new WindowClose());

    }

    public static void main(String[] args){
        new GameWindow();
    }
}
