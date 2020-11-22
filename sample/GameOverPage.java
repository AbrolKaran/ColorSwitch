package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class GameOverPage extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        try
        {
            // set title for the stage
            stage.setTitle("Game Over");
            Label score = new Label("10");
            Label highScore = new Label("20");
            score.setStyle("-fx-font-size: 35px; -fx-font-family: 'Verdana'");
            score.setTranslateX(0);
            score.setTranslateY(-80);
            highScore.setStyle("-fx-font-size: 35px; -fx-font-family: 'Verdana'");
            score.setTextFill(Color.web("WHITE"));
            highScore.setTextFill(Color.web("WHITE"));

            //Creating graphic home
            Image img2 = new Image(new FileInputStream("GameOver\\6.png"));
            ImageView view2 = new ImageView(img2);
            view2.setFitHeight(70);
            view2.setPreserveRatio(true);

            // create home button
            Button button2 = new Button();
            button2.setTranslateX(-50);
            button2.setTranslateY(290);
            button2.setStyle("-fx-background-color: #241E1E");
            //Setting the size of the button
            button2.setPrefSize(70, 70);
            //Setting a graphic to the button
            button2.setGraphic(view2);

            button2.setOnAction(new EventHandler<ActionEvent>()
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

            //Creating a dialog for resurrect
            Dialog<String> dialog = new Dialog<String>();
            //Setting the title
            dialog.setTitle("Resurrect");
            ButtonType type = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
            ButtonType type2 = new ButtonType("NO", ButtonBar.ButtonData.OK_DONE);
            //Setting the content of the dialog
            dialog.setHeaderText("Are you sure you want to use 10 stars to resurrect?");
            //dialog.setWidth(500);
            //Adding buttons to the dialog pane
            dialog.getDialogPane().getButtonTypes().addAll(type2, type);

            //Creating graphic resurrect
            Image img3 = new Image(new FileInputStream("GameOver\\7.png"));
            ImageView view3 = new ImageView(img3);
            view3.setFitHeight(70);
            view3.setPreserveRatio(true);

            // create resurrect button
            Button button3 = new Button();
            button3.setTranslateX(50);
            button3.setTranslateY(200);
            button3.setStyle("-fx-background-color: #241E1E");
            //Setting the size of the button
            button3.setPrefSize(70, 70);
            //Setting a graphic to the button
            button3.setGraphic(view3);

            button3.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    dialog.showAndWait();
                }
            });

            // add the heading and buttons
            VBox hbox = new VBox(button2, button3, score, highScore);

            // set spacing
            hbox.setSpacing(10);

            // set alignment for the HBox
            hbox.setAlignment(Pos.CENTER);

            // create a scene
            Scene scene = new Scene(hbox, 324, 576);

            // create a input stream
            FileInputStream input = new FileInputStream("GameOver\\HighScore.png");

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
