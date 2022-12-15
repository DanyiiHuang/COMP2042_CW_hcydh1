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
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Optional;


// for all code handle inputs from user

public class Controller extends Stage{


    Stage primaryStage;

    View view;
    // single design
    private static Controller singleInstance = null;
    public static Boolean isCommon = null;
    private Controller()
    {
        view = View.getSingleInstance();
        setOnCloseRequest(e -> {
            // Call the Platform.exit() method when the window is closed
            Platform.exit();
        });
    }
    public static Controller getSingleInstance() {
        if (singleInstance == null)
            singleInstance = new Controller();
        return singleInstance;
    }

    public void initColor()
    {
        view.cb.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {

                    @Override
                    public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                        // new:
                        // Auto-generated method stub
//			System.out.println("choice   " + "" + arg0.getValue());
//			System.out.println("choice" + "" + arg1.intValue());
//			System.out.println("choice" + "" + arg2.intValue());
                        if (arg0.getValue().equals(0))
                        {
                            //no change: default
                        }
                        else if (arg0.getValue().equals(1))
                        {
                            View.setChoice(1);
                        }

                    }
                });


    }

    public void gameMove(Stage primaryStage)
    {
        view.getGameScene().addEventHandler(KeyEvent.KEY_PRESSED, key ->{
            Platform.runLater(() -> {

                view.game.move(key);
//                GameScene.this.maxCellNumbersToScore();
                // score update is wrong , not the sum,
                // but the sum of merge value

                view.game.setScore();
                view.game.fillNewNumberOrEnd(primaryStage, view.getEndGameScene(), view.getEndGameRoot());
            });
        });
    }

    public void runGame(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        view.readyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Ready Dialog");
//                alert.setHeaderText("Start from this page");
//                alert.setContentText("Are you sure?");
//
//                Optional<ButtonType> result = alert.showAndWait();
//                if (result.get() == ButtonType.OK){
//                	colorRoot.getChildren().clear();
//                }
//                else
//                {
//                	System.exit(0);
//                }

//				 game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);

                if(View.ab.getSelectionModel().getSelectedItem() == null ){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);


                    alert.setTitle("Game mode");
                    alert.setHeaderText("Please select game mode or Please selecat Challenge time");
                    alert.setContentText("Are you sure?");


                    Game.isChallengeEasy = null;// choose common : isEasy = null , TimeNum = null
                    //change challengeText to common mode
                    Game.challengeText.setText("common");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){

                        //root.getChildren().clear();

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //renew main JavaFX code
                                Game.score = 0;
                                //EndGame.scoreText.setText("Score: ");
                                /**
                                 * view.game.game(view.getGameScene(), view.getGameRoot(), primaryStage, view.getEndGameScene(), view.getEndGameRoot());
                                 *                             view.showScene(primaryStage, view.getGameScene());
                                 */
                                //Main.main(new String[]{"",""});


                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        new JFXPanel(); // this will prepare JavaFX toolkit and environment
                                        Platform.runLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                //----------------------------
                                                //use no parameter method to execute start(new Stage()) to achieve loading
                                                //reopen
                                                try {
                                                    //Game.isChallengeEasy = null;
                                                    //Game.isEasy = null;
                                                    //Game.TimeNum = null;

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
                    }}else {


                    view.game.game(view.getGameScene(), view.getGameRoot(), primaryStage, view.getEndGameScene(), view.getEndGameRoot());
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

                            //60s 困难模式
                            Game.TimeNum = 60;
                            Game.isEasy = 0;
                            Game.isChallengeEasy = true;
                        }else if(newValue.equals("180s") ){

                            //180s,容易模式
                            Game.TimeNum = 180;
                            Game.isEasy = 1;
                            Game.isChallengeEasy = false;
                        }
                    }
                });

                View.ab.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {


                        //gain level of difficult
                        //System.out.println(newValue);
                        //set challenge mode 3min as easy，1min as difficult,common mode doesn't have countdown
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
                            //更新JavaFX的主线程的代码放在此处
                            Game.score = 0;
                            //EndGame.scoreText.setText("Score: ");
                            /**
                             * view.game.game(view.getGameScene(), view.getGameRoot(), primaryStage, view.getEndGameScene(), view.getEndGameRoot());
                             *                             view.showScene(primaryStage, view.getGameScene());
                             */
                            //Main.main(new String[]{"",""});


                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    new JFXPanel(); // this will prepare JavaFX toolkit and environment
                                    Platform.runLater(new Runnable() {
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
                                                //Game.TimeNum = null;
                                                Main main = new Main();
                                                main.start(new Stage());
                                                //Main.main(new String[]{""});

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


    private class JFXPanel {
    }
}
