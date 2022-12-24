package com.example.semproject.Pages;

import com.example.semproject.AnimationComponents.Popup;
import com.example.semproject.Events.HoverIn;
import com.example.semproject.Events.HoverOut;
import com.example.semproject.Firebase.Auth;
import com.example.semproject.Firebase.Login;
import com.example.semproject.Firebase.SignUp;
import com.example.semproject.HelloApplication;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;

public class SignUpPage extends VBox {
    Stage stage = new Stage();
    Scene scene;
    Image image = new Image(new FileInputStream("profile.png"));
    ImageView imageView = new ImageView(image);
    File file;
    public SignUpPage(String name, Stage stage1, Scene previousScene) throws FileNotFoundException {

        // creating layouts
        HBox headingContainer = new HBox();
        HBox headlineContainer = new HBox();
        HBox userNameContainer = new HBox();
        HBox emailContainer = new HBox();
        HBox passwordContainer = new HBox();
        HBox buttonContainer = new HBox();
        HBox warningContainer = new HBox();
        HBox bioContainer = new HBox();
        HBox pictureContainer = new HBox();

        // creating components
        Label heading = new Label(name);
        Button button = new Button(name);
        Label headline = new Label("Experience the Era of Chadding with AI");
        TextField username = new TextField();
        TextField email = new TextField();
        PasswordField password = new PasswordField();
        Label warning = new Label("");
        Label forgot = new Label("Forgot password?");
        TextArea bio = new TextArea();
        Stage fileStage = new Stage();
        FileChooser fileChooser = new FileChooser();

        // setting components
        headingContainer.getChildren().add(heading);
        headlineContainer.getChildren().add(headline);
        userNameContainer.getChildren().add(username);
        emailContainer.getChildren().add(email);
        passwordContainer.getChildren().add(password);
        buttonContainer.getChildren().addAll(forgot, button);
        warningContainer.getChildren().add(warning);
        bioContainer.getChildren().add(bio);
        pictureContainer.getChildren().add(imageView);

        if (name.equals("Sign Up")) this.getChildren().addAll(headingContainer, pictureContainer, headlineContainer, userNameContainer, emailContainer, passwordContainer, bioContainer, buttonContainer, warningContainer);
        else this.getChildren().addAll(headingContainer, headlineContainer, userNameContainer, passwordContainer, buttonContainer, warningContainer);

        // creating scenes
        scene = new Scene(this, 350, 500);
        stage.setScene(scene);
        stage.setTitle("Sign Up");
        stage.show();

        // styling
        headingContainer.setMinWidth(350);
        headlineContainer.setMinWidth(350);
        userNameContainer.setMinWidth(350);
        emailContainer.setMinWidth(350);
        passwordContainer.setMinWidth(350);
        buttonContainer.setMinWidth(350);
        bioContainer.setMinWidth(350);
        warningContainer.setMinWidth(350);
        pictureContainer.setMinWidth(350);

        headingContainer.setAlignment(Pos.CENTER);
        headlineContainer.setAlignment(Pos.CENTER);
        userNameContainer.setAlignment(Pos.CENTER);
        emailContainer.setAlignment(Pos.CENTER);
        passwordContainer.setAlignment(Pos.CENTER);
        buttonContainer.setAlignment(Pos.CENTER);
        bioContainer.setAlignment(Pos.CENTER);
        warningContainer.setAlignment(Pos.CENTER);
        pictureContainer.setAlignment(Pos.CENTER);

        if (name.equals("Sign Up")) heading.setStyle("-fx-padding: 20 0 20 0; -fx-font-size: 35; -fx-font-weight: 700");
        else heading.setStyle("-fx-padding: 40 0 40 0; -fx-font-size: 35; -fx-font-weight: 700");
        headline.setStyle("-fx-padding: 20 0 20 0; -fx-font-size: 12;");
        warning.setStyle("-fx-padding: 10; -fx-text-fill: #ff0000");
        userNameContainer.setPadding(new Insets(10));
        emailContainer.setPadding(new Insets(10));
        passwordContainer.setPadding(new Insets(10));
        buttonContainer.setPadding(new Insets(10));
        bioContainer.setPadding(new Insets(10));
        warningContainer.setPadding(new Insets(10));

        username.setPromptText("Enter your unique username");
        email.setPromptText("Enter your email");
        password.setPromptText("Enter your password");
        bio.setPromptText("Tell us something about yourself...");

        username.setMinWidth(250);
        username.setStyle("-fx-padding: 10; -fx-border-width: 1; -fx-border-color: #42a4ff; -fx-border-radius: 5");
        email.setMinWidth(250);
        email.setStyle("-fx-padding: 10; -fx-border-width: 1; -fx-border-color: #42a4ff; -fx-border-radius: 5");
        password.setMinWidth(250);
        password.setStyle("-fx-padding: 10; -fx-border-width: 1; -fx-border-color: #42a4ff; -fx-border-radius: 5");
        bio.setMinWidth(250);
        bio.setMaxWidth(250);
        bio.setWrapText(true);
        bio.setStyle("-fx-border-width: 1; -fx-border-color: #42a4ff; -fx-border-radius: 5");
        button.setStyle(HomePage.buttonStyles);
        button.setCursor(Cursor.HAND);
        forgot.setCursor(Cursor.HAND);
        forgot.setStyle("-fx-padding: 5; -fx-text-fill: #ff0000;");

        if (name.equals("Sign Up")) {
            forgot.setVisible(false);
            forgot.setText("");
        }

        imageView.setFitHeight(75);
        imageView.setFitWidth(75);
        imageView.setCursor(Cursor.HAND);

        this.setStyle("-fx-background-color: #fff");

        // handling events

        button.addEventHandler(MouseEvent.MOUSE_ENTERED, new HoverIn(button));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, new HoverOut(button));

        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                file = fileChooser.showOpenDialog(fileStage);
                if (file != null) {
                    try {
                        image = new Image(new FileInputStream(file.getAbsolutePath()));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    imageView.setPreserveRatio(true);
                    imageView.setImage(image);
                }
            }
        });

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        ChaddingPage cp = new ChaddingPage(stage1, previousScene);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // warnings
                if (username.getText().toCharArray().length == 0) {
                    warning.setText("Enter username first please");
                } else if (password.getText().toCharArray().length < 8) {
                    warning.setText("Password should be greater than 8");
                    password.setText("");
                }
                else {
                    warning.setText("");
                    if (name.equals("Sign Up")) {
                        if (bio.getText().toCharArray().length == 0) {
                            warning.setText("Write a little about yourself...");
                            return;
                        } else if (!pattern.matcher(email.getText()).matches()){
                            warning.setText("Not a valid Email");
                            email.setText("");
                            return;
                        }
                        SignUp signUp = new SignUp(username, password, stage1, cp, stage, warning, bio, email);
                        signUp.start();
                        HelloApplication.group.getChildren().add(new Popup("waiting for server"));
                    } else {
                        Login login = new Login(username, password, stage1, cp);
                        login.start();
                        stage.close();
                        HelloApplication.group.getChildren().add(new Popup("waiting for server"));
                    }
                }
            }
        });

        forgot.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
    }
}
