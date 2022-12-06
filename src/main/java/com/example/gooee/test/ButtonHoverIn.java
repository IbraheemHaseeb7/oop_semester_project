package com.example.gooee.test;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ButtonHoverIn implements EventHandler<MouseEvent> {
    Button button = new Button();

    public ButtonHoverIn(Button button) {
        this.button = button;
    }

    @Override
    public void handle(MouseEvent e) {

        // resetting styles
        button.setStyle(null);

        Demo.buttonColors = "-fx-border-insets: 5px; -fx-background-insets: 5px;  -fx-background-color: #8b8b8b; -fx-text-fill: #000; -fx-border-width: 3; -fx-border-color: #" + Demo.themeColor;

        // reapplying styles
        button.setStyle(Demo.buttonColors);
    }
}
