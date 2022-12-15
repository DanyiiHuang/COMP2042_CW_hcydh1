package com.example.comp2042huangdanyi.Views;

import com.example.comp2042huangdanyi.data.Game;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Scanner;

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


    private Scene colorschemeChoice;


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

    public Scene getDiffRootChoice() {
        return diffRootChoice;
    }

    public void setDiffRootChoice(Scene diffRootChoice) {
        this.diffRootChoice = diffRootChoice;
    }


    public static void setChoice(int c) { choice = c; }

    public static void setColorByNumber(int number, Rectangle rectangle, int choice) {
        if (choice == 0) tmpColors = colors;
        else tmpColors = colors2;
        switch (number) {
            case 0:
                rectangle.setFill(tmpColors[0]);
                break;
            case 2:
                rectangle.setFill(tmpColors[1]);
                break;
            case 4:
                rectangle.setFill(tmpColors[2]);
                break;
            case 8:
                rectangle.setFill(tmpColors[3]);
                break;
            case 16:
                rectangle.setFill(tmpColors[4]);
                break;
            case 32:
                rectangle.setFill(tmpColors[5]);
                break;
            case 64:
                rectangle.setFill(tmpColors[6]);
                break;
            case 128:
                rectangle.setFill(tmpColors[7]);
                break;
            case 256:
                rectangle.setFill(tmpColors[8]);
                break;
            case 512:
                rectangle.setFill(tmpColors[9]);
                break;
            case 1024:
                rectangle.setFill(tmpColors[10]);
                break;
            case 2048:
                rectangle.setFill(tmpColors[11]);
        }

    }



    private View(){
        setOnCloseRequest(e -> {
            // Call the Platform.exit() method when the window is closed
            Platform.exit();
        });
    }

    public static View getSingleInstance() {
        if (singleInstance == null)
            singleInstance = new View();
        return singleInstance;
    }

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }
    public Scene getGameScene() {
        return gameScene;
    }

    public Scene getColorScene() {
        return colorschemeChoice;
    }


    public Scene getEndGameScene() {
        return endGameScene;
    }

    public Group getGameRoot() {
        return gameRoot;
    }

    public Group getEndGameRoot() {
        return endgameRoot;
    }


    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    public void init(Stage primaryStage)
    {

        // we have group of:
        // menuRoot, accountRoot,

        Group menuRoot = new Group();
        // old : null new : 108 97 127
        Scene menuScene = new Scene(menuRoot, WIDTH, HEIGHT,Color.rgb(108, 97, 127, 0.2));
        Group accountRoot = new Group();
        Scene accountScene = new Scene(accountRoot, WIDTH, HEIGHT, Color.rgb(150, 20, 100, 0.2));
        Group getAccountRoot = new Group();
        Scene getAccountScene = new Scene(getAccountRoot, WIDTH, HEIGHT, Color.rgb(200, 20, 100, 0.2));

        endgameRoot = new Group();
        //End Game Scene Background old 250 20 100  new: 108 97 127
        endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, Color.rgb(108, 97, 127, 0.2));
        Group rankRoot = new Group();
        Scene rankScene = new Scene(rankRoot, WIDTH, HEIGHT, Color.rgb(250, 50, 120, 0.3));
        //background
        BackgroundFill background_fill = new BackgroundFill(Color.rgb(120, 100, 100), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);

        //old: 120, 120, 120  new: 108,97,127
        Rectangle backgroundOfMenu = new Rectangle(240, 120, Color.rgb(120, 120, 120, 0.2));
        backgroundOfMenu.setX(WIDTH / 2 - 120);
        backgroundOfMenu.setY(180);
        menuRoot.getChildren().add(backgroundOfMenu);

        Rectangle backgroundOfMenuForPlay = new Rectangle(240, 140, Color.rgb( 120, 120, 120,0.2));
        backgroundOfMenuForPlay.setX(WIDTH / 2 - 120);
        backgroundOfMenuForPlay.setY(180);
        accountRoot.getChildren().add(backgroundOfMenuForPlay);

        gameRoot = new Group();
        setGameRoot(gameRoot);

        // duplicated variable
        //Game Scene Background: old: 189 177 92  new: 252 224 203  newV2: 169 150 136
        gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(169, 150, 136));

//        setGameScene(gameScene);
//        primaryStage.setScene(gameScene);
        game = new Game();
        // select color scheme

        Group  colorRoot= new Group();
        //colorRoot Style  new: 108,97,127
        //colorRoot.setStyle("-fx-background-color: BEIGE;");
        //set background color
        colorschemeChoice = new Scene(colorRoot, WIDTH, HEIGHT,Color.rgb(120, 120, 120, 0.2));

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

        //Text challengeText = new Text("(Challenge Mode)Choose a challenge mode");
        challengeText.setText("(Challenge Mode)Choose a challenge mode");
        challengeText.setFill(Color.BLACK);
        challengeText.setVisible(true);
        challengeText.relocate(270,470);
        challengeText.setFont(Font.font(20));
        colorRoot.getChildren().add(challengeText);

//        cb.getSelectionModel().selectedIndexProperty()
//        .addListener(new ChangeListener<Number>() {
////          public void changed(ObservableValue ov, Number value, Number new_value) {
//////            label.setText(greetings[new_value.intValue()]);
////          }
//
//		@Override
//		public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
//			// Auto-generated method stub
//			System.out.println("choice" + arg0 + "");
//		}
//        });
        //exitButton
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
//        readyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//
////                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
////                alert.setTitle("Ready Dialog");
////                alert.setHeaderText("Start from this page");
////                alert.setContentText("Are you sure?");
////
////                Optional<ButtonType> result = alert.showAndWait();
////                if (result.get() == ButtonType.OK){
////                	colorRoot.getChildren().clear();
////                }
////                else
////                {
////                	System.exit(0);
////                }
//
//				 game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);
//				 primaryStage.setScene(gameScene);
//				 primaryStage.show();
//            }
//        });

//        primaryStage.setScene(colorschemeChoice);
//         primaryStage.show();

    }
    public void showScene(Stage primaryStage, Scene ColorScene)
    {
        primaryStage.setScene(ColorScene);
        primaryStage.show();
    }


}