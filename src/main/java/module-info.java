module com.example.gooee {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gooee to javafx.fxml;
    exports com.example.gooee;
    exports com.example.gooee.test;
    opens com.example.gooee.test to javafx.fxml;
}