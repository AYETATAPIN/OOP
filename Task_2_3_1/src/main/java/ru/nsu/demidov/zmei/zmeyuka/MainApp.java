package ru.nsu.demidov.zmei.zmeyuka;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * MainApp class.
 */

public class MainApp extends Application {

    /**
     * start method.
     */

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("snakeGame.fxml"));
        Parent gameContent = fxmlLoader.load();
        StackPane root = new StackPane();
        Image bgImage = new Image(MainApp.class.getResourceAsStream("pill.jpg"));
        BackgroundImage backgroundImage = new BackgroundImage(
                bgImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );
        root.setBackground(new Background(backgroundImage));
        root.getChildren().add(gameContent);
        Scene scene = new Scene(root);
        stage.setTitle("zmeyuka");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * nu tipa main.
     */

    public static void main(String[] args) {
        launch();
    }
}