package src;
import javafx.scene.Group;

import java.util.ArrayList;

public class SquareCircleObstacle extends Obstacle{
    private SquareObstacle s1;
    private CircleObstacle c1;

    @Override
    public void move(float vel,int ch){

        s1.move(vel,ch);
        c1.move(vel,ch);
    }

    @Override
    public void generateDirection(){

    }

    @Override
    public void display(Group r){
        s1.display(r);
        c1.display(r);
    }



    public SquareObstacle getS1() {
        return s1;
    }

    public CircleObstacle getC1() {
        return c1;
    }

    SquareCircleObstacle(int d, ArrayList<String> c, float x, float y,float l,int dir){
        super(d,c,x,y,l,dir);
        s1 = new SquareObstacle(d,c,x-(l+100)/2,y-(l+100)/2,l+90,d*dir);
        c1 = new CircleObstacle(d,c,x,y,l,-1*dir*d);

        myColors = new ArrayList<>();
        myColors.add("#CB6CE6");
    }

    @Override
    public boolean intersect(Ball ball)
    {
        return s1.intersect(ball) || c1.intersect(ball);
    }

    @Override
    public boolean offscreen(Ball ball)
    {
        return s1.offscreen(ball);
    }

    @Override
    public float getY()
    {
        return c1.getY();
    }


}