package com.example.semproject.Pages;

import com.example.semproject.Events.HoverIn;
import com.example.semproject.Events.HoverOut;
import com.example.semproject.Events.SignupEventHandling;
import com.example.semproject.Firebase.Auth;
import com.google.firebase.auth.FirebaseAuthException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class HomePage extends BorderPane {

    public static String buttonStyles = "-fx-border-radius: 5px; -fx-background-radius: 5px; -fx-background-color: #60c6ff; -fx-text-fill: #fff; -fx-margin: 10px; -fx-font-size: 15";
    public static String buttonHoverStyles = "-fx-border-radius: 5px; -fx-background-radius: 5px; -fx-background-color: #14aaff; -fx-text-fill: #fff; -fx-margin: 10px; -fx-font-size: 15";

    public HomePage(Stage stage, Scene scene) {

        // containers for the layout
        HBox topContainer = new HBox();
        GridPane centerContainer = new GridPane();
        HBox bottomContainer = new HBox();

        // components
        Text top = new Text("Welcome to Chadding");
        Button signUp = new Button("Sign Up With Google");
        Button login = new Button("Log in With Google");
        Text bottom = new Text("Developed by\nIbraheem Bin Haseeb & Marhaba Eman Ahmad");

        // adding components to layouts
        topContainer.getChildren().add(top);

        centerContainer.add(signUp, 0, 0);
        centerContainer.add(login, 0, 1);

        bottomContainer.getChildren().add(bottom);

        this.setTop(topContainer);
        this.setCenter(centerContainer);
        this.setBottom(bottomContainer);

        // styles and effects
        topContainer.setMinWidth(scene.getWidth());
        topContainer.setAlignment(Pos.CENTER);
        topContainer.setPadding(new Insets(20));

        top.setStyle("-fx-font-size: 40; -fx-font-weight: 700; -fx-padding: 20; -fx-font-family: \"Inter\"");

        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.setMinHeight(300);
        centerContainer.setVgap(20);

        signUp.setStyle(buttonStyles);
        signUp.setMinWidth(200);
        signUp.setMinHeight(40);
        signUp.setCursor(Cursor.HAND);
        signUp.addEventHandler(MouseEvent.MOUSE_ENTERED, new HoverIn(signUp));
        signUp.addEventHandler(MouseEvent.MOUSE_EXITED, new HoverOut(signUp));

        login.setStyle(buttonStyles);
        login.setMinWidth(200);
        login.setMinHeight(40);
        login.setCursor(Cursor.HAND);
        login.addEventHandler(MouseEvent.MOUSE_ENTERED, new HoverIn(login));
        login.addEventHandler(MouseEvent.MOUSE_EXITED, new HoverOut(login));

        bottomContainer.setMinWidth(scene.getWidth());
        bottomContainer.setAlignment(Pos.CENTER);
        bottomContainer.setPadding(new Insets(40, 0, 0, 0));

        bottom.setTextAlignment(TextAlignment.CENTER);


        ChaddingPage cp = new ChaddingPage(stage, scene);
        // event handling
        signUp.setOnAction(new SignupEventHandling(stage, cp.chaddingPageScene));
        login.setOnAction(new SignupEventHandling(stage, cp.chaddingPageScene));

        // creating account
        signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SignUpPage signUpPage = new SignUpPage("Sign Up", stage, cp.chaddingPageScene);
            }
        });
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SignUpPage signUpPage = new SignUpPage("Log In", stage, cp.chaddingPageScene);
            }
        });
    }
}
