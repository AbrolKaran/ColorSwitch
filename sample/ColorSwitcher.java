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
    private Image cs;

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
            this.cs = new Image(new FileInputStream("Constants\\7.png"));
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public Image getCs()
    {
        return cs;
    }

    public void move(float vel,int ch){
        if(ch==1){
            //Imageview.setLayoutY(Imageview.getLayoutY()-vel);
        }
    }
}