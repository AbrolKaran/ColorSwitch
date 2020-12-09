package sample;

import javafx.scene.image.Image;
import java.io.FileInputStream;

public class Star
{
    private final int points;
    private float X;
    private float Y;
    private int state;
    private Image star;


    public Star(float _X, float _Y)
    {
        this.points = 1;
        this.state = 1;
        this.X = _X;
        this.Y = _Y;

        try
        {
            this.star = new Image(new FileInputStream("Constants\\8.png"));
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public Image getStar()
    {
        return star;
    }

    public void move(float vel,int ch){
        if(ch==1){
            //Imageview.setLayoutY(Imageview.getLayoutY()-vel);
        }
    }

}