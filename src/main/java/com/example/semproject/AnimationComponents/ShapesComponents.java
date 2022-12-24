package com.example.semproject.AnimationComponents;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ShapesComponents extends Group {

    public ShapesComponents() {

        // shapes to animate
        Rectangle rect1 = new Rectangle();
        Rectangle rect2 = new Rectangle();
        Circle circle1 = new Circle();
        Circle circle2 = new Circle();

        // properties for shapes
        rect1.setHeight(80);
        rect1.setWidth(150);
        rect1.setX(50);
        rect1.setY(50);
        rect1.setFill(Color.valueOf("#48ff86"));
        rect1.setRotate(-40);

        rect2.setHeight(60);
        rect2.setWidth(120);
        rect2.setX(600);
        rect2.setY(350);
        rect2.setFill(Color.valueOf("#0038ff"));
        rect2.setRotate(40);

        circle1.setCenterY(100);
        circle1.setCenterX(700);
        circle1.setRadius(40);
        circle1.setFill(Color.RED);

        circle2.setCenterY(400);
        circle2.setCenterX(100);
        circle2.setRadius(30);
        circle2.setFill(Color.valueOf("ff00c7"));

        // assigning animations
        Animations animations = new Animations();

        animations.<Rectangle>animate(rect1, 50, -100, 900, 250, 500, 600, -100, 350, 50, -100);
        animations.<Rectangle>animate(rect2, 900, 400, 450, -100, -100, 450, 700, 600, 900, 400);
        animations.<Circle>animate(circle1, 900, 100, 200, 600, 450, -100, 600, 600, 900, 100);
        animations.<Circle>animate(circle2, 700, 600, 600, -100, -50, 250, 900, 300, 700, 600);

        // adding components into layouts
        this.getChildren().addAll(rect1, rect2, circle1, circle2);
    }

}
