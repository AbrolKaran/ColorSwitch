package sample;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.animation.RotateTransition;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.paint.*;

public class Main extends Application
{
    MainMenu menu = new MainMenu();

    public static void main(String[] args)
    {
        launch(args);
        //Application.launch(PausePage.class, args);
        //Application.launch(GameOverPage.class, args);
        //Application.launch(GamePlay.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        try
        {
            // set title for the stage
            stage.setTitle("Color Switch");

            ArrayList<String> colors = new ArrayList<>();
            colors.add("#38B6FF");
            colors.add("#CB6CE6");
            colors.add("#FFDE59");
            colors.add("#FF5757");

            HBox hbox = new HBox();

            Group root = new Group();

            //Create outer ring
            Image outerRing = new Image(new FileInputStream("Rings1\\2.png"));
            ImageView ringview = new ImageView(outerRing);
            ringview.setFitHeight(270);
            ringview.setPreserveRatio(true);
            ringview.setTranslateX(105);
            ringview.setTranslateY(55);

            //Create inner ring
            Image innerRing = new Image(new FileInputStream("Rings1\\3.png"));
            ImageView ringview2 = new ImageView(innerRing);
            ringview2.setFitHeight(125);
            ringview2.setPreserveRatio(true);
            ringview2.setTranslateX(-100);
            ringview2.setTranslateY(55);

            Obstacle cr = new CircleObstacle(1, colors, 50, 230, 80, -1);
            Obstacle cr2 = new CircleObstacle(1, colors, 50, 230, 55, 1);
            Obstacle cr3 = new CircleObstacle(1, colors, 50, 230, 110, 1);

            CircleObstacle crt = (CircleObstacle) cr;
            CircleObstacle crt2 = (CircleObstacle) cr3;
            crt.getArc1().setStrokeWidth(18.0f);
            crt.getArc2().setStrokeWidth(18.0f);
            crt.getArc3().setStrokeWidth(18.0f);
            crt.getArc4().setStrokeWidth(18.0f);

            crt2.getArc1().setStrokeWidth(22.0f);
            crt2.getArc2().setStrokeWidth(22.0f);
            crt2.getArc3().setStrokeWidth(22.0f);
            crt2.getArc4().setStrokeWidth(22.0f);

            //root.setLayoutX(100);
            //root.setLayoutY(100);

            AnimationTimer timer = new AnimationTimer()
            {
                @Override
                public void handle(long l)
                {
                    cr.move(0,0);
                    cr2.move(0,0);
                    cr3.move(0,0);
                }
            };

            timer.start();
            cr.display(root);
            cr2.display(root);
            cr3.display(root);

            //Creating graphic play
            Image img = new Image(new FileInputStream("Rings1\\4.png"));
            ImageView view = new ImageView(img);
            view.setFitHeight(47);
            view.setPreserveRatio(true);

            // create play button
            Button button = new Button();
            button.setLayoutX(20);
            button.setLayoutY(205);
            button.setStyle("-fx-background-color: #241E1E");
            //Setting the size of the button
            button.setPrefSize(47, 47);
            //Setting a graphic to the button
            button.setGraphic(view);

            button.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    try
                    {
                        stage.close();
                        menu.start(new Stage());
                    }

                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            });

            //Instantiating RotateTransition class
            RotateTransition rotate = new RotateTransition();

            //Setting Axis of rotation
            rotate.setAxis(Rotate.Z_AXIS);

            // setting the angle of rotation
            rotate.setByAngle(360);

            //setting cycle count of the rotation
            rotate.setCycleCount(500);

            //Setting duration of the transition
            rotate.setDuration(Duration.millis(1000));

            //the transition will be auto reversed by setting this to true
            rotate.setAutoReverse(true);

            //setting Rectangle as the node onto which the transition will be applied
            rotate.setNode(ringview);

            //playing the transition
            rotate.play();

            //Instantiating RotateTransition class
            RotateTransition rotate2 = new RotateTransition();
            rotate2.setAxis(Rotate.Z_AXIS);
            rotate2.setByAngle(-360);
            rotate2.setCycleCount(500);
            rotate2.setDuration(Duration.millis(1000));
            rotate2.setAutoReverse(true);
            rotate2.setNode(ringview2);
            rotate2.play();

            // add the heading and buttons


            // set spacing
            hbox.setSpacing(10);

            // set alignment for the HBox
            hbox.setAlignment(Pos.CENTER);

            // create a scene
            Scene scene = new Scene(hbox, 324, 576);
            root.getChildren().add(button);
            hbox.getChildren().addAll(root);

            // create a input stream
            FileInputStream input = new FileInputStream("IntroPage\\2.png");

            // create a image
            Image image = new Image(input);

            // create a background image
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);

            // create Background
            Background background = new Background(backgroundimage);

            // set background
            hbox.setBackground(background);

            // set the scene
            stage.setScene(scene);
            stage.show();
        }

        catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
