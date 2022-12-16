package com.example.comp2042huangdanyi.data;

import javafx.application.Platform;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private static Game game;
    private static int n = Game.getN();
    private static Cell[][] cells = Game.getCells();
    private static Group root = new Group();

    @BeforeAll
    public static void startUp() {
        Platform.startup(() -> game = new Game());
        double LENGTH = Game.getLENGTH();
        int distanceBetweenCells = Game.getDistanceBetweenCells();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }
        }
    }

    @Test
    void testCanNotMove() {
        assertFalse(game.canNotMove());
    }

    @Test
    void testHaveEmptyCell() {
        assertEquals(1, game.haveEmptyCell());
    }

    @Test
    void testHaveSameNumberNearly() {
        assertTrue(game.haveSameNumberNearly(0, 0));
    }

    @Test
    void testIsValidDesH() {
        assertFalse(game.isValidDesH(0, 0, 0, 0));
    }

    @Test
    void testIsValidDesV() {
        assertFalse(game.isValidDesV(0, 0, 0, 0));
    }

    @Test
    void testMoveDown() {
        // Setup
        // Run the test
        game.moveDown();

        // Verify the results
    }

    @Test
    void testMoveHorizontally() {
        // Setup
        // Run the test
        game.moveHorizontally(0, 0, 0, 0);

        // Verify the results
    }

    @Test
    void testMoveLeft() {
        // Setup
        // Run the test
        game.moveLeft();

        // Verify the results
    }

    @Test
    void testMoveRight() {
        // Setup
        // Run the test
        game.moveRight();

        // Verify the results
    }

    @Test
    void testMoveUp() {
        // Setup
        // Run the test
        game.moveUp();

        // Verify the results
    }

    @Test
    void testMoveVertically() {
        // Setup
        // Run the test
        game.moveVertically(0, 0, 0, 0);

        // Verify the results
    }

    @Test
    void testPassDestination() {
        assertEquals(0, game.passDestination(0, 0, 'l'));
    }

    @Test
    void testRandomFillNumber() {
        // Setup
        // Run the test
        game.randomFillNumber(0);
        // Verify the results
    }
}