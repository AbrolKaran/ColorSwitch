package src;
import javafx.scene.control.Label;

import java.io.*;
import java.util.ArrayList;

public class GameState implements Serializable
{
    private int id;
    private String name;
    private int score;
    private int difficultyLevel;
    private int flag;
    private int obCount;

    private float bX;
    private float bY;
    private String bC;

    private float obX;
    private float obY;

    private float csX;
    private float csY;

    private float sX;
    private float sY;

    private int count1;
    private int count2;

    //private GameState gameState;

    public GameState(int i, int sc, float bx, float by, String bc, float sx, float sy, float obx, float oby, float csx, float csy, int d, int f, int obC)
    {
        this.id = i;
        this.score = sc;
        this.difficultyLevel = d;
        this.obCount = obC;
        this.flag = f;
        this.obX = obx;
        this.obY = oby;
        this.csX = csx;
        this.csY = csy;
        this.sX = sx;
        this.sY = sy;
        this.bX = bx;
        this.bY = by;
        this.bC = bc;
        this.count1 = 0;
        this.count2 = 0;
    }

    public int getCount1() {
        return count1;
    }

    public int getCount2() {
        return count2;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setCount1(int count1) {
        this.count1 = count1;
    }

    public void setCount2(int count2) {
        this.count2 = count2;
    }

    public int getObCount() {
        return obCount;
    }

    public float getbX() {
        return bX;
    }

    public float getCsX() {
        return csX;
    }

    public float getbY() {
        return bY;
    }

    public float getCsY() {
        return csY;
    }

    public int getFlag() {
        return flag;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getObX() {
        return obX;
    }

    public float getObY() {
        return obY;
    }

    public float getsX() {
        return sX;
    }

    public float getsY() {
        return sY;
    }

    public String getbC() {
        return bC;
    }




    /*public void deserialize() throws IOException, ClassNotFoundException{

        ObjectInputStream in = null;
        try{
            in = new ObjectInputStream(new FileInputStream("out.txt"));
            gameState = (GameState) in.readObject();
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

        finally {
            in.close();
        }
    }*/

    /*Integer ints=new Integer(1);
    String filename = "tester.txt";

    // Serialization
    try
    {
        System.out.println("Heyy");
        //Saving of object in a file
        FileOutputStream file = new FileOutputStream(filename);
        System.out.println("Heyy1");
        ObjectOutputStream out = new ObjectOutputStream(file);
        System.out.println("Heyy11");
        // Method for serialization of object
        out.writeObject(ints);
        System.out.println("Heyy12");

        out.close();
        file.close();
        System.out.println("Heyy13");

        System.out.println("Object has been serialized");

    }

    catch(IOException ex)
    {
        System.out.println("IOException is caught");
        ex.printStackTrace();
    }

    Integer object1 = null;

    // Deserialization
    try
    {
        // Reading the object from a file
        FileInputStream file = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(file);

        // Method for deserialization of object
        object1 = (Integer) in.readObject();

        in.close();
        file.close();

        System.out.println("Object has been deserialized ");
        System.out.println("a = " + object1);
        //System.out.println("b = " + object1.b);
        //Parent root=object1.pane;
        //primaryStage.setTitle("Color Switch");
        //primaryStage.setScene(new Scene(root, 400, 600));
        //primaryStage.show();
    }

    catch(IOException ex)
    {
        System.out.println("IOException is caught");
    }

    catch(ClassNotFoundException ex)
    {
        System.out.println("ClassNotFoundException is caught");
    }*/
}
