module ru.nsu.demidov.zmei.zmeyuka {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ru.nsu.demidov.zmei.zmeyuka to javafx.fxml;
    exports ru.nsu.demidov.zmei.zmeyuka;
}