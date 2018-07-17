package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.view.animation.Animation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;

import static android.content.ContentValues.TAG;

public class Sprite {
    /* PROPERTIES */
    private String imageFilePath;
    private Bitmap image;
    private Rect source;
    private Rect destination;
    private NgApp root;
    private int anchorX;
    private int anchorY;
    private int indicatorX;
    private int indicatorY;
    private float velocityX;
    private float velocityY;

    private Point currentPoint;

    private Map<String, NgAnimation> animations;

    /* INITIALIZE METHODS */

    Sprite() {}

    /**
     * Initializes sprite with no parameter.
     */
    Sprite(NgApp root) {
        this.root = root;
        imageFilePath = "gamelab-istanbul_logo.png";
        image = Utils.loadImage(root, imageFilePath);
        anchorX = 0;
        anchorY = 0;
        indicatorX = 0;
        indicatorY = 0;
        velocityX = 0;
        velocityY = 0;
        source = new Rect(0, 0, image.getWidth(), image.getHeight());
        destination = new Rect(0, 0, image.getWidth(), image.getHeight());

        animations = new HashMap<>();
    }

    /**
     * Initialize sprite with image.
     * @param root
     * @param imageFilePath
     */
    Sprite(NgApp root, String imageFilePath) {
        this.root = root;
        this.imageFilePath = imageFilePath;
        image = Utils.loadImage(root, imageFilePath);
        indicatorX = 0;
        indicatorY = 0;
        velocityX = 0;
        velocityY = 0;
        source = new Rect(0,0, image.getWidth(),image.getHeight());
        destination = new Rect(0, 0, image.getWidth(), image.getHeight());
        animations = new HashMap<>();
        anchorX = getDestinationWidth() / 2;
        anchorY = getDestinationHeight() / 2;
    }

    /**
     * Intialize sprite with image, position and size.
     * @param root
     * @param imageFilePath
     */
    Sprite(NgApp root, String imageFilePath, Rect source, Rect destination) {
        this.root = root;
        this.imageFilePath = imageFilePath;
        image = Utils.loadImage(root, imageFilePath);
        indicatorX = 0;
        indicatorY = 0;
        velocityX = 0;
        velocityY = 0;
        this.source = source;
        this.destination = destination;
        animations = new HashMap<>();
        anchorX = getDestinationWidth() / 2;
        anchorY = getDestinationHeight() / 2;
    }

    /**
     * Intialize sprite with image, position and size.
     * @param root
     * @param imageFilePath
     */
    Sprite(NgApp root, String imageFilePath, Rect source, Rect destination, NgAnimation animation) {
        this.root = root;
        this.imageFilePath = imageFilePath;
        image = Utils.loadImage(root, imageFilePath);
        indicatorX = 0;
        indicatorY = 0;
        velocityX = 0;
        velocityY = 0;
        this.source = source;
        this.destination = destination;
        animations = new HashMap<>();
        animations.put(animation.getName(), animation);
        anchorX = getDestinationWidth() / 2;
        anchorY = getDestinationHeight() / 2;
    }

    /**
     * Intialize sprite with image, position and size.
     * @param root
     * @param imageFilePath
     */
    Sprite(NgApp root, String imageFilePath, Rect source, Rect destination, Map<String, NgAnimation> animations) {
        this.root = root;
        this.imageFilePath = imageFilePath;
        image = Utils.loadImage(root, imageFilePath);
        indicatorX = 0;
        indicatorY = 0;
        velocityX = 0;
        velocityY = 0;
        this.source = source;
        this.destination = destination;
        this.animations = new HashMap<>();
        this.animations = animations;
        anchorX = getDestinationWidth() / 2;
        anchorY = getDestinationHeight() / 2;
    }



    public void setImage(String imageFilePath) {
        image = Utils.loadImage(root, imageFilePath);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, source, destination, null);
    }

    /* SETTER METHODS */

    public void addAnimation(NgAnimation animation) {
        animations.put(animation.getName(), animation);
    }

    public void setPosition(int x, int y) {
        destination.offsetTo(x - getAnchorX(), y - getAnchorY());
    }

    public void setSource(int x, int y, int width, int height) {
        source.set(x, y, width, height);
    }

    public void setDestination(int x, int y, int width, int height) {
        destination.set(x, y, width, height);
    }

    public void setSourceX(int x) {
        source.offsetTo(x, source.top);
    }

    public void setSourceY(int y) {
        source.offsetTo(source.left, y);
    }

    public void setDestinationX(int x) {
        destination.offsetTo(x, destination.top);
    }
    public void setDestinationY(int y) {
        destination.offsetTo(destination.left, y);
    }

    public void setAnchorX(int x) { anchorX = x; }
    public void setAnchorY(int y) { anchorY = y; }

    public void setIndicatorX(int indicatorX) { this.indicatorX = indicatorX; }
    public void setIndicatorY(int indicatorY) { this.indicatorY = indicatorY; }
    public void setVelocityX(float velocityX) { this.velocityX = velocityX; }
    public void setVelocityY(float velocityY) {this.velocityY = velocityY; }


    /* GETTER METHODS */

    public Rect getSource() { return source; }

    public Rect getDestination() { return destination; }

    public int getSourceX() { return source.left; }
    public int getSourceY() { return source.top; }
    public int getSourceWidth() { return (source.right - source.left); }
    public int getSourceHeight() { return (source.bottom - source.top); }

    public int getDestinationX() { return destination.left; }
    public int getDestinationY() { return destination.top; }
    public int getDestinationWidth() { return (destination.right - destination.left); }
    public int getDestinationHeight() { return (destination.bottom - destination.top); }

    public int getAnchorX() { return anchorX; }
    public int getAnchorY() { return anchorY; }

    public int getIndicatorX() { return  indicatorX; }
    public int getIndicatorY() { return  indicatorY; }
    public float getVelocityX() { return  velocityX; }
    public float getVelocityY() { return  velocityY; }

    public NgAnimation getAnimationWithName(String animationName) { return animations.get(animationName); }

    /* UTILITIES METHODS */

    public boolean isCollidedWithRect(Rect sprite) {
        if(Rect.intersects(this.destination, sprite)){
            return true;
        } else {
            return  false;
        }
    }

    public boolean isCollidedWithPosition(int x, int y, int width, int height) {

        if(destination.left >= x && destination.top >= y && destination.left < (x + width) && destination.top < (y + height)) {
            return true;
        } else {
            return  false;
        }

    }

    public boolean isTouchedUp(int touchX, int touchY) {
        if(touchX > destination.left && touchY > destination.top && touchX < destination.right && touchY < destination.bottom) {
            return true;
        } else {
            return false;
        }

    }

    public void playAnimationWithName(String animationName){
        NgAnimation animation = animations.get(animationName);
        source = animation.getAnimationCurrentFrame();
    }

}
