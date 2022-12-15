package com.example.comp2042huangdanyi.Views;

import javafx.application.Platform;
import javafx.stage.Stage;

public class MyState extends Stage {
    public MyState() {
        // Add a window listener to the stage
        setOnCloseRequest(e -> {
            // Call the Platform.exit() method when the window is closed
            Platform.exit();
        });
    }
}