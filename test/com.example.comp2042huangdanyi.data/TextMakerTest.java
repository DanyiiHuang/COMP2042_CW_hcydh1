package com.example.comp2042huangdanyi.data;

import javafx.scene.Group;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextMakerTest {
    @Test
    public void testChangeTwoText() {
        // Setup
        final Text first = new Text(0.0, 0.0, "s");
        final Text second = new Text(0.0, 0.0, "s");

        // Run the test
        TextMaker.changeTwoText(first, second);

        // Verify the results
    }

    @Test
    public void testMadeText() {
        // Setup
        final Group root = new Group(List.of());

        // Run the test
        final Text result = TextMaker.madeText("32", 0.0, 0.0, root);

        // Verify the results
        assertEquals("32",result.getText());
    }

}