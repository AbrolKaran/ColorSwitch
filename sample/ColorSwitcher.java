package sample;

import java.io.FileInputStream;
import java.util.ArrayList;
import javafx.scene.image.*;

public class ColorSwitcher
{
    private ArrayList<String> allColors;
    private int state;
    private float X;
    private float Y;
    private int radius;
    private Image img;
    private ImageView cs;

    public ColorSwitcher(ArrayList<String> colors, int _radius, float _X, float _Y)
    {
        this.allColors = new ArrayList<>(4);
        allColors.add("#38B6FF");
        allColors.add("#CB6CE6");
        allColors.add("#FFDE59");
        allColors.add("#FF5757");

        this.state = 0;
        this.X = _X;
        this.Y = _Y;
        this.radius = _radius;

        try
        {
            this.img = new Image(new FileInputStream("Constants\\7.png"));
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        this.cs = new ImageView(img);
        cs.setFitHeight(_radius);
        cs.setPreserveRatio(true);
        cs.setLayoutX(_X);
        cs.setLayoutY(_Y);
    }

    public ImageView getCs()
    {
        return cs;
    }

    public void move(float vel,int ch){
        if(ch==1){
            getCs().setLayoutY(getCs().getLayoutY()-vel);
        }
    }
}
