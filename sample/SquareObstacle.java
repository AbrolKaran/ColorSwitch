package sample;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SquareObstacle extends Obstacle{

    private Rectangle r1;
    private Rectangle r2;
    private Rectangle r3;
    private Rectangle r4;

    @Override
    public void move(){

    }

    @Override
    public void generateDirection(){

    }

    public SquareObstacle(int d, ArrayList<Integer> c,float x,float y){
        super(d,c,x,y);

        r1 = new Rectangle();
        r1.setX(250);
        r1.setY(100);
        r1.setWidth(100);
        r1.setHeight(10);
        r1.setFill(Color.BLUE);

        r2 = new Rectangle();
        r2.setX(250);
        r2.setY(100);
        r2.setWidth(10);
        r2.setHeight(100);
        r2.setFill(Color.GREEN);

        r3 = new Rectangle();
        r3.setX(250);
        r3.setY(200);
        r3.setWidth(100);
        r3.setHeight(10);
        r3.setFill(Color.YELLOW);

        r4 = new Rectangle();
        r4.setX(340);
        r4.setY(100);
        r4.setWidth(10);
        r4.setHeight(100);
        r4.setFill(Color.RED);

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
}
