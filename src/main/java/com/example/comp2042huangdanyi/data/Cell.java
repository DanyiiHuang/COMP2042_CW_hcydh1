package com.example.comp2042huangdanyi.data;

import com.example.comp2042huangdanyi.Views.View;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static com.example.comp2042huangdanyi.Views.View.choice;

/** Class to create cell and control the context of the cell.
 * @author DanyiHuang-modified
 */
public class Cell {
    private Rectangle rectangle;
    private Group root;
    private Text textClass;
    private boolean modify = false;

    /** Layout of cell board in game.
     * @param x coordination of cell in x.
     * @param y coordination of cell in y.
     * @param scale Scale of the cell board.
     * @param root Group root to store component.
     */
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

    /** Method to change the cell's coordinate in 2D array after movement.
     * @param cell cell board.
     */
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

    /** Method for cell's number sum together after movement.
     * @param cell cell board.
     */
    void adder(Cell cell) {
        cell.getTextClass().setText((cell.getNumber() + this.getNumber()) + "");
        textClass.setText("0");
        root.getChildren().remove(textClass);
        View.setColorByNumber(getNumber(), rectangle, View.choice);
        cell.setColorByNumber(cell.getNumber()); //*
    }

    /** Method to change color based on cell's number.
     * @param number number text in cell.
     */
    void setColorByNumber(int number) {
        View.setColorByNumber(number, rectangle, View.choice);
    }

    /** Getter for x.
     * @return double x.
     */
    double getX() {
        return rectangle.getX();
    }

    /** Getter for y.
     * @return double y.
     */
    double getY() {
        return rectangle.getY();
    }

    /** Getter for number.
     * @return int number inside textClass.
     */
    int getNumber() {
        return Integer.parseInt(textClass.getText());
    }

    /** Getter for textClass.
     * @return text TextClass.
     */
    private Text getTextClass() {
        return textClass;
    }

    /** Setter for modify
     * @param modify boolean modify.
     */
    void setModify(boolean modify) {
        this.modify = modify;
    }

    /** Getter for modify.
     * @return boolean modify.
     */
    boolean getModify() {
        return modify;
    }

    /** Setter for textClass.
     * @param textClass text textClass.
     */
    void setTextClass(Text textClass) {
        this.textClass = textClass;
    }

}