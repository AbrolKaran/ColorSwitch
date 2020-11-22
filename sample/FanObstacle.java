package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class FanObstacle extends Obstacle{

    private Rectangle r1;
    private Rectangle r2;
    private Rectangle r3;
    private Rectangle r4;
    private ArrayList<Rectangle> rect;

    @Override
    public void move(){
        Rotate rotate = new Rotate();
        rotate.setAngle(1);
        rotate.setPivotX(posX);
        rotate.setPivotY(posY);
        getR1().getTransforms().add(rotate);
        getR2().getTransforms().add(rotate);
        getR3().getTransforms().add(rotate);
        getR4().getTransforms().add(rotate);
    }

    @Override
    public void generateDirection(){

    }

    @Override
    public void display(){

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
        r1.setX(x-length-5);
        r1.setY(y-5);
        r1.setWidth(50);
        r1.setHeight(10);
        r1.setFill(Color.BLUE);

        r2 = new Rectangle();
        r2.setX(x-5);
        r2.setY(y-length - 5);
        r2.setWidth(10);
        r2.setHeight(50);
        r2.setFill(Color.GREEN);

        r3 = new Rectangle();
        r3.setX(posX+5);
        r3.setY(posY-5);
        r3.setWidth(50);
        r3.setHeight(10);
        r3.setFill(Color.YELLOW);

        r4 = new Rectangle();
        r4.setX(posX-5);
        r4.setY(posY+5);
        r4.setWidth(10);
        r4.setHeight(50);
        r4.setFill(Color.RED);

    }

}
