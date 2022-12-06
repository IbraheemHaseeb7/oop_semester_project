package com.example.gooee.test;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Scanner;

public class Demo extends Application {

    // Strings for Styling
    static String themeColor = "eaeaea";
    static String fontColors = "-fx-text-fill: #fff;";
    static String buttonColors = "-fx-border-insets: 5px; -fx-background-insets: 5px;  -fx-background-color: #b3b3b3; -fx-text-fill: #000; -fx-border-width: 3; -fx-border-color: #" + themeColor;
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
        HBox hBox = new HBox();
        bp.setTop(hBox);
        hBox.getChildren().add(label);
        hBox.setAlignment(Pos.CENTER);

        Label label2 = new Label();
        label2.setText("Google Authentication");
        bp.setCenter(gp);
        gp.add(label2, 0, 0);
        gp.add(signin, 0, 1);
        gp.add(signup, 0, 2);
        signin.setMaxWidth(Double.MAX_VALUE); // grow
        signup.setMaxWidth(Double.MAX_VALUE); // grow

        Label developers = new Label("Developed by\nIbraheem Bin Haseeb & Marhaba Eman Ahmad");

        ColorPicker colorPicker = new ColorPicker();
        HBox colorPickerContainer = new HBox();
        colorPickerContainer.getChildren().add(colorPicker);

        bp.setBottom(colorPickerContainer);
        bp.setStyle("-fx-background-color: #000");

        gp.setVgap(10);
        gp.setHgap(10);
        gp.setAlignment(Pos.CENTER);

        // applying styling here
        label.setFont(new Font("Times New Roman", 25));
        label.setStyle(fontColors);
        label.setPadding(new Insets(20));

        label2.setFont(new Font("Times New Roman", 15));
        label2.setStyle(fontColors);

        developers.setStyle("-fx-text-fill: #b3b3b3");
        developers.setPadding(new Insets(10));
        developers.setTextAlignment(TextAlignment.CENTER);

        colorPicker.setMinWidth(30);
        colorPicker.setMaxWidth(30);

        signin.setStyle(buttonColors);
        signin.setCursor(Cursor.HAND);
        signup.setStyle(buttonColors);
        signup.setCursor(Cursor.HAND);

        // hover styling
        signin.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                // resetting styles
                signin.setStyle(null);

                buttonColors = "-fx-border-insets: 5px; -fx-background-insets: 5px;  -fx-background-color: #b3b3b3; -fx-text-fill: #000; -fx-border-width: 3; -fx-border-color: #" + themeColor;

                // reapplying styles
                signin.setStyle(buttonColors);
            }
        });
        signup.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                // resetting styles
                signup.setStyle(null);

                buttonColors = "-fx-border-insets: 5px; -fx-background-insets: 5px;  -fx-background-color: #b3b3b3; -fx-text-fill: #000; -fx-border-width: 3; -fx-border-color: #" + themeColor;

                // reapplying styles
                signup.setStyle(buttonColors);
            }
        });

        signin.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                // resetting styles
                signin.setStyle(null);

                buttonColors = "-fx-border-insets: 5px; -fx-background-insets: 5px;  -fx-background-color: #b3b3b3; -fx-text-fill: #000; -fx-border-width: 3; -fx-border-color: #" + themeColor;

                // reapplying styles
                signin.setStyle(buttonColors);
            }
        });
        signup.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                // resetting styles
                signup.setStyle(null);

                buttonColors = "-fx-border-insets: 5px; -fx-background-insets: 5px;  -fx-background-color: #b3b3b3; -fx-text-fill: #000; -fx-border-width: 3; -fx-border-color: #" + themeColor;

                // reapplying styles
                signup.setStyle(buttonColors);
            }
        });

        // Creating all scenes
        scene = new Scene(bp, 500, 500);
        Inbox inboxPage = new Inbox(stage, scene);

        HBox bottomBox = new HBox();
        bottomBox.setMinWidth(500);
        HBox developersContainer = new HBox();
        HBox leftContainer = new HBox();
        leftContainer.setMinWidth(50);

        // screen resize listener
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                developersContainer.setMinWidth(scene.getWidth() - 100);
            }
        });

        developersContainer.setMinWidth(scene.getWidth() - 100);
        developersContainer.setAlignment(Pos.CENTER);
        developersContainer.getChildren().add(developers);
        bottomBox.getChildren().addAll(developersContainer, colorPickerContainer);
        bottomBox.setMinWidth(500);
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        colorPickerContainer.setMinWidth(50);
        colorPickerContainer.setAlignment(Pos.BOTTOM_RIGHT);
        colorPickerContainer.setPadding(new Insets(10));
        bp.setBottom(bottomBox);

        // Event handling
        signin.setOnAction(new SignInEventHandling(stage, inboxPage));
        signup.setOnAction(new SignUpEventHandling(stage, inboxPage));
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                // resetting styles
                signin.setStyle(null);
                signup.setStyle(null);

                themeColor = "";
                for (int counter = 3; counter < 9; counter++) {
                    themeColor = themeColor + colorPicker.getCustomColors().toString().toCharArray()[counter];
                }

                buttonColors = "-fx-background-color: #b3b3b3; -fx-text-fill: #000; -fx-border-width: 3; -fx-border-color: #" + themeColor;

                // reapplying styles
                signin.setStyle(buttonColors);
                signup.setStyle(buttonColors);
            }
        });


        stage.setScene(scene);
        stage.setTitle("Chadding Application");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
