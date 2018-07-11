package com.ngdroidapp;

import android.graphics.Canvas;
import android.graphics.Rect;

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
    final private String backgroundImagePath = "background.png";
    final private String playButtonImagePath = "buttonPlaySpriteSet.png";
    final private String soundButtonImagePath = "buttonSoundSpriteSet.png";
    final private String musicButtonImagePath = "buttonMusicSpriteSet.png";
    final private String infoButtonImagePath = "buttonInfoSpriteSet.png";

    /* DEĞİŞKENLER */

    // Sesin açık/kapalı olma durumunu kontrol eden değişken.
    private boolean soundState = true;

    // Müziğin açık/kapalı olma durumunu kontrol eden değişken.
    private boolean musicState = true;

    // Background ile ilgili değişkenler.
    private Sprite backgroundSprite;
    private Rect backgroundSourceRect;
    private Rect backgroundDestinationRect;


    // Play button ile ilgili değişkenler.
    private Sprite playButtonSprite;
    private Rect playButtonSourceRect;
    private Rect playButtonDestinationRect;

    private int playButtonWidth = 384;
    private int playButtonHeight= 384;

    // Sound button ile ilgili değişkenler.
    private Sprite soundButtonSprite;
    private Rect soundButtonSourceRect;
    private Rect soundButtonDestinationRect;

    private int soundButtonWidth = 256;
    private int soundButtonHeight = 256;

    // Music button ile ilgili değişkenler.
    private Sprite musicButtonSprite;
    private Rect musicButtonSourceRect;
    private Rect musicButtonDestinationRect;

    private int musicButtonWidth = 256;
    private int musicButtonHeight = 256;

    // Info button ile ilgili değişkenler.
    private Sprite infoButtonSprite;
    private Rect infoButtonSourceRect;
    private Rect infoButtonDestinationRect;

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

        backgroundSourceRect = new Rect(0, 20, 1020, 2040);
        backgroundDestinationRect = new Rect(0, 0, getWidth(), getHeight());
        backgroundSprite = new Sprite(root, backgroundImagePath, backgroundSourceRect, backgroundDestinationRect);

        playButtonSourceRect = new Rect(0, 0, spriteSourceWidth, spriteSourceHeight);
        playButtonDestinationRect = new Rect(((getWidth() - playButtonWidth) / 2), ((getHeight() - playButtonHeight) / 2), ((getWidth() - playButtonWidth) / 2) + playButtonWidth, ((getHeight() - playButtonHeight) / 2) + playButtonHeight);
        playButtonSprite = new Sprite(root, playButtonImagePath, playButtonSourceRect, playButtonDestinationRect);

        soundButtonSourceRect = new Rect(0, 0, spriteSourceWidth, spriteSourceHeight);
        soundButtonDestinationRect = new Rect(0, 0, soundButtonWidth, soundButtonHeight);
        soundButtonSprite = new Sprite(root, soundButtonImagePath, soundButtonSourceRect, soundButtonDestinationRect);

        musicButtonSourceRect = new Rect(0, 0, spriteSourceWidth, spriteSourceHeight);
        musicButtonDestinationRect = new Rect(soundButtonWidth, 0, soundButtonWidth + musicButtonWidth, musicButtonHeight);
        musicButtonSprite = new Sprite(root, musicButtonImagePath, musicButtonSourceRect, musicButtonDestinationRect);

        infoButtonSourceRect = new Rect(0, 0, spriteSourceWidth, spriteSourceHeight);
        infoButtonDestinationRect = new Rect((getWidth() - infoButtonWidth), (getHeight() - infoButtonHeight), (getWidth() - infoButtonWidth) + infoButtonWidth, (getHeight() - infoButtonHeight) + infoButtonHeight);
        infoButtonSprite = new Sprite(root, infoButtonImagePath, infoButtonSourceRect, infoButtonDestinationRect);

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
        /*
        if(soundState) {
            // Play sound
        } else {
            // Stop sound
        }
        */
    }

    public void draw(Canvas canvas) {
        backgroundSprite.draw(canvas);
        playButtonSprite.draw(canvas);
        soundButtonSprite.draw(canvas);
        musicButtonSprite.draw(canvas);
        infoButtonSprite.draw(canvas);
    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {
    }

    public boolean backPressed() {
        return false;
    }

    public void touchDown(int x, int y, int id) {

        if(playButtonSprite.isTouchedUp(x, y)) {
            Log.i(TAG, "Play Butonuna Basıldı.");
            playClickSound();
            playButtonSprite.setSourceX(420);
        }

        if(infoButtonSprite.isTouchedUp(x, y)) {
            playClickSound();
            infoButtonSprite.setSourceX(384);
        }

        if(soundButtonSprite.isTouchedUp(x, y)) {
            Log.i(TAG, "Sound Butonuna Basıldı.");
            playClickSound();
            if(soundState) {
                soundButtonSprite.setSourceX(384);
            } else {
                soundButtonSprite.setSourceX(1152);
            }

        }

        if(musicButtonSprite.isTouchedUp(x, y)) {
            Log.i(TAG, "Music Butonuna Basıldı.");
            playClickSound();
            if(musicState) {
                musicButtonSprite.setSourceX(384);
            } else {
                musicButtonSprite.setSourceX(1152);
            }
        }

    }

    public void touchMove(int x, int y, int id) {
    }

    public void touchUp(int x, int y, int id) {
        if(playButtonSprite.isTouchedUp(x, y)) {
            playButtonSprite.setSource(0, 0, 384, 384);
            playGame();
        }

        if(infoButtonSprite.isTouchedUp(x, y)) {
            infoButtonSprite.setSourceX(0);
        }

        if(soundButtonSprite.isTouchedUp(x, y)) {
            // soundButton nesnesinden parmak kaldırıldığında olacak işlemleri burada yapıyoruz.
            if(soundState) {
                soundButtonSprite.setSourceX(768);
            } else {
                soundButtonSprite.setSourceX(0);
            }
            soundState = !soundState;

        }

        if(musicButtonSprite.isTouchedUp(x, y)) {
            if(musicState) {
                musicButtonSprite.setSourceX(768);
            } else {
                musicButtonSprite.setSourceX(0);
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

    private void playClickSound() {
        if(soundState) {
            clickSound.start();
        }
    }

}
