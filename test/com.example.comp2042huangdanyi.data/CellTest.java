package com.example.comp2042huangdanyi.data;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CellTest {

    private static Game game;
    Group root = new Group();
    Cell cell = new Cell(20,20,162.5, root);

    @BeforeAll
    public static void startUp() {
        Platform.startup(() -> game = new Game());
    }

    @Test
    void testAdder() {
        // Setup
        final Cell cell1 = new Cell(20,20,162.5, root);
        Text text = TextMaker.madeText("8", cell1.getX(), cell1.getY(),root);
        cell1.setTextClass(text);
        cell.setTextClass(TextMaker.madeText("4", cell1.getX(), cell1.getY(),root));

        // Run the test
        cell.adder(cell1);

        // Verify the results
        Assertions.assertEquals(0,cell.getNumber());
        Assertions.assertEquals(12,cell1.getNumber());
    }

    @Test
    public void testChangeCell() {
        // Setup
        final Cell cell1 = new Cell(20,20,162.5, root);
        Text text = TextMaker.madeText("8", cell1.getX(), cell1.getY(),root);
        cell1.setTextClass(text);
        cell.setTextClass(TextMaker.madeText("4", cell1.getX(), cell1.getY(),root));

        // Run the test
        cell.changeCell(cell1);

        // Verify the results
        Assertions.assertEquals(8,cell.getNumber());
        Assertions.assertEquals(4,cell1.getNumber());
    }
}