package sample;

import java.io.*;
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

            //Creating graphic play
            Image img = new Image(new FileInputStream("Rings1\\4.png"));
            ImageView view = new ImageView(img);
            view.setFitHeight(43);
            view.setPreserveRatio(true);

            // create play button
            Button button = new Button();
            button.setTranslateX(-200);
            button.setTranslateY(55);
            button.setStyle("-fx-background-color: #241E1E");
            //Setting the size of the button
            button.setPrefSize(43, 43);
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

            //Creating graphic music play
            Image img2 = new Image(new FileInputStream("IntroPage\\4.png"));
            ImageView view2 = new ImageView(img2);
            view2.setFitHeight(70);
            view2.setPreserveRatio(true);

            // create music play button
            Button button2 = new Button();
            button2.setTranslateX(150);
            button2.setTranslateY(200);
            button2.setStyle("-fx-background-color: #241E1E");
            //Setting the size of the button
            button2.setPrefSize(70, 70);
            //Setting a graphic to the button
            button2.setGraphic(view2);

            //Creating graphic high score
            Image img3 = new Image(new FileInputStream("IntroPage\\5.png"));
            ImageView view3 = new ImageView(img3);
            view3.setFitHeight(70);
            view3.setPreserveRatio(true);

            // create high score button
            Button button3 = new Button();
            button3.setTranslateX(220);
            button3.setTranslateY(200);
            button3.setStyle("-fx-background-color: #241E1E");
            //Setting the size of the button
            button3.setPrefSize(70, 70);
            //Setting a graphic to the button
            button3.setGraphic(view3);

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
            HBox hbox = new HBox(ringview, ringview2, button);

            // set spacing
            hbox.setSpacing(10);

            // set alignment for the HBox
            hbox.setAlignment(Pos.CENTER);

            // create a scene
            Scene scene = new Scene(hbox, 324, 576);

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
