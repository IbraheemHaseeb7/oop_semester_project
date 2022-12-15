package com.example.semproject;

import com.example.semproject.Firebase.Auth;
import com.example.semproject.Pages.HomePage;
import com.example.semproject.AnimationComponents.ShapesComponents;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class HelloApplication extends Application {

    public static String userId = null;
    public static String userName = null;
    public static boolean isAccount = false;

    @Override
    public void start(Stage stage) {
        // setting up firebase
        try {
            FileInputStream fileInputStream = new FileInputStream("secret.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(fileInputStream))
                    .build();
            FirebaseApp fb = FirebaseApp.initializeApp(options);

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }

        // creating layouts
        Group group = new Group();
        Scene scene = new Scene(group, 800, 500);
        scene.setFill(Color.WHITE);
        stage.setTitle("Hello!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        // adding components to layouts
        group.getChildren().addAll(new ShapesComponents(), new HomePage(stage, scene));
    }

    public static void main(String[] args) {
        launch();
    }
}