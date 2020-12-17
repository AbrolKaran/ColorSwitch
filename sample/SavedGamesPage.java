package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.control.cell.ComboBoxListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableList;


public class SavedGamesPage extends Application implements Serializable
{
    private HashMap<String,GameState> listGames;
    private ArrayList<String> names;
    private int HighScore;
    private transient MainMenu menu;

    public SavedGamesPage(MainMenu mm){
        listGames = new HashMap<>();
        names = new ArrayList<>();
        menu = mm;
    }

    public HashMap<String, GameState> getListGames() {
        return listGames;
    }

    public void setListGames(HashMap<String,GameState> listGames) {
        this.listGames = listGames;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public int getHighScore() {
        return HighScore;
    }

    public void setHighScore(int highScore) {
        HighScore = highScore;
    }

    public MainMenu getMenu() {
        return menu;
    }

    public void setMenu(MainMenu menu) {
        this.menu = menu;
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        try
        {
            // set title for the stage
            stage.setTitle("Saved Games");

            //list View for games
            ListView<String> listView = this.displayList();

            //Creating graphic home
            Image img2 = new Image(new FileInputStream("GameOver\\6.png"));
            ImageView view2 = new ImageView(img2);
            view2.setFitHeight(70);
            view2.setPreserveRatio(true);

            // create home button
            Button button2 = new Button();
            button2.setTranslateX(-60);
            button2.setTranslateY(330);
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

            //Creating a dialog for high score
            Dialog<String> dialog2 = new Dialog<String>();
            //Setting the title
            dialog2.setTitle("High Score");
            ButtonType type3 = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            //Setting the content of the dialog
            dialog2.setHeaderText("The High Score is 20");
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
            button3.setTranslateX(40);
            button3.setTranslateY(240);
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

            Button btn = new Button("Load Selected Game");
            btn.setStyle("-fx-border-width: 1px; -fx-border-color: #FFDE59; -fx-background-color: #241E1E; -fx-font-size: 14px; -fx-font-family: 'Verdana'");
            btn.setTextFill(Color.WHITE);
            btn.setLayoutX(0);
            btn.setTranslateY(-50);

            btn.setOnAction(event -> {
                String select = listView.getSelectionModel().getSelectedItem();


                //int idx = (int)(selectedIndices.get(0));

                try
                {

                    GamePlay nGame = new GamePlay(1,menu);
                    nGame.reLoad(listGames.get(select));
                }

                catch(Exception e)
                {
                    System.out.println("Exception in deserialization");
                    e.printStackTrace();
                }
            });

            // add the heading and buttons
            VBox hbox = new VBox(button2, button3, listView, btn);

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

    public ListView<String> displayList()
    {
        /*
        for(GameState gs : listGames)
        {
            names.add(gs.getName());
        }*/

        //ObservableList<String> list = FXCollections.observableArrayList("Game1", "Game2", "Game3", "Game4", "Game5", "Game6", "Game7");
        ObservableList<String> gameNames = FXCollections.observableArrayList(names);

        ListView<String> listView = new ListView<String>(gameNames);
        listView.setMaxSize(200, 160);
        listView.setTranslateX(0);
        listView.setTranslateY(-50);
        listView.setStyle("-fx-border-width: 0px; -fx-control-inner-background: #241E1E; -fx-font-size: 15px; -fx-font-family: 'Verdana'");

        return listView;
    }



}
