module com.example.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires com.opencsv;
    requires jbcrypt;


    opens com.example.test to javafx.fxml;
    exports com.example.test;
}