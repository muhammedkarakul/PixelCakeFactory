package com.ngdroidapp;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.HashMap;
import java.util.Map;

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
    private ImageSet playButtonImageSet;
    private NgAnimation playButtonTouchUpAnimation;

    private int playButtonWidth = 384;
    private int playButtonHeight= 384;

    // Sound button ile ilgili değişkenler.
    private Sprite soundButtonSprite;
    private Rect soundButtonSourceRect;
    private Rect soundButtonDestinationRect;
    private ImageSet soundButtonImageSet;
    private NgAnimation soundButtonTouchUpAnimation;
    private NgAnimation soundButtonPressedTouchUpAnimation;

    private int soundButtonWidth = 256;
    private int soundButtonHeight = 256;

    // Music button ile ilgili değişkenler.
    private Sprite musicButtonSprite;
    private Rect musicButtonSourceRect;
    private Rect musicButtonDestinationRect;
    private ImageSet musicButtonImageSet;
    private NgAnimation musicButtonTouchUpAnimation;
    private NgAnimation musicButtonPressedTouchUpAnimation;

    private int musicButtonWidth = 256;
    private int musicButtonHeight = 256;

    // Info button ile ilgili değişkenler.
    private Sprite infoButtonSprite;
    private Rect infoButtonSourceRect;
    private Rect infoButtonDestinationRect;
    private ImageSet infoButtonImageSet;
    private NgAnimation infoButtonTouchUpAnimation;

    private int infoButtonWidth = 256;
    private int infoButtonHeight = 256;

    // Arkaplan müziği ile ilgili değişken.
    private NgMediaPlayer backgroundMusic;

    // Buton tıklama sesi ile ilgili değişken.
    private NgMediaPlayer clickSound;

    public MenuCanvas(NgApp ngApp, boolean musicState, boolean soundState) {
        super(ngApp);
        this.musicState = musicState;
        this.soundState = soundState;
    }

    public void setup() {

        backgroundSourceRect = new Rect(0, 20, 1020, 2040);
        backgroundDestinationRect = new Rect(0, 0, getWidth(), getHeight());
        backgroundSprite = new Sprite(root, backgroundImagePath, backgroundSourceRect, backgroundDestinationRect);

        playButtonSourceRect = new Rect(0, 0, spriteSourceWidth, spriteSourceHeight);
        playButtonDestinationRect = new Rect(((getWidth() - playButtonWidth) / 2), ((getHeight() - playButtonHeight) / 2), ((getWidth() - playButtonWidth) / 2) + playButtonWidth, ((getHeight() - playButtonHeight) / 2) + playButtonHeight);
        playButtonImageSet = new ImageSet(root, playButtonImagePath);
        playButtonImageSet.divideBy(spriteSourceWidth, spriteSourceHeight);
        playButtonTouchUpAnimation = new NgAnimation(root, "touchUpInside", playButtonImageSet, 0, 1);
        playButtonSprite = new Sprite(root, playButtonImagePath, playButtonSourceRect, playButtonDestinationRect, playButtonTouchUpAnimation);

        soundButtonSourceRect = new Rect(0, 0, spriteSourceWidth, spriteSourceHeight);
        soundButtonDestinationRect = new Rect(0, 0, soundButtonWidth, soundButtonHeight);
        soundButtonImageSet = new ImageSet(root, soundButtonImagePath);
        soundButtonImageSet.divideBy(spriteSourceWidth, spriteSourceHeight);
        soundButtonTouchUpAnimation = new NgAnimation(root, "touchUpInside", soundButtonImageSet, 0, 1);
        soundButtonPressedTouchUpAnimation = new NgAnimation(root, "pressedTouchUpInside", soundButtonImageSet, 2, 3);
        Map<String, NgAnimation> soundButtonAnimations = new HashMap<String, NgAnimation>();
        soundButtonAnimations.put(soundButtonTouchUpAnimation.getName(), soundButtonTouchUpAnimation);
        soundButtonAnimations.put(soundButtonPressedTouchUpAnimation.getName(), soundButtonPressedTouchUpAnimation);
        soundButtonSprite = new Sprite(root, soundButtonImagePath, soundButtonSourceRect, soundButtonDestinationRect, soundButtonAnimations);

        musicButtonSourceRect = new Rect(0, 0, spriteSourceWidth, spriteSourceHeight);
        musicButtonDestinationRect = new Rect(soundButtonWidth, 0, soundButtonWidth + musicButtonWidth, musicButtonHeight);
        musicButtonImageSet = new ImageSet(root, musicButtonImagePath);
        musicButtonImageSet.divideBy(spriteSourceWidth, spriteSourceHeight);
        musicButtonTouchUpAnimation = new NgAnimation(root, "touchUpInside", musicButtonImageSet, 0, 1);
        musicButtonPressedTouchUpAnimation = new NgAnimation(root, "pressedTouchUpInside", musicButtonImageSet, 2, 3);
        Map<String, NgAnimation> musicButtonAnimations = new HashMap<String, NgAnimation>();
        musicButtonAnimations.put(musicButtonTouchUpAnimation.getName(), musicButtonTouchUpAnimation);
        musicButtonAnimations.put(musicButtonPressedTouchUpAnimation.getName(), musicButtonPressedTouchUpAnimation);
        musicButtonSprite = new Sprite(root, musicButtonImagePath, musicButtonSourceRect, musicButtonDestinationRect, musicButtonAnimations);

        infoButtonSourceRect = new Rect(0, 0, spriteSourceWidth, spriteSourceHeight);
        infoButtonDestinationRect = new Rect((getWidth() - infoButtonWidth), (getHeight() - infoButtonHeight), (getWidth() - infoButtonWidth) + infoButtonWidth, (getHeight() - infoButtonHeight) + infoButtonHeight);
        infoButtonImageSet = new ImageSet(root, infoButtonImagePath);
        infoButtonImageSet.divideBy(spriteSourceWidth, spriteSourceHeight);
        infoButtonTouchUpAnimation = new NgAnimation(root, "touchUpInside", infoButtonImageSet, 0, 1);
        infoButtonSprite = new Sprite(root, infoButtonImagePath, infoButtonSourceRect, infoButtonDestinationRect, infoButtonTouchUpAnimation);

        if(musicState) {
            backgroundMusic = setupMediaPlayer(backgroundMusic, "sounds/bgm_menu.mp3", true, true);
        }

        if(soundState) {
            clickSound = setupMediaPlayer(clickSound, "sounds/click3.wav", false, false);
        }


    }

    public void update() {

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
            playButtonSprite.playAnimationWithName("touchUpInside");
        }

        if(infoButtonSprite.isTouchedUp(x, y)) {
            playClickSound();
            infoButtonSprite.playAnimationWithName("touchUpInside");
        }

        if(soundButtonSprite.isTouchedUp(x, y)) {
            Log.i(TAG, "Sound Butonuna Basıldı.");
            playClickSound();
            if(!soundState) {
                soundButtonSprite.playAnimationWithName("touchUpInside");
            } else {
                soundButtonSprite.playAnimationWithName("pressedTouchUpInside");
            }

        }

        if(musicButtonSprite.isTouchedUp(x, y)) {
            Log.i(TAG, "Music Butonuna Basıldı.");
            playClickSound();
            if(!musicState) {
                musicButtonSprite.playAnimationWithName("touchUpInside");
            } else {
                musicButtonSprite.playAnimationWithName("pressedTouchUpInside");
            }
        }

    }

    public void touchMove(int x, int y, int id) {
    }

    public void touchUp(int x, int y, int id) {
        if(playButtonSprite.isTouchedUp(x, y)) {
            playButtonSprite.playAnimationWithName("touchUpInside");
            goToGameCanvas();
        }

        if(infoButtonSprite.isTouchedUp(x, y)) {
            infoButtonSprite.playAnimationWithName("touchUpInside");
            goToInfoCanvas();
        }

        if(soundButtonSprite.isTouchedUp(x, y)) {
            // soundButton nesnesinden parmak kaldırıldığında olacak işlemleri burada yapıyoruz.
            if(!soundState) {
                soundButtonSprite.playAnimationWithName("touchUpInside");
            } else {
                soundButtonSprite.playAnimationWithName("pressedTouchUpInside");
            }

            soundState = !soundState;

            if(soundState) {
                clickSound = setupMediaPlayer(clickSound, "sounds/click3.wav", false, false);
            } else {
                clickSound.stop();
            }

        }

        if(musicButtonSprite.isTouchedUp(x, y)) {
            if(!musicState) {
                musicButtonSprite.playAnimationWithName("touchUpInside");
            } else {
                musicButtonSprite.playAnimationWithName("pressedTouchUpInside");
            }

            musicState = !musicState;

            if(musicState) {
                backgroundMusic = setupMediaPlayer(backgroundMusic, "sounds/bgm_menu.mp3", true, true);
            } else {
                backgroundMusic.stop();
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

    public void goToGameCanvas() {
        canvasDidDisappear();
        GameCanvas mc = new GameCanvas(root, musicState, soundState);
        root.canvasManager.setCurrentCanvas(mc);
    }

    public void goToInfoCanvas() {
        canvasDidDisappear();
        InfoCanvas infoCanvas = new InfoCanvas(root, musicState, soundState);
        root.canvasManager.setCurrentCanvas(infoCanvas);
    }

    private void playClickSound() {
        if(soundState) {
            clickSound.start();
        }
    }

    private NgMediaPlayer setupMediaPlayer(NgMediaPlayer mediaPlayer, String mediaFilePath, boolean loopState, boolean mediaPlayerState) {

        mediaPlayer = new NgMediaPlayer(root);
        mediaPlayer.load(mediaFilePath);
        mediaPlayer.prepare();
        mediaPlayer.setLooping(loopState);

        if(mediaPlayerState) {
            mediaPlayer.start();
        }

        return mediaPlayer;
    }

    private void canvasDidDisappear() {
        if(musicState) {
            backgroundMusic.stop();
        }

        if(soundState) {
            clickSound.stop();
        }
    }

}
