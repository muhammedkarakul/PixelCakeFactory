package com.ngdroidapp;

import android.graphics.Canvas;
import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.core.NgMediaPlayer;

/**
 * Created by noyan on 24.06.2016.
 * Nitra Games Ltd.
 */

public class GameCanvas extends BaseCanvas {

    /***    SABİTLER    ***/

    // Ekranın orta noktasının koordinatlarını tutan değişkenler.
    final private int centerX = getWidth() / 2;
    final private int centerY = getHeight() / 2;

    //
    final private String platformSpriteSetFilePath = "candyPlatformSpriteSet.png";

    // Sprite görsellerinin dosya yollarını tutmak için değişken tanımlaması yapılıyor.
    final private String playerLeftSpriteSetFilePath = "dilara.png";
    final private String playerRightSpriteSetFilePath = "partyGirl.png";
    final private String converoyBeltSpriteSetFilePath = "converoyBeltSpriteSetLongVer2.png";

    // Yürüyen bantlar arasındaki boşluklar ayarlanıyor.
    final private int converoyBeltSpaces = 300;


    /***    DEĞİŞKENLER    ***/

    // Görsellerden çekilecek sprite boyutları.
    private int platformSourceWidth = 380;
    private int platformSourceHeight = 220;
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

    private int platformLeftSourceX = 380;
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

    private int playerLeftSourceWidth = 328;
    private int playerLeftSourceHeight = 296;
    private int playerLeftDestinationWidth = 164;
    private int playerLeftDestinationHeight = 148;

    private int playerRightSourceWidth = 85;
    private int playerRightSourceHeight = 178;
    private int playerRightDestinationWidth = 85;
    private int playerRightDestinationHeight = 178;

    // Swipe gesture kontrolü için dokunma anının koordinatlarını tutacak değişkenler tanımlanıyor.
    private int touchDownX = 0;
    private int touchDownY = 0;

    // Ekranın hangi tarafına dokunulduğunu tutacak değişken tanımlanıyor(false: sol, true: sağ).
    private  boolean screenSide = false;

    // Yürüyen bantlar ile ilgili değişkenler tanımlanıyor.

    private Sprite conveyorBelt;
    private Sprite conveyorBelt2;
    private Sprite conveyorBelt3;
    private Sprite conveyorBelt4;
    private Sprite conveyorBelt5;
    private Sprite conveyorBelt6;

    //private Sprite converoyBeltBottom;
    //private Sprite converoyBeltMiddle;
    //private Sprite converoyBeltMiddle2;
    //private Sprite converoyBeltMiddle3;
    //private Sprite converoyBeltTop;

    // Yürüyen bantların ölçüleri ile ilgili değişkenler.
    private int conveyorBeltDestinationWidth = 720;
    private int conveyorBeltDestinationHeight = 90;

    private int conveyorBeltSourceWidth = 1440;
    private int conveyorBeltSourceHeight = 160;

    // Yürüyen bantların konumları ile ilgili değişkenler.
    private int conveyorBeltBottom2Y = converoyBeltSpaces * 6;
    private int conveyorBeltBottomY = converoyBeltSpaces * 5;
    private int conveyorBeltMiddleY = converoyBeltSpaces * 4;
    private int conveyorBeltMiddle2Y = converoyBeltSpaces * 3;
    private int conveyorBeltMiddle3Y = converoyBeltSpaces * 2;
    private int conveyorBeltTopY = converoyBeltSpaces;

    private int conveyorBeltRightX = (int)((getWidth() - conveyorBeltDestinationWidth) / 2.2);
    private int conveyorBeltLeftX = (int) ((getWidth() - conveyorBeltDestinationWidth) / 1.8);

    // Merdivenler ile ilgili değişkenler.

    // Orta platform ile ilgili değişkenler.
    private Sprite middlePlatfrom;

    // Alt kısımdaki blok(ürünlerin çıktığı) ile ilgili değişkenler.
    //private Sprite block;
   // private int blockDimension = 300 ;

    // Orta platformun üstündeki top ile ilgili değişkenler tanımlanıyor.
    private Sprite ball;
    private int ballDimention = 200;


    // Pipe ile ilgili değişkenler tanımlanıyor.
    private Sprite pipe;

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

        playerLeft = new Sprite(root, playerLeftSpriteSetFilePath, 0, 0, 328, 296, platformLeftX, platformLeftBottomY - 148, 164, 148);
        playerRight = new Sprite(root, playerRightSpriteSetFilePath, 0, 0, 85, 178, platformRightX, platformRightBottomY - 178, 85, 178);

        conveyorBelt = new Sprite(root, converoyBeltSpriteSetFilePath, 0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight, conveyorBeltLeftX, conveyorBeltTopY, conveyorBeltDestinationWidth, conveyorBeltDestinationHeight);
        conveyorBelt2 = new Sprite(root, converoyBeltSpriteSetFilePath, 0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight, conveyorBeltRightX, conveyorBeltMiddleY, conveyorBeltDestinationWidth, conveyorBeltDestinationHeight);
        conveyorBelt3 = new Sprite(root, converoyBeltSpriteSetFilePath, 0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight, conveyorBeltLeftX, conveyorBeltMiddle2Y, conveyorBeltDestinationWidth, conveyorBeltDestinationHeight);
        conveyorBelt4 = new Sprite(root, converoyBeltSpriteSetFilePath, 0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight, conveyorBeltRightX, conveyorBeltMiddle3Y, conveyorBeltDestinationWidth, conveyorBeltDestinationHeight);
        conveyorBelt5 = new Sprite(root, converoyBeltSpriteSetFilePath, 0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight, conveyorBeltLeftX, conveyorBeltBottomY, conveyorBeltDestinationWidth, conveyorBeltDestinationHeight);
        conveyorBelt6 = new Sprite(root, converoyBeltSpriteSetFilePath, 0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight, conveyorBeltLeftX, conveyorBeltBottom2Y, conveyorBeltDestinationWidth, conveyorBeltDestinationHeight);

        //converoyBeltBottom = new Sprite(root, "converoybeltSpriteSet.png", 0, 0, 512, 128, converoyBeltRightX, converoyBeltBottomY, converoyBeltWidth, converoyBeltHeight);
        //converoyBeltMiddle = new Sprite(root, "converoybeltSpriteSet.png", 0, 0, 512, 128, converoyBeltLeftX, converoyBeltMiddleY, converoyBeltWidth, converoyBeltHeight);
        //converoyBeltMiddle2 = new Sprite(root, "converoybeltSpriteSet.png", 0, 0, 512, 128, converoyBeltRightX, converoyBeltMiddle2Y, converoyBeltWidth, converoyBeltHeight);
        //converoyBeltMiddle3 = new Sprite(root, "converoybeltSpriteSet.png", 0, 0, 512, 128, converoyBeltLeftX, converoyBeltMiddle3Y, converoyBeltWidth, converoyBeltHeight);
        //converoyBeltTop = new Sprite(root, "converoybeltSpriteSet.png", 0, 0, 512, 128, converoyBeltRightX, converoyBeltTopY, converoyBeltWidth, converoyBeltHeight);


        // Orta platform ile ilgili ilk değer atamaları.
        middlePlatfrom = new Sprite(root, "middlePlatformWithLightAndShadow.png", 0, 0, 360, 360, 0, 0, 360/3, 360/3);
        middlePlatfrom.setPosition(centerX, middlePlatfrom.getDestinationHeight() / 2);

        //block = new Sprite(root, "block.png", 0, 0, 380, 380, 0, getHeight() - 285, blockDimension, blockDimension);
        ball = new Sprite(root, "ball.png", 0, 0, 380, 380, 0, ballDimention, ballDimention, ballDimention);
        ball.setPosition(centerX, ballDimention);

        pipe = new Sprite(root, "pipeActs.png", 0, 0, 384, 384, 0, getHeight() - 384, 350, 350);
    }

    public void setupMediaPlayer(String assetFilePath) {
        player = new NgMediaPlayer(root);
        player.load(assetFilePath);
        player.prepare();
        player.setLooping(true);
        player.start();
    }

    public void update() {
        //converoyBeltAnimation(converoyBeltBottom);
        //converoyBeltAnimation(converoyBeltMiddle);
        //converoyBeltAnimation(converoyBeltMiddle2);
        //converoyBeltAnimation(converoyBeltMiddle3);
        //converoyBeltAnimation(converoyBeltTop);
        conveyorBeltAnimation(conveyorBelt);
        conveyorBeltAnimation(conveyorBelt2);
        conveyorBeltAnimation(conveyorBelt3);
        conveyorBeltAnimation(conveyorBelt4);
        conveyorBeltAnimation(conveyorBelt5);
        conveyorBeltAnimation(conveyorBelt6);

        if(pipe.getSourceX() < pipe.getSourceWidth()*9) {
            pipe.setSourceX(pipe.getSourceWidth() + pipe.getSourceX());
        } else {
            pipe.setSourceX(0);
        }


    }

    private void conveyorBeltAnimation(Sprite conveyorBelt) {
        if(conveyorBelt.getSourceY() == 0) {
            conveyorBelt.setSourceY(conveyorBelt.getSourceHeight());
        } else {
            conveyorBelt.setSourceY(0);
        }
    }

    /*
    private void converoyBeltAnimation(Sprite converoyBelt) {
        if(converoyBelt.getSourceY() == 0) {
            converoyBelt.setSourceY(converoyBelt.getSourceHeight());
        } else {
            converoyBelt.setSourceY(0);
        }
    }
    */

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

        conveyorBelt.draw(canvas);
        conveyorBelt2.draw(canvas);
        conveyorBelt3.draw(canvas);
        conveyorBelt4.draw(canvas);
        conveyorBelt5.draw(canvas);
        conveyorBelt6.draw(canvas);

        //drawConveroyBelt(canvas, 5, centerX, centerY);

        //converoyBeltBottom.draw(canvas);
        //converoyBeltMiddle.draw(canvas);
        //converoyBeltMiddle2.draw(canvas);
        //converoyBeltMiddle3.draw(canvas);
        //converoyBeltTop.draw(canvas);

        // Orta platform erkarana çiziliyor.
        for(int i = ballDimention; i < getHeight(); i = i + middlePlatfrom.getDestinationHeight()){
            middlePlatfrom.setDestinationY(i);
            middlePlatfrom.draw(canvas);
        }

        /*
        for(int i = 0; i < blockDimension * 2; i += blockDimension) {
            block.setDestinationX(i);
            block.draw(canvas);
        }
        */

        ball.draw(canvas);


        // Pipe sprite'ı ekrana çizdiriliyor.
        pipe.draw(canvas);


    }
    /*
    public void drawConveroyBelt(Canvas canvas, int width, int x, int y) {
        int converoyBeltTotalWidth = converoyBelt.getDestinationWidth() * width;

        converoyBelt.setDestinationX(x);
        converoyBelt.setDestinationY(y);
        converoyBelt.setSourceX(0);
        converoyBelt.draw(canvas);

        for(int i = converoyBelt.getDestinationWidth(); i < converoyBeltTotalWidth; i += converoyBelt.getDestinationWidth()) {
                converoyBelt.setSourceX(converoyBelt.getSourceWidth());
                converoyBelt.setDestinationX(i);
                converoyBelt.draw(canvas);
        }
        converoyBelt.setDestinationX(converoyBelt.getDestinationX()+converoyBelt.getDestinationWidth());
        converoyBelt.setSourceX(converoyBelt.getSourceWidth() * 3);
        converoyBelt.draw(canvas);


    }
    */
    private void animateConveroyBelt(Canvas canvas) {

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
                    } else if(diffrentY > 0) {
                        if(!(playerRight.getDestinationY() == (platformRightBottomY - playerRight.getDestinationHeight()))) {
                            playerRight.setDestinationY(playerRight.getDestinationY() - (platformRightMiddleY - platformRightBottomY));
                        }
                    }
                }
            }
        } else {

            Log.i(TAG, "playerLeft.getDestinationY:" + playerLeft.getDestinationY());
            Log.i(TAG, "platformTopY - playerLeft.getDestinationHeight:" + playerLeft.getDestinationY());

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
                    } else if(diffrentY > 0) {
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
