module academy.learn.ui {
    requires javafx.fxml;
    requires javafx.controls;
    requires academy.learn.db;

    exports academy.learn.ui to javafx.graphics, javafx.fxml;
    opens academy.learn.ui to javafx.fxml;
}