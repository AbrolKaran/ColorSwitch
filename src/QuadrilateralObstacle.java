package src;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;
import javafx.scene.shape.Shape;
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

    public void move(float vel, int ch){
        Rotate rotate = new Rotate();
        rotate.setAngle(1*direction);
        rotate.setPivotX(posX);
        rotate.setPivotY(posY);
        for(Line ln: lines){
            ln.getTransforms().add(rotate);
        }

        if(ch==1){
            Y-= vel;
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
        r.getChildren().addAll(line1,line2,line3,line4);
    }

    public QuadrilateralObstacle(int d, ArrayList<String> c, float x,float y, float l, int dir){
        super(d,c,x,y,l,dir);

        myColors = new ArrayList<String>();
        myColors.add(colors.get(0));
        myColors.add(colors.get(1));
        myColors.add(colors.get(2));
        myColors.add(colors.get(3));

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

    @Override
    public boolean intersect(Ball ball)
    {
        Shape shape1 = Shape.intersect(ball.display(), line1);
        Shape shape2 = Shape.intersect(ball.display(), line2);
        Shape shape3 = Shape.intersect(ball.display(), line3);
        Shape shape4 = Shape.intersect(ball.display(), line4);

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
