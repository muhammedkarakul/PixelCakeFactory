package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Canvas;
import istanbul.gamelab.ngdroid.util.Utils;

public class Sprite {
    /* PROPERTIES */
    private String imageFilePath;
    private Bitmap image;
    private Rect source;
    private Rect destination;
    private int sourceX;
    private int sourceY;
    private int sourceWidth;
    private int sourceHeight;
    private int destinationX;
    private int destinationY;
    private int destinationWidth;
    private int destinationHeight;
    private int anchorX;
    private int anchorY;

    private int indicatorX;
    private int indicatorY;
    private int velocityX;
    private int velocityY;

    /* INITIALIZE METHODS */

    /**
     * Initializes sprite with no parameter.
     */
    Sprite() {
        imageFilePath = "";
        image = null;
        sourceX = 0;
        sourceY = 0;
        sourceWidth = 0;
        sourceHeight = 0;
        destinationX = 0;
        destinationY = 0;
        destinationWidth = 0;
        destinationHeight = 0;
        anchorX = 0;
        anchorY = 0;

        indicatorX = 0;
        indicatorY = 0;
        velocityX = 0;
        velocityY = 0;

        source = new Rect(sourceX, sourceY, (sourceX + sourceWidth), (sourceY + sourceHeight));
        destination = new Rect(destinationX, destinationY, (destinationX + destinationWidth), (destinationY + destinationHeight));
    }

    /**
     * Initialize sprite with image.
     * @param root
     * @param imageFilePath
     */
    Sprite(NgApp root, String imageFilePath) {
        this.imageFilePath = imageFilePath;
        image = Utils.loadImage(root, this.imageFilePath);
        sourceX = 0;
        sourceY = 0;
        sourceWidth = image.getWidth();
        sourceHeight = image.getHeight();
        destinationX = 0;
        destinationY = 0;
        destinationWidth = image.getWidth();
        destinationHeight = image.getHeight();
        anchorX = getDestinationWidth() / 2;
        anchorY = getDestinationHeight() / 2;

        indicatorX = 0;
        indicatorY = 0;
        velocityX = 0;
        velocityY = 0;

        source = new Rect(sourceX, sourceY, (sourceX + sourceWidth), (sourceY + sourceHeight));
        destination = new Rect(destinationX, destinationY, (destinationX + destinationWidth), (destinationY + destinationHeight));
        //updateSource();
        //updateDestination();
    }

    /**
     * Intialize sprite with image, position and size.
     * @param root
     * @param imageFilePath
     * @param sourceX Sprite source file selected area's left up side x position.
     * @param sourceY Sprite source file selected area's left up side y position.
     * @param sourceWidth Sprite source file selecte area's width.
     * @param sourceHeight Sprite source file selecte area's height.
     * @param destinationX X coordinate of the upper left corner of the area where the sprite is to be drawn on the screen.
     * @param destinationY Y coordinate of the upper left corner of the area where the sprite is to be drawn on the screen
     * @param destinationWidth The width of the sprite that will be drawn on the screen.
     * @param destinationHeight The height of the sprite that will be drawn on the screen.
     */
    Sprite(NgApp root, String imageFilePath, int sourceX, int sourceY, int sourceWidth, int sourceHeight, int destinationX, int destinationY, int destinationWidth, int destinationHeight) {
        this.imageFilePath = imageFilePath;
        image = Utils.loadImage(root, this.imageFilePath);
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.sourceWidth = sourceWidth;
        this.sourceHeight = sourceHeight;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.destinationWidth = destinationWidth;
        this.destinationHeight = destinationHeight;
        anchorX = getDestinationWidth() / 2;
        anchorY = getDestinationHeight() / 2;

        indicatorX = 0;
        indicatorY = 0;
        velocityX = 0;
        velocityY = 0;

        source = new Rect(this.sourceX, this.sourceY, (this.getSourceX() + this.sourceWidth), (this.getSourceY() + this.sourceHeight));
        destination = new Rect(this.destinationX, this.destinationY, (this.getDestinationX() + this.destinationWidth), (this.getDestinationY() + this.destinationHeight));
    }

    public void setImage(NgApp root, String imageFilePath) {
        image = Utils.loadImage(root, imageFilePath);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, source, destination, null);
    }

    /* SETTER METHODS */

    public void setPosition(int x, int y) {
        destinationX = x - getAnchorX();
        destinationY = y - getAnchorY();
        updateDestination();
    }

    public void setSource(int x, int y, int width, int height) {
        sourceX = x;
        sourceY = y;
        sourceWidth = width;
        sourceHeight = height;
        updateSource();
    }

    public void setDestination(int x, int y, int width, int height) {
        destinationX = x;
        destinationY = y;
        destinationWidth = width;
        destinationHeight = height;
        updateDestination();
    }

    public void setSourceX(int x) {
        sourceX = x;
        updateSource();
    }

    public void setSourceY(int y) {
        sourceY = y;
        updateSource();
    }

    public void setSourceWidth(int width) {
        sourceWidth = width;
        updateSource();
    }

    public void setSourceHeight(int height) {
        sourceHeight = height;
        updateSource();
    }

    public void setDestinationX(int x) {
        destinationX = x;
        updateDestination();
    }
    public void setDestinationY(int y) {
        destinationY = y;
        updateDestination();
    }
    public void setDestinationWidth(int width) {
        destinationWidth = width;
        updateDestination();
    }
    public void setDestinationHeight(int height) {
        destinationHeight = height;
        updateDestination();
    }

    public void setAnchorX(int x) { anchorX = x; }
    public void setAnchorY(int y) { anchorY = y; }

    public void setIndicatorX(int indicatorX) { this.indicatorX = indicatorX; }
    public void setIndicatorY(int indicatorY) { this.indicatorY = indicatorY; }
    public void setVelocityX(int velocityX) { this.velocityX = velocityX; }
    public void setVelocityY(int velocityY) {this.velocityY = velocityY; }

    public void updateSource() {
        source.set(sourceX, sourceY, sourceX + sourceWidth, sourceY + sourceHeight);
    }

    public void updateDestination() {
        destination.set(destinationX, destinationY, destinationX + destinationWidth, destinationY + destinationHeight);
    }


    /* GETTER METHODS */

    public Rect getSource() { return source; }

    public Rect getDestination() { return destination; }

    public int getSourceX() { return sourceX; }
    public int getSourceY() { return sourceY; }
    public int getSourceWidth() { return sourceWidth; }
    public int getSourceHeight() { return sourceHeight; }

    public int getDestinationX() { return destinationX; }
    public int getDestinationY() { return destinationY; }
    public int getDestinationWidth() { return destinationWidth; }
    public int getDestinationHeight() { return destinationHeight; }

    public int getAnchorX() { return anchorX; }
    public int getAnchorY() { return anchorY; }

    public int getIndicatorX() { return  indicatorX; }
    public int getIndicatorY() { return  indicatorY; }
    public int getVelocityX() { return  velocityX; }
    public int getVelocityY() { return  velocityY; }

    /* UTILITIES METHODS */

    public boolean isCollidedWithRect(Rect sprite) {
        if(Rect.intersects(this.destination, sprite)){
            return true;
        } else {
            return  false;
        }
    }

    public boolean isCollidedWithPosition(int x, int y, int width, int height) {

        if(destinationX >= x && destinationY >= y && destinationX < (x + width) && destinationY < (y + height)) {
            return true;
        } else {
            return  false;
        }

    }

    public boolean isTouchedUp(int touchX, int touchY) {
        if(touchX > destinationX && touchY > destinationY && touchX < (destinationX + destinationWidth) && touchY < (destinationY + destinationHeight)) {
            return true;
        } else {
            return false;
        }

    }

    public void moveTo(int x, int y) {

    }
}
