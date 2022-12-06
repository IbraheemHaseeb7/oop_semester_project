package com.example.gooee.test;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Inbox extends Page {
    Stage stage;
    Scene scene;

    public Inbox(Stage stage, Scene scene) {
        super(stage, scene);
    }

    public void display()
    {
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

        hBox.setMinWidth(500);
        backHbox.setMinWidth(500/3);
        SearchHbox.setMinWidth(500/3);
        logoutHbox.setMinWidth(500/3);
        hBox.getChildren().addAll(backHbox, SearchHbox, logoutHbox);

        Scene inboxScene = new Scene(bp, 500, 500);

        // Event Handling
        back.setOnAction(new BackButtonEventHandling(stage, scene));

        stage.setScene(inboxScene);
        stage.show();
    }

}
