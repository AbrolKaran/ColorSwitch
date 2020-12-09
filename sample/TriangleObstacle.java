package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Rotate;

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

    public void move(float vel,int ch){
        Rotate rotate = new Rotate();
        rotate.setAngle(1*direction);
        rotate.setPivotX(posX+length/2);
        rotate.setPivotY(posY-length*0.288);
        getLine1().getTransforms().add(rotate);
        getLine2().getTransforms().add(rotate);
        getLine3().getTransforms().add(rotate);

        if(ch==1){
            getLine1().setLayoutY(getLine1().getLayoutY()-vel);
            getLine2().setLayoutY(getLine2().getLayoutY()-vel);
            getLine3().setLayoutY(getLine3().getLayoutY()-vel);
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

}