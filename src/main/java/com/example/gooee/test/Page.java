package com.example.gooee.test;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Page {
    Stage stage;
    Scene scene;

    public Page(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }

    public abstract void display();
}
