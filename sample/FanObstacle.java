package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class FanObstacle extends Obstacle{

    private Rectangle r1;
    private Rectangle r2;
    private Rectangle r3;
    private Rectangle r4;
    private ArrayList<Rectangle> rect;

    @Override
    public void move(float vel, int ch){
        Rotate rotate = new Rotate();
        rotate.setAngle(1*direction);
        rotate.setPivotX(posX);
        rotate.setPivotY(posY);
        for(Rectangle rt: rect){
            rt.getTransforms().add(rotate);
        }

        if(ch==1){
            Y -= vel;
            for(Rectangle rt: rect){
                rt.setLayoutY(rt.getLayoutY()-vel);
            }
        }
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

        myColors = new ArrayList<String>();
        myColors.add(colors.get(0));
        myColors.add(colors.get(1));
        myColors.add(colors.get(2));
        myColors.add(colors.get(3));

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

    @Override
    public boolean intersect(Ball ball)
    {
        Shape shape1 = Shape.intersect(ball.display(), r1);
        Shape shape2 = Shape.intersect(ball.display(), r2);
        Shape shape3 = Shape.intersect(ball.display(), r3);
        Shape shape4 = Shape.intersect(ball.display(), r4);

        boolean cond1 = !shape1.getBoundsInLocal().isEmpty() && !colors.get(0).equals(ball.getColor());
        boolean cond2 = !shape2.getBoundsInLocal().isEmpty() && !colors.get(1).equals(ball.getColor());
        boolean cond3 = !shape3.getBoundsInLocal().isEmpty() && !colors.get(2).equals(ball.getColor());
        boolean cond4 = !shape4.getBoundsInLocal().isEmpty() && !colors.get(3).equals(ball.getColor());

        return cond1 || cond2 || cond3 || cond4;
        //return collides(ball, r1) || collides(ball, r2) || collides(ball, r3) || collides(ball, r4);
    }

    public boolean collides(Ball c1, Rectangle r1)
    {
        double closestX = this.clamp(c1.getX(), r1.getLayoutX(), r1.getLayoutX() + r1.getWidth());
        double closestY = this.clamp(c1.getY(), r1.getLayoutY() - r1.getHeight(), r1.getLayoutY());

        double distanceX = c1.getX() - closestX;
        double distanceY = c1.getY() - closestY;

        return Math.pow(distanceX, 2) + Math.pow(distanceY, 2) < Math.pow(c1.display().getRadius(), 2);
    }

    public double clamp(double value, double min, double max)
    {
        double x = value;
        if (x < min)
        {
            x = min;
        }
        else if (x > max)
        {
            x = max;
        }
        return x;
    }

    @Override
    public boolean offscreen(Ball ball)
    {
        if(this.getY() - ball.getY() > 800)
        {
            return true;
        }

        return false;
    }
}
