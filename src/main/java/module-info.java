module com.example.comp2042huangdanyi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.comp2042huangdanyi to javafx.fxml;
    exports com.example.comp2042huangdanyi;
}