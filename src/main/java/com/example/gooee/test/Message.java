package com.example.gooee.test;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.HashMap;

public class Message extends HBox {

    public Message(String body, String uid, Scene scene) {

        this.setMinWidth(scene.getWidth() - 20);
        this.setMaxWidth(scene.getWidth() - 20);

        this.setPadding(new Insets(10));
        Label message = new Label(body);
        message.setWrapText(true);
        message.setStyle("-fx-text-fill: #fff");
        message.setMaxWidth(scene.getWidth() - 150);
        this.getChildren().add(message);
        if (Demo.userID.equals(uid)) {
            message.setStyle("-fx-padding: 5; -fx-border-radius: 5; -fx-border-color: #eaeaea; -fx-border-width: 1; -fx-text-fill: #eaeaea");
            this.setAlignment(Pos.CENTER_RIGHT);
        } else {
            message.setStyle("-fx-padding: 5; -fx-border-radius: 5; -fx-background-radius: 5px; -fx-border-color: #eaeaea; -fx-border-width: 1; -fx-text-fill: #000; -fx-background-color: #eaeaea; -fx-border-insets: 5px; -fx-background-insets: 5px;");
            this.setAlignment(Pos.CENTER_LEFT);
        }
    }
}
