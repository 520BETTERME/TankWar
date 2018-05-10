package Game;

import java.awt.*;

public class MyTank extends Tank {

    public MyTank(int x, int y, Color color){

        super(x, y, color);
    }

    public void moveUp(){
        this.y = this.y - speed;
    }

    public void moveDown(){
        this.y = this.y + speed;
    }

    public void moveLeft(){
        this.x = this.x - speed;
    }

    public void moveRight(){
        this.x = this.x + speed;
    }





}
