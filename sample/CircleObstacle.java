package sample;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;


import java.util.ArrayList;

public class CircleObstacle extends Obstacle{
    private Arc arc4;
    private Arc arc1;
    private Arc arc2;
    private Arc arc3;
    private ArrayList<Arc> arcList;

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

    public ArrayList<Arc> getArcList() {
        return arcList;
    }

    @Override
    public void move(float vel, int ch){
        Rotate rotate = new Rotate();
        rotate.setAngle(1*direction);
        rotate.setPivotX(posX);
        rotate.setPivotY(posY);

        for(Arc ac: arcList){
            ac.getTransforms().add(rotate);
        }

        if(ch==1){
            Y -= vel;
            for(Arc ac: arcList){
                ac.setLayoutY(ac.getLayoutY()-vel);
            }
        }
    }

    @Override
    public void generateDirection(){

    }

    @Override
    public void display(Group r){
        r.getChildren().addAll(arc1,arc2,arc3,arc4);
    }

    public void show(HBox hb)
    {
        hb.getChildren().addAll(arc1, arc2, arc3, arc4);
    }

    public CircleObstacle(int d, ArrayList<String> c,float x, float y,float l, int dir){

        super(d,c,x,y,l,dir);
        myColors = new ArrayList<String>();
        myColors.add(colors.get(0));
        myColors.add(colors.get(1));
        myColors.add(colors.get(2));
        myColors.add(colors.get(3));
        this.posY = y;
        this.length = l;
        this.direction = dir*d;
        arc1 = new Arc();
        arc1.setCenterX(posX);
        arc1.setCenterY(posY);
        arc1.setStartAngle(135);
        arc1.setRadiusX(length);
        arc1.setRadiusY(length);
        arc1.setLength(90.0f);
        arc1.setStroke(Color.web(colors.get(0)));
        arc1.setStrokeWidth(10.0f);
        arc1.setFill(Color.TRANSPARENT);
        arc1.setType(ArcType.OPEN);

        arc2 = new Arc();
        arc2.setCenterX(posX);
        arc2.setCenterY(posY);
        arc2.setStartAngle(225);
        arc2.setRadiusX(length);
        arc2.setRadiusY(length);
        arc2.setLength(90.0f);
        arc2.setStroke(Color.web(colors.get(3)));
        arc2.setStrokeWidth(10.0f);
        arc2.setFill(Color.TRANSPARENT);
        arc2.setType(ArcType.OPEN);

        arc3 = new Arc();
        arc3.setCenterX(posX);
        arc3.setCenterY(posY);
        arc3.setStartAngle(315);
        arc3.setRadiusX(length);
        arc3.setRadiusY(length);
        arc3.setLength(90);
        arc3.setStroke(Color.web(colors.get(1)));
        arc3.setStrokeWidth(10.0f);
        arc3.setFill(Color.TRANSPARENT);
        arc3.setType(ArcType.OPEN);

        arc4 = new Arc();
        arc4.setCenterX(posX);
        arc4.setCenterY(posY);
        arc4.setStartAngle(45);
        arc4.setRadiusX(length);
        arc4.setRadiusY(length);
        arc4.setLength(90.0f);
        arc4.setStroke(Color.web(colors.get(2)));
        arc4.setStrokeWidth(10.0f);
        arc4.setFill(Color.TRANSPARENT);
        arc4.setType(ArcType.OPEN);

        arcList = new ArrayList<>();
        arcList.add(arc1);
        arcList.add(arc2);
        arcList.add(arc3);
        arcList.add(arc4);
    }

    @Override
    public boolean intersect(Ball ball)
    {
        Shape shape1 = Shape.intersect(ball.display(), arc1);
        Shape shape2 = Shape.intersect(ball.display(), arc2);
        Shape shape3 = Shape.intersect(ball.display(), arc3);
        Shape shape4 = Shape.intersect(ball.display(), arc4);

        boolean cond1 = !shape1.getBoundsInLocal().isEmpty() && !colors.get(0).equals(ball.getColor());
        boolean cond2 = !shape2.getBoundsInLocal().isEmpty() && !colors.get(1).equals(ball.getColor());
        boolean cond3 = !shape3.getBoundsInLocal().isEmpty() && !colors.get(2).equals(ball.getColor());
        boolean cond4 = !shape4.getBoundsInLocal().isEmpty() && !colors.get(3).equals(ball.getColor());

        return cond1 || cond2 || cond3 || cond4;
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