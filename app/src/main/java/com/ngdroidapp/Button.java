package com.ngdroidapp;

import android.graphics.Rect;
import android.graphics.Bitmap;

import istanbul.gamelab.ngdroid.util.Utils;
import istanbul.gamelab.ngdroid.util.Log;

import static android.content.ContentValues.TAG;

public class Button extends Sprite {
    private boolean state;
    private String clickedButtonImagePath;
    private Bitmap clickedButtonImage;
    private Rect clickedSource;
    private int clickedSourceX;
    private int clickedSourceY;
    private int clickedSourceWidth;
    private int clickedSourceHeight;

    Button() {
        state = false;
        clickedButtonImagePath = "";
        clickedButtonImage = null;
        clickedSource = new Rect();
        clickedSourceX = 0;
        clickedSourceY = 0;
        clickedSourceWidth = 0;
        clickedSourceHeight = 0;
    }

    Button(NgApp root, String imageFilePath) {
        state = false;
        this.clickedButtonImagePath = imageFilePath;
        clickedButtonImage = Utils.loadImage(root, this.clickedButtonImagePath);
        clickedSourceX = 0;
        clickedSourceY = 0;
        clickedSourceWidth = clickedButtonImage.getWidth();
        clickedSourceHeight = clickedButtonImage.getHeight();
        clickedSource = new Rect(clickedSourceX, clickedSourceY, (clickedSourceX + clickedSourceWidth), ( clickedSourceY + clickedSourceHeight));
    }

    Button(NgApp root, String imageFilePath, int clickedSourceX, int clickedSourceY, int clickedSourceWidth, int clickedSourceHeight) {
        state = false;
        this.clickedButtonImagePath = imageFilePath;
        clickedButtonImage = Utils.loadImage(root, this.clickedButtonImagePath);
        this.clickedSourceX = clickedSourceX;
        this.clickedSourceY = clickedSourceY;
        this.clickedSourceWidth = clickedSourceX + clickedSourceWidth;
        this.clickedSourceHeight = clickedSourceY + clickedSourceHeight;
        clickedSource = new Rect(this.clickedSourceX, this.clickedSourceY, this.clickedSourceWidth, this.clickedSourceHeight);
    }

    Button(NgApp root, String imageFilePath, int sourceX, int sourceY, int sourceWidth, int sourceHeight, int clickedSourceX, int clickedSourceY, int clickedSourceWidth, int clickedSourceHeight, int destinationX, int destinationY, int destinationWidth, int destinationHeight) {
        state = false;
        this.clickedButtonImagePath = imageFilePath;
        clickedButtonImage = Utils.loadImage(root, this.clickedButtonImagePath);
        this.setSource(sourceX, sourceY, sourceX + sourceWidth, sourceY + sourceHeight);
        this.clickedSourceX = clickedSourceX;
        this.clickedSourceY = clickedSourceY;
        this.clickedSourceWidth = clickedSourceX + clickedSourceWidth;
        this.clickedSourceHeight = clickedSourceY + clickedSourceHeight;
        this.setDestination(destinationX, destinationY, destinationX + destinationWidth, destinationY + destinationHeight);
        clickedSource = new Rect(this.clickedSourceX, this.clickedSourceY, this.clickedSourceWidth, this.clickedSourceHeight);
    }

    public boolean isTouched(int touchX, int touchY) {
        if(touchX > this.getDestinationX() && touchY > this.getDestinationY() && touchX < (this.getDestinationX() + this.getDestinationWidth()) && touchY < (this.getDestinationY() + this.getDestinationHeight())) {
            Log.i(TAG, "butona tıklandı.");
        }
        return  true;
    }

}
