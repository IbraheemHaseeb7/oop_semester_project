package com.example.gooee.test;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Search extends Page{

    public Search(Stage stage, Scene scene) {
        super(stage, scene);
    }

    @Override
    public void display() {
        TextField textField = new TextField();
        textField.setPromptText("Search usernames");
        Button searchButton = new Button("Search");
        Button backButton = new Button("Back Button");
        BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();
        HBox hBox = new HBox();
        HBox textHbox = new HBox();
        HBox searcButtonHbox = new HBox();
        HBox backHbox = new HBox();

        backHbox.getChildren().add(backButton);
        textHbox.getChildren().add(textField);
        searcButtonHbox.getChildren().add(searchButton);
        hBox.getChildren().addAll(backHbox, textHbox, searcButtonHbox);
        backHbox.setAlignment(Pos.CENTER);
        backHbox.setPadding(new Insets(10));
        textHbox.setAlignment(Pos.CENTER);
        textHbox.setPadding(new Insets(10));
        searcButtonHbox.setAlignment(Pos.CENTER);
        searcButtonHbox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.CENTER);
        bp.setTop(hBox);

        bp.setStyle("-fx-background-color: #000");
        searchButton.setStyle(Demo.buttonColors);
        searchButton.setCursor(Cursor.HAND);
        searchButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new ButtonHoverIn(searchButton));
        searchButton.addEventHandler(MouseEvent.MOUSE_EXITED, new ButtonHoverOut(searchButton));

        backButton.setStyle(Demo.buttonColors);
        backButton.setCursor(Cursor.HAND);
        backButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new ButtonHoverIn(backButton));
        backButton.addEventHandler(MouseEvent.MOUSE_EXITED, new ButtonHoverOut(backButton));

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(scene);
            }
        });

        Scene searchScene = new Scene(bp, scene.getWidth(), scene.getHeight());
        stage.setScene(searchScene);
        stage.show();
    }
}
