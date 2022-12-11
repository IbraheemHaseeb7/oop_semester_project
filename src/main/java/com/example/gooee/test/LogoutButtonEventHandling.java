package com.example.gooee.test;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogoutButtonEventHandling implements EventHandler<ActionEvent> {

    Stage stage;
    Scene scene;
    Scene scene2;

    public LogoutButtonEventHandling(Stage stage, Scene scene, Scene scene2) {
        this.stage = stage;
        this.scene = scene;
        this.scene2 = scene2;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        stage.setWidth(scene2.getWidth());
        stage.setHeight(scene2.getHeight());
        stage.setScene(scene);
    }
}
