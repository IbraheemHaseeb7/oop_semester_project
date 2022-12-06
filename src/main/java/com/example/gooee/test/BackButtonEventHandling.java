package com.example.gooee.test;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BackButtonEventHandling implements EventHandler<ActionEvent> {

    Stage stage;
    Scene scene;

    public BackButtonEventHandling(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        stage.setScene(scene);
    }
}
