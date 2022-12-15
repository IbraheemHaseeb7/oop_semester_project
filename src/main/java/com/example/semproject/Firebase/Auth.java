package com.example.semproject.Firebase;

import com.example.semproject.HelloApplication;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class Auth {

    public void createAccount(TextField username, PasswordField password) {
        Firestore db = FirestoreClient.getFirestore();
        Date date = new Date();
//                int id = date.getTime()

        DocumentReference doc = db.collection("accounts").document(+date.getTime() + "uid");
        Map<String, String> data = new HashMap<>();

        String uid = date.getTime() + "uid";

        data.put("username", username.getText());
        data.put("password", password.getText());
        data.put("uid", uid);

        ApiFuture<WriteResult> result = doc.set(data);
        HelloApplication.isAccount = true;
        HelloApplication.userName = username.getText();
        HelloApplication.userId = uid;
    }

    public void login(TextField username, PasswordField password) {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection("accounts").whereEqualTo("username", username.getText()).get();

        try {
           if (future.get().getDocuments().size() == 1) {
               if (future.get().getDocuments().get(0).getData().get("password").equals(password.getText())) {
                   HelloApplication.userName = username.getText();
                   HelloApplication.isAccount = true;
                   HelloApplication.userId = (String)future.get().getDocuments().get(0).getData().get("uid");
               }
           }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
