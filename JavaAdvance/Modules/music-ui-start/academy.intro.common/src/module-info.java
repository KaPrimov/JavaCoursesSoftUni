module academy.intro.common {
    requires javafx.base;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    exports academy.learn.common to academy.learn.db, academy.learn.ui;
    opens academy.learn.common to javafx.base;
}