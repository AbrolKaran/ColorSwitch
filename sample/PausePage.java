package sample;
import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.*;
import javafx.geometry.*;
import javafx.scene.Group;

public class PausePage extends Application
{
    private AnimationTimer timer2;
    private GameState gameState;
    private Stage game;
    private MainMenu menu;

    public PausePage(AnimationTimer timer, GameState gm, Stage g, MainMenu mm)
    {
        this.timer2 = timer;
        this.gameState = gm;
        this.game = g;
        this.menu = mm;
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        try
        {
            ArrayList<String> colors = new ArrayList<>();
            colors.add("#38B6FF");
            colors.add("#CB6CE6");
            colors.add("#FFDE59");
            colors.add("#FF5757");

            // set title for the stage
            stage.setTitle("Pause Page");

            //HBox hbox = new HBox();
            StackPane pane = new StackPane();
            pane.setPadding(new Insets(20));

            Group root = new Group();
            Group root2 = new Group();
            Group root3 = new Group();
            //Group root4 = new Group();

            Obstacle cr = new CircleObstacle(1, colors, 50, 850, 60, -1);
            Obstacle cr2 = new CircleObstacle(1, colors, 50, 850, 40, 1);
            Obstacle cr3 = new CircleObstacle(1, colors, 50, 850, 85, 1);

            CircleObstacle crt = (CircleObstacle) cr;
            CircleObstacle crt2 = (CircleObstacle) cr3;
            CircleObstacle crt3 = (CircleObstacle) cr2;
            crt.getArc1().setStrokeWidth(15.0f);
            crt.getArc2().setStrokeWidth(15.0f);
            crt.getArc3().setStrokeWidth(15.0f);
            crt.getArc4().setStrokeWidth(15.0f);

            crt2.getArc1().setStrokeWidth(20.0f);
            crt2.getArc2().setStrokeWidth(20.0f);
            crt2.getArc3().setStrokeWidth(20.0f);
            crt2.getArc4().setStrokeWidth(20.0f);

            AnimationTimer timer = new AnimationTimer()
            {
                @Override
                public void handle(long l)
                {
                    cr.move(0, 0);
                    cr2.move(0, 0);
                    cr3.move(0, 0);
                }
            };

            timer.start();
            cr.display(root);
            cr2.display(root);
            cr3.display(root);

            //Creating graphic replay
            Image img = new Image(new FileInputStream("Rings2\\6.png"));
            ImageView view = new ImageView(img);
            view.setFitHeight(30);
            view.setPreserveRatio(true);

            // create replay button
            Button button = new Button();
            button.setLayoutY(1270); //850
            button.setLayoutX(25);
            button.setStyle("-fx-background-color: #241E1E");
            //Setting the size of the button
            button.setPrefSize(30, 30);
            //Setting a graphic to the button
            button.setGraphic(view);

            button.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    String thePath = "Sound//button.wav";
                    Media media = new Media(new File(thePath).toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.setVolume(menu.getVol());
                    mediaPlayer.play();

                    timer2.start();
                    stage.close();
                }
            });

            //Creating graphic save and exit
            Image img2 = new Image(new FileInputStream("Pause\\ColorSwitchCopy (1).png"));
            ImageView view2 = new ImageView(img2);
            view2.setFitHeight(50);
            view2.setPreserveRatio(true);

            // create save and exit button
            Button button2 = new Button();
            button2.setLayoutX(-50);
            button2.setLayoutY(280);       //1220
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
                    String thePath = "Sound//button.wav";
                    Media media = new Media(new File(thePath).toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.setVolume(menu.getVol());
                    mediaPlayer.play();

                    Optional<String> name = dialog.showAndWait();

                    name.ifPresent(input -> {

                        for(Star st : menu.getCurrentGame().getStars())
                        {
                            if(st.getCollected() == 1)
                            {
                                gameState.setCount1(gameState.getCount1()+1);
                            }
                        }

                        for(ColorSwitcher cs : menu.getCurrentGame().getColorSwitchers())
                        {
                            if(cs.getCollected() == 1)
                            {
                                gameState.setCount2(gameState.getCount2()+1);
                            }
                        }
                        gameState.setName(name.get());
                        menu.getSavedGames().getNames().add(name.get());
                        menu.getSavedGames().getListGames().put(name.get(),gameState);

                        try
                        {
                            menu.serialize();
                            stage.close();
                            game.close();
                            MainMenu mm = new MainMenu();
                            //mm.addState(gameState);
                            mm.start(new Stage());
                        }

                        catch(Exception e)
                        {
                            System.out.println("Exception is caught in Serialize");
                        }

                    });

                }
            });

            //Creating graphic quit
            Image img3 = new Image(new FileInputStream("Pause\\ColorSwitchCopy (2).png"));
            ImageView view3 = new ImageView(img3);
            view3.setFitHeight(50);
            view3.setPreserveRatio(true);

            // create quit button
            Button button3 = new Button();
            button3.setLayoutX(-50);
            button3.setLayoutY(340);
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
                    String thePath = "Sound//button.wav";
                    Media media = new Media(new File(thePath).toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.setVolume(menu.getVol());
                    mediaPlayer.play();

                    try
                    {
                        menu.serialize();
                        stage.close();
                        game.close();
                        (new MainMenu()).start(new Stage());
                    }

                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            });

            root3.setTranslateY(200);
            root2.getChildren().addAll(button);
            root3.getChildren().addAll(button2, button3);

            //root4.getChildren().add(button3);
            pane.getChildren().addAll(root, root2, root3);
            //pane.getChildren().add(root2);
            //pane.getChildren().add(root3);
            //pane.getChildren().add(root4);

            // create a scene
            Scene scene = new Scene(pane, 324, 576);

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
            pane.setBackground(background);

            // set the scene
            stage.setScene(scene);
            stage.show();
        }

        catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
