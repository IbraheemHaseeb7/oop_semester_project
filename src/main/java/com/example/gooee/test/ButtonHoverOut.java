package com.example.gooee.test;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ButtonHoverOut implements EventHandler<MouseEvent> {
    Button button = new Button();
    public ButtonHoverOut(Button button) {
        this.button = button;
    }

    @Override
    public void handle(MouseEvent e) {

        // resetting styles
        button.setStyle(null);

        Demo.buttonColors = "-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-insets: 5px; -fx-background-insets: 5px;  -fx-background-color: #b3b3b3; -fx-text-fill: #000; -fx-border-width: 2; -fx-border-color: #" + Demo.themeColor;

        // reapplying styles
        button.setStyle(Demo.buttonColors);
    }
}
