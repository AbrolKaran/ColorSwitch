package sample;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class CircleObstacle extends Obstacle{
    private Arc arc4;
    private Arc arc1;
    private Arc arc2;
    private Arc arc3;

    public Arc getArc4() {
        return arc4;
    }

    public Arc getArc1() {
        return arc1;
    }

    public Arc getArc2() {
        return arc2;
    }

    public Arc getArc3(){
        return arc3;
    }

    @Override
    public void move(){
        Rotate rotate = new Rotate();
        rotate.setAngle(1);
        rotate.setPivotX(posX);
        rotate.setPivotY(posY);
        getArc1().getTransforms().add(rotate);
        getArc2().getTransforms().add(rotate);
        getArc3().getTransforms().add(rotate);
        getArc4().getTransforms().add(rotate);
    }

    @Override
    public void generateDirection(){

    }

    @Override
    public void display(){

    }

    public CircleObstacle(int d, ArrayList<Integer> c,float x, float y){

        super(d,c,x,y);
        this.length = 45;
        arc1 = new Arc();
        arc1.setCenterX(posX);
        arc1.setCenterY(posY);
        arc1.setStartAngle(90.0f);
        arc1.setRadiusX(45);
        arc1.setRadiusY(45);
        arc1.setLength(90.0f);
        arc1.setStroke(Color.GREEN);
        arc1.setStrokeWidth(10.0f);
        arc1.setFill(Color.TRANSPARENT);
        arc1.setType(ArcType.OPEN);

        arc2 = new Arc();
        arc2.setCenterX(posX);
        arc2.setCenterY(posY);
        arc2.setStartAngle(180.0f);
        arc2.setRadiusX(45);
        arc2.setRadiusY(45);
        arc2.setLength(90.0f);
        arc2.setStroke(Color.RED);
        arc2.setStrokeWidth(10.0f);
        arc2.setFill(Color.TRANSPARENT);
        arc2.setType(ArcType.OPEN);

        arc3 = new Arc();
        arc3.setCenterX(posX);
        arc3.setCenterY(posY);
        arc3.setStartAngle(270.0f);
        arc3.setRadiusX(45);
        arc3.setRadiusY(45);
        arc3.setLength(90.0f);
        arc3.setStroke(Color.YELLOW);
        arc3.setStrokeWidth(10.0f);
        arc3.setFill(Color.TRANSPARENT);
        arc3.setType(ArcType.OPEN);

        arc4 = new Arc();
        arc4.setCenterX(posX);
        arc4.setCenterY(posY);
        arc4.setStartAngle(0.0f);
        arc4.setRadiusX(45);
        arc4.setRadiusY(45);
        arc4.setLength(90.0f);
        arc4.setStroke(Color.BLUE);
        arc4.setStrokeWidth(10.0f);
        arc4.setFill(Color.TRANSPARENT);
        arc4.setType(ArcType.OPEN);
    }
}
