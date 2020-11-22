package sample;

import java.util.ArrayList;

public abstract class Obstacle {
    protected float speed;
    protected float length;
    protected float posX;
    protected float posY;
    protected ArrayList<Integer> colors;
    protected int numColors;
    protected int direction;

    public Obstacle(int difficulty, ArrayList<Integer> colors,float x, float y) {
        this.colors = colors;
        posX = x;
        posY = y;
    }
    //public abstract void generateColors(Ball ball);

    public abstract void generateDirection();

    public abstract void move();

    public abstract void display();

}
