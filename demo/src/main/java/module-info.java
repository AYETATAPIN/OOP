module ru.nsu.demidov.zmei.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.demidov.zmei.demo to javafx.fxml;
    exports ru.nsu.demidov.zmei.demo;
}