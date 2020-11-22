package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class FanObstacle extends Obstacle{

    private Rectangle r1;
    private Rectangle r2;
    private Rectangle r3;
    private Rectangle r4;
    private ArrayList<Rectangle> rect;

    @Override
    public void move(){

    }

    @Override
    public void generateDirection(){

    }
    public Rectangle getR1() {
        return r1;
    }

    public Rectangle getR2() {
        return r2;
    }

    public Rectangle getR3() {
        return r3;
    }

    public Rectangle getR4() {
        return r4;
    }

    public FanObstacle(int d, ArrayList<Integer> c,float x,float y) {
        super(d,c,x,y);
        this.length = 50;
        r1 = new Rectangle();
        r1.setX(x-length);
        r1.setY(y);
        r1.setWidth(50);
        r1.setHeight(10);
        r1.setFill(Color.BLUE);

        r2 = new Rectangle();
        r2.setX(x);
        r2.setY(y-length);
        r2.setWidth(10);
        r2.setHeight(50);
        r2.setFill(Color.GREEN);

        r3 = new Rectangle();
        r3.setX(x+10);
        r3.setY(y);
        r3.setWidth(50);
        r3.setHeight(10);
        r3.setFill(Color.YELLOW);

        r4 = new Rectangle();
        r4.setX(posX);
        r4.setY(posY+10);
        r4.setWidth(10);
        r4.setHeight(50);
        r4.setFill(Color.RED);

    }

}
