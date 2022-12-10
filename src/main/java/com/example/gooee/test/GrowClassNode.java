package com.example.gooee.test;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GrowClassNode<K extends HBox, T extends TextField> implements ChangeListener<Number> {

    int subtraction;
    K k;
    Node node;

    public GrowClassNode(int subtraction, K k, Node node) {
        this.subtraction = subtraction;
        this.k = k;
        this.node = node;
    }


    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        ((T)node).setMinWidth(k.getWidth() - subtraction);
    }
}
