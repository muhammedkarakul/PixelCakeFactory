package com.ngdroidapp;

import istanbul.gamelab.ngdroid.core.NgMediaPlayer;

public class Switch {

    private Sprite sprite;
    private NgMediaPlayer mediaPlayer;
    private boolean state;

    Switch(Sprite sprite, NgMediaPlayer mediaPlayer, boolean state) {
        this.sprite = sprite;
        this.mediaPlayer = mediaPlayer;
        this.state = state;
    }

    public boolean isTouchDown() {
        mediaPlayer.start();

        if(state) {
            sprite.playAnimationWithName("on");
        } else {
            sprite.playAnimationWithName("off");
        }

        return state;
    }

    public boolean isTouchUp() {

        if(state) {
            sprite.playAnimationWithName("on");
        } else {
            sprite.playAnimationWithName("off");
        }

        state = !state;

        return state;
    }

}
