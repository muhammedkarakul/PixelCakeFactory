package com.ngdroidapp;

import android.graphics.Canvas;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.core.NgMediaPlayer;
import istanbul.gamelab.ngdroid.util.Log;

/**
 * Created by noyan on 27.06.2016.
 * Nitra Games Ltd.
 */

public class MenuCanvas extends BaseCanvas {

    /* SABİTLER */

    // Görsellerden çekilecek sprite boyutları.
    final private int spriteSourceWidth = 384;
    final private int spriteSourceHeight = 384;

    /* DEĞİŞKENLER */

    // Sesin açık/kapalı olma durumunu kontrol eden değişken.
    private boolean soundState = true;

    // Müziğin açık/kapalı olma durumunu kontrol eden değişken.
    private boolean musicState = true;

    // Background ile ilgili değişkenler.
    private Sprite background;
    private String backgroundImagePath = "background.png";

    // Play button ile ilgili değişkenler.
    private Sprite playButton;
    private String playButtonImagePath = "buttonPlaySpriteSet.png";
    private int playButtonWidth = 384;
    private int playButtonHeight= 384;

    // Sound button ile ilgili değişkenler.
    private Sprite soundButton;
    private String soundButtonImagePath = "buttonSoundSpriteSet.png";
    private int soundButtonWidth = 256;
    private int soundButtonHeight = 256;

    // Music button ile ilgili değişkenler.
    private Sprite musicButton;
    private String musicButtonImagePath = "buttonMusicSpriteSet.png";
    private int musicButtonWidth = 256;
    private int musicButtonHeight = 256;

    // Info button ile ilgili değişkenler.
    private Sprite infoButton;
    private String infoButtonImagePath = "buttonInfoSpriteSet.png";
    private int infoButtonWidth = 256;
    private int infoButtonHeight = 256;

    // Arkaplan müziği ile ilgili değişken.
    private NgMediaPlayer menuSound;

    // Buton tıklama sesi ile ilgili değişken.
    private NgMediaPlayer clickSound;

    public MenuCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void setup() {
        background = new Sprite(root, backgroundImagePath, 0, 0, 500, 500, 0, 0, getWidth(), getHeight());
        playButton = new Sprite(root, playButtonImagePath, 0, 0, spriteSourceWidth, spriteSourceHeight, ((getWidth() - spriteSourceWidth) / 2), ((getHeight() - spriteSourceHeight) / 2), playButtonWidth, playButtonHeight);
        soundButton = new Sprite(root, soundButtonImagePath, 0, 0, spriteSourceWidth, spriteSourceHeight, 0, 0, soundButtonWidth, soundButtonHeight);
        musicButton = new Sprite(root, musicButtonImagePath, 0, 0, spriteSourceWidth, spriteSourceHeight, soundButtonWidth, 0, musicButtonWidth, musicButtonHeight);
        infoButton = new Sprite(root, infoButtonImagePath, 0, 0, spriteSourceWidth, spriteSourceHeight, (getWidth() - infoButtonWidth), (getHeight() - infoButtonHeight), infoButtonWidth, infoButtonHeight);
        setupSound();
        setupClickSound();
    }

    public void setupSound() {
        menuSound = new NgMediaPlayer(root);
        menuSound.load("sounds/bgm_menu.mp3");
        menuSound.prepare();
        menuSound.setLooping(true);
        menuSound.start();
    }

    public void setupClickSound() {
        clickSound = new NgMediaPlayer(root);
        clickSound.load("sounds/click3.wav");
        clickSound.prepare();
        clickSound.setLooping(false);
    }

    public void update() {
        if(soundState) {
            // Play sound
        } else {
            // Stop sound
        }

    }

    public void draw(Canvas canvas) {
        background.draw(canvas);
        playButton.draw(canvas);
        soundButton.draw(canvas);
        musicButton.draw(canvas);
        infoButton.draw(canvas);
    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {
    }

    public boolean backPressed() {
        return false;
    }

    public void touchDown(int x, int y, int id) {

        if(soundState) {
            clickSound.start();
        }

        if(playButton.isTouchedUp(x, y)) {
            //Log.i(TAG, "Play Butonuna Basıldı.");
            playButton.setSourceX(420);
        }

        if(infoButton.isTouchedUp(x, y)) {
            infoButton.setSourceX(384);
        }

        if(soundButton.isTouchedUp(x, y)) {
            //Log.i(TAG, "Sound Butonuna Basıldı.");
            if(soundState) {
                soundButton.setSourceX(384);
            } else {
                soundButton.setSourceX(1152);
            }

        }

        if(musicButton.isTouchedUp(x, y)) {
            if(musicState) {
                musicButton.setSourceX(384);
            } else {
                musicButton.setSourceX(1152);
            }
        }


    }

    public void touchMove(int x, int y, int id) {
    }

    public void touchUp(int x, int y, int id) {
        if(playButton.isTouchedUp(x, y)) {
            playButton.setSource(0, 0, 384, 384);
            playGame();
        }

        if(infoButton.isTouchedUp(x, y)) {
            infoButton.setSourceX(0);
        }

        if(soundButton.isTouchedUp(x, y)) {
            // soundButton nesnesinden parmak kaldırıldığında olacak işlemleri burada yapıyoruz.
            if(soundState) {
                soundButton.setSourceX(768);
            } else {
                soundButton.setSourceX(0);
            }
            soundState = !soundState;

        }

        if(musicButton.isTouchedUp(x, y)) {
            if(musicState) {
                musicButton.setSourceX(768);
            } else {
                musicButton.setSourceX(0);
            }

            musicState = !musicState;

            if(musicState) {
                setupSound();
            } else {
                menuSound.stop();
            }

        }
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

    public void playGame() {

        menuSound.stop();
        clickSound.stop();

        GameCanvas mc = new GameCanvas(root);
        root.canvasManager.setCurrentCanvas(mc);
    }

}
