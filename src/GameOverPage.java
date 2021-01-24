package src;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;

public class GameOverPage extends Application
{
    private AnimationTimer tim;
    private int sc;
    private GamePlay game;
    private MainMenu menu;
    private Stage st;

    public GameOverPage(AnimationTimer t, int s, GamePlay g, MainMenu mm, Stage _st)
    {
        this.tim = t;
        this.sc = s;
        this.game = g;
        this.menu = mm;
        this.st = _st;
    }
    @Override
    public void start(Stage stage) throws Exception
    {
        try
        {
            // set title for the stage
            stage.setTitle("Game Over");
            Label score = new Label(Integer.toString(sc));
            Label highScore = new Label(Integer.toString(menu.getHighScore()));
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
                        System.out.println("High score : " + menu.getSavedGames().getHighScore());
                        menu.serialize();
                        st.close();
                        stage.close();
                        (new MainMenu()).start(new Stage());
                    }

                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            });

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Resurrect");
            alert.setContentText("Are you sure you want to use 5 stars to resurrect?");

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
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK && sc>=5){
                        // ... user chose OK
                        try
                        {
                            menu.serialize();
                        }

                        catch(Exception e)
                        {
                            System.out.println("Game over page error");
                        }

                        game.resurrect(tim);
                        stage.close();
                    }

                    else if (result.get() == ButtonType.OK && sc<5){
                        // user chose OK but not enough stars
                        String thePath = "Sound//error.wav";
                        Media media = new Media(new File(thePath).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        mediaPlayer.setVolume(1);
                        mediaPlayer.play();

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setHeaderText("You cannot resurrect!");
                        alert.setContentText("Sorry, you don't have enough stars.");

                        alert.showAndWait();
                        st.close();
                    }

                    else {
                        // ... user chose CANCEL or closed the dialog
                        System.out.println("else");
                    }

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
