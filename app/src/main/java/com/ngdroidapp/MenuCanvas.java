package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.Image;

import java.util.HashMap;
import java.util.Map;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.core.NgMediaPlayer;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;

/**
 * Created by noyan on 27.06.2016.
 * Nitra Games Ltd.
 */

public class MenuCanvas extends BaseCanvas {

    /* DEĞİŞKENLER */

    // Sesin açık/kapalı olma durumunu kontrol eden değişken.
    private boolean soundState = true;

    // Müziğin açık/kapalı olma durumunu kontrol eden değişken.
    private boolean musicState = true;

    // Background ile ilgili değişkenler.
    private Sprite backgroundSprite;

    // Play button ile ilgili değişkenler.
    private Button playButton;

    // Sound button ile ilgili değişkenler.
    private Sprite soundButtonSprite;

    // Music button ile ilgili değişkenler.
    private Sprite musicButtonSprite;

    // Info button ile ilgili değişkenler.
    private Button infoButton;

    //private Button storeButton;

    // Arkaplan müziği ile ilgili değişken.
    private NgMediaPlayer musicMediaPlayer;

    // Buton tıklama sesi ile ilgili değişken.
    private NgMediaPlayer soundMediaPlayer;

    public MenuCanvas(NgApp ngApp, boolean musicState, boolean soundState) {
        super(ngApp);
        this.musicState = musicState;
        this.soundState = soundState;
    }

    public void setup() {

        if(musicState) {
            musicMediaPlayer = setupMediaPlayer("sounds/bgm_menu.mp3", true, true);
        }

        if(soundState) {
            soundMediaPlayer = setupMediaPlayer("sounds/click3.wav", false, false);
        }

        int spriteSourceWidth = 384;
        int spriteSourceHeight = 384;
        Bitmap backgroundBitmap = Utils.loadImage(root, "background.png");
        Rect backgroundSourceRect = new Rect(0, 20, 1020, 2040);
        Rect backgroundDestinationRect = new Rect(0, 0, getWidth(), getHeight());
        backgroundSprite = new Sprite(backgroundBitmap, backgroundSourceRect, backgroundDestinationRect);

        int playButtonWidth = 384;
        int playButtonHeight= 384;
        Bitmap playButtonBitmap = Utils.loadImage(root, "buttonPlaySpriteSet.png");
        Rect playButtonSourceRect = new Rect(0, 0, spriteSourceWidth, spriteSourceHeight);
        Rect playButtonDestinationRect = new Rect(((getWidth() - playButtonWidth) / 2), ((getHeight() - playButtonHeight) / 2), ((getWidth() - playButtonWidth) / 2) + playButtonWidth, ((getHeight() - playButtonHeight) / 2) + playButtonHeight);
        ImageSet playButtonImageSet = new ImageSet(playButtonBitmap);
        playButtonImageSet.divideBy(spriteSourceWidth, spriteSourceHeight);
        NgAnimation playButtonTouchUpAnimation = new NgAnimation("click", playButtonImageSet, 0, 1);
        Sprite playButtonSprite = new Sprite(playButtonBitmap, playButtonSourceRect, playButtonDestinationRect, playButtonTouchUpAnimation);
        playButton = new Button(playButtonSprite, soundMediaPlayer);

        int soundButtonWidth = 256;
        int soundButtonHeight = 256;
        Bitmap soundButtonBitmap = Utils.loadImage(root, "buttonSoundSpriteSet.png");
        Rect soundButtonSourceRect = new Rect(0, 0, spriteSourceWidth, spriteSourceHeight);
        Rect soundButtonDestinationRect = new Rect(0, 0, soundButtonWidth, soundButtonHeight);
        ImageSet soundButtonImageSet = new ImageSet(soundButtonBitmap);
        soundButtonImageSet.divideBy(spriteSourceWidth, spriteSourceHeight);
        NgAnimation soundButtonTouchUpAnimation = new NgAnimation("touchUpInside", soundButtonImageSet, 0, 1);
        NgAnimation soundButtonPressedTouchUpAnimation = new NgAnimation("pressedTouchUpInside", soundButtonImageSet, 2, 3);
        Map<String, NgAnimation> soundButtonAnimations = new HashMap<String, NgAnimation>();
        soundButtonAnimations.put(soundButtonTouchUpAnimation.getName(), soundButtonTouchUpAnimation);
        soundButtonAnimations.put(soundButtonPressedTouchUpAnimation.getName(), soundButtonPressedTouchUpAnimation);
        soundButtonSprite = new Sprite(soundButtonBitmap, soundButtonSourceRect, soundButtonDestinationRect, soundButtonAnimations);
        //soundButton = new Button(soundButtonSprite, soundMediaPlayer);

        int musicButtonWidth = 256;
        int musicButtonHeight = 256;
        Bitmap musicButtonBitmap = Utils.loadImage(root, "buttonMusicSpriteSet.png");
        Rect musicButtonSourceRect = new Rect(0, 0, spriteSourceWidth, spriteSourceHeight);
        Rect musicButtonDestinationRect = new Rect(soundButtonWidth, 0, soundButtonWidth + musicButtonWidth, musicButtonHeight);
        ImageSet musicButtonImageSet = new ImageSet(musicButtonBitmap);
        musicButtonImageSet.divideBy(spriteSourceWidth, spriteSourceHeight);
        NgAnimation musicButtonTouchUpAnimation = new NgAnimation("touchUpInside", musicButtonImageSet, 0, 1);
        NgAnimation musicButtonPressedTouchUpAnimation = new NgAnimation("pressedTouchUpInside", musicButtonImageSet, 2, 3);
        Map<String, NgAnimation> musicButtonAnimations = new HashMap<String, NgAnimation>();
        musicButtonAnimations.put(musicButtonTouchUpAnimation.getName(), musicButtonTouchUpAnimation);
        musicButtonAnimations.put(musicButtonPressedTouchUpAnimation.getName(), musicButtonPressedTouchUpAnimation);
        musicButtonSprite = new Sprite(musicButtonBitmap, musicButtonSourceRect, musicButtonDestinationRect, musicButtonAnimations);
        //musicButton = new Button(musicButtonSprite, soundMediaPlayer);

        int infoButtonWidth = 256;
        int infoButtonHeight = 256;
        Bitmap infoButtonBitmap = Utils.loadImage(root, "buttonInfoSpriteSet.png");
        Rect infoButtonSourceRect = new Rect(0, 0, spriteSourceWidth, spriteSourceHeight);
        Rect infoButtonDestinationRect = new Rect((getWidth() - infoButtonWidth), (getHeight() - infoButtonHeight), (getWidth() - infoButtonWidth) + infoButtonWidth, (getHeight() - infoButtonHeight) + infoButtonHeight);
        ImageSet infoButtonImageSet = new ImageSet(infoButtonBitmap);
        infoButtonImageSet.divideBy(spriteSourceWidth, spriteSourceHeight);
        NgAnimation infoButtonTouchUpAnimation = new NgAnimation("click", infoButtonImageSet, 0, 1);
        Sprite infoButtonSprite = new Sprite(infoButtonBitmap, infoButtonSourceRect, infoButtonDestinationRect, infoButtonTouchUpAnimation);
        infoButton = new Button(infoButtonSprite, soundMediaPlayer);

        /*int storeButtonWidth = 256;
        int storeButtonHeight = 256;
        Bitmap storeButtonBitmap = Utils.loadImage(root, "buttonInfoSpriteSet.png");
        Rect storeButtonSourceRect = new Rect(0, 0, spriteSourceWidth, spriteSourceHeight);
        Rect storeButtonDestinationRect = new Rect(0, getHeight() - storeButtonHeight, storeButtonWidth, getHeight());
        ImageSet storeButtonImageSet = new ImageSet(storeButtonBitmap);
        storeButtonImageSet.divideBy(spriteSourceWidth, spriteSourceHeight);
        NgAnimation storeButtonTouchUpAnimation = new NgAnimation("click", storeButtonImageSet, 0, 1);
        Sprite storeButtonSprite = new Sprite(infoButtonBitmap, storeButtonSourceRect, storeButtonDestinationRect, storeButtonTouchUpAnimation);
        storeButton = new Button(storeButtonSprite, soundMediaPlayer);*/


    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        backgroundSprite.draw(canvas);
        playButton.getSprite().draw(canvas);
        soundButtonSprite.draw(canvas);
        musicButtonSprite.draw(canvas);
        infoButton.getSprite().draw(canvas);
        //storeButton.getSprite().draw(canvas);
    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {
    }

    public boolean backPressed() {
        return false;
    }

    public void touchDown(int x, int y, int id) {

        playButton.isTouchDown(x, y);

        infoButton.isTouchDown(x, y);

        //storeButton.isTouchUp(x, y);

        //soundButton.isTouchDown(x, y);

        //musicButton.isTouchDown(x, y);


        if(soundButtonSprite.isTouchedUp(x, y)) {
            playClickSound();
            if(!soundState) {
                soundButtonSprite.playAnimationWithName("touchUpInside");
            } else {
                soundButtonSprite.playAnimationWithName("pressedTouchUpInside");
            }
        }



        if(musicButtonSprite.isTouchedUp(x, y)) {
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
        if(playButton.isTouchUp(x, y)) { goToGameCanvas(); }

        if(infoButton.isTouchUp(x, y)) { goToInfoCanvas(); }

        //if(storeButton.isTouchUp(x, y)) { goToStoreCanvas(); }

        /*
        if(soundButton.isTouchUp(x, y)) {
            soundState = !soundState;

            if(soundState) {
                soundMediaPlayer = setupMediaPlayer("sounds/click3.wav", false, false);
            } else {
                soundMediaPlayer.stop();
            }
        }

        if(musicButton.isTouchUp(x, y)) {
            musicState = !musicState;

            if(musicState) {
                musicMediaPlayer = setupMediaPlayer("sounds/bgm_menu.mp3", true, true);
            } else {
                musicMediaPlayer.stop();
            }
        }
        */


        if(soundButtonSprite.isTouchedUp(x, y)) {
            // soundButton nesnesinden parmak kaldırıldığında olacak işlemleri burada yapıyoruz.
            if(!soundState) {
                soundButtonSprite.playAnimationWithName("touchUpInside");
            } else {
                soundButtonSprite.playAnimationWithName("pressedTouchUpInside");
            }

            soundState = !soundState;

            if(soundState) {
                soundMediaPlayer = setupMediaPlayer("sounds/click3.wav", false, false);
            } else {
                soundMediaPlayer.stop();
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
                musicMediaPlayer = setupMediaPlayer("sounds/bgm_menu.mp3", true, true);
            } else {
                musicMediaPlayer.stop();
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

    public void goToStoreCanvas() {
        canvasDidDisappear();
        InfoCanvas infoCanvas = new InfoCanvas(root, musicState, soundState);
        root.canvasManager.setCurrentCanvas(infoCanvas);
    }

    private void playClickSound() {
        if(soundState) {
            soundMediaPlayer.start();
        }
    }

    private NgMediaPlayer setupMediaPlayer(String mediaFilePath, boolean loopState, boolean mediaPlayerState) {

        NgMediaPlayer mediaPlayer = new NgMediaPlayer(root);
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
            musicMediaPlayer.stop();
        }

        if(soundState) {
            soundMediaPlayer.stop();
        }
    }

}
