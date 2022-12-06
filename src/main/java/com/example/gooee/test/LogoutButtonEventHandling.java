package com.example.gooee.test;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogoutButtonEventHandling implements EventHandler<ActionEvent> {

    Stage stage;
    Scene scene;

    public LogoutButtonEventHandling(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        stage.setScene(scene);
    }
}
