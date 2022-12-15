package com.example.comp2042huangdanyi.data;

import com.example.comp2042huangdanyi.Controls.Controller;
import com.example.comp2042huangdanyi.Views.EndGame;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;
// games scene should have:
// length per cell
// how many cells alone one axis
// total length
// single instance DESIGN MODEL


// 1)in game func:
// score update change: adder
// 2) if no move , no number will be generated,
// modified is in use

// 3) set modified " the merged cell"


// ====================
// duplciated GameScene

public class Game  {

    public Game (){
        // Add a window listener to the stage

    }
    private static int HEIGHT = 700;
    private static int n = 4;
    private final static int distanceBetweenCells = 10;
    private static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    private TextMaker textMaker = TextMaker.getSingleInstance();
    private Cell[][] cells = new Cell[n][n];
    private Group root;
    public static int score = 0;  // score should be int
    private boolean moveFlag = false;
    private Text scoreText;
    private Text scoreTime;
    private Text leaderText;
    public static Text challengeText = new Text();
    public static Boolean isChallengeEasy = null;

    public static Integer highLeader = 0;
    public static volatile Integer TimeNum = null;
    // set 1 as easy, 0 as difficult
    public static Integer isEasy = null;


    public Text getText() { return scoreText; }
    public int getScore() { return score; }

    static void setN(int number) {
        n = number;
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    }

    static double getLENGTH() {
        return LENGTH;
    }

    private void randomFillNumber(int turn) {
        Cell[][] emptyCells = new Cell[n][n];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0) {
                    emptyCells[a][b] = cells[i][j];
                    if (b < n-1) {
                        bForBound=b;
                        b++;

                    } else {
                        aForBound=a;
                        a++;
                        b = 0;
                        if(a==n)
                            break outer;
                    }
                }
            }
        }


        Text text;
        Random random = new Random();
        boolean putTwo = true;
        if (random.nextInt() % 2 == 0)
            putTwo = false;
        int xCell, yCell;
        xCell = random.nextInt(aForBound+1);
        yCell = random.nextInt(bForBound+1);
        if (putTwo) {
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
        } else {
            text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
        }
    }

    // 0 means win
    // 1 means has Empty cell
    private int  haveEmptyCell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0)
                    return 1;
                if(cells[i][j].getNumber() == 2048)
                    return 0;
            }
        }
        return -1;
    }

    // return
    private int passDestination(int i, int j, char direct) {
        int coordinate = j;
        if (direct == 'l') {
            // search the "not 0 cell"
            for (int k = j - 1; k >= 0; k--) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        coordinate = j;
        if (direct == 'r') {
            for (int k = j + 1; k <= n - 1; k++) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k - 1;
                    break;
                } else if (k == n - 1) {
                    coordinate = n - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        if (direct == 'd') {
            for (int k = i + 1; k <= n - 1; k++) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k - 1;
                    break;

                } else if (k == n - 1) {
                    coordinate = n - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        if (direct == 'u') {
            for (int k = i - 1; k >= 0; k--) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        return -1;
    }

    private void moveLeft() {
        for (int i = 0; i < n; i++) {
            // is the right direction to loop
            for (int j = 1; j < n; j++) {
                // i is row number, j is column number,
                if (cells[i][j].getNumber() != 0)
                {
                    int des = passDestination(i, j, 'l');
                    moveHorizontally(i, j, des, -1);
                }
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }

    // not necessary:  each one has a dest
    private void moveRight() {
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (cells[i][j].getNumber() != 0)
                {
                    int des = passDestination(i, j, 'r');
                    moveHorizontally(i, j, des, 1);
                }
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }

    private void moveUp() {
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                if (cells[i][j].getNumber() != 0)
                {
                    int des = passDestination(i, j, 'u');
                    moveVertically(i, j, des, -1);
                }
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }

    }

    private void moveDown() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                if (cells[i][j].getNumber() != 0)
                {
                    int des = passDestination(i, j, 'd');
                    moveVertically(i, j, des, 1);
                }
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }

    }

    private boolean isValidDesH(int i, int j, int des, int sign) {
        // not modified: prevent merge 2 times
        // merge first and second cell
        // first cell should  equal to second , and they both be non zero

        if (des + sign < n && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }

    private void moveHorizontally(int i, int j, int des, int sign) {
        // if a valid move,
        // add two numbers, and des is modified
        if (isValidDesH(i, j, des, sign)) {
            // update scores
            score += cells[i][j].getNumber();
            score += cells[i][j].getNumber();

            cells[i][j].adder(cells[i][des + sign]);
            cells[i][des+sign].setModify(true); //should set the "merged cell"

            moveFlag = true;// merge counts as move
            // only "swap" this two cells
        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);
            moveFlag = true;  // actual move
        }
    }

    // check if valid dest
    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0)
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
                return true;
            }
        return false;
    }

    // how to move
    private void moveVertically(int i, int j, int des, int sign) {
        if (isValidDesV(i, j, des, sign)) {

            // update scores
            score += cells[i][j].getNumber();
            score += cells[i][j].getNumber();

            cells[i][j].adder(cells[des + sign][j]);
            cells[des+sign][j].setModify(true);  //should set the "merged cell"
            moveFlag = true; // similar to moveHorizontally
        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);
            moveFlag = true;
        }
    }

    private boolean haveSameNumberNearly(int i, int j) {
        if (i < n - 1 && j < n - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }

    private boolean canNotMove() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (haveSameNumberNearly(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    // this is wrong , score is sum of merged values

//    private void sumCellNumbersToScore() {
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                score += cells[i][j].getNumber();
//            }
//        }
//    }

    public void move(KeyEvent key)
    {
        if (key.getCode() == KeyCode.DOWN) {
            moveDown(); // no need reflection
        } else if (key.getCode() == KeyCode.UP) {
            moveUp();
        } else if (key.getCode() == KeyCode.LEFT) {
            moveLeft();
        } else if (key.getCode() == KeyCode.RIGHT) {
            moveRight();
        }

    }
    public void setScore()
    {
        scoreText.setText(score + "");

    }
    public void fillNewNumberOrEnd(Stage primaryStage, Scene endGameScene, Group endGameRoot)
    {


        int haveEmptyCell;
        haveEmptyCell = Game.this.haveEmptyCell();

        // check the result in each step
        if (haveEmptyCell == -1) {
            if (canNotMove()) {

                primaryStage.setScene(endGameScene);

                EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, score);
                Controller.getSingleInstance().gameOver(endGameRoot);
                root.getChildren().clear();
                if(Game.isChallengeEasy == null){
                    //说明是普通模式，然后才进行设置排行榜
                    //change HIGN
                    if(highLeader < score){
                        highLeader = score;
                    }

                }
            }
        } else if(haveEmptyCell == 1 && moveFlag)
        {
            randomFillNumber(2);
        }
        // reset
        moveFlag = false;
    }


    // game main: duplicated variables
    public void game(Scene gameScene, Group root, Stage primaryStage, Scene endGameScene, Group endGameRoot) {

        // initialize BEGIN ===================
        this.root = root;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }

        }

        Text text = new Text();
        root.getChildren().add(text);
        text.setText("SCORE:");
        text.setFont(Font.font(30));
        text.relocate(750, 100);
        scoreText = new Text();
        root.getChildren().add(scoreText);
        scoreText.relocate(750, 150);
        scoreText.setFont(Font.font(20));
        //old: 0
        scoreText.setText(score + "");

        if(challengeText.getText().equals("common")){
            //at time time is common mode，no timer。need to move the text upward and hide level of difficulty

            //set yexy of leaderboard
            Text leader = new Text();
            root.getChildren().add(leader);
            leader.setText("HIGH:");
            leader.setFont(Font.font(30));
            leader.relocate(750, 270);
            leaderText = new Text();
            root.getChildren().add(leaderText);
            leaderText.relocate(750,320);
            leaderText.setFont(Font.font(20));
            //st HIGH
            leaderText.setText(Game.highLeader + "");


            // set text of level of difficulty
            Text challenge = new Text();
            root.getChildren().add(challenge);
            challenge.setText("MODE:");
            challenge.setFont(Font.font(30));
            challenge.relocate(750,400);
            // challengeText = new Text();
            root.getChildren().add(challengeText);
            challengeText.relocate(750,450);
            challengeText.setFont(Font.font(20));
            //set HIGH
            Boolean isChallengeEasy = Game.isChallengeEasy;


        }else if(challengeText.getText().equals("challenge")){
            //challenge mode
            Text time = new Text();
            root.getChildren().add(time);
            time.setText("TIME:");
            time.setFont(Font.font(30));
            time.relocate(750, 270);
            scoreTime = new Text();
            root.getChildren().add(scoreTime);
            scoreTime.relocate(750, 320);
            scoreTime.setFont(Font.font(20));
            scoreTime.setText( Game.TimeNum+ "");

            /**
             * if(Game.isEasy != null && Game.isEasy == 1){
             *                 TimeNum = 180;
             *                 scoreTime.setText(TimeNum +"");
             *             }else if (Game.isEasy != null && Game.isEasy == 0){
             *                 TimeNum = 60;
             *                 scoreTime.setText(TimeNum +"");
             *             }
             */
            /**
             *             //set text of leaderboard
             *             Text leader = new Text();
             *             root.getChildren().add(leader);
             *             leader.setText("HIGH:");
             *             leader.setFont(Font.font(30));
             *             leader.relocate(750,400);
             *             leaderText = new Text();
             *             root.getChildren().add(leaderText);
             *             leaderText.relocate(750,450);
             *             leaderText.setFont(Font.font(20));
             *             //设置HIGH
             *             leaderText.setText(highLeader + "");
             */



            //set text of difficulty
            Text challenge = new Text();
            root.getChildren().add(challenge);
            challenge.setText("MODE:");
            challenge.setFont(Font.font(30));
            challenge.relocate(750,400);
            //challengeText = new Text();
            root.getChildren().add(challengeText);
            challengeText.relocate(750,450);
            challengeText.setFont(Font.font(20));
            //set HIGH
            Boolean isChallengeEasy = Game.isChallengeEasy;

        }






        new Thread(()->{

            /**
             *              if(Game.isEasy != null && Game.isEasy == 1){
             *                     Game.TimeNum = 180;
             *                 }else if(Game.isEasy != null && Game.isEasy == 0){
             *                     Game.TimeNum = 60;
             *                 }
             */

            synchronized (this){
                while(true){
                    if(Game.isChallengeEasy == null || Game.isEasy == null || Game.TimeNum == null){
                        //now common mode, no timer,break directly
                        //break;

                    }else {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            if(e != null){
                                e.printStackTrace();
                            }
                        }
                        if(Game.TimeNum != null && Game.TimeNum != null){
                            if(scoreTime != null){
                                scoreTime.setText(String.valueOf(Game.TimeNum--));
                            }
                        }
                        if(Game.TimeNum != null && Game.TimeNum != null && Game.TimeNum == 0){
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            scoreTime.setText(String.valueOf(Game.TimeNum));

                            break;
                        }
                    }
                }
            }

            //clear TimeNum
            //Game.TimeNum = 0;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //update JavaFX main code to here
                    primaryStage.setScene(endGameScene);
                    EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, score);
                    Integer highLeader = Game.highLeader;
                    //set leaderboard
                    if(score > highLeader && Game.isChallengeEasy == null){
                        Game.highLeader = score;
                    }

                    Controller.getSingleInstance().gameOver(endGameRoot);

                    root.getChildren().clear();
                    score = 0;
                }
            });



        }).start();

        randomFillNumber(1);
        randomFillNumber(1);
        // initialize DONE ===================

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key ->{
            Platform.runLater(() -> {

                move(key);
//                    GameScene.this.maxCellNumbersToScore();
                // score update is wrong , not the sum,
                // but the sum of merge value

                setScore();
                fillNewNumberOrEnd(primaryStage, endGameScene, endGameRoot);
            });
        });
    }
}