package com.ngdroidapp;

/**
 * Created by Muhammed T. Karakul on 6.08.2018.
 */

public class Product {

    // Properties

    private Sprite sprite;
    private boolean isHidden;
    private boolean isChangeConveyorBelt;

    Product(Sprite sprite) {
        this.sprite = sprite;
    }

    // Methods

    // Getter
    public Sprite getSprite() { return sprite; }

    public boolean getHiddenState() { return isHidden; }

    public boolean getConveyorBeltState() { return isChangeConveyorBelt; }

    // Setter
    public void setSprite(Sprite sprite) { this.sprite = sprite; }

    public void setHiddenState(boolean state) { this.isHidden = state; }

    public void setConveyorBeltState(boolean state) { this.isChangeConveyorBelt = state; }

    // Utilities
    public void changeHiddenState() {
        isHidden = !isHidden;
    }

    public void changeConveyorBeltState() {
        isChangeConveyorBelt = !isChangeConveyorBelt;
    }

    public void increaseVelocityXBy(float increase) { sprite.setVelocityX(sprite.getVelocityX() + increase); }
}
