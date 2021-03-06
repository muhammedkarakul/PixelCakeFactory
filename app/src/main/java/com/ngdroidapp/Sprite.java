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
    protected Bitmap image;
    protected Rect source;
    protected Rect destination;
    protected int anchorX;
    protected int anchorY;
    protected int indicatorX;
    protected int indicatorY;
    protected float velocityX;
    protected float velocityY;

    private Point currentPoint;

    private Map<String, NgAnimation> animations;

    /* INITIALIZE METHODS */

    /**
     * Initialize sprite with image.
     * @param image
     */
    Sprite(Bitmap image) {
        this.image = image;
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
     * @param image
     * @param source
     * @param destination
     */
    Sprite(Bitmap image, Rect source, Rect destination) {
        this.image = image;
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
     * @param image
     * @param source
     * @param destination
     * @param animation
     */
    Sprite(Bitmap image, Rect source, Rect destination, NgAnimation animation) {
        this.image = image;
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
     * @param image
     * @param source
     * @param destination
     * @param animations
     */
    Sprite(Bitmap image, Rect source, Rect destination, Map<String, NgAnimation> animations) {
        this.image = image;
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

    public void setImage(Bitmap image) {
        this.image = image;
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

    public void stopAnimation() {

    }

    public void moveToX() {
        destination.offsetTo( destination.left + (int)(velocityX * indicatorX), destination.top);
    }

    public void moveToY(float velocityY, int indicatoyY) {
        destination.offsetTo(destination.left, destination.top + (int)(velocityY * indicatorY));
    }

    public void moveToY() {
        destination.offsetTo(destination.left, destination.top + (int)(velocityY * indicatorY));
    }

    public void moveTo(float velocityX, float velocityY, int indicatorX, int indicatorY) {
        destination.offsetTo((int)(velocityX * indicatorX), (int)(velocityY * indicatorY));
    }

}
