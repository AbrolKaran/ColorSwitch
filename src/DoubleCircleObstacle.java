package src;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class DoubleCircleObstacle extends Obstacle{

    private CircleObstacle c1;
    private CircleObstacle c2;
    @Override
    public void move(float vel,int ch){

        c1.move(vel, ch);
        c2.move(vel, ch);
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

    DoubleCircleObstacle(int d, ArrayList<String> c, float x, float y,float l,int dir){
        super(d,c,x,y,l,dir);
        c1 = new CircleObstacle(d,c,x,y,l+20,d*dir);
        c2 = new CircleObstacle(d,c,x,y,l,-1*dir*d);
        myColors = new ArrayList<String>();
        myColors.add("#FF5757");
        myColors.add("#FFDE59");
    }

    @Override
    public boolean intersect(Ball ball)
    {
        return c1.intersect(ball) || c2.intersect(ball);
    }

    @Override
    public boolean offscreen(Ball ball)
    {
        return c1.offscreen(ball);
    }

    @Override
    public float getY()
    {
        return c1.getY();
    }
}