package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Ball
{
    private float X;
    private final double acceleration;
    private float velocity;
    private int color;
    private int radius;
    private int state;
    private Circle ball;

    public Ball(float pos, float vel, int _radius, int _color)
    {
        this.acceleration = 9.8;
        this.velocity = vel;
        this.radius = _radius;
        this.X = pos;
        this.color = _color;
        this.ball = new Circle(X, 500, radius, Color.web("#38B6FF"));
    }

    public Circle display()
    {
        return this.ball;
    }

}
