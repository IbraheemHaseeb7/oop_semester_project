package com.example.gooee.test;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignUpEventHandling implements EventHandler<ActionEvent> {
    Stage stage;
    Inbox inbox;

    public SignUpEventHandling(Stage stage, Inbox inbox) {
        this.stage = stage;
        this.inbox = inbox;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        stage.setScene(inbox.scene);
        inbox.display();
    }
}
