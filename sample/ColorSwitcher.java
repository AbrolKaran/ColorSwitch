package sample;

import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import java.awt.Rectangle;
import java.util.concurrent.RecursiveAction;
import java.util.Random;
import javafx.scene.image.*;
import org.w3c.dom.css.Rect;

public class ColorSwitcher
{
    private ArrayList<String> myColors;
    private int state;
    private float X;
    private float Y;
    private float radius;
    private Image img;
    private ImageView cs;
    private static Random rand = new Random();

    public ColorSwitcher(ArrayList<String> colors, int _radius, float _X, float _Y)
    {
        this.myColors = colors;
        this.state = 0;
        this.X = _X;
        this.Y = _Y;
        this.radius = _radius;

        try
        {
            this.img = new Image(new FileInputStream("Constants\\7.png"));
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        this.cs = new ImageView(img);
        cs.setFitHeight(_radius);
        cs.setPreserveRatio(true);
        cs.setLayoutX(_X);
        cs.setLayoutY(_Y);
    }

    public ImageView getCs()
    {
        return cs;
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public float getX()
    {
        return this.X;
    }

    public float getY()
    {
        return this.Y;
    }

    public void move(float vel,int ch)
    {
        if(ch==1)
        {
            cs.setLayoutY(cs.getLayoutY()-vel);
            Y -= vel;
        }
    }

    public boolean intersect(Ball ball)
    {
        double distance = Point2D.distance(this.X, this.Y, ball.getX(), ball.getY());
        double sumRadii = img.getHeight()/4 + ball.display().getRadius();

        return sumRadii >= distance;
    }

    public void switchColor(Ball ball)
    {
        String currColor = ball.getColor();
        int changeTo = rand.nextInt(myColors.size());

        while(myColors.get(changeTo).equals(currColor))
        {
            changeTo = rand.nextInt(myColors.size());
        }

        String newColor = myColors.get(changeTo);
        ball.display().setFill(Color.web(newColor));
        ball.setColor(newColor);
    }


}
