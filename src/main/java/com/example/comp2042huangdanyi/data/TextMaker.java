package com.example.comp2042huangdanyi.data;


import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/** Class for displaying in-game text.
 * @author DanyiHuang
 */
class TextMaker {
    private static TextMaker singleInstance = null;

    private TextMaker() {

    }

    /** Method for TextMaker for creating object
     * @return TextMaker if exists, else create new object for TextMaker
     */
    static TextMaker getSingleInstance() {
        if (singleInstance == null)
            singleInstance = new TextMaker();
        return singleInstance;
    }

    /** Method to create text inside cell.
     * @param input String number
     * @param xCell coordination of xCell
     * @param yCell coordination of yCell
     * @param root Root to store component
     * @return text number.
     */
    public static Text madeText(String input, double xCell, double yCell, Group root) {
        double length = Game.getLENGTH();
        double fontSize = (3 * length) / 7.0;
        Text text = new Text(input);
        text.setFont(Font.font(fontSize));
        text.relocate((xCell + (1.2)* length / 7.0), (yCell + 2 * length / 7.0));
        text.setFill(Color.WHITE);

        return text;
    }

    /** Method to change text between cells.
     * @param first first text in Cell[][].
     * @param second second text in Cell[][].
     */
    static void changeTwoText(Text first, Text second) {
        String temp;
        temp = first.getText();
        first.setText(second.getText());
        second.setText(temp);

        double tempNumber;
        tempNumber = first.getX();
        first.setX(second.getX());
        second.setX(tempNumber);

        tempNumber = first.getY();
        first.setY(second.getY());
        second.setY(tempNumber);

    }

}
