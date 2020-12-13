package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class Star
{
    private final int points;
    private float X;
    private float Y;
    private int state;
    private Image img;
    private ImageView star;


    public Star(float _X, float _Y)
    {
        this.points = 1;
        this.state = 1;
        this.X = _X;
        this.Y = _Y;

        try
        {
            this.img = new Image(new FileInputStream("Constants\\8.png"));
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


        this.star = new ImageView(img);
        star.setFitHeight(27);
        star.setPreserveRatio(true);
        star.setLayoutX(_X);
        star.setLayoutY(_Y);
    }


    public ImageView getStar()
    {
        return star;
    }

    public void move(float vel,int ch){
        if(ch==1){
            getStar().setLayoutY(getStar().getLayoutY()-vel);
        }
    }

}
