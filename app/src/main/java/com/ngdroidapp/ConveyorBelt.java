package com.ngdroidapp;

public class ConveyorBelt extends Sprite {
    private int movementDirection;
    private Sprite sprite;

    ConveyorBelt() {
        sprite = new Sprite();
        movementDirection = 0;
    }

    ConveyorBelt(Sprite sprite) {
        this.sprite = sprite;
    }
}
