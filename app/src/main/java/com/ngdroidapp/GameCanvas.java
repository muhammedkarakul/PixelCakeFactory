package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;
import istanbul.gamelab.ngdroid.core.NgMediaPlayer;


/**
 * Created by noyan on 24.06.2016.
 * Nitra Games Ltd.
 */


public class GameCanvas extends BaseCanvas {

    /***    SABİTLER    ***/
    final private String platformSpriteSetFilePath = "platformSpriteSet.png";

    // Sprite görsellerinin dosya yollarını tutmak için değişken tanımlaması yapılıyor.
    final private String playerLeftSpriteSetFilePath = "dilaraSprite.png";
    final private String playerRightSpriteSetFilePath = "partyGirl.png";

    // Yürüyen bantlar arasındaki boşluklar ayarlanıyor.
    final private int converoyBeltSpaces = 300;


    /***    DEĞİŞKENLER    ***/

    // Görsellerden çekilecek sprite boyutları.
    private int platformSourceWidth = 320;
    private int platformSourceHeight = 200;
    private int platformDestinationWidth = 120;
    private int platformDestinationHeight = 75;

    // Platformlar ile ilgili değişkenler.

    // Soldaki platformlar için sprite nesnesi tanımlamaları yapılıyor.
    private Sprite platformLeftBottom;
    private Sprite platformLeftMiddle;
    private Sprite platformLeftTop;

    // Sağdaki platformlar için sprite nesnesi tanımlamaları yapılıyor.
    private Sprite platformRightBottom;
    private Sprite platformRightMiddle;
    private Sprite platformRightTop;

    // Platformların ekrandaki pozisyonları ile ilgili değişkenlerin tanımlama işlemleri yapılıyor.
    private int platformLeftTopY = (getHeight() - platformDestinationHeight * 11 * 2);
    private int platformLeftMiddleY = (getHeight() - platformDestinationHeight * 7 * 2);
    private int platformLeftBottomY = (getHeight() - platformDestinationHeight * 3 * 2);

    private int platformRightTopY = (getHeight() - platformDestinationHeight * 9 * 2);
    private int platformRightMiddleY = (getHeight() - platformDestinationHeight * 5 * 2);
    private int platformRightBottomY = (getHeight() - platformDestinationHeight * 2);

    private int platformRightSourceX = 0;
    private int platformRightSourceY = 0;

    private int platformLeftSourceX = 640;
    private int platformLeftSourceY = 0;

    private int platformLeftX = 0;
    private int platformRightX = (getWidth() - platformDestinationWidth);

    // Arkaplan görseli ile ilgili değişken.
    private Sprite background;

    // Müzik ile ilgili değişken.
    private NgMediaPlayer player;

    // Playerlar ile ilgili sprite nesnesi tanımlamaları yapılıyor.
    private Sprite playerLeft;
    private Sprite playerRight;

    // Swipe gesture kontrolü için dokunma anının koordinatlarını tutacak değişkenler tanımlanıyor.
    private int touchDownX = 0;
    private int touchDownY = 0;

    // Ekranın hangi tarafına dokunulduğunu tutacak değişken tanımlanıyor(false: sol, true: sağ).
    private  boolean screenSide = false;

    // Yürüyen bantlar ile ilgili değişkenler tanımlanıyor.
    private Sprite converoyBeltBottom;
    private Sprite converoyBeltMiddle;
    private Sprite converoyBeltMiddle2;
    private Sprite converoyBeltMiddle3;
    private Sprite converoyBeltTop;

    // Yürüyen bantların ölçüleri ile ilgili değişkenler.
    private int converoyBeltWidth = 512;
    private int converoyBeltHeight = 128;

    // Yürüyen bantların konumları ile ilgili değişkenler.
    private int converoyBeltBottomY = converoyBeltSpaces;
    private int converoyBeltMiddleY = converoyBeltSpaces*2;
    private int converoyBeltMiddle2Y = converoyBeltSpaces*3;
    private int converoyBeltMiddle3Y = converoyBeltSpaces*4;
    private int converoyBeltTopY = converoyBeltSpaces*5;

    private int converoyBeltRightX = (int)((getWidth() - converoyBeltWidth) / 2.2);
    private int converoyBeltLeftX = (int) ((getWidth() - converoyBeltWidth) / 1.8);

    // Merdivenler ile ilgili değişkenler.

    // Orta platform ile ilgili değişkenler.
    private Sprite middlePlatfrom;


    public GameCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void setup() {
        background = new Sprite(root, "background.png", 0, 0, 500, 500, 0, 0, getWidth(), getHeight());

        platformRightBottom = new Sprite(root, platformSpriteSetFilePath, platformRightSourceX, platformRightSourceY, platformSourceWidth, platformSourceHeight, platformRightX, platformRightBottomY, platformDestinationWidth, platformDestinationHeight);
        platformRightMiddle = new Sprite(root, platformSpriteSetFilePath, platformRightSourceX, platformRightSourceY, platformSourceWidth, platformSourceHeight, platformRightX, platformRightMiddleY, platformDestinationWidth, platformDestinationHeight);
        platformRightTop = new Sprite(root, platformSpriteSetFilePath, platformRightSourceX, platformRightSourceY, platformSourceWidth, platformSourceHeight, platformRightX, platformRightTopY, platformDestinationWidth, platformDestinationHeight);

        platformLeftBottom = new Sprite(root, platformSpriteSetFilePath, platformLeftSourceX, platformLeftSourceY, platformSourceWidth, platformSourceHeight, platformLeftX, platformLeftBottomY, platformDestinationWidth, platformDestinationHeight);
        platformLeftMiddle = new Sprite(root, platformSpriteSetFilePath, platformLeftSourceX, platformLeftSourceY, platformSourceWidth, platformSourceHeight, platformLeftX, platformLeftMiddleY, platformDestinationWidth, platformDestinationHeight);
        platformLeftTop = new Sprite(root, platformSpriteSetFilePath, platformLeftSourceX, platformLeftSourceY, platformSourceWidth, platformSourceHeight, platformLeftX, platformLeftTopY, platformDestinationWidth, platformDestinationHeight);

        setupMediaPlayer("sounds/bgm_action_5.mp3");

        playerLeft = new Sprite(root, playerLeftSpriteSetFilePath, 0, 0, 128, 128, platformLeftX, platformLeftBottomY - 256, 256, 256);
        playerRight = new Sprite(root, playerRightSpriteSetFilePath, 0, 0, 85, 178, platformRightX, platformRightBottomY - 178, 85, 178);


        converoyBeltBottom = new Sprite(root, "converoybeltSpriteSet.png", 0, 0, 512, 128, converoyBeltRightX, converoyBeltBottomY, converoyBeltWidth, converoyBeltHeight);
        converoyBeltMiddle = new Sprite(root, "converoybeltSpriteSet.png", 0, 0, 512, 128, converoyBeltLeftX, converoyBeltMiddleY, converoyBeltWidth, converoyBeltHeight);
        converoyBeltMiddle2 = new Sprite(root, "converoybeltSpriteSet.png", 0, 0, 512, 128, converoyBeltRightX, converoyBeltMiddle2Y, converoyBeltWidth, converoyBeltHeight);
        converoyBeltMiddle3 = new Sprite(root, "converoybeltSpriteSet.png", 0, 0, 512, 128, converoyBeltLeftX, converoyBeltMiddle3Y, converoyBeltWidth, converoyBeltHeight);
        converoyBeltTop = new Sprite(root, "converoybeltSpriteSet.png", 0, 0, 512, 128, converoyBeltRightX, converoyBeltTopY, converoyBeltWidth, converoyBeltHeight);


        // Orta platform ile ilgili ilk değer atamaları.
        middlePlatfrom = new Sprite(root, "middlePlatform.png", 0, 0, 360, 360, 0, 0, 180, 180);
    }

    public void setupMediaPlayer(String assetFilePath) {
        player = new NgMediaPlayer(root);
        player.load(assetFilePath);
        player.prepare();
        player.setLooping(true);
        player.start();
    }

    public void update() {
        converoyBeltAnimation(converoyBeltBottom);
        converoyBeltAnimation(converoyBeltMiddle);
        converoyBeltAnimation(converoyBeltMiddle2);
        converoyBeltAnimation(converoyBeltMiddle3);
        converoyBeltAnimation(converoyBeltTop);
    }

    private void converoyBeltAnimation(Sprite converoyBelt) {
        if(converoyBelt.getSourceY() == 0) {
            converoyBelt.setSourceY(converoyBelt.getSourceHeight());
        } else {
            converoyBelt.setSourceY(0);
        }
    }

    public void draw(Canvas canvas) {
        background.draw(canvas);

        platformRightBottom.draw(canvas);
        platformRightMiddle.draw(canvas);
        platformRightTop.draw(canvas);

        platformLeftBottom.draw(canvas);
        platformLeftMiddle.draw(canvas);
        platformLeftTop.draw(canvas);

        playerLeft.draw(canvas);
        playerRight.draw(canvas);

        converoyBeltBottom.draw(canvas);
        converoyBeltMiddle.draw(canvas);
        converoyBeltMiddle2.draw(canvas);
        converoyBeltMiddle3.draw(canvas);
        converoyBeltTop.draw(canvas);

        // Orta platform erkarana çiziliyor.
        middlePlatfrom.draw(canvas);
    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {

    }

    public boolean backPressed() {
        return true;
    }

    public void surfaceChanged(int width, int height) {

    }

    public void surfaceCreated() {

    }

    public void surfaceDestroyed() {

    }

    public void touchDown(int x, int y, int id) {
        touchDownX = x;
        touchDownY = y;

        if(touchDownX > getWidth() / 2) {
            // Ekranın sağ tarafına dokunuluyorsa burası çalışır.
            screenSide = true;
        } else {
            // Ekranın sol tarafına dokunuluyorsa burası çalışır.
            screenSide = false;
        }
    }

    public void touchMove(int x, int y, int id) {
    }

    public void touchUp(int x, int y, int id) {
        int diffrentX = x - touchDownX;
        int diffrentY = y - touchDownY;

        if(screenSide) {
            Log.i(TAG, "playerRight.getDestinationY:" + playerRight.getDestinationY());
            Log.i(TAG, "platformTopY - playerRight.getDestinationHeight:" + playerRight.getDestinationY());

            if(!(playerRight.getDestinationY() < (platformRightTopY - playerRight.getDestinationHeight()) || playerRight.getDestinationY() > (platformRightBottomY - playerRight.getDestinationHeight()))) {
                // Ekranın sağ tarafındaki player konum değiştirecek.
                if (Math.abs(diffrentX) > Math.abs(diffrentY)) {
                    // X düzlemindeki parmak hareketi Y düzlemindekinden fazla ise burası çalışır.
                } else {
                    // Y düzlemindeki parmak hareketi X düzlemindekinden fazla ise burası çalışır.
                    if (diffrentY < 0) {
                        if(!(playerRight.getDestinationY() == (platformRightTopY - playerRight.getDestinationHeight()))) {
                            playerRight.setDestinationY(playerRight.getDestinationY() + (platformRightMiddleY - platformRightBottomY));
                        }
                    } else {
                        if(!(playerRight.getDestinationY() == (platformRightBottomY - playerRight.getDestinationHeight()))) {
                            playerRight.setDestinationY(playerRight.getDestinationY() - (platformRightMiddleY - platformRightBottomY));
                        }
                    }
                }
            }
        } else {
            if(!(playerLeft.getDestinationY() < (platformLeftTopY - playerLeft.getDestinationHeight()) || playerLeft.getDestinationY() > (platformLeftBottomY - playerLeft.getDestinationHeight()))) {
                // Ekranın sol tarafındaki player konum değiştirecek.
                if (Math.abs(diffrentX) > Math.abs(diffrentY)) {
                    // X düzlemindeki parmak hareketi Y düzlemindekinden fazla ise burası çalışır.
                } else {

                    // Y düzlemindeki parmak hareketi X düzlemindekinden fazla ise burası çalışır.
                    if (diffrentY < 0) {
                        if(!(playerLeft.getDestinationY() == (platformLeftTopY - playerLeft.getDestinationHeight()))) {
                            playerLeft.setDestinationY(playerLeft.getDestinationY() + (platformLeftMiddleY - platformLeftBottomY));
                        }
                    } else {
                        if(!(playerLeft.getDestinationY() == (platformLeftBottomY - playerLeft.getDestinationHeight()))) {
                            playerLeft.setDestinationY(playerLeft.getDestinationY() - (platformLeftMiddleY - platformLeftBottomY));
                        }
                    }

                }
            }
        }

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

}
