package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class TriangleObstacle extends Obstacle{

    private Line line1;
    private Line line2;
    private Line line3;
    private ArrayList<Line> lines;


    public Line getLine1() {
        return line1;
    }

    public Line getLine2() {
        return line2;
    }

    public Line getLine3() {
        return line3;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public void move(float vel,int ch)
    {
        Rotate rotate = new Rotate();
        rotate.setAngle(1*direction);
        rotate.setPivotX(posX+length/2);
        rotate.setPivotY(posY-length*0.288);
        for(Line ln: lines){
            ln.getTransforms().add(rotate);
        }

        if(ch==1){
            Y -= vel;
            for(Line ln: lines){
                ln.setLayoutY(ln.getLayoutY()-vel);
            }
        }
    }

    @Override
    public void generateDirection(){

    }

    @Override
    public void display(Group r){
        r.getChildren().addAll(line1,line2,line3);
    }

    public TriangleObstacle(int d, ArrayList<String> c, float x,float y, float l, int dir){
        super(d,c,x,y,l,dir);
        myColors = new ArrayList<String>();
        myColors.add(colors.get(0));
        myColors.add(colors.get(1));
        myColors.add(colors.get(2));

        line1 = new Line(x,y,x+0.5*l,y-0.866*l);
        line1.setStrokeWidth(10);
        line1.setStroke(Color.web(colors.get(0)));
        line1.setStrokeLineCap(StrokeLineCap.ROUND);
        line2 = new Line(x,y,x+l,y);
        line2.setStrokeWidth(10);
        line2.setStroke(Color.web(colors.get(1)));
        line2.setStrokeLineCap(StrokeLineCap.ROUND);
        line3 = new Line(x+0.5*l,y-0.866*l,x+l,y);
        line3.setStrokeWidth(10);
        line3.setStroke(Color.web(colors.get(2)));
        line3.setStrokeLineCap(StrokeLineCap.ROUND);

        lines = new ArrayList<>();
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
    }

    @Override
    public boolean intersect(Ball ball)
    {
        Shape shape1 = Shape.intersect(ball.display(), line1);
        Shape shape2 = Shape.intersect(ball.display(), line2);
        Shape shape3 = Shape.intersect(ball.display(), line3);

        boolean cond1 = !shape1.getBoundsInLocal().isEmpty() && !colors.get(0).equals(ball.getColor());
        boolean cond2 = !shape2.getBoundsInLocal().isEmpty() && !colors.get(1).equals(ball.getColor());
        boolean cond3 = !shape3.getBoundsInLocal().isEmpty() && !colors.get(2).equals(ball.getColor());

        return cond1 || cond2 || cond3;
    }

    public boolean collides(Ball c1, Line line)
    {
        double closestX = this.clamp(c1.display().getLayoutX(), line.getLayoutX(), line.getLayoutX() + length(line));
        double closestY = this.clamp(c1.display().getLayoutY(), line.getLayoutY() - line.getStrokeWidth(), line.getLayoutY());

        double distanceX = c1.display().getLayoutX() - closestX;
        double distanceY = c1.display().getLayoutY() - closestY;

        return Math.pow(distanceX, 2) + Math.pow(distanceY, 2) < Math.pow(c1.display().getRadius(), 2);
    }

    public double midX(Line line)
    {
        return (line.getStartX() + line.getEndX())/2;
    }

    public double midY(Line line)
    {
        return (line.getStartY() + line.getEndY())/2;
    }

    public double length(Line line)
    {
        return Point2D.distance(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
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