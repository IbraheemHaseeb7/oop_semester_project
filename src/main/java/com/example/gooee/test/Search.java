package com.example.gooee.test;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class Search extends Page{

    public Search(Stage stage, Scene scene) {
        super(stage, scene);
    }

    ArrayList<HashMap<Object, Object>> dataArray = new ArrayList<>();
    HashMap<Object, Object> dummy = new HashMap<>();

    {
        dummy.put("uid", "123");
        dummy.put("name", "Zarhaba");

        dataArray.add(dummy);
        dummy = new HashMap<>(); // create a new hashmap

        dummy.put("uid", "234");
        dummy.put("name", "Zibraheem");

        dataArray.add(dummy);
        System.out.println(dataArray);
    }

    @Override
    public void display() {
        TextField textField = new TextField();
        textField.setPromptText("Search usernames");
        Button searchButton = new Button("Search");
        Button backButton = new Button("Back Button");
        BorderPane bp = new BorderPane();
        HBox hBox = new HBox();
        HBox textHbox = new HBox();
        HBox searcButtonHbox = new HBox();
        HBox backHbox = new HBox();

        backHbox.getChildren().add(backButton);
        textHbox.getChildren().add(textField);
        searcButtonHbox.getChildren().add(searchButton);
        hBox.getChildren().addAll(backHbox, textHbox, searcButtonHbox);
        backHbox.setMinWidth(150);
        backHbox.setMaxWidth(150);
        backHbox.setAlignment(Pos.CENTER);
        backHbox.setPadding(new Insets(10));
        textHbox.setAlignment(Pos.CENTER);
        textHbox.setPadding(new Insets(10));

        searcButtonHbox.setAlignment(Pos.CENTER);
        searcButtonHbox.setPadding(new Insets(10));
        searcButtonHbox.setMinWidth(150);
        searcButtonHbox.setMaxWidth(150);
        hBox.setAlignment(Pos.CENTER);
        bp.setTop(hBox);

        bp.setStyle("-fx-background-color: #000");
        searchButton.setStyle(Demo.buttonColors);
        searchButton.setCursor(Cursor.HAND);
        searchButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new ButtonHoverIn(searchButton));
        searchButton.addEventHandler(MouseEvent.MOUSE_EXITED, new ButtonHoverOut(searchButton));

        backButton.setStyle(Demo.buttonColors);
        backButton.setCursor(Cursor.HAND);
        backButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new ButtonHoverIn(backButton));
        backButton.addEventHandler(MouseEvent.MOUSE_EXITED, new ButtonHoverOut(backButton));

        VBox searchResults = new VBox();
        GridPane gp = new GridPane();

        Scene searchScene = new Scene(bp, scene.getWidth(), scene.getHeight());

        int counter = 0;
        for (HashMap<Object, Object> data : dataArray) {
            gp.add(new UserBox((String)data.get("name"), (String)data.get("uid"), searchScene, stage).display(), 0, counter++);
        }

        gp.setGridLinesVisible(true);

        bp.setCenter(gp);

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(scene);
            }
        });

        searchScene.widthProperty().addListener(new GrowClass(300, searchScene, textHbox));
        searchScene.widthProperty().addListener(new GrowClass(50, searchScene, searchResults));
        searchScene.widthProperty().addListener(new GrowClassNode<HBox, TextField>(0, textHbox, textField));

        stage.setScene(searchScene);
        stage.show();
    }
}
