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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class SignUp extends Thread {

    TextField username = new TextField();
    TextField email = new TextField();
    PasswordField password = new PasswordField();
    Stage stage;
    Stage signUpStage;
    Label warning;
    TextArea bio;
    ChaddingPage cp;

    public SignUp(TextField username, PasswordField password, Stage stage, ChaddingPage cp, Stage signUpStage, Label warning, TextArea bio, TextField email) {
        this.username = username;
        this.password = password;
        this.stage = stage;
        this.cp = cp;
        this.bio = bio;
        this.email = email;
        this.warning = warning;
        this.signUpStage = signUpStage;
    }

    @Override
    public void run() {
        Firestore db = FirestoreClient.getFirestore();

        Date date = new Date();
        String roomId = date.getTime() + "uid";

        ApiFuture<QuerySnapshot> q = db.collection("accounts").whereEqualTo("username", username.getText()).get();
        try {
            if (q.get().getDocuments().size() == 1) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        warning.setText("This username already exists...");
                        username.setText("");
                    }
                });
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        DocumentReference documentReference = db.collection("accounts").document(roomId);

        HashMap<String, String> data = new HashMap<>() {{
            put("uid", roomId);
            put("username", username.getText());
            put("password", password.getText());
            put("bio", bio.getText());
            put("email", email.getText());
        }};


        try {
            ApiFuture<WriteResult> result = documentReference.set(data);
            HelloApplication.userName = username.getText();
            HelloApplication.email = email.getText();
            HelloApplication.bio = bio.getText();
            HelloApplication.isAccount = true;
            HelloApplication.userId = roomId;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (HelloApplication.isAccount) {
                    stage.setScene(cp.chaddingPageScene);
                    cp.welcomeLabel.setText("Welcome back, " + HelloApplication.userName);
                    signUpStage.close();
                }
            }
        });
    }
}
