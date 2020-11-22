package sample;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class DoubleCircleObstacle extends Obstacle{

    private CircleObstacle c1;
    private CircleObstacle c2;

    @Override
    public void move(){

        c1.move();
        c2.move();
    }

    @Override
    public void generateDirection(){

    }

    @Override
    public void display(Group r){
        c1.display(r);
        c2.display(r);
    }

    public CircleObstacle getC1() {
        return c1;
    }

    public CircleObstacle getC2() {
        return c2;
    }

    DoubleCircleObstacle(int d, ArrayList<Integer> c, float x, float y,float l,int dir){
        super(d,c,x,y,l,dir);
        c1 = new CircleObstacle(d,c,x,y,l+20,1*dir);
        c2 = new CircleObstacle(d,c,x,y,l,-1*dir);
    }

}
