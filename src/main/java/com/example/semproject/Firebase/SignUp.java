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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Date;
import java.util.HashMap;

public class SignUp extends Thread {

    TextField username = new TextField();
    PasswordField password = new PasswordField();
    Stage stage;

    ChaddingPage cp;

    public SignUp(TextField username, PasswordField password, Stage stage, ChaddingPage cp) {
        this.username = username;
        this.password = password;
        this.stage = stage;
        this.cp = cp;
    }

    @Override
    public void run() {
        Firestore db = FirestoreClient.getFirestore();

        Date date = new Date();
        String roomId = date.getTime() + "uid";
        DocumentReference documentReference = db.collection("accounts").document(roomId);

        HashMap<String, String> data = new HashMap<>() {{
            put("uid", roomId);
            put("username", username.getText());
            put("password", password.getText());
        }};


        try {
            ApiFuture<WriteResult> result = documentReference.set(data);
            HelloApplication.userName = username.getText();
            HelloApplication.isAccount = true;
            HelloApplication.userId = roomId;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (HelloApplication.isAccount) {
//                    cp.getInboxList();
                    stage.setScene(cp.chaddingPageScene);
                    cp.welcomeLabel.setText("Welcome back, " + HelloApplication.userName);
//                    for (HashMap<Object, Object> d:cp.list) {
//                        cp.leftVbox.getChildren().add(new InboxUsers(cp.chaddingPageScene, (String)d.get("name"), (String)d.get("room"), cp.chat, cp.messages, cp.send, cp.textArea, cp.scrollPane, cp.name));
//                    }
                }
            }
        });
    }
}
