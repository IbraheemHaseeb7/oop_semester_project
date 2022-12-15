package com.example.semproject.Pages;

import com.example.semproject.Events.HoverIn;
import com.example.semproject.Events.HoverOut;
import com.example.semproject.Firebase.Auth;
import com.example.semproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignUpPage extends VBox {
    Stage stage = new Stage();
    Scene scene;

    public SignUpPage(String name, Stage stage1, Scene previousScene) {

        // creating layouts
        HBox headingContainer = new HBox();
        HBox headlineContainer = new HBox();
        HBox userNameContainer = new HBox();
        HBox passwordContainer = new HBox();
        HBox buttonContainer = new HBox();

        // creating components
        Label heading = new Label(name);
        Button button = new Button(name);
        Label headline = new Label("Experience the Era of Chadding with AI");
        TextField username = new TextField();
        PasswordField password = new PasswordField();

        // setting components
        headingContainer.getChildren().add(heading);
        headlineContainer.getChildren().add(headline);
        userNameContainer.getChildren().add(username);
        passwordContainer.getChildren().add(password);
        buttonContainer.getChildren().add(button);

        this.getChildren().addAll(headingContainer, headlineContainer, userNameContainer, passwordContainer, buttonContainer);

        // creating scenes
        scene = new Scene(this, 350, 500);
        stage.setScene(scene);
        stage.setTitle("Sign Up");
        stage.show();

        // styling
        headingContainer.setMinWidth(350);
        headlineContainer.setMinWidth(350);
        userNameContainer.setMinWidth(350);
        passwordContainer.setMinWidth(350);
        buttonContainer.setMinWidth(350);

        headingContainer.setAlignment(Pos.CENTER);
        headlineContainer.setAlignment(Pos.CENTER);
        userNameContainer.setAlignment(Pos.CENTER);
        passwordContainer.setAlignment(Pos.CENTER);
        buttonContainer.setAlignment(Pos.CENTER);

        heading.setStyle("-fx-padding: 40 0 40 0; -fx-font-size: 35; -fx-font-weight: 700");
        headline.setStyle("-fx-padding: 20 0 20 0; -fx-font-size: 12;");
        userNameContainer.setPadding(new Insets(10));
        passwordContainer.setPadding(new Insets(10));
        buttonContainer.setPadding(new Insets(10));

        username.setPromptText("Enter your unique username");
        password.setPromptText("Enter your password");

        username.setMinWidth(250);
        username.setStyle("-fx-padding: 10; -fx-border-width: 1; -fx-border-color: #42a4ff; -fx-border-radius: 5");
        password.setMinWidth(250);
        password.setStyle("-fx-padding: 10; -fx-border-width: 1; -fx-border-color: #42a4ff; -fx-border-radius: 5");
        button.setStyle(HomePage.buttonStyles);
        button.setCursor(Cursor.HAND);

        this.setStyle("-fx-background-color: #fff");

        // handling events

        button.addEventHandler(MouseEvent.MOUSE_ENTERED, new HoverIn(button));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, new HoverOut(button));

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                button.setText("Working on it...");
                ChaddingPage cp;
                if (name.equals("Sign Up")) {
                    new Auth().createAccount(username, password);
                     cp = new ChaddingPage(stage1, previousScene);
                } else {
                    new Auth().login(username, password);
                     cp = new ChaddingPage(stage1, previousScene);
                }
                if (HelloApplication.isAccount) stage1.setScene(cp.chaddingPageScene);
                stage.close();
            }
        });
    }
}
