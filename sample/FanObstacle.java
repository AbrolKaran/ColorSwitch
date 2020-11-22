package sample;

import javafx.scene.Group;
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
        rotate.setAngle(1*direction);
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
    public void display(Group r){
        r.getChildren().addAll(r1,r2,r3,r4);

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

    public FanObstacle(int d, ArrayList<String> c,float x,float y,float l, int dir) {
        super(d,c,x,y,l,dir);
        this.length = 50;
        r1 = new Rectangle();
        r1.setX(x-length-5);
        r1.setY(y-5);
        r1.setWidth(length);
        r1.setHeight(10);
        r1.setFill(Color.web(colors.get(0)));

        r2 = new Rectangle();
        r2.setX(x-5);
        r2.setY(y-length - 5);
        r2.setWidth(10);
        r2.setHeight(length);
        r2.setFill(Color.web(colors.get(1)));

        r3 = new Rectangle();
        r3.setX(posX+5);
        r3.setY(posY-5);
        r3.setWidth(length);
        r3.setHeight(10);
        r3.setFill(Color.web(colors.get(2)));

        r4 = new Rectangle();
        r4.setX(posX-5);
        r4.setY(posY+5);
        r4.setWidth(10);
        r4.setHeight(length);
        r4.setFill(Color.web(colors.get(3)));

        rect = new ArrayList<>();
        rect.add(r1);
        rect.add(r2);
        rect.add(r3);
        rect.add(r4);

    }

}
