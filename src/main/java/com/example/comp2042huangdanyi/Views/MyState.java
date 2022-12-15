package com.example.comp2042huangdanyi.Views;

import javafx.application.Platform;
import javafx.stage.Stage;

/** Class as added monitor to terminate the program
 * @author DanyiHuang
 */
public class MyState extends Stage {
    /** Method to check on Window's status.
     *
     */
    public MyState() {
        // Add a window listener to the stage
        setOnCloseRequest(e -> {
            // Call the Platform.exit() method when the window is closed
            Platform.exit();
        });
    }
}