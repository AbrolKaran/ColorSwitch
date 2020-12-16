package sample;

import javafx.scene.Group;

import java.util.ArrayList;

public abstract class Obstacle {
    protected float speed;
    protected float length;
    protected float posX;
    protected float posY;
    protected ArrayList<String> colors;
    protected int difficulty;
    protected int direction;
    protected int numColors;
    protected ArrayList<String> myColors;
    protected float Y;

    public Obstacle(int difficulty, ArrayList<String> colors,float x, float y,float l,int d) {
        this.colors = colors;
        posX = x;
        posY = y;
        Y = y;
        length = l;
        direction = d;
        this.difficulty = difficulty;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getY() {
        return Y;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    //public abstract void generateColors(Ball ball);

    public abstract void generateDirection();

    public abstract void move(float vel,int ch);

    public abstract void display(Group root);

    public abstract boolean intersect(Ball ball);

    public ArrayList<String> getMyColors() {
        return myColors;
    }

    public abstract boolean offscreen(Ball ball);



}
