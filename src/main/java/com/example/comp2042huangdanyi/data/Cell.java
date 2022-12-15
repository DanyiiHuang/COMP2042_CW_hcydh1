package com.example.comp2042huangdanyi.data;

import com.example.comp2042huangdanyi.Views.View;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static com.example.comp2042huangdanyi.Views.View.choice;

// ecap of methods : change to private
// Rectangle

// cell should has color scheme

public class Cell {
    private Rectangle rectangle;
    private Group root;
    private Text textClass;
    private boolean modify = false;

    void setModify(boolean modify) {
        this.modify = modify;
    }

    boolean getModify() {
        return modify;
    }

    Cell(double x, double y, double scale, Group root) {
        rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setHeight(scale);
        rectangle.setWidth(scale);
        this.root = root;
        this.textClass = TextMaker.getSingleInstance().madeText("0", x, y, root);
        View.setColorByNumber(getNumber(), rectangle, choice); //*
        root.getChildren().add(rectangle);
    }

    void setTextClass(Text textClass) {
        this.textClass = textClass;
    }

    void changeCell(Cell cell) {
        TextMaker.changeTwoText(textClass, cell.getTextClass());
        root.getChildren().remove(cell.getTextClass());
        root.getChildren().remove(textClass);

        if (!cell.getTextClass().getText().equals("0")) {
            root.getChildren().add(cell.getTextClass());
        }
        if (!textClass.getText().equals("0")) {
            root.getChildren().add(textClass);
        }
        // Duplicated
//        setColorByNumber(getNumber());
//        cell.setColorByNumber(cell.getNumber());
        View.setColorByNumber(getNumber(), rectangle, View.choice);
        cell.setColorByNumber(cell.getNumber()); //*
    }

    void adder(Cell cell) {
        cell.getTextClass().setText((cell.getNumber() + this.getNumber()) + "");
        textClass.setText("0");
        root.getChildren().remove(textClass);
//        cell.setColorByNumber(cell.getNumber());
//        setColorByNumber(getNumber());
        View.setColorByNumber(getNumber(), rectangle, View.choice);
        cell.setColorByNumber(cell.getNumber()); //*
    }

    // push to view class
    void setColorByNumber(int number) {
        View.setColorByNumber(number, rectangle, View.choice);
//        switch (number) {
//            case 0:
//                rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
//                break;
//            case 2:
//                rectangle.setFill(Color.rgb(232, 255, 100, 0.5));
//                break;
//            case 4:
//                rectangle.setFill(Color.rgb(232, 220, 50, 0.5));
//                break;
//            case 8:
//                rectangle.setFill(Color.rgb(232, 200, 44, 0.8));
//                break;
//            case 16:
//                rectangle.setFill(Color.rgb(232, 170, 44, 0.8));
//                break;
//            case 32:
//                rectangle.setFill(Color.rgb(180, 120, 44, 0.7));
//                break;
//            case 64:
//                rectangle.setFill(Color.rgb(180, 100, 44, 0.7));
//                break;
//            case 128:
//                rectangle.setFill(Color.rgb(180, 80, 44, 0.7));
//                break;
//            case 256:
//                rectangle.setFill(Color.rgb(180, 60, 44, 0.8));
//                break;
//            case 512:
//                rectangle.setFill(Color.rgb(180, 30, 44, 0.8));
//                break;
//            case 1024:
//                rectangle.setFill(Color.rgb(250, 0, 44, 0.8));
//                break;
//            case 2048:
//                rectangle.setFill(Color.rgb(250,0,0,1));
//
//
//        }
    }

    double getX() {
        return rectangle.getX();
    }

    double getY() {
        return rectangle.getY();
    }

    int getNumber() {
        return Integer.parseInt(textClass.getText());
    }

    private Text getTextClass() {
        return textClass;
    }

}