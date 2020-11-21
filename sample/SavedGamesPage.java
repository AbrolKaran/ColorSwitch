package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileInputStream;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;


public class SavedGamesPage extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        try
        {
            // set title for the stage
            stage.setTitle("Saved Games");

            //list View for educational qualification
            ObservableList<String> names = FXCollections.observableArrayList("Game1", "Game2", "Game3", "Game4", "Game5", "Game6", "Game7");
            ListView<String> listView = new ListView<String>(names);
            listView.setMaxSize(200, 160);
            listView.setTranslateX(0);
            listView.setTranslateY(-50);
            listView.setStyle("-fx-border-style: solid; -fx-border-color:  #FFDE59; -fx-border-width: 0px; -fx-control-inner-background: #241E1E; -fx-font-size: 15px; -fx-font-family: 'Verdana'");

            //Creating graphic music play
            Image img2 = new Image(new FileInputStream("IntroPage\\4.png"));
            ImageView view2 = new ImageView(img2);
            view2.setFitHeight(70);
            view2.setPreserveRatio(true);

            // create music play button
            Button button2 = new Button();
            button2.setTranslateX(-60);
            button2.setTranslateY(330);
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
            button3.setTranslateX(40);
            button3.setTranslateY(240);
            button3.setStyle("-fx-background-color: #241E1E");
            //Setting the size of the button
            button3.setPrefSize(70, 70);
            //Setting a graphic to the button
            button3.setGraphic(view3);

            // add the heading and buttons
            VBox hbox = new VBox(button2, button3, listView);

            // set spacing
            hbox.setSpacing(10);

            // set alignment for the HBox
            hbox.setAlignment(Pos.CENTER);

            // create a scene
            Scene scene = new Scene(hbox, 324, 576);

            // create a input stream
            FileInputStream input = new FileInputStream("SavedGames.png");

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
