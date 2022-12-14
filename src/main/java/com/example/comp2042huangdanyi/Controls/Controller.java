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

import java.util.Optional;


// for all code handle inputs from user

public class Controller {

    Stage primaryStage;

    View view;
    // single design
    private static Controller singleInstance = null;
    private Controller()
    {
        view = View.getSingleInstance();

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
                        // TODO Auto-generated method stub
//			System.out.println("choice   " + "" + arg0.getValue());
//			System.out.println("choice" + "" + arg1.intValue());
//			System.out.println("choice" + "" + arg2.intValue());
                        if (arg0.getValue().equals(0))
                        {
                            View.setChoice(0);
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
                view.game.game(view.getGameScene(), view.getGameRoot(), primaryStage, view.getEndGameScene(), view.getEndGameRoot());
                view.showScene(primaryStage, view.getGameScene());
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


                View.ab.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        //TODO 获取了difficult属性值
                        //System.out.println(newValue);
                        //TODO 根据难易程序对TIME进行设置 3分钟为容易程序，一分钟为困难程度
                        if(newValue.equals("easy")){
                            //容易程度
                            Game.isEasy = 1;
                            Game.TimeNum = 180;
                        }else {
                            //困难程度
                            Game.isEasy = 0;
                            Game.TimeNum = 60;
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

                    //root.getChildren().clear();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //更新JavaFX的主线程的代码放在此处
                            Game.score = 0;
                            //EndGame.scoreText.setText("Score: ");
                            view.game.game(view.getGameScene(), view.getGameRoot(), primaryStage, view.getEndGameScene(), view.getEndGameRoot());
                            view.showScene(primaryStage, view.getGameScene());
                        }
                    });
                }
            }
        });

    }


}
