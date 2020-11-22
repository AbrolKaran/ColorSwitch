package sample;

import javafx.scene.Group;

import java.util.ArrayList;

public class SquareCircleObstacle extends Obstacle{
    private SquareObstacle s1;
    private CircleObstacle c1;

    @Override
    public void move(){

        s1.move();
        c1.move();
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

    SquareCircleObstacle(int d, ArrayList<Integer> c, float x, float y,float l,int dir){
        super(d,c,x,y,l,dir);
        s1 = new SquareObstacle(d,c,x-l-20,y-l-20,l+90,dir);
        c1 = new CircleObstacle(d,c,x,y,l,-1*dir);
    }

}
