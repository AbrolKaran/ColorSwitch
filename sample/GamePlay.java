package sample;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import javafx.application.Application;
import javafx.animation.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import javafx.scene.transform.Rotate;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.*;
import javafx.scene.web.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.paint.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileInputStream;

public class GamePlay extends Application
{
    private int id;
    private int score = 0;
    private ArrayList<String> colors;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Star> stars;
    private ArrayList<ColorSwitcher> colorSwitchers;
    private float length = 650;
    private float width = 365;
    private int difficultyLevel;
    int flag = 0;

    @Override
    public void start(Stage stage) throws Exception
    {
        float posX = width/2;

        colors = new ArrayList<>();
        colors.add("#38B6FF");
        colors.add("#CB6CE6");
        colors.add("#FFDE59");
        colors.add("#FF5757");

        //Creating graphic pause
        Image img = new Image(new FileInputStream("Pause\\Pause2.png"));
        ImageView view = new ImageView(img);
        view.setFitHeight(35);
        view.setPreserveRatio(true);

        // create pause button
        Button pause = new Button();
        pause.setLayoutX(width - 65);
        pause.setLayoutY(10);
        pause.setStyle("-fx-background-color: #000000");
        //Setting the size of the button
        pause.setPrefSize(35, 35);
        //Setting a graphic to the button
        pause.setGraphic(view);

        pause.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                try
                {
                    (new PausePage()).start(new Stage());
                }

                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        });

        // create quit button
        Button end = new Button("QUIT GAME");
        end.setLayoutX(width - 100);
        end.setLayoutY(length - 60);
        end.setStyle("-fx-background-color: #000000; -fx-font-size: 13");
        end.setTextFill(Color.WHITE);
        //Setting the size of the button
        end.setPrefSize(100, 40);

        end.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                try
                {
                    stage.close();
                    (new GameOverPage()).start(new Stage());
                }

                catch (Exception e)
                {
                     System.out.println(e.getMessage());
                }
            }
        });

        //set score
        Label scoreLabel = new Label(Integer.toString(score));
        scoreLabel.setStyle("-fx-font-size: 35");
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setLayoutX(20);
        scoreLabel.setLayoutY(0);

        //create stars
        Star s1 = new Star(posX-16, 150);
        ImageView starView1 = s1.getStar();

        Star s2 = new Star(posX-16, 403);
        ImageView starView2 = s2.getStar();


        //create color switchers
        ColorSwitcher CS1 = new ColorSwitcher(colors, 27, posX-16, 10);
        ImageView csView1 = CS1.getCs();

        ColorSwitcher CS2 = new ColorSwitcher(colors, 27, posX-16, 270);
        ImageView csView2 = CS2.getCs();


        Ball ball = new Ball(posX,530, -6.5f, 7, 2);


        float posY = 160;
        float posY2 = 420;

        Obstacle cr = new CircleObstacle(1, colors, posX, posY2, 60, 1);
        Obstacle fn = new FanObstacle(1, colors, posX+30, posY, 50, 1);
        Obstacle sq = new SquareObstacle(1, colors, posX-50, posY-50, 90, 1);
        Obstacle dcr = new DoubleCircleObstacle(1, colors, posX, posY2, 60, 1);
        Obstacle sqcr = new SquareCircleObstacle(1, colors, posX, posY, 60, 1);
        Obstacle crfn = new CircleFanObstacle(1,colors,posX,posY2,60,1);
        Obstacle tn = new TriangleObstacle(1,colors,posX-70,posY2+30,140,1);
        Obstacle qd = new QuadrilateralObstacle(1,colors,posX,posY,100,1);

        EventHandler<KeyEvent> eventEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                flag = 1;
                ball.move(flag);
                flag = 0;
            }
        };



        /*Group root = new Group(((CircleObstacle)ob).getArc4(),((CircleObstacle)ob).getArc1(),((CircleObstacle)ob).getArc2(),((CircleObstacle)ob).getArc3());
        root.getChildren().addAll(((FanObstacle)fn).getR1(), ((FanObstacle)fn).getR2(), ((FanObstacle)fn).getR3(), ((FanObstacle)fn).getR4());*/
        Group root = new Group();
        int ob1 = 0;
        int ob2 = 0;

        cr.display(root); ob2 = 0;
        fn.display(root); ob1 = 1;
        //sq.display(root); ob1 = 0;
        //dcr.display(root); ob2 = 0;
        //sqcr.display(root); ob1 = 0;
        //crfn.display(root); ob2 = 1;
        //tn.display(root); ob2 = 0;
        //qd.display(root); ob1 = 0;

        //this.placeStar(ob1, ob2, root);

        root.getChildren().addAll(ball.display(), pause, end);
        root.getChildren().addAll(scoreLabel, csView1, csView2,starView1,starView2);
        //Creating the scroll pane
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(324, 576);
        //Setting content to the scroll pane
        scroll.setContent(root);

        // create a scene
        Scene scene = new Scene(root, width, length, Color.BLACK);
        scene.setOnKeyPressed(e -> {
            if(e.getCode().equals(KeyCode.SHIFT));
            {
                flag=1;
            }
        });
        AnimationTimer timer = new AnimationTimer()
        {
            @Override
            public void handle(long l)
            {
                float vel = ball.getVelocity();
                int ch;
                if(ball.getY()<=length/2 && vel<0) {
                    ch=1;
                    ball.updateVel(flag);
                }
                else{
                    ch=0;
                    ball.move(flag);
                }
                flag = 0;
                cr.move(vel,ch);
                fn.move(vel,ch);
                sq.move(vel,ch);
                dcr.move(vel,ch);
                sqcr.move(vel,ch);
                crfn.move(vel,ch);
                tn.move(vel,ch);
                qd.move(vel,ch);
                s1.move(vel,ch);
                s2.move(vel,ch);
                CS1.move(vel,ch);
                CS2.move(vel,ch);


            }
        };

        timer.start();
        // set the scene
        stage.setScene(scene);
        stage.show();

    }

    /*public void placeStar(int ob1, int ob2, Group root)
    {
        float posX = width/2;
        //create stars
        Image star1 = (new Star(146, 50)).getStar();
        ImageView starView1 = new ImageView(star1);
        starView1.setFitHeight(27);
        starView1.setPreserveRatio(true);
        starView1.setLayoutX(posX-16);

        if(ob1 == 0)
        {
            starView1.setLayoutY(150);
        }

        else if(ob1 == 1)
        {
            starView1.setLayoutY(80);
        }

        Image star2 = (new Star(146, 50)).getStar();
        ImageView starView2 = new ImageView(star2);
        starView2.setFitHeight(27);
        starView2.setPreserveRatio(true);
        starView2.setLayoutX(posX-16);

        if(ob2 == 0)
        {
            starView2.setLayoutY(403);
        }

        else if(ob2 == 1)
        {
            starView2.setLayoutY(349);
        }

        root.getChildren().addAll(starView1, starView2);

    }*/
}


