package sample;
import java.io.*;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.transform.Rotate;
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
import javafx.util.Duration;

public class PausePage extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        try
        {
            // set title for the stage
            stage.setTitle("Pause Page");

            //Create outer ring
            Image outerRing = new Image(new FileInputStream("Rings2\\5.png"));
            ImageView ringview = new ImageView(outerRing);
            ringview.setFitHeight(190);
            ringview.setPreserveRatio(true);
            ringview.setTranslateX(275);
            ringview.setTranslateY(25);

            //Create inner ring
            Image innerRing = new Image(new FileInputStream("Rings2\\5.png"));
            ImageView ringview2 = new ImageView(innerRing);
            ringview2.setFitHeight(80);
            ringview2.setPreserveRatio(true);
            ringview2.setTranslateX(135);
            ringview2.setTranslateY(25);

            //Instantiating RotateTransition class
            RotateTransition rotate = new RotateTransition();
            rotate.setAxis(Rotate.Z_AXIS);
            rotate.setByAngle(360);
            rotate.setCycleCount(500);
            rotate.setDuration(Duration.millis(1000));
            rotate.setAutoReverse(true);
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

            //Creating graphic replay
            Image img = new Image(new FileInputStream("Rings2\\6.png"));
            ImageView view = new ImageView(img);
            view.setFitHeight(25);
            view.setPreserveRatio(true);

            // create replay button
            Button button = new Button();
            button.setTranslateX(65);
            button.setTranslateY(25);
            button.setStyle("-fx-background-color: #241E1E");
            //Setting the size of the button
            button.setPrefSize(25, 25);
            //Setting a graphic to the button
            button.setGraphic(view);

            //Creating graphic save and exit
            Image img2 = new Image(new FileInputStream("Pause\\ColorSwitchCopy (1).png"));
            ImageView view2 = new ImageView(img2);
            view2.setFitHeight(50);
            view2.setPreserveRatio(true);

            // create save and exit button
            Button button2 = new Button();
            button2.setTranslateX(-75);
            button2.setTranslateY(170);
            button2.setStyle("-fx-background-color: #241E1E");
            //Setting the size of the button
            button2.setPrefSize(50, 50);
            //Setting a graphic to the button
            button2.setGraphic(view2);

            //dialog box to enter game name
            TextInputDialog dialog = new TextInputDialog("Game 1");
            dialog.setTitle("Save and Exit Game");
            dialog.setHeaderText("Enter the name of your game : ");

            button2.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    dialog.showAndWait();
                }
            });

            //Creating graphic quit
            Image img3 = new Image(new FileInputStream("Pause\\ColorSwitchCopy (2).png"));
            ImageView view3 = new ImageView(img3);
            view3.setFitHeight(50);
            view3.setPreserveRatio(true);

            // create quit button
            Button button3 = new Button();
            button3.setTranslateX(-275);
            button3.setTranslateY(225);
            button3.setStyle("-fx-background-color: #241E1E");
            //Setting the size of the button
            button3.setPrefSize(50, 50);
            //Setting a graphic to the button
            button3.setGraphic(view3);

            button3.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    try
                    {
                        stage.close();
                        (new MainMenu()).start(new Stage());
                    }

                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            });

            // add the heading and buttons
            HBox hbox = new HBox(ringview, ringview2, button, button2, button3);

            // set spacing
            hbox.setSpacing(10);

            // set alignment for the HBox
            hbox.setAlignment(Pos.CENTER);

            // create a scene
            Scene scene = new Scene(hbox, 324, 576);

            // create a input stream
            FileInputStream input = new FileInputStream("Pause\\PauseBG.png");

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
