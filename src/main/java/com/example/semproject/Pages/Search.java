package com.example.semproject.Pages;

import com.example.semproject.HelloApplication;
import com.example.semproject.MyComponents.SearchComponent;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class Search {

    public Search(Stage stage, Scene scene, ChaddingPage cp) {

        // creating containers
        BorderPane borderPane = new BorderPane();
        HBox welcomeContainer = new HBox();
        HBox buttonsContainer = new HBox(10);
        HBox topContainer = new HBox();
        HBox searchContainer = new HBox(10);
        HBox centerContainer = new HBox();
        VBox searchResults = new VBox(10);

        // creating components
        Label welcome = new Label("Welcome Back, " + HelloApplication.userName);
        Button back = new Button("back");
        Button logout = new Button("logout");
        TextField search = new TextField();
        Button searchButton = new Button("search");

        // adding components into layouts
        borderPane.setTop(topContainer);
        borderPane.setCenter(centerContainer);
        borderPane.setBottom(searchResults);

        topContainer.getChildren().addAll(welcomeContainer, buttonsContainer);
        welcomeContainer.getChildren().add(welcome);
        buttonsContainer.getChildren().addAll(back, logout);
        searchContainer.getChildren().addAll(search, searchButton);
        centerContainer.getChildren().add(searchContainer);

        // creating styles
        topContainer.setMinWidth(800);
        welcomeContainer.setMinWidth(600);
        buttonsContainer.setMinWidth(200);

        back.setStyle(HomePage.buttonStyles);
        logout.setStyle("-fx-border-radius: 5px; -fx-background-radius: 5px; -fx-background-color: #ff6060; -fx-text-fill: #fff; -fx-margin: 10px; -fx-font-size: 15");

        welcome.setStyle("-fx-padding: 10; -fx-font-size: 25; -fx-font-weight: 700");

        buttonsContainer.setAlignment(Pos.CENTER_RIGHT);
        buttonsContainer.setPadding(new Insets(10));

        search.setPromptText("Enter your search...");
        topContainer.setStyle("-fx-padding: 10 0 10 0; -fx-border-width: 1; -fx-border-color: #ff0000; -fx-border-style: hidden hidden solid hidden");
        search.setMinWidth(500);
        search.setStyle("-fx-padding: 5 10 5 10; -fx-border-width 1; -fx-border-color: #000; -fx-border-radius: 5");
        searchButton.setStyle("-fx-background-color: #fff; -fx-border-width: 1; -fx-border-color: #000; -fx-border-radius: 5; -fx-padding: 5 10 5 10");
        searchContainer.setMinWidth(600);
        searchContainer.setAlignment(Pos.CENTER);
        borderPane.setStyle("-fx-background-color: #fff");
        searchButton.setAlignment(Pos.CENTER);
        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.setStyle("-fx-padding: 10 0 10 0");
        searchResults.setMinHeight(350);
        searchResults.setAlignment(Pos.TOP_CENTER);

        back.setCursor(Cursor.HAND);
        logout.setCursor(Cursor.HAND);
        searchButton.setCursor(Cursor.HAND);

        // creating and setting scene
        Scene scene1 = new Scene(borderPane, 800, 500);
        stage.setScene(scene1);

        // event handling here
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String query = search.getText();
                Firestore db = FirestoreClient.getFirestore();
                ApiFuture<QuerySnapshot> q = db.collection("accounts").whereEqualTo("username", query).get();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                searchResults.getChildren().add(new SearchComponent((String) q.get().getDocuments().get(0).get("uid"), (String) q.get().getDocuments().get(0).get("username"), scene, stage, cp));
                            } catch (Exception e) {
                                System.out.println("hello world twada error a chuka");
                            }
                    }
                });
            }
        });

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(scene);
            }
        });
    }
}
