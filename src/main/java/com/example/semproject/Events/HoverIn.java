package com.example.semproject.Events;

import com.example.semproject.Pages.HomePage;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class HoverIn implements EventHandler<MouseEvent> {

    Button button;

    public HoverIn(Button button) {
        this.button = button;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED) {
            button.setStyle(HomePage.buttonHoverStyles);
        }
    }
}
