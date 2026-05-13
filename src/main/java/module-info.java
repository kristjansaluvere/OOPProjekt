module org.example.horoskoobigeneraator {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.horoskoobigeneraator to javafx.fxml;
    exports org.example.horoskoobigeneraator;
}