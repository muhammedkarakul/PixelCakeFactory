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

    Button() {

    }

}
