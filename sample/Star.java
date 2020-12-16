package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.Serializable;

public class Star implements Serializable
{
    private int points;
    private float X;
    private float Y;
    private int state;
    private Image img;
    private ImageView star;


    public Star(float _X, float _Y)
    {
        this.points = 1;
        this.state = 0;
        this.X = _X;
        this.Y = _Y;

        try
        {
            this.img = new Image(new FileInputStream("Constants\\Star.png"));
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        this.star = new ImageView(img);
        star.setFitHeight(27);
        star.setPreserveRatio(true);
        star.setLayoutX(_X);
        star.setLayoutY(_Y);
    }

    public ImageView getStar()
    {
        return star;
    }

    public void move(float vel,int ch)
    {
        if(ch==1){
            getStar().setLayoutY(getStar().getLayoutY()-vel);
            Y -= vel;
        }
    }

    public float getY() {
        return Y;
    }

    public float getX() {
        return X;
    }


    public boolean intersect(Ball ball)
    {
        double distance = Point2D.distance(this.X, this.Y, ball.getX(), ball.getY());
        double sumRadii = img.getHeight()/4 + ball.display().getRadius();

        return sumRadii >= distance;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
