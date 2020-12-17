package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class MainMenu extends Application
{
    private GamePlay currentGame;
    private SavedGamesPage savedGames;
    private int HighScore;
    private ArrayList<GameState> savedStates = new ArrayList<>();
    private MainMenu menu;
    private int vol;

    public MainMenu(){
        currentGame = new GamePlay(1,this);
        savedGames = new SavedGamesPage(this);
        savedStates = new ArrayList<>();
        menu = this;
        HighScore = 0;
        this.vol = 1;
        try
        {
            this.deserialize();
            HighScore = savedGames.getHighScore();
        }

        catch(Exception e)
        {
            System.out.println("Exception in deserialization");
            savedGames = new SavedGamesPage(this);
        }

    }

    public GamePlay getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(GamePlay currentGame) {
        this.currentGame = currentGame;
    }

    public SavedGamesPage getSavedGames() {
        return savedGames;
    }

    public void setSavedGames(SavedGamesPage savedGames) {
        this.savedGames = savedGames;
    }

    public int getHighScore() {
        return HighScore;
    }

    public void setHighScore(int highScore) {
        HighScore = highScore;
    }

    public ArrayList<GameState> getSavedStates() {
        return savedStates;
    }

    public void setSavedStates(ArrayList<GameState> savedStates) {
        this.savedStates = savedStates;
    }

    public MainMenu getMenu() {
        return menu;
    }

    public void setMenu(MainMenu menu) {
        this.menu = menu;
    }

    public int getVol() {
        return vol;
    }

    public void serialize() throws IOException {
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new FileOutputStream("out.txt"));
            out.writeObject(savedGames);
            System.out.println("Hello");
            System.out.println(savedGames);
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
            ex.printStackTrace();
        }

        finally {
            out.close();
        }
    }


    public void deserialize() throws IOException, ClassNotFoundException{

        ObjectInputStream in = null;
        try{
            in = new ObjectInputStream(new FileInputStream("out.txt"));
            savedGames = (SavedGamesPage) in.readObject();
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
        finally {
            in.close();
        }
    }


    @Override
    public void start(Stage stage) throws Exception
    {
        try
        {
            // set title for the stage
            stage.setTitle("Main Menu");
            //updateSavedGames();

            // create a label
            Label label = new Label("Name : ");

            // create a text field
            TextField textfield = new TextField();

            // set preferred column count
            textfield.setPrefColumnCount(10);

            //Creating graphic new game
            Image img = new Image(new FileInputStream("MainMenu\\3.png"));
            ImageView view = new ImageView(img);
            view.setFitHeight(60);
            view.setPreserveRatio(true);

            // create new game button
            Button button = new Button();
            button.setTranslateX(325);
            button.setTranslateY(-55);
            button.setStyle("-fx-background-color: #241E1E");
            //Setting the size of the button
            button.setPrefSize(60, 60);
            //Setting a graphic to the button
            button.setGraphic(view);

            //set action on new game button
            button.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    try
                    {
                        String thePath = "Sound//button.wav";
                        Media media = new Media(new File(thePath).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        mediaPlayer.setVolume(1);
                        mediaPlayer.play();

                        stage.close();
                        currentGame.start(new Stage());
                    }

                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            });

            //Creating graphic saved game
            Image img4 = new Image(new FileInputStream("MainMenu\\4.png"));
            ImageView view4 = new ImageView(img4);
            view4.setFitHeight(55);
            view4.setPreserveRatio(true);

            // create saved game button
            Button button4 = new Button();
            button4.setTranslateX(-105);
            button4.setTranslateY(25);
            button4.setStyle("-fx-background-color: #241E1E");
            //Setting the size of the button
            button4.setPrefSize(55, 55);
            //Setting a graphic to the button
            button4.setGraphic(view4);

            //set action on button4
            button4.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    try
                    {
                        String thePath = "Sound//button.wav";
                        Media media = new Media(new File(thePath).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        mediaPlayer.setVolume(1);
                        mediaPlayer.play();

                        stage.close();
                        System.out.println("savedGames");
                        savedGames.start(new Stage());
                    }

                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            });

            //Creating graphic exit game
            Image img5 = new Image(new FileInputStream("MainMenu\\5.png"));
            ImageView view5 = new ImageView(img5);
            view5.setFitHeight(55);
            view5.setPreserveRatio(true);

            // create exit game button
            Button button5 = new Button();
            button5.setTranslateX(-330);
            button5.setTranslateY(100);
            button5.setStyle("-fx-background-color: #241E1E");
            //Setting the size of the button
            button5.setPrefSize(55, 55);
            //Setting a graphic to the button
            button5.setGraphic(view5);

            button5.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    String thePath = "Sound//button.wav";
                    Media media = new Media(new File(thePath).toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.setVolume(1);
                    mediaPlayer.play();

                    try
                    {
                        stage.close();
                    }

                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            });

            //Creating a dialog
            Dialog<ButtonType> dialog = new Dialog<ButtonType>();
            //Setting the title
            dialog.setTitle("Music Settings");
            ButtonType type = new ButtonType("ON", ButtonData.OK_DONE);
            ButtonType type2 = new ButtonType("OFF", ButtonData.NO);
            //Setting the content of the dialog
            dialog.setHeaderText("Turn Music On/Off");
            //dialog.setWidth(500);
            //Adding buttons to the dialog pane
            dialog.getDialogPane().getButtonTypes().addAll(type2, type);

            //Creating graphic music play
            Image img2 = new Image(new FileInputStream("IntroPage\\4.png"));
            ImageView view2 = new ImageView(img2);
            view2.setFitHeight(72);
            view2.setPreserveRatio(true);

            // create music play button
            Button button2 = new Button();
            button2.setTranslateX(110);
            button2.setTranslateY(200);
            button2.setStyle("-fx-background-color: #241E1E");
            //Setting the size of the button
            button2.setPrefSize(72, 72);
            //Setting a graphic to the button
            button2.setGraphic(view2);

            button2.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {

                    Optional<ButtonType> result = dialog.showAndWait();

                    if (result.get() == type){
                        // ... user chose OK
                        vol = 1;
                    }

                    else {
                        // ... user chose CANCEL or closed the dialog
                        System.out.println("else");
                        vol = 0;
                    }
                }
            });

            //Creating a dialog
            Dialog<String> dialog2 = new Dialog<String>();
            //Setting the title
            dialog2.setTitle("High Score");
            ButtonType type3 = new ButtonType("OK", ButtonData.OK_DONE);
            //Setting the content of the dialog
            dialog2.setHeaderText("The High Score is " + getHighScore());
            dialog2.setHeight(35);
            //Adding buttons to the dialog pane
            dialog2.getDialogPane().getButtonTypes().addAll(type3);

            //Creating graphic high score
            Image img3 = new Image(new FileInputStream("IntroPage\\5.png"));
            ImageView view3 = new ImageView(img3);
            view3.setFitHeight(70);
            view3.setPreserveRatio(true);

            // create high score button
            Button button3 = new Button();
            button3.setTranslateX(110);
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
                    dialog2.showAndWait();
                }
            });

            // add the heading and buttons
            HBox hbox = new HBox(button, button2, button3, button4, button5);

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

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    /*public static void addState(GameState gm)
    {
        savedStates.add(gm);
    }

    public void updateSavedGames()
    {
        savedGames.addList(savedStates);
    }*/

}
