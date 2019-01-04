package com.ngdroidapp;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Date;

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
    private Sprite playerDilaraSprite;
    private Sprite playerEsenSprite ;
    private Sprite playerFatmaSprite;
    private Sprite playerMuhammedSprite;
    private String hakkinda[];
    private int linenumber, linespacing,location,change;
    private Paint font;
    private long startAnim, finishAnim;

    public InfoCanvas(NgApp ngApp, boolean musicState, boolean soundState) {
        super(ngApp);
        this.musicState = musicState;
        this.soundState = soundState;
    }

    public void setup() {

        startAnim = System.currentTimeMillis();

        if(soundState) {
            setupClickSound();
        }

        if(musicState) {
            setupMusic();
        }

        Bitmap backgroundBitmap = Utils.loadImage(root, "background.png");
        Rect backgroundSourceRect = new Rect(0, 250, 1020, 2040);
        Rect backgroundDestinationRect = new Rect(0, 0, getWidth(), getHeight());
        backgroundSprite = new Sprite(backgroundBitmap, backgroundSourceRect, backgroundDestinationRect);

        Bitmap playerDilaraBitmap = Utils.loadImage(root, "dilara_all.png");
        ImageSet playerDilaraImageSet = new ImageSet(playerDilaraBitmap);
        playerDilaraImageSet.divideBy(328, 296);
        NgAnimation playerDilaraAnimation = new NgAnimation("dilara", playerDilaraImageSet, 0, 3);
        Rect playerDilaraSourceRect = new Rect(0, 0, 328, 296);
        Rect playerDilaraDestinationRect = new Rect(0, 200, 328, 496);
     //   Rect playerDilaraDestinationRect = new Rect(0, 200, 328, 496);
       playerDilaraSprite = new Sprite(playerDilaraBitmap, playerDilaraSourceRect, playerDilaraDestinationRect,playerDilaraAnimation);

        Bitmap playerEsenBitmap = Utils.loadImage(root, "playerLeftImageSet.png");
        ImageSet playerEsenImageSet = new ImageSet(playerEsenBitmap);
        playerEsenImageSet.divideBy(264,304);
        NgAnimation playerEsenAnimation = new NgAnimation("esen",playerEsenImageSet,0,3);
        Rect playerEsenSourceRect = new Rect(0,0,264,304);
       Rect playerEsenDestinationRect = new Rect(getWidth()-264,596,getWidth(),900);
      //  Rect playerEsenDestinationRect = new Rect(getWidth()-264,200,getWidth(),496);
        playerEsenSprite = new Sprite(playerEsenBitmap, playerEsenSourceRect, playerEsenDestinationRect, playerEsenAnimation);

        Bitmap playerFatmaBitmap = Utils.loadImage(root, "fatma_all.png");
        ImageSet playerFatmaImageSet = new ImageSet(playerFatmaBitmap);
        playerFatmaImageSet.divideBy(264,232);
        NgAnimation playerFatmaAnimation = new NgAnimation("fatma",playerFatmaImageSet,0,3);
        Rect playerFatmaSourceRect = new Rect(0,0,264,232);
        Rect playerFatmaDestinationRect = new Rect(0,1000,264,1232);
     //   Rect playerFatmaDestinationRect = new Rect(0,getHeight()-232,264,getHeight());
        playerFatmaSprite = new Sprite(playerFatmaBitmap, playerFatmaSourceRect,playerFatmaDestinationRect,playerFatmaAnimation);

        Bitmap playerMuhammedBitmap = Utils.loadImage(root, "playerRightImageSet.png");
        ImageSet playerMuhammedImageSet = new ImageSet(playerMuhammedBitmap);
        playerMuhammedImageSet.divideBy(264,224);
        NgAnimation playerMuhammedAnimation = new NgAnimation("muhammed",playerFatmaImageSet,0,3);
        Rect playerMuhammedSourceRect = new Rect(0,0,264,224);
        Rect playerMuhammedDestinationRect = new Rect(getWidth()-264,1332,getWidth(),1556);
     //   Rect playerMuhammedDestinationRect = new Rect(getWidth()-264,getHeight()-224,getWidth(),getHeight());
        playerMuhammedSprite = new Sprite(playerMuhammedBitmap, playerMuhammedSourceRect,playerMuhammedDestinationRect,playerMuhammedAnimation);

        font= new Paint();
        font.setTextSize(getWidth()/20);
        font.setTextAlign(Paint.Align.CENTER);
        font.setARGB(255,0,0,0);
        linenumber= 4;
        hakkinda= new String[linenumber];
        hakkinda[0]= "Dilara Tülen";
        hakkinda[1]= "  Esen Bahar Türkay";
        hakkinda[2]= "Fatma Erdoğan";
        hakkinda[3]= "    Muhammed Karakul";

        location = getHeight()-(getHeight()/200);

        change =getWidth()/100;

        linespacing = getWidth()/20;



    }
    public void update() {

        location -= change;

        if (location <= - linenumber * linespacing + (getHeight()/200)) {
            location =getHeight()+(getHeight()/200);
        }

        finishAnim = System.currentTimeMillis();

        timeBoundAnimations();

        if(finishAnim - startAnim > 100) {
            startAnim = System.currentTimeMillis();
        }

    }
    private void timeBoundAnimations() {
        if(finishAnim - startAnim < 100 ){
            return;
        }


        playerDilaraSprite.playAnimationWithName("dilara");
        playerEsenSprite.playAnimationWithName("esen");
        playerFatmaSprite.playAnimationWithName("fatma");
        playerMuhammedSprite.playAnimationWithName("muhammed");

    }
    public void draw(Canvas canvas) {
        backgroundSprite.draw(canvas);
        playerDilaraSprite.draw(canvas);
        playerEsenSprite.draw(canvas);
        playerFatmaSprite.draw(canvas);
        playerMuhammedSprite.draw(canvas);
        for(int i=0;i<linenumber;i++){
            canvas.drawText(hakkinda[i],getWidthHalf(),location + i * linespacing,font);
        }
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
