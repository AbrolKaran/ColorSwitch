import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Translate;
import java.awt.Rectangle;
import java.io.Serializable;

public class Ball implements Serializable
{
    private float X;
    private float Y;
    private final double acceleration;
    private float velocity;
    private String color;
    private int radius;
    private int state;
    private Circle ball;

    public Ball(float posX, float posY, float vel, int _radius, String _color)
    {
        this.acceleration = 0.4;
        this.velocity = vel;
        this.radius = _radius;
        this.X = posX;
        this.Y = posY;
        this.color = _color;
        this.ball = new Circle(X,Y, radius, Color.web(_color));
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

    public void move(int flag){
        updateVel(flag);
        Translate translate = new Translate();
        translate.setX(0);
        translate.setY(velocity);
        Y = Y + velocity;
        this.ball.getTransforms().addAll(translate);
    }

    public float getY()
    {
        return Y;
    }

    public float getX()
    {
        return X;
    }

    public float getVelocity()
    {
        return velocity;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getColor()
    {
        return color;
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)ball.getCenterX(), (int)ball.getCenterY(), (int)(2*ball.getRadius()), (int)(2*ball.getRadius()));
    }

}
