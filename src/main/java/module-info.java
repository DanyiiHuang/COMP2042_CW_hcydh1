module com.example.comp2042huangdanyi {
    requires javafx.controls;
    requires javafx.fxml;
    requires  javafx.base;
    requires java.desktop;


    opens com.example.comp2042huangdanyi.Controls to javafx.controls;
    exports com.example.comp2042huangdanyi.Controls;
    opens com.example.comp2042huangdanyi.data to javafx.base;
    exports com.example.comp2042huangdanyi.data;
    opens  com.example.comp2042huangdanyi.Views to javafx.fxml;
    exports com.example.comp2042huangdanyi.Views;
}