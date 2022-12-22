package com.example.semproject.Pages;

import com.example.semproject.Events.HoverIn;
import com.example.semproject.Events.HoverOut;
import com.example.semproject.Events.LogoutHoverOut;
import com.example.semproject.Events.LogutHoverIn;
import com.example.semproject.HelloApplication;
import com.example.semproject.MyComponents.InboxUsers;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.Callable;

public class ChaddingPage extends BorderPane {
    // ==================================
    public ArrayList<HashMap<String, Object>> chat = new ArrayList<>();
    public ArrayList<HashMap<Object, Object>> list = new ArrayList<>();
    public Firestore db = FirestoreClient.getFirestore();

    public int getInboxList() {
        // inbox data
        CollectionReference chats = db.collection("chat");
        Query q = chats.whereArrayContains("users", new HashMap<String, Object>() { { put("uid", HelloApplication.userId); put("username", HelloApplication.userName); } });
        ApiFuture<QuerySnapshot> inboxChats = q.get();

        try {
            for (QueryDocumentSnapshot d:inboxChats.get()) {

                HashMap<String, Object> temp = (HashMap<String, Object>) d.getData();
                list.add(new HashMap<Object, Object>() {
                    {
                        if (HelloApplication.userId.equals(((HashMap)((ArrayList)temp.get("users")).get(0)).get("uid"))) {
                            put("name", ((HashMap)((ArrayList)temp.get("users")).get(1)).get("username"));
                            put("uid", ((HashMap)((ArrayList)temp.get("users")).get(1)).get("uid"));
                            put("room", temp.get("id"));
                        } else {
                            put("name", ((HashMap)((ArrayList)temp.get("users")).get(0)).get("username"));
                            put("uid", ((HashMap)((ArrayList)temp.get("users")).get(0)).get("uid"));
                            put("room", temp.get("id"));
                        }
                    }
                });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.println("i was called");

        return 0;
    }

    public Scene chaddingPageScene;
    public Label welcomeLabel = new Label("Welcome back, ");
    public Label name = new Label("Name");
    public TextArea textArea = new TextArea();
    public VBox leftVbox = new VBox();
    public ScrollPane scrollPane = new ScrollPane();
    public VBox messages = new VBox();
    public Button send = new Button("Send");



    public ChaddingPage(Stage stage, Scene scene){

        chaddingPageScene = new Scene(this, 800, 500);
        this.setStyle("-fx-background-color: #fff");
        // Layouts Declarations
        HBox topHbox = new HBox();
        HBox welcomeContainer = new HBox();
        HBox buttonsContainer = new HBox();
        HBox centerHbox = new HBox();
        VBox rightVbox = new VBox();
        HBox nameContainer = new HBox();
        HBox sendContainer = new HBox();

        // Components
        Button back = new Button("Back");
        Button profile = new Button("Profile");
        Button search = new Button("Search");
        Button logout = new Button("Logout");

        // Adding components to layouts
        welcomeContainer.getChildren().add(welcomeLabel);
        buttonsContainer.getChildren().addAll(back, search, profile, logout);
        topHbox.getChildren().addAll(welcomeContainer, buttonsContainer);

        centerHbox.getChildren().addAll(leftVbox, rightVbox);
        rightVbox.getChildren().addAll(nameContainer, scrollPane, sendContainer);
        nameContainer.getChildren().add(name);
        sendContainer.getChildren().addAll(textArea, send);

        scrollPane.setContent(messages);

        this.setTop(topHbox);
        this.setCenter(centerHbox);

        // Styling
        topHbox.setMinWidth(chaddingPageScene.getWidth());
        welcomeContainer.setMinWidth(800*0.5);
        buttonsContainer.setMinWidth(800*0.5);
        welcomeLabel.setStyle("-fx-padding: 20; -fx-font-weight: 700; -fx-font-size: 20");
        back.setStyle(HomePage.buttonStyles);
        search.setStyle(HomePage.buttonStyles);
        profile.setStyle(HomePage.buttonStyles);
        search.setCursor(Cursor.HAND);
        logout.setCursor(Cursor.HAND);
        back.setCursor(Cursor.HAND);
        profile.setCursor(Cursor.HAND);
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

        ChaddingPage cp = this;

        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Search search1 = new Search(stage, chaddingPageScene, cp);
            }
        });

        logout.addEventHandler(MouseEvent.MOUSE_ENTERED, new LogutHoverIn(logout));
        logout.addEventHandler(MouseEvent.MOUSE_EXITED, new LogoutHoverOut(logout));
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HelloApplication.isAccount = false;
                stage.setScene(scene);
            }
        });

        profile.addEventHandler(MouseEvent.MOUSE_ENTERED, new HoverIn(profile));
        profile.addEventHandler(MouseEvent.MOUSE_EXITED, new HoverOut(profile));

        profile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ProfilePage page = new ProfilePage(stage, chaddingPageScene, true, HelloApplication.userName);
            }
        });

        name.setCursor(Cursor.HAND);
        name.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ProfilePage page = new ProfilePage(stage, chaddingPageScene, false, name.getText());
            }
        });
    }
}
