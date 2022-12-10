package com.example.gooee.test;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GrowClass implements ChangeListener<Number> {

    int subtraction;
    Scene scene;
    HBox hBox;
    VBox vBox;
    boolean isHbox = true;
    Node node;

    public GrowClass(int subtraction, Scene scene, HBox hBox) {
        this.subtraction = subtraction;
        this.scene = scene;
        this.hBox = hBox;
        isHbox = true;
    }

    public GrowClass(int subtraction, Scene scene, Node node) {
        this.subtraction = subtraction;
        this.scene = scene;
        this.node = node;
        isHbox = true;
    }

    public GrowClass(int subtraction, Scene scene, VBox vBox) {
        this.subtraction = subtraction;
        this.scene = scene;
        this.vBox = vBox;
        isHbox = false;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        if (isHbox == true) {
            hBox.setMinWidth(scene.getWidth() - subtraction);
        } else {
            vBox.setMinWidth(scene.getWidth() - subtraction);
        }
    }
}
