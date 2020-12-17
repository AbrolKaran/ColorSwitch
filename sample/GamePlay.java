package sample;

import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.animation.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.scene.paint.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import java.io.FileInputStream;

public class GamePlay extends Application
{
    private int id;
    private int score;
    private Label scoreLabel;
    private Ball ball;
    private ArrayList<String> colors;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<ColorSwitcher> colorSwitchers;
    private ArrayList<Star> stars;
    private float length = 650;
    private float width = 365;
    private int difficultyLevel;
    private int flag = 0;
    private int obCount;
    private ArrayList<SmallBall> smallBalls;
    private MainMenu menu;
    private int var;
    private GamePlay gameplay;
    private int noDetect;
    private int cnt1;
    private int cnt2;
    private Random rand = new Random();


    public GamePlay(int ID, MainMenu mm)
    {
        this.id = ID;
        this.score = 0;
        this.obstacles = new ArrayList<>();
        this.colorSwitchers = new ArrayList<>();
        this.stars = new ArrayList<>();
        this.smallBalls = new ArrayList<>();
        this.difficultyLevel = 1;
        this.obCount = 2;
        this.var = 0;
        this.menu = mm;
        this.gameplay = this;
        this.noDetect = 0;
        this.cnt1 = 0;
        this.cnt2 = 0;

        this.colors = new ArrayList<>();
        colors.add("#38B6FF");
        colors.add("#CB6CE6");
        colors.add("#FFDE59");
        colors.add("#FF5757");
    }

    public int getCnt1() {
        return cnt1;
    }

    public int getCnt2() {
        return cnt2;
    }

    public ArrayList<Star> getStars() {
        return stars;
    }

    public ArrayList<ColorSwitcher> getColorSwitchers() {
        return colorSwitchers;
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        float posX = width/2;

        Group root = new Group();

        // create quit button
        Button end = new Button("QUIT GAME");
        end.setLayoutX(width - 100);
        end.setLayoutY(length - 60);
        end.setStyle("-fx-background-color: #000000; -fx-font-size: 13");
        end.setTextFill(Color.WHITE);
        //Setting the size of the button
        end.setPrefSize(100, 40);


        //create stars
        Star s1 = new Star(posX-16, 75);
        ImageView starView1 = s1.getStar();

        Star s2 = new Star(posX-16, 403);
        ImageView starView2 = s2.getStar();

        stars.add(s1);
        stars.add(s2);


        //create color switchers
        ColorSwitcher CS2 = new ColorSwitcher(colors, 27, posX-16, 270);
        ImageView csView2 = CS2.getCs();

        colorSwitchers.add(CS2);

        int c = rand.nextInt(4);
        this.ball = new Ball(posX,600, -6.5f, 7, colors.get(c));

        float posY = 160;
        float posY2 = 420;

        obstacles.add(new CircleObstacle(1, colors, posX, posY2, 75, 1));
        obstacles.add(new FanObstacle(1, colors, posX+30, posY, 65, 1));

        EventHandler<KeyEvent> eventEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                flag = 1;
                ball.move(flag);
                flag = 0;
            }
        };

        for(Obstacle ob : obstacles)
        {
            ob.display(root);
        }

        root.getChildren().addAll(ball.display(), end);
        root.getChildren().addAll(csView2, starView1, starView2);

        //Creating the scroll pane
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(324, 576);

        //Setting content to the scroll pane
        scroll.setContent(root);

        // create a scene
        Scene scene = new Scene(root, width, length, Color.BLACK);



        AnimationTimer timer = new AnimationTimer()
        {
            @Override
            public void handle(long l)
            {
                if(var == 1){
                float vel = ball.getVelocity();
                int ch;
                if(ball.getY()<=length/2 && vel<0) {
                    ch=1;
                    ball.updateVel(flag);
                }
                else{
                    ch=0;
                    ball.move(flag);
                }
                flag = 0;

                System.out.println("ball Y : " + ball.getY());

                for(Obstacle ob : obstacles)
                {
                    ob.move(vel, ch);
                    if(ob.intersect(ball))
                    {
                        System.out.println("intersect");

                        if(noDetect == 0)
                        {
                            gameOver(root);
                            this.stop();

                            try
                            {
                                (new GameOverPage(this, score, gameplay, menu, stage)).start(new Stage());
                            }

                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                if(ball.getY() > 1000)
                {
                    gameOver(root);
                    this.stop();

                    try
                    {
                        (new GameOverPage(this, score, gameplay, menu, stage)).start(new Stage());
                        throw new BallNotFoundException("Ball has fallen");
                    }

                    catch (Exception e)
                    {
                        e.getMessage();
                    }
                }

                for(Obstacle ob : obstacles)
                {
                    if(ob.offscreen(ball))
                    {
                        obstacles.remove(ob);
                        System.out.println("removed " + obstacles.size());
                        break;
                    }
                }

                try
                {
                    placeObstacle(root);
                }

                catch (ObstacleNotPlacedException e)
                {
                    e.getMessage();
                }

                for(Star s : stars)
                {
                    s.move(vel, ch);
                    if(s.intersect(ball))
                    {
                        collectStar(s);
                    }
                }

                for(Star s : stars)
                {
                    if(!s.getStar().isVisible())
                    {
                        stars.remove(s);
                        break;
                    }
                }

                for(ColorSwitcher cs : colorSwitchers)
                {
                    cs.move(vel, ch);
                    if(cs.intersect(ball))
                    {
                        useColorSwitcher(cs);
                        if(noDetect == 1)
                        {
                            noDetect = 0;
                        }
                    }
                }

                for(ColorSwitcher cs : colorSwitchers)
                {
                    if(!cs.getCs().isVisible())
                    {
                        colorSwitchers.remove(cs);
                        break;
                    }
                }
            }}
        };

        timer.start();

        displayOptions(root, timer, stage);
        end.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                try
                {
                    stage.close();
                    (new GameOverPage(timer, score, gameplay, menu, stage)).start(new Stage());
                }

                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        });

        scene.setOnKeyPressed(e -> {
            if(e.getCode().equals(KeyCode.S));
            {
                if(var == 0)
                {
                    var =1;
                }

                flag=1;

                String thePath = "Sound//jump.wav";
                Media media = new Media(new File(thePath).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(menu.getVol());
                mediaPlayer.play();
            }
            if(e.getCode().equals(KeyCode.P)){
                try
                {
                    timer.stop();
                    var = 0;
                    GameState gm1 = new GameState(id, score, ball.getX(), ball.getY(), ball.getColor(), stars.get(0).getX(), stars.get(0).getY(), obstacles.get(0).getPosX(), obstacles.get(0).getY(), colorSwitchers.get(0).getX(), colorSwitchers.get(0).getY(), difficultyLevel, flag, obCount-2);
                    (new PausePage(timer, gm1, stage, menu)).start(new Stage());
                }

                catch (Exception e1)
                {
                    System.out.println(e1.getMessage());
                }

            }

        });
        // set the scene
        stage.setScene(scene);
        stage.show();
    }

    public void collectStar(Star s)
    {
        String thePath = "Sound//star.wav";
        Media media = new Media(new File(thePath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(menu.getVol());
        mediaPlayer.play();

        s.getStar().setVisible(false);
        score += s.getPoints();
        s.setPoints(0);
        this.scoreLabel.setText(Integer.toString(score));
    }

    public void useColorSwitcher(ColorSwitcher cs)
    {
        String thePath = "Sound//colorswitch.wav";
        Media media = new Media(new File(thePath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(menu.getVol());
        mediaPlayer.play();

        if(cs.getState() == 0)
        {
            cs.setState(1);
            cs.getCs().setVisible(false);
            cs.switchColor(ball);
        }
    }

    public void displayOptions(Group root, AnimationTimer timer, Stage st) throws Exception
    {
        //set score
        this.scoreLabel = new Label(Integer.toString(score));
        scoreLabel.setStyle("-fx-font-size: 35");
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setLayoutX(20);
        scoreLabel.setLayoutY(0);

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
                    timer.stop();
                    var = 0;
                    GameState gm = new GameState(id, score, ball.getX(), ball.getY(), ball.getColor(), stars.get(0).getX(), stars.get(0).getY(), obstacles.get(0).getPosX(), obstacles.get(0).getY(), colorSwitchers.get(0).getX(), colorSwitchers.get(0).getY(), difficultyLevel, flag, obCount-2);
                    (new PausePage(timer, gm, st, menu)).start(new Stage());
                }

                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        });

        root.getChildren().addAll(scoreLabel, pause);
    }

    public float getPrevY() {
        return obstacles.get(obstacles.size()-1).getY();
    }

    public int getObCount() {
        return obCount;
    }

    public void setObCount() {

        if(this.obCount < 7)
        {
            this.obCount += 1;
        }

        else
        {
            this.obCount = 0;
        }

    }

    public void placeStar(float _Y, Group root)
    {
        Star star = new Star((width/2) - 16, _Y);
        if(this.cnt1 > 0)
        {
            this.cnt1--;
        }

        else
        {
            root.getChildren().add(star.getStar());
            this.stars.add(star);
        }
    }
    
    public void placeCS(float Y1, float Y2, Group root, ArrayList<String> myColors)
    {
        float _Y = (Y1 + Y2) / 2;
        ColorSwitcher cs = new ColorSwitcher(myColors, 27, (width/2)-16, _Y);

        if(this.cnt2 > 0)
        {
            this.cnt2--;
        }

        else {

            root.getChildren().add(cs.getCs());
            this.colorSwitchers.add(cs);
        }
    }

    public void placeObstacle(Group root) throws ObstacleNotPlacedException
    {
        float posX = width/2;

        if(obstacles.size() < 10){

        switch(getObCount())
        {
            case 0 :
                Obstacle newOb = new CircleObstacle(getDifficultyLevel(), colors, posX, getPrevY()-310, 75, 1);
                placeCS(getPrevY(), getPrevY()-310, root, newOb.getMyColors());
                obstacles.add(newOb);

                System.out.println("Prev Y : " + getPrevY());
                newOb.display(root);
                setObCount();
                placeStar(newOb.getPosY() - 15, root);
                break;

            case 1 :
                Obstacle newOb1 = new FanObstacle(getDifficultyLevel(), colors, posX+30, getPrevY()-310, 65, 1);
                placeCS(getPrevY(), getPrevY()-310, root, newOb1.getMyColors());
                obstacles.add(newOb1);
                newOb1.display(root);

                System.out.println("Prev Y : " + getPrevY());

                setObCount();
                placeStar(newOb1.getPosY() - 90, root);
                break;

            case 2 :
                Obstacle newOb2 = new SquareObstacle(getDifficultyLevel(), colors, posX-50, getPrevY()-360, 115, 1);
                placeCS(getPrevY(), getPrevY()-360, root, newOb2.getMyColors());
                obstacles.add(newOb2);
                newOb2.display(root);
                //System.out.println("Prev Y : " + getPrevY());
                setObCount();
                placeStar(newOb2.getPosY() + 50, root);
                break;

            case 3 :
                Obstacle newOb3 = new TriangleObstacle(getDifficultyLevel(),colors,posX-70,getPrevY()-230,155,1);
                placeCS(getPrevY(), getPrevY()-230, root, newOb3.getMyColors());
                obstacles.add(newOb3);
                newOb3.display(root);
                System.out.println("Prev Y : " + getPrevY());

                setObCount();
                placeStar(newOb3.getPosY() - 50, root);
                break;

            case 4 :
                Obstacle newOb4 = new QuadrilateralObstacle(getDifficultyLevel(),colors,posX,getPrevY()-380,130,1);
                placeCS(getPrevY(), getPrevY()-380, root, newOb4.getMyColors());
                obstacles.add(newOb4);
                System.out.println("Prev Y : " + getPrevY());

                newOb4.display(root);
                setObCount();
                placeStar(newOb4.getPosY() - 20, root);
                break;

            case 5 :
                Obstacle newOb5 = new DoubleCircleObstacle(getDifficultyLevel(), colors, posX, getPrevY()-350, 75, 1);
                placeCS(getPrevY(), getPrevY()-350, root, newOb5.getMyColors());
                obstacles.add(newOb5);
                newOb5.display(root);

                System.out.println("Prev Y : " + getPrevY());
                setObCount();
                placeStar(newOb5.getPosY() - 15, root);
                break;

            case 6 :
                Obstacle newOb6 = new SquareCircleObstacle(getDifficultyLevel(), colors, posX, getPrevY()-350, 70, 1);
                placeCS(getPrevY(), getPrevY()-350, root, newOb6.getMyColors());

                obstacles.add(newOb6);
                newOb6.display(root);

                System.out.println("Prev Y : " + getPrevY());
                setObCount();
                placeStar(newOb6.getPosY() - 15, root);
                break;

            case 7 :
                Obstacle newOb7 = new CircleFanObstacle(getDifficultyLevel(),colors,posX,getPrevY()-350,80,1);
                placeCS(getPrevY(), getPrevY()-350, root, newOb7.getMyColors());
                obstacles.add(newOb7);

                System.out.println("Prev Y : " + getPrevY());

                newOb7.display(root);
                setObCount();
                placeStar(newOb7.getPosY() - 70, root);
                this.setDifficultyLevel(1);
                break;
        }}

        else
        {
            throw new ObstacleNotPlacedException("Obstacle array full");
        }
    }

    public void setDifficultyLevel(int Level)
    {
        this.difficultyLevel += Level;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void gameOver(Group root)
    {
        if(this.score > menu.getHighScore())
        {
            menu.getSavedGames().setHighScore(score);
            menu.setHighScore(score);
            String thePath = "Sound//victory.wav";
            Media media = new Media(new File(thePath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(menu.getVol());
            mediaPlayer.play();

            /*try
            {
                menu.serialize();
            }

            catch(Exception e)
            {
                System.out.println("Gameplay serialization error");
            }*/

        }

        ball.display().setVisible(false);

        String thePath = "Sound//dead.wav";
        Media media = new Media(new File(thePath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(menu.getVol());
        mediaPlayer.play();

        for(int i=0; i<30; i++)
        {
            smallBalls.add(new SmallBall(this.colors, ball.getX(), ball.getY(), 4));
        }

        for(int j = 0; j < smallBalls.size(); j++)
        {
            smallBalls.get(j).display(root);
        }

        // Create animation for moving small balls
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(20), e -> moveBalls()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation
    }

    public void resurrect(AnimationTimer timer)
    {
        for(SmallBall s : smallBalls)
        {
            s.getSmallBall().setVisible(false);
        }
        smallBalls = new ArrayList<>();
        ball.display().setVisible(true);
        score -= 5;
        scoreLabel.setText(Integer.toString(score));
        //ball.display().relocate(ball.getX(), ball.getY()+0.2);
        var = 0;
        noDetect = 1;
        timer.start();
    }

    public final void moveBalls() {
        smallBalls.forEach(SmallBall::moveBall);
    }

    public void reLoad(GameState gm) throws Exception
    {
        this.score = gm.getScore();
        this.difficultyLevel = gm.getDifficultyLevel();
        this.obCount = gm.getObCount();
        this.cnt1 = gm.getCount1();
        this.cnt2 = gm.getCount2();

        Stage stage = new Stage();

        float posX = width/2;

        Group root = new Group();


        // create quit button
        Button end = new Button("QUIT GAME");
        end.setLayoutX(width - 100);
        end.setLayoutY(length - 60);
        end.setStyle("-fx-background-color: #000000; -fx-font-size: 13");
        end.setTextFill(Color.WHITE);
        //Setting the size of the button
        end.setPrefSize(100, 40);


        //create stars
        Star s1 = new Star(gm.getsX(), gm.getsY());
        ImageView starView1 = s1.getStar();
        stars.add(s1);

        /*Star s2 = new Star(posX-16, 403);
        ImageView starView2 = s2.getStar();

        stars.add(s2);*/


        //create color switchers
        ColorSwitcher CS2 = new ColorSwitcher(colors, 27, gm.getCsX(), gm.getCsY());
        ImageView csView2 = CS2.getCs();

        colorSwitchers.add(CS2);

        this.ball = new Ball(posX,gm.getbY(), -6.5f, 7, gm.getbC());

        switch(getObCount())
        {
            case 0 :
                Obstacle newOb = new CircleObstacle(getDifficultyLevel(), colors, gm.getObX(), gm.getObY(), 75, 1);
                obstacles.add(newOb);
                break;

            case 1 :
                Obstacle newOb1 = new FanObstacle(getDifficultyLevel(), colors, gm.getObX(), gm.getObY(), 65, 1);
                obstacles.add(newOb1);
                break;

            case 2 :
                Obstacle newOb2 = new SquareObstacle(getDifficultyLevel(), colors, gm.getObX(), gm.getObY(), 115, 1);
                obstacles.add(newOb2);
                break;

            case 3 :
                Obstacle newOb3 = new TriangleObstacle(getDifficultyLevel(),colors,gm.getObX(),gm.getObY(),155,1);
                obstacles.add(newOb3);
                break;

            case 4 :
                Obstacle newOb4 = new QuadrilateralObstacle(getDifficultyLevel(),colors,gm.getObX(),gm.getObY(),130,1);
                obstacles.add(newOb4);
                break;

            case 5 :
                Obstacle newOb5 = new DoubleCircleObstacle(getDifficultyLevel(), colors, gm.getObX(), gm.getObY(), 75, 1);
                obstacles.add(newOb5);
                break;

            case 6 :
                Obstacle newOb6 = new SquareCircleObstacle(getDifficultyLevel(), colors, gm.getObX(), gm.getObY(), 70, 1);
                obstacles.add(newOb6);
                break;

            case 7 :
                Obstacle newOb7 = new CircleFanObstacle(getDifficultyLevel(),colors,gm.getObX(),gm.getObY(),80,1);
                obstacles.add(newOb7);
                break;
        }

        obCount++;


        EventHandler<KeyEvent> eventEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                flag = 1;
                ball.move(flag);
                flag = 0;
            }
        };

        for(Obstacle ob : obstacles)
        {
            ob.display(root);
        }

        root.getChildren().addAll(ball.display(), end);
        //root.getChildren().addAll(csView2, starView1);

        //Creating the scroll pane
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(324, 576);

        //Setting content to the scroll pane
        scroll.setContent(root);

        // create a scene
        Scene scene = new Scene(root, width, length, Color.BLACK);



        AnimationTimer timer = new AnimationTimer()
        {
            @Override
            public void handle(long l)
            {
                if(var == 1){
                    float vel = ball.getVelocity();
                    int ch;
                    if(ball.getY()<=length/2 && vel<0) {
                        ch=1;
                        ball.updateVel(flag);
                    }
                    else{
                        ch=0;
                        ball.move(flag);
                    }
                    flag = 0;

                    System.out.println("ball Y : " + ball.getY());

                    for(Obstacle ob : obstacles)
                    {
                        ob.move(vel, ch);
                        if(ob.intersect(ball))
                        {
                            System.out.println("intersect");

                            if(noDetect == 0)
                            {
                                gameOver(root);
                                this.stop();

                                try
                                {
                                    (new GameOverPage(this, score, gameplay, menu, stage)).start(new Stage());
                                }

                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                    if(ball.getY() > 1000)
                    {
                        gameOver(root);
                        this.stop();

                        try
                        {
                            (new GameOverPage(this, score, gameplay, menu, stage)).start(new Stage());
                            throw new BallNotFoundException("Ball has fallen");
                        }

                        catch (Exception e)
                        {
                            e.getMessage();
                        }
                    }

                    for(Obstacle ob : obstacles)
                    {
                        if(ob.offscreen(ball))
                        {
                            obstacles.remove(ob);
                            System.out.println("removed " + obstacles.size());
                            break;
                        }
                    }

                    try
                    {
                        placeObstacle(root);
                    }

                    catch (ObstacleNotPlacedException e)
                    {
                        e.getMessage();
                    }

                    for(Star s : stars)
                    {
                        s.move(vel, ch);
                        if(s.intersect(ball))
                        {
                            collectStar(s);
                        }
                    }

                    for(Star s : stars)
                    {
                        if(!s.getStar().isVisible())
                        {
                            stars.remove(s);
                            break;
                        }
                    }

                    for(ColorSwitcher cs : colorSwitchers)
                    {
                        cs.move(vel, ch);
                        if(cs.intersect(ball))
                        {
                            useColorSwitcher(cs);
                            if(noDetect == 1)
                            {
                                noDetect = 0;
                            }
                        }
                    }

                    for(ColorSwitcher cs : colorSwitchers)
                    {
                        if(!cs.getCs().isVisible())
                        {
                            colorSwitchers.remove(cs);
                            break;
                        }
                    }
                }}
        };

        timer.start();

        displayOptions(root, timer, stage);
        end.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                try
                {
                    stage.close();
                    (new GameOverPage(timer, score, gameplay, menu, stage)).start(new Stage());
                }

                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        });
        scene.setOnKeyPressed(e -> {
            if(e.getCode().equals(KeyCode.S));
            {
                if(var == 0)
                {
                    var =1;
                }

                flag=1;

                String thePath = "Sound//jump.wav";
                Media media = new Media(new File(thePath).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(menu.getVol());
                mediaPlayer.play();
            }
            if(e.getCode().equals(KeyCode.P)){
                try
                {
                    timer.stop();
                    var = 0;
                    GameState gm1 = new GameState(id, score, ball.getX(), ball.getY(), ball.getColor(), stars.get(0).getX(), stars.get(0).getY(), obstacles.get(0).getPosX(), obstacles.get(0).getY(), colorSwitchers.get(0).getX(), colorSwitchers.get(0).getY(), difficultyLevel, flag, obCount-2);
                    (new PausePage(timer, gm1, stage, menu)).start(new Stage());
                }

                catch (Exception e1)
                {
                    System.out.println(e1.getMessage());
                }

            }

        });
        // set the scene
        stage.setScene(scene);
        stage.show();
    }
}