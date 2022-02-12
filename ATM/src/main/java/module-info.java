module com.example.atm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.atm to javafx.fxml;
    exports com.example.atm;
}