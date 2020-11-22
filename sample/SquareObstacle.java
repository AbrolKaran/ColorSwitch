package sample;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class SquareObstacle extends Obstacle{

    private Rectangle r1;
    private Rectangle r2;
    private Rectangle r3;
    private Rectangle r4;

    @Override
    public void move(){
        Rotate rotate = new Rotate();
        rotate.setAngle(1);
        rotate.setPivotX(posX+50);
        rotate.setPivotY(posY+50);
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

    public SquareObstacle(int d, ArrayList<Integer> c,float x,float y){
        super(d,c,x,y);
        this.length = 90;
        r1 = new Rectangle();
        r1.setX(x+10);
        r1.setY(y);
        r1.setWidth(length);
        r1.setHeight(10);
        r1.setFill(Color.BLUE);

        r2 = new Rectangle();
        r2.setX(x);
        r2.setY(y);
        r2.setWidth(10);
        r2.setHeight(length);
        r2.setFill(Color.GREEN);

        r3 = new Rectangle();
        r3.setX(x);
        r3.setY(y+length);
        r3.setWidth(length);
        r3.setHeight(10);
        r3.setFill(Color.YELLOW);

        r4 = new Rectangle();
        r4.setX(x+length);
        r4.setY(y+10);
        r4.setWidth(10);
        r4.setHeight(length);
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
