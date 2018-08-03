package com.ngdroidapp;

import android.graphics.Rect;
import android.graphics.Bitmap;

import istanbul.gamelab.ngdroid.core.NgMediaPlayer;
import istanbul.gamelab.ngdroid.util.Utils;
import istanbul.gamelab.ngdroid.util.Log;

import static android.content.ContentValues.TAG;

public class Button {

    protected Sprite sprite;
    protected NgMediaPlayer mediaPlayer;

    Button(Sprite sprite, NgMediaPlayer mediaPlayer) {
        this.sprite = sprite;
        this.mediaPlayer = mediaPlayer;
    }

    public boolean isTouchDown(int x, int y) {
        if(sprite.isTouchedUp(x, y)){
            mediaPlayer.start();
            sprite.playAnimationWithName("click");
            return true;
        } else {
            return  false;
        }
    }

    public boolean isTouchUp(int x, int y) {
        if(sprite.isTouchedUp(x, y)){
            sprite.playAnimationWithName("click");
            return true;
        } else {
            return  false;
        }
    }

    public Sprite getSprite() { return  sprite; }

}
