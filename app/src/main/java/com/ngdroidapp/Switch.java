package com.ngdroidapp;

import istanbul.gamelab.ngdroid.core.NgMediaPlayer;

public class Switch extends Button {

    Switch(Sprite sprite, NgMediaPlayer mediaPlayer) {
        super(sprite, mediaPlayer);
    }

    @Override
    public boolean isTouchDown(int x, int y) {
        return super.isTouchDown(x, y);
    }

    @Override
    public boolean isTouchUp(int x, int y) {
        return super.isTouchUp(x, y);
    }
}
