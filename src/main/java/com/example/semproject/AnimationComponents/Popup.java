package com.example.semproject.AnimationComponents;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Popup extends HBox {

    String message;
    public TranslateTransition anim = new TranslateTransition();
    public TranslateTransition anim2 = new TranslateTransition();
    public Popup() {
        Label label = new Label(message);

        this.setMinHeight(50);
        this.setMinWidth(300);
        this.setLayoutX(250);
        this.setLayoutY(-50);
        this.setStyle("-fx-background-color: #60c6ff; -fx-background-radius: 5");
        this.getChildren().add(label);
        this.setAlignment(Pos.CENTER);

        label.setStyle("-fx-text-fill: #fff; -fx-font-size: 15; -fx-font-weight: 700");

        TranslateTransition anim = new TranslateTransition();
        anim.setCycleCount(1);
        anim.setDuration(Duration.millis(300));
        anim.setAutoReverse(false);
        anim.setFromY(-50);
        anim.setToY(75);
        anim.setNode(this);
        anim.setInterpolator(Interpolator.EASE_BOTH);
        anim.play();

        TranslateTransition anim2 = new TranslateTransition();
        anim2.setCycleCount(1);
        anim2.setDuration(Duration.millis(300));
        anim2.setAutoReverse(false);
        anim2.setFromY(75);
        anim2.setToY(-50);
        anim2.setNode(this);
        anim2.setInterpolator(Interpolator.EASE_BOTH);
        anim2.setDelay(Duration.seconds(3));
        anim2.play();
    }

    public Popup(String message) {
        Label label = new Label(message);

        this.setMinHeight(50);
        this.setMinWidth(300);
        this.setLayoutX(250);
        this.setLayoutY(-50);
        this.setStyle("-fx-background-color: #60c6ff; -fx-background-radius: 5");
        this.getChildren().add(label);
        this.setAlignment(Pos.CENTER);

        label.setStyle("-fx-text-fill: #fff; -fx-font-size: 15; -fx-font-weight: 700");

        anim.setCycleCount(1);
        anim.setDuration(Duration.millis(300));
        anim.setAutoReverse(false);
        anim.setFromY(-50);
        anim.setToY(75);
        anim.setNode(this);
        anim.setInterpolator(Interpolator.EASE_BOTH);

        anim2.setCycleCount(1);
        anim2.setDuration(Duration.millis(300));
        anim2.setAutoReverse(false);
        anim2.setFromY(75);
        anim2.setToY(-50);
        anim2.setNode(this);
        anim2.setInterpolator(Interpolator.EASE_BOTH);
        anim2.setDelay(Duration.seconds(3));
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void play() {
        anim.play();
        anim2.play();
    }
}
