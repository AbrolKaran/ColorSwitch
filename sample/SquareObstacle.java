package sample;


import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class SquareObstacle extends Obstacle{

    private Rectangle r1;
    private Rectangle r2;
    private Rectangle r3;
    private Rectangle r4;
    private ArrayList<Rectangle> rect;


    @Override
    public void move(){
        Rotate rotate = new Rotate();
        rotate.setAngle(1*direction);
        rotate.setPivotX(posX+(length+10)/2);
        rotate.setPivotY(posY+(length+10)/2);
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

    public SquareObstacle(int d, ArrayList<String> c,float x,float y,float l,int dir){
        super(d,c,x,y,l,dir);
        this.length = l;
        this.direction = dir;
        r1 = new Rectangle();
        r1.setX(x+10);
        r1.setY(y);
        r1.setWidth(length);
        r1.setHeight(10);
        r1.setFill(Color.web(colors.get(0)));

        r2 = new Rectangle();
        r2.setX(x);
        r2.setY(y);
        r2.setWidth(10);
        r2.setHeight(length);
        r2.setFill(Color.web(colors.get(1)));

        r3 = new Rectangle();
        r3.setX(x);
        r3.setY(y+length);
        r3.setWidth(length);
        r3.setHeight(10);
        r3.setFill(Color.web(colors.get(2)));

        r4 = new Rectangle();
        r4.setX(x+length);
        r4.setY(y+10);
        r4.setWidth(10);
        r4.setHeight(length);
        r4.setFill(Color.web(colors.get(3)));

        rect = new ArrayList<>();
        rect.add(r1);
        rect.add(r2);
        rect.add(r3);

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

    public Rectangle getR4() { return r4; }
}
