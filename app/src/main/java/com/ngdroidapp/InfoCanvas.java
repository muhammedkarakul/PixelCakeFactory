package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.core.NgMediaPlayer;
import istanbul.gamelab.ngdroid.util.Utils;

public class InfoCanvas extends BaseCanvas {

    /* DEĞİŞKENLER */

    private boolean musicState;
    private boolean soundState;

    private NgMediaPlayer backgroundMusic;
    private NgMediaPlayer clickSound;

    private Sprite backgroundSprite;


    public InfoCanvas(NgApp ngApp, boolean musicState, boolean soundState) {
        super(ngApp);
        this.musicState = musicState;
        this.soundState = soundState;
    }

    public void setup() {
        if(soundState) {
            setupClickSound();
        }

        if(musicState) {
            setupMusic();
        }
        Bitmap backgroundBitmap = Utils.loadImage(root, "background.png");
        Rect backgroundSourceRect = new Rect(0, 20, 1020, 2040);
        Rect backgroundDestinationRect = new Rect(0, 0, getWidth(), getHeight());
        backgroundSprite = new Sprite(backgroundBitmap, backgroundSourceRect, backgroundDestinationRect);

    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        backgroundSprite.draw(canvas);
    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {
    }

    public boolean backPressed() {

        if(musicState) {
            backgroundMusic.stop();
        }

        if(soundState) {
            clickSound.stop();
        }

        goToMainCanvas();
        return true;
    }

    public void touchDown(int x, int y, int id) {

    }

    public void touchMove(int x, int y, int id) {

    }

    public void touchUp(int x, int y, int id) {

    }

    public void surfaceChanged(int width, int height) {

    }

    public void surfaceCreated() {

    }

    public void surfaceDestroyed() {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void reloadTextures() {

    }

    public void showNotify() {

    }

    public void hideNotify() {

    }

    public void setupMusic() {
        backgroundMusic = new NgMediaPlayer(root);
        backgroundMusic.load("sounds/bgm_action_4.mp3");
        backgroundMusic.prepare();
        backgroundMusic.setLooping(true);
        backgroundMusic.start();
    }

    public void setupClickSound() {
        clickSound = new NgMediaPlayer(root);
        clickSound.load("sounds/click3.wav");
        clickSound.prepare();
        clickSound.setLooping(false);
    }

    public void goToMainCanvas() {
        MenuCanvas menuCanvas = new MenuCanvas(root, musicState, soundState);
        root.canvasManager.setCurrentCanvas(menuCanvas);
    }
}
