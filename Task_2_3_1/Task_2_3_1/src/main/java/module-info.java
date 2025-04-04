module ru.nsu.demidov.zmei.task_2_3_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ru.nsu.demidov.zmei.task_2_3_1 to javafx.fxml;
    exports ru.nsu.demidov.zmei.task_2_3_1;
}