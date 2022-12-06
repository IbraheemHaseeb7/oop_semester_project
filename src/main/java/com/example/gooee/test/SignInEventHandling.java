package com.example.gooee.test;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignInEventHandling implements EventHandler <ActionEvent> {

    Stage stage;
    Inbox inbox;

    public SignInEventHandling(Stage stage, Inbox inbox) {
        this.stage = stage;
        this.inbox = inbox;
    }

    @Override
    public void handle(ActionEvent e) {
        stage.setScene(inbox.scene);
        inbox.display();
    }
}
