module org.example.loginapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.loginapp to javafx.fxml;
    exports org.example.loginapp;
}