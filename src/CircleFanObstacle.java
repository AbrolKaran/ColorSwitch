package src;
import javafx.scene.Group;

import java.util.ArrayList;

public class CircleFanObstacle extends Obstacle{
    private CircleObstacle c1;
    private FanObstacle f1;

    @Override
    public void move(float vel,int ch){

        f1.move(vel, ch);
        c1.move(vel, ch);
    }

    @Override
    public void generateDirection(){

    }

    @Override
    public void display(Group r){
        f1.display(r);
        c1.display(r);
    }

    public FanObstacle getS1() {
        return f1;
    }

    public CircleObstacle getC1() {
        return c1;
    }

    CircleFanObstacle(int d, ArrayList<String> c, float x, float y, float l, int dir){
        super(d,c,x,y,l,dir);
        f1 = new FanObstacle(d,c,x+20,y,l-10,d*dir);
        c1 = new CircleObstacle(d,c,x,y,l+20,-1*dir*d);
        myColors = new ArrayList<String>();
        myColors.add("#FF5757");
        myColors.add("#FFDE59");
        myColors.add("#CB6CE6");
    }

    @Override
    public boolean intersect(Ball ball)
    {
        return c1.intersect(ball) || f1.intersect(ball);
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
