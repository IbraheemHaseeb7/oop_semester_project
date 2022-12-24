package com.example.semproject.Events;

import com.example.semproject.Pages.ChaddingPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignupEventHandling implements EventHandler<ActionEvent> {
    Stage stage;
    Scene scene;

    public SignupEventHandling(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        stage.setScene(scene);
    }
}
