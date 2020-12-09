package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;
import sample.Obstacle;

import java.util.ArrayList;

public class QuadrilateralObstacle extends Obstacle {
    private Line line1;
    private Line line2;
    private Line line3;
    private Line line4;
    private ArrayList<Line> lines;

    public Line getLine4() {
        return line4;
    }

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
        rotate.setPivotX(posX);
        rotate.setPivotY(posY);
        getLine1().getTransforms().add(rotate);
        getLine2().getTransforms().add(rotate);
        getLine3().getTransforms().add(rotate);
        getLine4().getTransforms().add(rotate);

        if(ch==1){
            getLine1().setLayoutY(getLine1().getLayoutY()-vel);
            getLine2().setLayoutY(getLine2().getLayoutY()-vel);
            getLine3().setLayoutY(getLine3().getLayoutY()-vel);
            getLine4().setLayoutY(getLine4().getLayoutY()-vel);
        }
    }

    @Override
    public void generateDirection(){

    }

    @Override
    public void display(Group r){
        r.getChildren().addAll(line1,line2,line3,line4);
    }

    public QuadrilateralObstacle(int d, ArrayList<String> c, float x,float y, float l, int dir){
        super(d,c,x,y,l,dir);
        line1 = new Line(x-l/2,y,x,y-l);
        line1.setStrokeWidth(10);
        line1.setStroke(Color.web(colors.get(0)));
        line1.setStrokeLineCap(StrokeLineCap.ROUND);

        line2 = new Line(x,y-l,x+l/2,y);
        line2.setStrokeWidth(10);
        line2.setStroke(Color.web(colors.get(1)));
        line2.setStrokeLineCap(StrokeLineCap.ROUND);

        line3 = new Line(x+l/2,y,x,y+l);
        line3.setStrokeWidth(10);
        line3.setStroke(Color.web(colors.get(2)));
        line3.setStrokeLineCap(StrokeLineCap.ROUND);

        line4 = new Line(x,y+l,x-l/2,y);
        line4.setStrokeWidth(10);
        line4.setStroke(Color.web(colors.get(3)));
        line4.setStrokeLineCap(StrokeLineCap.ROUND);


        lines = new ArrayList<>();
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
        lines.add(line4);
    }

}
