package com.example.comp2042huangdanyi.Controls;

import com.example.comp2042huangdanyi.Views.View;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


        primaryStage.setTitle("2048");
        View.getSingleInstance().init(primaryStage);
        Controller.getSingleInstance().initColor();
        // 此处添加绝对路径
        primaryStage.getIcons().add(new Image( "file:C:\\Users\\Administrator\\Desktop\\2048project\\src\\main\\resources\\app_icon.png"));
        View.getSingleInstance().showScene(primaryStage, View.getSingleInstance().getColorScene());

        Controller.getSingleInstance().runGame(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
