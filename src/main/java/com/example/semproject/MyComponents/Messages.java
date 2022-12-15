package com.example.semproject.MyComponents;

import com.example.semproject.HelloApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Messages extends HBox {
    String body;
    public Messages(String body, String uid) {

        this.body = body;

        // components
        Label msg = new Label(body);

        // adding into layouts
        this.getChildren().add(msg);
        if (uid.equals(HelloApplication.userId)) {
            msg.setStyle("-fx-padding: 10; -fx-border-width: 1; -fx-border-color: #ff0000; -fx-border-radius: 5");
            this.setAlignment(Pos.CENTER_RIGHT);
        } else {
            msg.setStyle("-fx-padding: 10; -fx-border-width: 1; -fx-border-color: #60c6ff; -fx-border-radius: 5");
            this.setAlignment(Pos.CENTER_LEFT);
        }

        // styling
        this.setMinWidth(570);
        this.setPadding(new Insets(10));

        msg.setWrapText(true);
        msg.setMaxWidth(400);
    }
}
