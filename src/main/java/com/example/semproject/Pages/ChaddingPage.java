package com.example.semproject.Pages;

import com.example.semproject.Events.HoverIn;
import com.example.semproject.Events.HoverOut;
import com.example.semproject.HelloApplication;
import com.example.semproject.MyComponents.Messages;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.EventListener;
import com.google.firebase.cloud.FirestoreClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class ChaddingPage extends BorderPane {
    // ==================================
    ArrayList<HashMap<Object, Object>> list = new ArrayList<>();
    HashMap<Object, Object> dummy = new HashMap<>();
    ArrayList<HashMap<String, Object>> messages = new ArrayList<>();

    {
        // inbox data
        for (int counter = 0; counter < 4; counter++) {
            dummy.put("name", "Rida" + counter);
            dummy.put("uid", "123" + counter);
            list.add(dummy);
            dummy = new HashMap<>();
        }

        // messages data
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("chat").get();

        try {
            int counter = 0;
            for (QueryDocumentSnapshot d:future.get().getDocuments()) {
                if (future.get().getDocuments().size() - 1 == counter++) break;
                messages.add((HashMap<String, Object>) d.getData());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ==================================

    Scene chaddingPageScene;
    public ChaddingPage(Stage stage, Scene scene){

        chaddingPageScene = new Scene(this, 800, 500);
        this.setStyle("-fx-background-color: #fff");
        // Layouts Declarations
        HBox topHbox = new HBox();
        HBox welcomeContainer = new HBox();
        HBox buttonsContainer = new HBox();
        HBox centerHbox = new HBox();
        VBox leftVbox = new VBox();
        VBox rightVbox = new VBox();
        HBox nameContainer = new HBox();
        HBox sendContainer = new HBox();
        ScrollPane scrollPane = new ScrollPane();
        VBox messages = new VBox();

        // Components
        Label welcomeLabel = new Label("Welcome Back, " + HelloApplication.userName);
        Button back = new Button("Back");
        Button search = new Button("Search");
        Button logout = new Button("Logout");
        Label name = new Label("Name");
        TextArea textArea = new TextArea();
        Button send = new Button("Send");

        // Adding components to layouts
        welcomeContainer.getChildren().add(welcomeLabel);
        buttonsContainer.getChildren().addAll(back, search, logout);
        topHbox.getChildren().addAll(welcomeContainer, buttonsContainer);

        centerHbox.getChildren().addAll(leftVbox, rightVbox);
        rightVbox.getChildren().addAll(nameContainer, scrollPane, sendContainer);
        nameContainer.getChildren().add(name);
        sendContainer.getChildren().addAll(textArea, send);

        for (HashMap<Object, Object> d:list) {
            Button b = new Button((String)d.get("name"));
            b.setMinWidth(chaddingPageScene.getWidth() * 0.22);
            b.setStyle("-fx-padding: 10; -fx-border-width: 1; -fx-border-color: #001aff; -fx-background-color: #fff; -fx-border-radius: 5");
            b.setCursor(Cursor.HAND);
            leftVbox.getChildren().add(b);
        }

        for (HashMap<String, Object> d:this.messages) {
            messages.getChildren().add(new Messages((String)d.get("body"), (String)d.get("uid")));
        }

        scrollPane.setContent(messages);

        this.setTop(topHbox);
        this.setCenter(centerHbox);

        // Styling
        topHbox.setMinWidth(chaddingPageScene.getWidth());
        welcomeContainer.setMinWidth(800*0.4);
        buttonsContainer.setMinWidth(800*0.6);
        welcomeLabel.setStyle("-fx-padding: 20; -fx-font-weight: 700; -fx-font-size: 25");
        back.setStyle(HomePage.buttonStyles);
        search.setStyle(HomePage.buttonStyles);
        search.setCursor(Cursor.HAND);
        logout.setCursor(Cursor.HAND);
        back.setCursor(Cursor.HAND);
        logout.setStyle("-fx-border-radius: 5px; -fx-background-radius: 5px; -fx-background-color: #ff6060; -fx-text-fill: #fff; -fx-margin: 10px; -fx-font-size: 15");
        topHbox.setStyle("-fx-border-width: 1; -fx-border-color: #ff0000; -fx-border-style: hidden hidden solid hidden;");

        centerHbox.setMinWidth(chaddingPageScene.getWidth());
        leftVbox.setMinWidth(chaddingPageScene.getWidth()*0.25);
        leftVbox.setSpacing(10);
        leftVbox.setPadding(new Insets(10));
        rightVbox.setMinWidth(chaddingPageScene.getWidth()*0.75);
        buttonsContainer.setAlignment(Pos.TOP_RIGHT);
        buttonsContainer.setPadding(new Insets(20));
        buttonsContainer.setSpacing(10);
        leftVbox.setStyle("-fx-border-width: 1; -fx-border-color: #ff0000; -fx-border-style: hidden solid hidden hidden;");

        name.setStyle("-fx-padding: 10; -fx-font-weight: 700; -fx-font-size: 20;");
        nameContainer.setStyle("-fx-border-width: 1; -fx-border-color: #ff0000; -fx-border-style: hidden hidden solid hidden");

        scrollPane.setMinHeight(315);
        textArea.setWrapText(true);
        textArea.setPromptText("Type your message here");
        rightVbox.setStyle("-fx-background-color: white");

        sendContainer.setAlignment(Pos.BOTTOM_LEFT);
        send.setStyle("-fx-background-color: #fff; -fx-border-width: 1; -fx-border-color: #000; -fx-border-radius: 5; -fx-padding: 10 18 10 18");
        send.setCursor(Cursor.HAND);
        sendContainer.setSpacing(10);
        sendContainer.setMinWidth(chaddingPageScene.getWidth() * 0.75);
        sendContainer.setStyle("-fx-padding: 10; -fx-border-width: 1; -fx-border-style: solid hidden hidden hidden; -fx-border-color: #ff0000");
        textArea.setMinWidth(chaddingPageScene.getWidth() * 0.63);
        textArea.setStyle("-fx-background-color: #fff; -fx-border-width: 1; -fx-border-color: #000; -fx-border-radius: 5;");

        // event handling
        back.addEventHandler(MouseEvent.MOUSE_ENTERED, new HoverIn(back));
        back.addEventHandler(MouseEvent.MOUSE_EXITED, new HoverOut(back));
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(scene);
            }
        });

        search.addEventHandler(MouseEvent.MOUSE_ENTERED, new HoverIn(search));
        search.addEventHandler(MouseEvent.MOUSE_EXITED, new HoverOut(search));

        logout.addEventHandler(MouseEvent.MOUSE_ENTERED, new HoverIn(logout));
        logout.addEventHandler(MouseEvent.MOUSE_EXITED, new HoverOut(logout));
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HelloApplication.isAccount = false;
                stage.setScene(scene);
            }
        });

        // sending data to the database
        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Firestore db = FirestoreClient.getFirestore();
                Date date = new Date();
//                int id = date.getTime()

                DocumentReference doc = db.collection("chat").document(+date.getTime() + "abcd");
                Map<String, String> data = new HashMap<>();

                data.put("body", textArea.getText());
                data.put("uid", HelloApplication.userId);
                data.put("id", date.getTime() + "");

                ApiFuture<WriteResult> result = doc.set(data);
                textArea.setText("");
            }
        });


        // getting data from the database
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference ref = db.collection("chat");

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
                        messages.getChildren().add(new Messages((String)data.get("body"), (String)data.get("uid")));
                        scrollPane.setVvalue(2);
                    }
                });
            }
        });
    }
}
