package com.example.comp2042huangdanyi.Controls;

import com.example.comp2042huangdanyi.Views.View;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public  void start(Stage primaryStage) throws Exception {

        // Add a window listener to the stage
        primaryStage.setOnCloseRequest(e -> {
            // Call the Platform.exit() method when the window is closed
            Platform.exit();
        });
        primaryStage.setTitle("2048");
        View.getSingleInstance().init(primaryStage);
        Controller.getSingleInstance().initColor();
        // added absolute path
        primaryStage.getIcons().add(new Image( "file:C:\\Users\\Administrator\\Desktop\\2048project\\src\\main\\resources\\app_icon.png"));
        View.getSingleInstance().showScene(primaryStage, View.getSingleInstance().getColorScene());

        Controller.getSingleInstance().runGame(primaryStage);
        primaryStage.setOnCloseRequest(e -> {
            // Call the Platform.exit() method when the window is closed
            System.exit(0);
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
