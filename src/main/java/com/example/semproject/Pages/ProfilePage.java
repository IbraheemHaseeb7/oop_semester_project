package com.example.semproject.Pages;

import com.example.semproject.Events.HoverIn;
import com.example.semproject.Events.HoverOut;
import com.example.semproject.HelloApplication;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class ProfilePage extends BorderPane {

    public static String styles = "-fx-padding: 10; -fx-background-radius: 5; -fx-border-radius: 5; -fx-border-width: 1; -fx-border-color: #ff0000";
    public static String labelStyles = "-fx-padding: 5; -fx-background-radius: 5; -fx-border-radius: 5; -fx-border-width: 1; -fx-border-color: #ff0000";

    public ProfilePage(Stage stage, Scene scene, boolean isMe, String naam) {
        // creating layouts
        HBox top = new HBox();
        GridPane container = new GridPane();

        // creating components
        Label name = new Label(HelloApplication.userName);

        TextField nameField = new TextField();
        TextArea bio = new TextArea();
        TextField email = new TextField();

        Label nameLabel = new Label();
        Label bioLabel = new Label();
        Label emailLabel = new Label();

        Button done = new Button("done");

        // adding components into layouts
        top.getChildren().add(name);

        if (isMe) {

            container.setAlignment(Pos.CENTER);
            container.add(nameField, 0, 0);
            container.add(email, 0, 1);
            container.add(bio, 0, 2);
        } else {
            container.setAlignment(Pos.TOP_CENTER);
            container.setPadding(new Insets(30));
            container.add(nameLabel, 0, 0);
            container.add(emailLabel, 0, 1);
            container.add(bioLabel, 0, 2);

            // fetching user data
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> q = db.collection("accounts").whereEqualTo("username", naam).get();

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    nameLabel.setText("Name: " + naam);
                    name.setText(naam);
                    try {
                        emailLabel.setText("Email: " + (String)q.get().getDocuments().get(0).get("email"));
                        bioLabel.setText("Bio " + (String)q.get().getDocuments().get(0).get("bio"));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        container.add(done, 0, 3);

        this.setTop(top);
        this.setCenter(container);

        // setting values
        nameField.setText(HelloApplication.userName);
        bio.setText(HelloApplication.bio);
        email.setText(HelloApplication.email);

        // styling

        this.setStyle("-fx-background-color: #fff");

        top.setMinWidth(800);
        top.setAlignment(Pos.CENTER);

        name.setStyle("-fx-font-size: 25; -fx-font-weight: 700; -fx-padding: 15");

        nameField.setMinWidth(500);
        bio.setMinWidth(500);
        email.setMinWidth(500);
        nameField.setMaxWidth(500);
        bio.setMaxWidth(500);
        email.setMaxWidth(500);

        nameLabel.setMinWidth(500);
        bioLabel.setMinWidth(500);
        emailLabel.setMinWidth(500);
        nameLabel.setMaxWidth(500);
        bioLabel.setMaxWidth(500);
        emailLabel.setMaxWidth(500);
        bioLabel.setMinHeight(200);
        bioLabel.setMaxHeight(200);

        bioLabel.setWrapText(true);
        bioLabel.setAlignment(Pos.TOP_LEFT);

        nameField.setStyle(styles);
        bio.setStyle(styles);
        email.setStyle(styles);
        done.setStyle(HomePage.buttonStyles);
        done.setCursor(Cursor.HAND);

        nameLabel.setStyle(labelStyles);
        emailLabel.setStyle(labelStyles);
        bioLabel.setStyle(labelStyles);

        container.setVgap(10);

        // setting scenes
        Scene profileScene = new Scene(this, 800, 500);
        stage.setScene(profileScene);

        // event handling
        done.addEventHandler(MouseEvent.MOUSE_ENTERED, new HoverIn(done));
        done.addEventHandler(MouseEvent.MOUSE_EXITED, new HoverOut(done));
        done.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (isMe) {
                    Firestore db = FirestoreClient.getFirestore();
                    DocumentReference d = db.collection("accounts").document(HelloApplication.userId);

                    d.update(new HashMap<String, Object>(){{
                        put("username", nameField.getText());
                        put("email", email.getText());
                        put("bio", bio.getText());
                    }});

                    HelloApplication.userName = nameField.getText();
                    HelloApplication.email = email.getText();
                    HelloApplication.bio = bio.getText();
                }
                stage.setScene(scene);
            }
        });
    }
}
