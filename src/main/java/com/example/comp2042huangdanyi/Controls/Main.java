package com.example.comp2042huangdanyi.Controls;

import com.example.comp2042huangdanyi.Views.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        View.getSingleInstance().init(primaryStage);
        Controller.getSingleInstance().initColor();
        View.getSingleInstance().showScene(primaryStage, View.getSingleInstance().getColorScene());
        Controller.getSingleInstance().runGame(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
