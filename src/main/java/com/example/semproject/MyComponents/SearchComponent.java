package com.example.semproject.MyComponents;

import com.example.semproject.Events.HoverIn;
import com.example.semproject.Events.HoverOut;
import com.example.semproject.HelloApplication;
import com.example.semproject.Pages.ChaddingPage;
import com.example.semproject.Pages.HomePage;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class SearchComponent extends HBox {

    public SearchComponent(String uid, String name, Scene scene, Stage stage, ChaddingPage cp) {

        // creating layouts
        HBox nameContainer = new HBox();
        HBox buttonContainer = new HBox();

        // creating components
        Label nameLabel = new Label(name);
        Button msg = new Button("message me");

        // adding into layouts
        nameContainer.getChildren().add(nameLabel);
        buttonContainer.getChildren().add(msg);

        // setting styles
        this.getChildren().addAll(nameContainer, buttonContainer);
        this.setMinWidth(600);

        this.setStyle("-fx-border-width: 1; -fx-border-color: #ff7a00; -fx-border-radius: 5");
        msg.setStyle(HomePage.buttonStyles);
        msg.setCursor(Cursor.HAND);
        buttonContainer.setPadding(new Insets(10, 10, 10, 0));
        nameLabel.setStyle("-fx-padding: 15");

        nameContainer.setMinWidth(300);
        buttonContainer.setMinWidth(300);

        buttonContainer.setAlignment(Pos.BOTTOM_RIGHT);
        msg.addEventHandler(MouseEvent.MOUSE_ENTERED, new HoverIn(msg));
        msg.addEventHandler(MouseEvent.MOUSE_EXITED, new HoverOut(msg));

        msg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Firestore db = FirestoreClient.getFirestore();
                Date date = new Date();
                String roomId = date.getTime() + "room";

                DocumentReference doc = db.collection("chat").document(roomId);
                Map<String, Object> data = new HashMap<>();

                ArrayList<HashMap<String, Object>> list = new ArrayList<>();
                HashMap<String, Object> dummy = new HashMap<>();

                dummy.put("uid", HelloApplication.userId);
                dummy.put("username", HelloApplication.userName);
                list.add(dummy);
                dummy = new HashMap<>();

                dummy.put("username", name);
                dummy.put("uid", uid);
                list.add(dummy);

                data.put("id", roomId);
                data.put("users", list);

                ApiFuture<WriteResult> result = doc.set(data);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        cp.getInboxList();
                        cp.leftVbox.getChildren().add(new InboxUsers(cp.chaddingPageScene, name, roomId, cp.chat, cp.messages, cp.send, cp.textArea, cp.scrollPane, cp.name));
                    }
                });

                stage.setScene(scene);
            }
        });
    }

}
