package com.example.comp2042huangdanyi.Controls;

import com.example.comp2042huangdanyi.Views.EndGame;
import com.example.comp2042huangdanyi.Views.View;
import com.example.comp2042huangdanyi.data.Game;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Optional;

/** for all code handle inputs from user.
 * @author DanyiHuang-modified
 */
public class Controller extends Stage{

    Stage primaryStage;
    View view;
    // single design
    private static Controller singleInstance = null;
    public static Boolean isCommon = null;

    /**Call the Platform.exit() method when the window is closed
     *
     */
    private Controller()
    {
        view = View.getSingleInstance();
        setOnCloseRequest(e -> {
            Platform.exit();
        });
    }

    public static Controller getSingleInstance() {
        if (singleInstance == null)
            singleInstance = new Controller();
        return singleInstance;
    }

    /** Method to get controller of color theme when selecting color scheme.
     *
     */
    public void initColor()
    {
        view.cb.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                        if (arg0.getValue().equals(0))
                        {
                            View.setChoice(0);
                            view.getColorschemeChoice().setFill(Color.rgb(168, 149, 135, 1.0));
                        }
                        else if (arg0.getValue().equals(1))
                        {
                            View.setChoice(1);
                            view.getColorschemeChoice().setFill(Color.rgb(255, 158, 12, 1.0));
                        }
                    }
                });
    }

    /** Method to get controller of user's movement in game.
     * @param primaryStage Group primaryStage to display scene content.
     */
    public void gameMove(Stage primaryStage)
    {
        view.getGameScene().addEventHandler(KeyEvent.KEY_PRESSED, key ->{
            Platform.runLater(() -> {

                view.game.move(key);
                view.game.setScore();
                view.game.fillNewNumberOrEnd(primaryStage, view.getEndGameScene(), view.getEndGameRoot());
            });
        });
    }

    /** Method to get controller of startGame view.
     * Choosing color scheme will change the color theme of the game.
     * Choosing 'common' or 'challenge' game mode.
     * If user chooses 'Challenge' mode, then user can choose
     * different time range to challenge the game.
     * Start the game after clicking the 'Start game' button
     * or terminating the application if click 'Exit game' button.
     * @param primaryStage Group primaryStage to display scene content.
     */
    public void startGame(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        view.readyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(View.ab.getSelectionModel().getSelectedItem() == null ){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                    alert.setTitle("Game mode");
                    alert.setHeaderText("Please select game mode or Please select Challenge time");
                    alert.setContentText("Are you sure?");

                    Game.isChallengeEasy = null;// choose common : isEasy = null , TimeNum = null
                    //change challengeText to common mode
                    Game.challengeText.setText("common");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //renew main JavaFX code
                                Game.score = 0;
                            }
                        });
                    }}else {
                    view.game.game(view.getGameScene(), view.getGameRoot(), primaryStage, view.getEndGameScene(), view.getEndGameRoot());
                    if(View.choice == 0) {
                        view.getGameScene().setFill((Color.rgb(168, 149, 135, 1.0)));
                    }else{
                        view.getGameScene().setFill((Color.rgb(255, 158, 12, 1.0)));
                    }
                    view.showScene(primaryStage, view.getGameScene());
                }
            }
        });


        view.exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                EndGame.quit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.exit(0);
                    }
                });
                View.challengeMode.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                        if(newValue.equals("60s") ){
                            //60s challenge mode - difficult
                            Game.TimeNum = 60;
                            Game.isEasy = 0;
                            Game.isChallengeEasy = true;
                        }else if(newValue.equals("180s") ){
                            //180s challenge mode - easy
                            Game.TimeNum = 180;
                            Game.isEasy = 1;
                            Game.isChallengeEasy = false;
                        }
                    }
                });

                View.ab.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if(newValue.equals("challenge")){
                            if(Game.isChallengeEasy != null && Game.isChallengeEasy == true){
                                //state and choose change mode,choose 60s or 180s
                                //level of difficulty
                                Game.isEasy = 1;
                                Game.TimeNum = 180;
                            }else if(Game.isChallengeEasy != null && Game.isChallengeEasy == false){
                                Game.isEasy = 0;
                                Game.TimeNum = 60;
                            }
                            // change challengeText as challenge common mode
                            Game.challengeText.setText("challenge");

                            //new: add challenge button。
                            View.challengeText.setText("(Challenge Mode)Choose a challenge mode");
                            View.challengeText.setVisible(true);
                            View.challengeMode.relocate(440,525);
                            View.challengeMode.setVisible(true);

                        }else if(Game.isChallengeEasy == null || newValue.equals("common")){
                            //new: add challenge button display。
                            View.challengeText.setText("");
                            View.challengeMode.relocate(440,525);
                            View.challengeMode.setVisible(false);
                            Game.isChallengeEasy = null;
                            // statement: common, at this time isEasy = null , TimeNum = null
                            //change challengeText as common mode
                            Game.challengeText.setText("common");
                        }else {
                            Game.isChallengeEasy = null;
                            // statement: choose common, at this time isEasy = null , TimeNum = null
                            //change challengeText as common mode
                            Game.challengeText.setText("common");
                        }
                    }
                });
            }
        });

    }

    /** Method to get controller of gameOver view.
     * @param root Group root to store the component inside gameOver view.
     */
    public void gameOver(Group root)
    {
        EndGame.quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Restart");
                alert.setHeaderText("Restart");
                alert.setContentText("Are you sure?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    root.getChildren().clear();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //update JavaFX main code
                            Game.score = 0;
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    new JFXPanel(); // this will prepare JavaFX toolkit and environment
                                    Platform.runLater(new Runnable() {
                                        /**
                                         *
                                         */
                                        @Override
                                        public void run() {
                                            //----------------------------
                                            //use no parameter method to execute start(new Stage()) to achieve loading
                                            //reopen
                                            try {
                                                //System.out.println(Game.highLeader);
                                                root.getChildren().clear();
                                                Game.isChallengeEasy = null;
                                                Game.isEasy = null;
                                                Main main = new Main();
                                                main.start(new Stage());
                                            }catch(Exception  e) {
                                                e.printStackTrace();
                                            }
                                            //----------------------------
                                        }
                                    });
                                }
                            });

                        }
                    });
                }
            }
        });

    }

    /** Class to display content inside application.
     *
     */
    private class JFXPanel {
    }
}
