package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Translate;

public class Ball
{
    private float X;
    private float Y;
    private final double acceleration;
    private float velocity;
    private int color;
    private int radius;
    private int state;
    private Circle ball;

    public Ball(float posX, float posY, float vel, int _radius, int _color)
    {
        this.acceleration = 0.4f;
        this.velocity = vel;
        this.radius = _radius;
        this.X = posX;
        this.Y = posY;
        this.color = _color;
        this.ball = new Circle(X,Y, radius, Color.web("#38B6FF"));
    }


    public void updateVel(int flag){
        velocity += acceleration;
        if(flag==1){
            velocity = -6.5f;
        }
    }

    public Circle display()
    {
        return this.ball;
    }

    public float getVelocity() {
        return velocity;
    }

    public float getY() {
        return Y;
    }

    public void move(int flag){
        updateVel(flag);
        this.Y +=velocity;
        Translate translate = new Translate();
        translate.setX(0);
        translate.setY(velocity);
        this.ball.getTransforms().addAll(translate);

    }

}
