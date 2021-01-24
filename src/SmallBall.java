package src;
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
    private double dx = this.velocity(), dy = this.velocity();
    private int movement;
    private int spawnPoint;
    private float WIDTH = 365;
    private float HEIGHT = 650;

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

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getSpawnPoint() {
        return spawnPoint;
    }

    public void setSpawnPoint(int spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

    public int velocity(){
        movement = rand.nextInt(1);
        if (movement == 1) {
            movement = rand.nextInt(10);
        }
        else if (movement == 0) {
            movement = (rand.nextInt(10)*-1);
        }
        return movement;
    }

    public int position(){
        return spawnPoint = rand.nextInt(500);

    }

    public double getDx() { return dx; }

    public final void setDx(double newDx) {
        while (newDx < -10 || newDx > 10) {
            newDx = this.velocity();
        }
        dx = newDx;
    }

    public double getDy() { return dy; }

    public final void setDy(double newDy) {
        while(newDy < -10 || newDy > 10) {
            newDy = this.velocity();
        }
        dy = newDy;
    }

    public void moveBall() {
        // Check boundaries
        if (X < radius || X > this.WIDTH - radius) {
            dx *= -1; // Change ball move direction
        }
        if (Y < radius || Y > this.HEIGHT - radius) {
            dy *= -1; // Change ball move direction
        }
        // Adjust ball position
        X += dx;
        Y += dy;
        smallBall.setCenterX(X);
        smallBall.setCenterY(Y);
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



