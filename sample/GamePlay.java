package sample;

import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.animation.*;
import javafx.scene.shape.Circle;
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
    private double length;
    private double width;
    private int difficultyLevel;

    @Override
    public void start(Stage stage) throws Exception
    {
        //set stage dimensions
        length = 650;
        width = 365.625;

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
        Image star1 = (new Star(146, 50)).getStar();
        ImageView starView1 = new ImageView(star1);
        starView1.setFitHeight(27);
        starView1.setPreserveRatio(true);
        starView1.setLayoutX(146);
        starView1.setLayoutY(50);

        Image star2 = (new Star(146, 50)).getStar();
        ImageView starView2 = new ImageView(star2);
        starView2.setFitHeight(27);
        starView2.setPreserveRatio(true);
        starView2.setLayoutX(146);
        starView2.setLayoutY(336);

        //create color switchers
        Image cs1 = (new ColorSwitcher(colors, 27, 146, 10)).getCs();
        ImageView csView1 = new ImageView(cs1);
        csView1.setFitHeight(27);
        csView1.setPreserveRatio(true);
        csView1.setLayoutX(146);
        csView1.setLayoutY(10);

        Image cs2 = new Image(new FileInputStream("Constants\\7.png"));
        ImageView csView2 = new ImageView(cs2);
        csView2.setFitHeight(27);
        csView2.setPreserveRatio(true);
        csView2.setLayoutX(146);
        csView2.setLayoutY(225);

        Ball ball = new Ball(162, 20, 7, 2);
        ball.display().relocate(162, 500);

        Obstacle cr = new CircleObstacle(1, colors, 162, 350, 60, 1);
        Obstacle fn = new FanObstacle(1, colors, 122, 140, 50, 1);
        Obstacle sq = new SquareObstacle(1, colors, 112, 110, 90, 1);
        Obstacle dcr = new DoubleCircleObstacle(1, colors, 162, 350, 60, 1);
        Obstacle sqcr = new SquareCircleObstacle(1, colors, 162, 140, 60, 1);
        Obstacle crfn = new CircleFanObstacle(1,colors,300,300,60,1);
        Obstacle tn = new TriangleObstacle(1,colors,230,300,140,1);
        Obstacle qd = new QuadrilateralObstacle(1,colors,300,300,100,1);


        AnimationTimer timer = new AnimationTimer()
        {
            @Override
            public void handle(long l)
            {
                //cr.move();
                //fn.move();
                //sq.move();
                dcr.move();
                sqcr.move();
                crfn.move();
                tn.move();
                qd.move();
            }
        };

        timer.start();
        /*Group root = new Group(((CircleObstacle)ob).getArc4(),((CircleObstacle)ob).getArc1(),((CircleObstacle)ob).getArc2(),((CircleObstacle)ob).getArc3());


        root.getChildren().addAll(((FanObstacle)fn).getR1(), ((FanObstacle)fn).getR2(), ((FanObstacle)fn).getR3(), ((FanObstacle)fn).getR4());*/
        Group root = new Group();
        //cr.display(root);
        //fn.display(root);
        //sq.display(root);
        tn.display(root);
        qd.display(root);
        crfn.display(root);
        dcr.display(root);
        sqcr.display(root);

        root.getChildren().addAll(ball.display(), pause, end);
        root.getChildren().addAll(scoreLabel, starView1, starView2, csView1, csView2);
        //Creating the scroll pane
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(324, 576);
        //Setting content to the scroll pane
        scroll.setContent(root);

        // create a scene
        Scene scene = new Scene(root, width, length, Color.BLACK);

        // set the scene
        stage.setScene(scene);
        stage.show();

        Bounds bounds = root.getBoundsInLocal();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), new KeyValue(ball.display().layoutYProperty(), bounds.getMaxY()-ball.display().getRadius())));
        timeline.setCycleCount(500);
        timeline.play();
    }
}

