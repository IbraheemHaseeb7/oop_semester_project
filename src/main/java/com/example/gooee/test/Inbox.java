package com.example.gooee.test;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class Inbox extends Page {

    public Inbox(Stage stage, Scene scene) {
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


    public void display() {

        Button back = new Button("Back");
        Button search = new Button("Search");
        Button logout = new Button("Logout");

        BorderPane bp = new BorderPane();
        HBox hBox = new HBox();
        HBox backHbox = new HBox();
        HBox SearchHbox = new HBox();
        HBox logoutHbox = new HBox();

        bp.setTop(hBox);
        backHbox.getChildren().add(back);
        SearchHbox.getChildren().add(search);
        logoutHbox.getChildren().add(logout);
        backHbox.setAlignment(Pos.TOP_LEFT);
        SearchHbox.setAlignment(Pos.CENTER);
        logoutHbox.setAlignment(Pos.TOP_RIGHT);

        hBox.setMinWidth(scene.getWidth());
        backHbox.setMinWidth(scene.getWidth()/3);
        SearchHbox.setMinWidth(scene.getWidth()/3);
        logoutHbox.setMinWidth(scene.getWidth()/3);
        hBox.getChildren().addAll(backHbox, SearchHbox, logoutHbox);

        // Styling
        bp.setStyle("-fx-background-color: black");
        back.setStyle(Demo.buttonColors);
        search.setStyle(Demo.buttonColors);
        logout.setStyle(Demo.buttonColors);
        back.setCursor(Cursor.HAND);
        search.setCursor(Cursor.HAND);
        logout.setCursor(Cursor.HAND);

        Scene inboxScene = new Scene(bp, scene.getWidth(), scene.getHeight());

        // Event Handling
//        back.setOnAction(new BackButtonEventHandling(stage, scene));
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(scene);
            }
        });
        logout.setOnAction(new LogoutButtonEventHandling(stage, scene, inboxScene));

        back.addEventHandler(MouseEvent.MOUSE_ENTERED, new ButtonHoverIn(back));
        search.addEventHandler(MouseEvent.MOUSE_ENTERED, new ButtonHoverIn(search));
        logout.addEventHandler(MouseEvent.MOUSE_ENTERED, new ButtonHoverIn(logout));
        back.addEventHandler(MouseEvent.MOUSE_EXITED, new ButtonHoverOut(back));
        search.addEventHandler(MouseEvent.MOUSE_EXITED, new ButtonHoverOut(search));
        logout.addEventHandler(MouseEvent.MOUSE_EXITED, new ButtonHoverOut(logout));

        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Search searchObj = new Search(stage, inboxScene);
                searchObj.display();
            }
        });

        inboxScene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                backHbox.setMinWidth(inboxScene.getWidth()/3);
                SearchHbox.setMinWidth(inboxScene.getWidth()/3);
                logoutHbox.setMinWidth(inboxScene.getWidth()/3);
            }
        });

        VBox vbox = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vbox);
        vbox.setStyle("-fx-background-color: #000");
        scrollPane.setStyle("-fx-background-color: #000");
        scrollPane.setMinWidth(inboxScene.getWidth()-10);
        vbox.setMinWidth(inboxScene.getWidth() - 10);
        scrollPane.setMinHeight(inboxScene.getHeight());
        vbox.setMinHeight(inboxScene.getHeight());

        for(int counter = 0; counter < dataArray.size(); counter++){
            UserBox ub = new UserBox((String) dataArray.get(counter).get("name"), (String) dataArray.get(counter).get("uid"), inboxScene, stage);
            vbox.getChildren().add(ub.display());
        }

        bp.setCenter(scrollPane);

        stage.setScene(inboxScene);
        stage.show();
    }

}
