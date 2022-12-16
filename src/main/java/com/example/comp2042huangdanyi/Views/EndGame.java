package com.example.comp2042huangdanyi.Views;

import com.example.comp2042huangdanyi.data.Game;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/** Class for EndGame to display view in EndGame.
 * @author DanyiHuang-modified
 */
public class EndGame {
    private static EndGame  singleInstance = null;
    public static Button quitButton;
    public static Button quit = new Button("Exit Game");
    public static Text scoreText = new Text();;

    /** Constructor for class EndGame.
     *
     */
    private EndGame(){

    }

    /** Method to create object for EndGame.
     * @return object EndGame.
     */
    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

    /** Method to display the content of endGame.
     * @param endGameScene Scene display the endGame view.
     * @param root Group root to store components
     * @param primaryStage Stage primaryStage of the application.
     * @param score Scoring of game.
     */
    public void endGameShow(Scene endGameScene, Group root, Stage primaryStage,long score){
        Text text = new Text("GAME OVER");
        text.relocate(230,250);
        text.setFont(Font.font(80));
        root.getChildren().add(text);
        //new:  old score  new Game.score
        int score1 = Game.score;
        scoreText.setText("Score:"+score);
        scoreText.setFill(Color.BLACK);
        scoreText.relocate(270,350);
        scoreText.setFont(Font.font(80));
        try {
            root.getChildren().add(scoreText);
        }catch (Exception e){

        }
        score = 0;
        //
        Text keepText = new Text(" Please keep trying!");
        keepText.setFill(Color.BLACK);
        keepText.relocate(300,467);
        keepText.setFont(Font.font(30));
        root.getChildren().add(keepText);
        //
        quitButton = new Button("Restart");
        quitButton.setPrefSize(200,50);
        quitButton.setTextFill(Color.BLACK);
        root.getChildren().add(quitButton);
        quitButton.relocate(345,500);

        quit.setPrefSize(200,50);
        quit.setTextFill(Color.BLACK);
        try {
            root.getChildren().add(quit);
        }catch (Exception e){

        }
        quit.relocate(345,600);
    }
}
