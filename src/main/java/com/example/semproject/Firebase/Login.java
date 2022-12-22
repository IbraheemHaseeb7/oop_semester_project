package com.example.semproject.Firebase;

import com.example.semproject.HelloApplication;
import com.example.semproject.MyComponents.InboxUsers;
import com.example.semproject.Pages.ChaddingPage;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Login extends Thread {

    TextField username = new TextField();
    PasswordField password = new PasswordField();
    Stage stage;

    ChaddingPage cp;

    public Login(TextField username, PasswordField password, Stage stage, ChaddingPage cp) {
        this.username = username;
        this.password = password;
        this.stage = stage;
        this.cp = cp;
    }

    @Override
    public void run() {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection("accounts").whereEqualTo("username", username.getText()).get();

        try {
            if (future.get().getDocuments().size() == 1) {
                if (future.get().getDocuments().get(0).getData().get("password").equals(password.getText())) {
                    HelloApplication.userName = username.getText();
                    HelloApplication.isAccount = true;
                    HelloApplication.userId = (String)future.get().getDocuments().get(0).getData().get("uid");
                    HelloApplication.bio = (String)future.get().getDocuments().get(0).getData().get("bio");
                    HelloApplication.email = (String)future.get().getDocuments().get(0).getData().get("email");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (HelloApplication.isAccount) {
                    cp.getInboxList();
                    stage.setScene(cp.chaddingPageScene);
                    cp.welcomeLabel.setText("Welcome back, " + HelloApplication.userName);
                    for (HashMap<Object, Object> d:cp.list) {
                        cp.leftVbox.getChildren().add(new InboxUsers(cp.chaddingPageScene, (String)d.get("name"), (String)d.get("room"), cp.chat, cp.messages, cp.send, cp.textArea, cp.scrollPane, cp.name));
                    }
                }
            }
        });
    }
}
