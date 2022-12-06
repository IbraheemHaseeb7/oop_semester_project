package com.example.gooee.test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Inbox extends Page {

    public Inbox(Stage stage, Scene scene) {
        super(stage, scene);
    }

    public void display() {
        Button back = new Button("Back");
        Button search = new Button("Search");
        Button logout = new Button("Logout");

        BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();
        HBox hBox = new HBox();
        HBox backHbox = new HBox();
        HBox SearchHbox = new HBox();
        HBox logoutHbox = new HBox();

        bp.setCenter(gp);
        bp.setTop(hBox);
        backHbox.getChildren().add(back);
        SearchHbox.getChildren().add(search);
        logoutHbox.getChildren().add(logout);
        backHbox.setAlignment(Pos.TOP_LEFT);
        SearchHbox.setAlignment(Pos.CENTER);
        logoutHbox.setAlignment(Pos.TOP_RIGHT);

        hBox.setMinWidth(scene.getWidth());
        backHbox.setMinWidth(scene.getWidth()/3);
        SearchHbox.setMinWidth(scene.getWidth()/3);
        logoutHbox.setMinWidth(scene.getWidth()/3);
        hBox.getChildren().addAll(backHbox, SearchHbox, logoutHbox);

        // Styling
        bp.setStyle("-fx-background-color: black");
        back.setStyle(Demo.buttonColors);
        search.setStyle(Demo.buttonColors);
        logout.setStyle(Demo.buttonColors);
        back.setCursor(Cursor.HAND);
        search.setCursor(Cursor.HAND);
        logout.setCursor(Cursor.HAND);

        Scene inboxScene = new Scene(bp, scene.getWidth(), scene.getHeight());

        // Event Handling
        back.setOnAction(new BackButtonEventHandling(stage, scene));
        logout.setOnAction(new BackButtonEventHandling(stage, scene));

        stage.setScene(inboxScene);
        stage.show();
    }

}
