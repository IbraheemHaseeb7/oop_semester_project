package com.example.semproject.Events;

import com.example.semproject.Pages.HomePage;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class HoverOut implements EventHandler<MouseEvent> {

    Button button;

    public HoverOut(Button button) {
        this.button = button;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED) {
            button.setStyle(HomePage.buttonStyles);
        }
    }
}
