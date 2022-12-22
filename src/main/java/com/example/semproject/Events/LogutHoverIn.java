package com.example.semproject.Events;

import com.example.semproject.Pages.HomePage;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class LogutHoverIn implements EventHandler<MouseEvent> {

    Button button;

    public LogutHoverIn(Button button) {
        this.button = button;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED) {
            button.setStyle("-fx-border-radius: 5px; -fx-background-radius: 5px; -fx-background-color: #ff0000; -fx-text-fill: #fff; -fx-margin: 10px; -fx-font-size: 15");
        }
    }
}
