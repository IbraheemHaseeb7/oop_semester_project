package com.example.gooee.test;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UserBox {
    String name;
    String uid;
    Scene scene;
    Stage stage;

    public UserBox(String name, String uid, Scene scene, Stage stage) {
        this.name = name;
        this.uid = uid;
        this.scene = scene;
        this.stage = stage;
    }

    public HBox display() {
        HBox hbox = new HBox();

        Button message = new Button("Message Me");
        message.setCursor(Cursor.HAND);
        message.setId(uid);
        message.setStyle(Demo.buttonColors);
        message.addEventHandler(MouseEvent.MOUSE_ENTERED, new ButtonHoverIn(message));
        message.addEventHandler(MouseEvent.MOUSE_EXITED, new ButtonHoverOut(message));


        message.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ChadPage chadPageObj = new ChadPage(stage, scene, name, uid);
                chadPageObj.display();
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

        messageContainer.setMinWidth(scene.getWidth() / 2 + 50);
        nameContainer.setMinWidth(scene.getWidth() / 2 + 50);
        messageContainer.setMaxWidth(scene.getWidth() / 2 + 50);
        nameContainer.setMaxWidth(scene.getWidth() / 2 + 50);
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                scene.widthProperty().addListener(new GrowClass((int)scene.getWidth() / 2 + 50, scene, hbox));
                scene.widthProperty().addListener(new GrowClass((int)scene.getWidth() / 2 + 50, scene, messageContainer));
                scene.widthProperty().addListener(new GrowClass((int)scene.getWidth() / 2 + 50, scene, nameContainer));
            }
        });


        return hbox;
    }
}
