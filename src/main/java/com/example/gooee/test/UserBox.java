package com.example.gooee.test;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class UserBox {
    String name;
    String uid;
    Scene scene;

    public UserBox(String name, String uid, Scene scene) {
        this.name = name;
        this.uid = uid;
        this.scene = scene;
    }

    public HBox display() {
        HBox hbox = new HBox();

        Button message = new Button("Message Me");
        message.setId(uid);

        message.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println(((Button)e.getSource()).getId());
            }
        });

        HBox messageContainer = new HBox();
        messageContainer.getChildren().add(message);
        messageContainer.setPadding(new Insets(10));
        messageContainer.setAlignment(Pos.CENTER_RIGHT);

        Label name = new Label(this.name);
        name.setFont(new Font(15));
        name.setStyle("-fx-text-fill: white");
        HBox nameContainer = new HBox();
        nameContainer.setPadding(new Insets(10));
        nameContainer.getChildren().add(name);
        nameContainer.setAlignment(Pos.CENTER_LEFT);

        hbox.getChildren().addAll(nameContainer, messageContainer);
        hbox.setAlignment(Pos.CENTER);
        scene.widthProperty().addListener(new GrowClass((int)scene.getWidth() / 2, scene, hbox));
        scene.widthProperty().addListener(new GrowClass((int)scene.getWidth() / 2, scene, messageContainer));
        scene.widthProperty().addListener(new GrowClass((int)scene.getWidth() / 2, scene, nameContainer));

        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                System.out.println(messageContainer.getWidth());
            }
        });


        return hbox;
    }
}
