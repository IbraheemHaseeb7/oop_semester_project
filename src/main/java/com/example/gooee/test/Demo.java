package com.example.gooee.test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Scanner;

public class Demo extends Application {

    Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();
        Button signin = new Button();
        signin.setText("Sign In");
        Button signup = new Button();
        signup.setText("Sign Up");

        Label label = new Label("WELCOME TO CHADDING");
        label.setFont(new Font("Times New Roman", 25));
        label.setPadding(new Insets(20));
        HBox hBox = new HBox();
        bp.setTop(hBox);
        hBox.getChildren().add(label);
        hBox.setAlignment(Pos.CENTER);

        Label label2 = new Label();
        label2.setText("Google Authentication");
        label2.setFont(new Font("Times New Roman", 15));
        bp.setCenter(gp);
        gp.add(label2, 0, 0);
        gp.add(signin, 0, 1);
        gp.add(signup, 0, 2);
        signin.setMaxWidth(Double.MAX_VALUE); // grow
        signup.setMaxWidth(Double.MAX_VALUE); // grow

        gp.setVgap(10);
        gp.setHgap(10);
        gp.setAlignment(Pos.CENTER);

        // Creating all scenes
        scene = new Scene(bp, 500, 500);
        Inbox inboxPage = new Inbox(stage, scene);

        // Event handling
        signin.setOnAction(new SignInEventHandling(stage, inboxPage));
        signup.setOnAction(new SignUpEventHandling(stage, inboxPage));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
