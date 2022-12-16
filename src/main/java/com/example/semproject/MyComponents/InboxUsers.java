package com.example.semproject.MyComponents;

import com.example.semproject.HelloApplication;
import com.example.semproject.Pages.ChaddingPage;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InboxUsers extends Button {

    String uid;
    VBox container;
    Button send = new Button();
    TextArea textArea = new TextArea();
    ScrollPane scrollPane = new ScrollPane();
    Label nameLabel = new Label();
    public InboxUsers(Scene scene, String name, String uid, ArrayList<HashMap<String, Object>> messages, VBox contain, Button send, TextArea textArea, ScrollPane scrollPane, Label nameLabel) {

        this.nameLabel = nameLabel;
        this.messages = messages;
        this.uid = uid;
        this.container = contain;
        this.send = send;
        this.textArea = textArea;
        this.scrollPane = scrollPane;

        this.setText(name);
        this.setId(uid);
        this.setMinWidth(scene.getWidth() * 0.22);
        this.setStyle("-fx-padding: 10; -fx-border-width: 1; -fx-border-color: #001aff; -fx-background-color: #fff; -fx-border-radius: 5");
        this.setCursor(Cursor.HAND);

        sendMessages();


        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                nameLabel.setText(name);
                getMessages();
                getRealTimeMessages();
            }
        });
    }

    Firestore db = FirestoreClient.getFirestore();
    ArrayList<HashMap<String, Object>> messages;


    public ArrayList<HashMap<String, Object>> getMessages() {

        ApiFuture<QuerySnapshot> q = db.collection("chat/" + uid + "/messages").get();
        messages = new ArrayList<>();

        try {
            int counter = 0;
            for (QueryDocumentSnapshot d:q.get().getDocuments()) {
                if (q.get().getDocuments().size() - 1 == counter++) break;
                messages.add((HashMap<String, Object>) d.getData());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // adding messages
                for (HashMap<String, Object> d:messages) {
                    container.getChildren().add(new Messages((String)d.get("body"), (String)d.get("uid")));
                }
            }
        });


        return messages;
    }

    public void getRealTimeMessages() {
        // getting data from the database
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference ref = db.collection("chat/" + uid + "/messages");

        ref.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirestoreException e) {
                if (e != null) {
                    System.out.println("Listen Failed");
                    return;
                }

                assert queryDocumentSnapshots != null;
                Map<String, Object> data = queryDocumentSnapshots.getDocuments().get(queryDocumentSnapshots.getDocuments().size() - 1).getData();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        container.getChildren().add(new Messages((String)data.get("body"), (String)data.get("uid")));
                        scrollPane.setVvalue(2);
                    }
                });
            }
        });
    }

    public void sendMessages() {
        // sending data to the database
        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Firestore db = FirestoreClient.getFirestore();
                Date date = new Date();
//                int id = date.getTime()

                DocumentReference doc = db.collection("chat/" + uid + "/messages").document(+date.getTime() + "abcd");
                Map<String, String> data = new HashMap<>();

                data.put("body", textArea.getText());
                data.put("uid", HelloApplication.userId);
                data.put("id", date.getTime() + "");

                ApiFuture<WriteResult> result = doc.set(data);
                textArea.setText("");
            }
        });
    }
}
