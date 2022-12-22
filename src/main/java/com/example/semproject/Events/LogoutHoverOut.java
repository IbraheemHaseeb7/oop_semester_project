package com.example.semproject.Events;

import com.example.semproject.Pages.HomePage;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class LogoutHoverOut implements EventHandler<MouseEvent> {

    Button button;

    public LogoutHoverOut(Button button) {
        this.button = button;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED) {
            button.setStyle("-fx-border-radius: 5px; -fx-background-radius: 5px; -fx-background-color: #ff6060; -fx-text-fill: #fff; -fx-margin: 10px; -fx-font-size: 15");
        }
    }
}
