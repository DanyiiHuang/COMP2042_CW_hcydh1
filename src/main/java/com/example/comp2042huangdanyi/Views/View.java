package com.example.comp2042huangdanyi.Views;

import com.example.comp2042huangdanyi.data.Game;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Scanner;

/** Class for View to display the design of the game.
 * @author DanyiHuang-modified
 */
public class View extends Stage{
    static final int WIDTH = 900;
    static final int HEIGHT = 900;
    public static Text challengeText = new Text();

    // add color scheme
    public static int choice = 0;
    public static Color[] colors = {Color.rgb(224, 226, 226, 0.5),
            Color.rgb(232, 255, 100, 0.5),
            Color.rgb(232, 220, 50, 0.5),
            Color.rgb(232, 200, 44, 0.8),
            Color.rgb(232, 170, 44, 0.8),
            Color.rgb(180, 120, 44, 0.7),
            Color.rgb(180, 100, 44, 0.7),
            Color.rgb(180, 80, 44, 0.7),
            Color.rgb(180, 60, 44, 0.8),
            Color.rgb(180, 30, 44, 0.8),
            Color.rgb(250, 0, 44, 0.8),
            Color.rgb(250,0,0,1)

    };

    static Color[] colors2 = {Color.rgb(24, 226, 226, 0.5),
            Color.rgb(32, 255, 100, 0.5),
            Color.rgb(32, 220, 50, 0.5),
            Color.rgb(32, 200, 44, 0.8),
            Color.rgb(32, 170, 44, 0.8),
            Color.rgb(80, 120, 44, 0.7),
            Color.rgb(80, 100, 44, 0.7),
            Color.rgb(80, 80, 44, 0.7),
            Color.rgb(80, 60, 44, 0.8),
            Color.rgb(80, 30, 44, 0.8),
            Color.rgb(50, 0, 44, 0.8),
            Color.rgb(50,0,0,1)

    };

    private Group gameRoot = new Group();
    private Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
    private static Scanner input= new Scanner(System.in);
    private static View singleInstance = null;
    private static Scene colorschemeChoice;
    private Scene diffRootChoice;
    private Scene endGameScene;
    private Group endgameRoot;
    public Button readyButton;
    public Button exitButton;
    public ChoiceBox cb;
    public  static ChoiceBox  ab = new ChoiceBox(FXCollections.observableArrayList(
            "challenge","common"));
    public  static ChoiceBox challengeMode = new ChoiceBox(FXCollections.observableArrayList(
            "","60s", "180s"));
    public Game game;
    static Color[] tmpColors;

    /** Getter method for diffRootChoice.
     * @return diffRootChoice.
     */
    public Scene getDiffRootChoice() {
        return diffRootChoice;
    }

    /** Getter method for colorschemeChoice.
     * @return colorschemeChoice.
     */
    public Scene getColorschemeChoice(){
        return colorschemeChoice;
    }

    /** Setter method for diffRootChoice.
     * @param diffRootChoice Scene diffRootChoice.
     */
    public void setDiffRootChoice(Scene diffRootChoice) {
        this.diffRootChoice = diffRootChoice;
    }

    /** Setter method for choice.
     * @param c int choice.
     */
    public static void setChoice(int c) { choice = c; }

    /** Method to set color based on Cell's number.
     * @param number int number inside cell.
     * @param rectangle filling color inside cell.
     * @param choice int choice to choose color theme.
     */
    public static void setColorByNumber(int number, Rectangle rectangle, int choice) {
        if (choice == 0) tmpColors = colors;
        else tmpColors = colors2;
        switch (number) {
            case 0 -> rectangle.setFill(tmpColors[0]);
            case 2 -> rectangle.setFill(tmpColors[1]);
            case 4 -> rectangle.setFill(tmpColors[2]);
            case 8 -> rectangle.setFill(tmpColors[3]);
            case 16 -> rectangle.setFill(tmpColors[4]);
            case 32 -> rectangle.setFill(tmpColors[5]);
            case 64 -> rectangle.setFill(tmpColors[6]);
            case 128 -> rectangle.setFill(tmpColors[7]);
            case 256 -> rectangle.setFill(tmpColors[8]);
            case 512 -> rectangle.setFill(tmpColors[9]);
            case 1024 -> rectangle.setFill(tmpColors[10]);
            case 2048 -> rectangle.setFill(tmpColors[11]);
        }
    }

    /** Method to handle status of the application.
     *
     */
    private View(){
        setOnCloseRequest(e -> {
            // Call the Platform.exit() method when the window is closed
            Platform.exit();
        });
    }

    /** Getter method for singleInstance in class View.
     * @return object View.
     */
    public static View getSingleInstance() {
        if (singleInstance == null)
            singleInstance = new View();
        return singleInstance;
    }

    /** Getter method for gameScene.
     * @return Scene gameScene.
     */
    public Scene getGameScene() {
        return gameScene;
    }

    /** Getter method for colorschemeChoice.
     * @return Scene colorschemeChoice.
     */
    public Scene getColorScene() {
        return colorschemeChoice;
    }

    /** Getter method for endGameScene.
     * @return Scene endGameScene.
     */
    public Scene getEndGameScene() {
        return endGameScene;
    }

    /** Getter method for gameRoot.
     * @return Group gameRoot.
     */
    public Group getGameRoot() {
        return gameRoot;
    }

    /** Getter method for endgameRoot.
     * @return Group endgameRoot.
     */
    public Group getEndGameRoot() {
        return endgameRoot;
    }

    /** Setter method for gameRoot.
     * @param gameRoot Group gameRoot.
     *
     */
    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    /** Method to create view of startScene.
     * @param primaryStage Stage primaryStage to allow Scene to display content.
     */
    public void init(Stage primaryStage)
    {
        Group accountRoot = new Group();
        Scene accountScene = new Scene(accountRoot, WIDTH, HEIGHT, Color.rgb(150, 20, 100, 0.2));
        Group getAccountRoot = new Group();
        Scene getAccountScene = new Scene(getAccountRoot, WIDTH, HEIGHT, Color.rgb(200, 20, 100, 0.2));

        endgameRoot = new Group();
        //End Game Scene Background old 250 20 100  new: 108 97 127
        endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, Color.rgb(108, 97, 127, 0.2));
        Group rankRoot = new Group();
        Scene rankScene = new Scene(rankRoot, WIDTH, HEIGHT, Color.rgb(250, 50, 120, 0.3));

        Rectangle backgroundOfMenuForPlay = new Rectangle(240, 140, Color.rgb( 120, 120, 120,0.2));
        backgroundOfMenuForPlay.setX(WIDTH / 2.0 - 120);
        backgroundOfMenuForPlay.setY(180);
        accountRoot.getChildren().add(backgroundOfMenuForPlay);

        gameRoot = new Group();
        setGameRoot(gameRoot);

        //Game Scene Background: old: 189 177 92  new: 252 224 203  newV2: 169 150 136
        gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(169, 150, 136));
        game = new Game();
        // select color scheme
        Group colorRoot= new Group();
        //colorRoot Style  new: 108,97,127
        //set background color
        colorschemeChoice = new Scene(colorRoot, WIDTH, HEIGHT,Color.rgb(168, 149, 135, 1.0));

        Text titleText = new Text("Welcome to 2048 Games");
        titleText.setFill(Color.BLACK);
        titleText.relocate(180,100);
        titleText.setFont(Font.font(50));
        colorRoot.getChildren().add(titleText);

        Text scoreText = new Text("choose a color scheme :            Choose the Game Mode :");
        scoreText.setFill(Color.BLACK);
        scoreText.relocate(200,250);
        scoreText.setFont(Font.font(20));
        colorRoot.getChildren().add(scoreText);
        cb = new ChoiceBox(FXCollections.observableArrayList(
                "First", "Second")
        );
        cb.relocate(250,350);
        colorRoot.getChildren().add(cb);

        ab.relocate(560,350);
        colorRoot.getChildren().add(ab);

        challengeMode.relocate(440,525);
        challengeMode.setVisible(true);
        colorRoot.getChildren().add(challengeMode);

        challengeText.setText("(Challenge Mode)Choose a challenge mode");
        challengeText.setFill(Color.BLACK);
        challengeText.setVisible(true);
        challengeText.relocate(270,470);
        challengeText.setFont(Font.font(20));
        colorRoot.getChildren().add(challengeText);

        exitButton = new Button("Exit Game");
        exitButton.setPrefSize(200,100);
        exitButton.setTextFill(Color.BLACK);
        colorRoot.getChildren().add(exitButton);
        exitButton.relocate(370,730);

        readyButton = new Button("Start Game");
        readyButton.setPrefSize(200,100);
        readyButton.setTextFill(Color.BLACK);
        colorRoot.getChildren().add(readyButton);
        readyButton.relocate(370,600);
    }

    /** Method to set scene, display scene inside primaryStage.
     * @param primaryStage Stage primaryStage.
     * @param ColorScene Scene colorScene.
     */
    public void showScene(Stage primaryStage, Scene ColorScene)
    {
        primaryStage.setScene(ColorScene);
        primaryStage.show();
    }

}