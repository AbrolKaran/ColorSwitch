package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;

public class SmallBall
{
    private float X;
    private float Y;
    private ArrayList<String> colors;
    private float radius;
    private Circle smallBall;
    private double angle;
    private double xVelocity;
    private double yVelocity;
    private double gravity;

    private static Random rand = new Random();

    public SmallBall(ArrayList<String> c, float _x, float _y, float r)
    {
        this.colors = c;
        this.X = _x;
        this.Y = _y;
        this.radius = r;
        this.angle = rand.nextInt((int)(2*Math.PI));
        this.xVelocity = Math.cos(this.angle) * rand.nextInt(14);
        this.yVelocity = Math.sin(this.angle) * rand.nextInt(14);
        this.gravity = 0.2;

        int color = rand.nextInt(colors.size());
        String newColor = colors.get(color);
        this.smallBall = new Circle(X,Y, radius, Color.web(newColor));
    }

    public void update()
    {
        this.yVelocity += this.gravity;
        this.Y += this.yVelocity;
        this.X += this.xVelocity;

        // If ball hits left or right, bounce off
        if (this.X + this.radius >= 365 || this.X - this.radius <= 0) {
            this.xVelocity *= -1;
        }
    }

    public boolean offscreen()
    {
        return this.Y - this.radius > 650;
    }

    public void display(Group root)
    {
        root.getChildren().add(this.smallBall);
    }

    public Circle getSmallBall() {
        return smallBall;
    }
}



