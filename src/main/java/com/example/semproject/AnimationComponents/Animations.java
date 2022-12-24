package com.example.semproject.AnimationComponents;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Animations {
    public <N extends Node> void animate(N n, double move1, double move2, double line1x, double line1y, double line2x, double line2y, double line3x, double line3y, double line4x, double line4y) {
        Path path = new Path();

        path.getElements().add(new MoveTo(move1, move2));
        path.getElements().add(new LineTo(line1x, line1y));
        path.getElements().add(new LineTo(line2x, line2y));
        path.getElements().add(new LineTo(line3x, line3y));
        path.getElements().add(new LineTo(line4x, line4y));

        PathTransition pathTransition = new PathTransition();
        pathTransition.setPath(path);
        pathTransition.setNode(n);
        pathTransition.setDuration(Duration.seconds(35));
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.setAutoReverse(false);
        pathTransition.play();

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.seconds(35));
        rotateTransition.setAutoReverse(false);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(720);
        rotateTransition.setNode(n);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.play();
    }
}
