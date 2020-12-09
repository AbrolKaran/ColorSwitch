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

    public Obstacle(int difficulty, ArrayList<String> colors,float x, float y,float l,int d) {
        this.colors = colors;
        posX = x;
        posY = y;
        length = l;
        direction = d;
        this.difficulty = difficulty;
    }
    //public abstract void generateColors(Ball ball);

    public abstract void generateDirection();

    public abstract void move(float vel,int ch);

    public abstract void display(Group root);

}
