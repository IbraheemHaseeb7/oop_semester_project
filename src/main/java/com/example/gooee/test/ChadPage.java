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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ChadPage extends Page{

    String name;
    String UID;
    public ChadPage(Stage stage, Scene scene, String name, String UID) {
        super(stage, scene);
        this.name = name;
        this.UID = UID;
    }

    @Override
    public void display() {
        BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();
        Button backButton = new Button("Back");
        backButton.setCursor(Cursor.HAND);

        backButton.setStyle(Demo.buttonColors);
        backButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new ButtonHoverIn(backButton));
        backButton.addEventHandler(MouseEvent.MOUSE_EXITED, new ButtonHoverOut(backButton));
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(scene);
            }
        });

        Label name = new Label(this.name);
        name.setFont(new Font("Times New Roman", 25));

        HBox topHboxContainer = new HBox();
        HBox nameContainer = new HBox();
        nameContainer.getChildren().add(name);
        HBox backButtonContainer = new HBox();
        backButtonContainer.getChildren().add(backButton);
        topHboxContainer.getChildren().addAll(nameContainer, backButtonContainer);

        topHboxContainer.setAlignment(Pos.CENTER);
        nameContainer.setAlignment(Pos.TOP_LEFT);
        backButtonContainer.setAlignment(Pos.TOP_RIGHT);


        TextField messageTyping = new TextField();
        messageTyping.setPromptText("Enter Message");
        Button sendButton = new Button("Send");
        sendButton.setStyle(Demo.buttonColors);
        sendButton.setCursor(Cursor.HAND);

        sendButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new ButtonHoverIn(sendButton));
        sendButton.addEventHandler(MouseEvent.MOUSE_EXITED, new ButtonHoverOut(sendButton));

        HBox messageTypingContainer = new HBox();
        messageTypingContainer.getChildren().add(messageTyping);
        HBox sendButtonContainer = new HBox();
        sendButtonContainer.getChildren().add(sendButton);
        HBox bigBottomHBox = new HBox();
        bigBottomHBox.getChildren().addAll(messageTypingContainer, sendButtonContainer);

        bigBottomHBox.setAlignment(Pos.CENTER);
        messageTypingContainer.setAlignment(Pos.BOTTOM_LEFT);
        sendButtonContainer.setAlignment(Pos.BOTTOM_RIGHT);


        bp.setTop(topHboxContainer);
        bp.setCenter(gp);
        bp.setBottom(bigBottomHBox);
        bp.setStyle("-fx-background-color: #000");

        Scene chadPageScene = new Scene(bp, scene.getWidth(), scene.getHeight());
        chadPageScene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                topHboxContainer.setMinWidth(chadPageScene.getWidth());
                nameContainer.setMinWidth(chadPageScene.getWidth()/2);
                backButtonContainer.setMinWidth(chadPageScene.getWidth()/2);
            }
        });

        chadPageScene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                bigBottomHBox.setMinWidth(chadPageScene.getWidth());
                messageTypingContainer.setMinWidth(chadPageScene.getWidth()/2);
                sendButtonContainer.setMinWidth(chadPageScene.getWidth()/2);
            }
        });

        stage.setScene(chadPageScene);
    }
}
