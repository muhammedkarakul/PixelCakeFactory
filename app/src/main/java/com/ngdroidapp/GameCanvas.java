package com.ngdroidapp;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.List;

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

    // Oyuncuların üstünde durduğu platform görsellerinin çekildiği dosya yolu.
    final private String platformSpriteSetFilePath = "candyPlatformSpriteSet.png";

    // Sprite görsellerinin dosya yollarını tutmak için değişken tanımlaması yapılıyor.
    final private String playerLeftSpriteSetFilePath = "dilara.png";
    final private String playerRightSpriteSetFilePath = "dilara.png";
    final private String converoyBeltSpriteSetFilePath = "converoyBeltSpriteSetLongVer2.png";

    // Yürüyen bantlar arasındaki boşluklar ayarlanıyor.
    final private int converoyBeltSpaces = 300;

    /***    DEĞİŞKENLER    ***/

    // Arkaplan görseli ile ilgili nesneler tanımlanıyor.
    private Sprite backgroundSprite;
    private Rect backgroundSourceRect;
    private Rect backgroundDestinationRect;

    // Görsellerden çekilecek sprite boyutları ile ilgili değişkenler tanımlanıyor.
    private int platformSourceWidth = 380;
    private int platformSourceHeight = 220;
    private int platformDestinationWidth = 120;
    private int platformDestinationHeight = 75;

    // Platformlar ile ilgili değişkenler.

    // Soldaki platformlar ile ilgili değişken tanımlamaları yapılıyor.

    // Sol alt platform ile ilgili nesneler tanımlanıyor.
    private Sprite platformLeftBottomSprite;
    private Rect platformLeftBottomSourceRect;
    private Rect platformLeftBottomDestinationRect;

    // Sol orta platform ile ilgili nesneler tanımlanıyor.
    private Sprite platformLeftMiddleSprite;
    private Rect platformLeftMiddleSourceRect;
    private Rect platformLeftMiddleDestinationRect;

    // Sol üst platform ile ilgili nesneler tanımlanıyor.
    private Sprite platformLeftTopSprite;
    private Rect platformLeftTopSourceRect;
    private Rect platformLeftTopDestinationRect;

    // Sağdaki platformlar için sprite nesnesi tanımlamaları yapılıyor.

    // Sağ alt platform ile ilgili nesneler tanımlanıyor.
    private Sprite platformRightBottomSprite;
    private Rect platformRightBottomSourceRect;
    private Rect platformRightBottomDestinationRect;

    // Sağ orta platform ile ilgili nesneler tanımlanıyor.
    private Sprite platformRightMiddleSprite;
    private Rect platformRightMiddleSourceRect;
    private Rect platformRightMiddleDestinationRect;

    // Sağ üst platform ile ilgili nesneler tanımlanıyor.
    private Sprite platformRightTopSprite;
    private Rect platformRightTopSourceRect;
    private Rect platformRightTopDestinationRect;

    // Platformların ekrandaki pozisyonları ile ilgili değişkenlerin tanımlama işlemleri yapılıyor.
    private int platformLeftTopY = (getHeight() - platformDestinationHeight * 22);
    private int platformLeftMiddleY = (getHeight() - platformDestinationHeight * 14);
    private int platformLeftBottomY = (getHeight() - platformDestinationHeight * 6);

    private int platformRightTopY = (getHeight() - platformDestinationHeight * 18);
    private int platformRightMiddleY = (getHeight() - platformDestinationHeight * 10);
    private int platformRightBottomY = (getHeight() - platformDestinationHeight * 2);

    private int platformRightSourceX = 0;
    private int platformRightSourceY = 0;

    private int platformLeftSourceX = 380;
    private int platformLeftSourceY = 0;

    private int platformLeftX = 0;
    private int platformRightX = (getWidth() - platformDestinationWidth);



    // Müzik ile ilgili değişken.
    private NgMediaPlayer player;

    // Playerlar ile ilgili nesne tanımlamaları yapılıyor.

    // Soldaki oyuncu ile ilgili nesne tanımlamaları yapılıyor.
    private Sprite playerLeftSprite;
    private Rect playerLeftSourceRect;
    private Rect playerLeftDestinationRect;

    // Sağdaki oyuncu ile ilgili nesne tanımlamaları yapılıyor.
    private Sprite playerRightSprite;
    private Rect playerRightSourceRect;
    private Rect playerRightDestinationRect;

    // Swipe gesture kontrolü için dokunma anının koordinatlarını tutacak değişkenler tanımlanıyor.
    private int touchDownX = 0;
    private int touchDownY = 0;

    // Ekranın hangi tarafına dokunulduğunu tutacak değişken tanımlanıyor(false: sol, true: sağ).
    private  boolean screenSide = false;

    // Yürüyen bantlar ile ilgili nesneler tanımlanıyor.

    // Üstteki yürüyen bantla ilgili nesne tanımlamaları yapılıyor.
    private Sprite conveyorBeltTopSprite;
    private Rect conveyorBeltTopSourceRect;
    private Rect conveyorBeltTopDestinationRect;

    private Sprite conveyorBeltMiddle1Sprite;
    private Rect conveyorBeltMiddle1SourceRect;
    private Rect conveyorBeltMiddle1DestinationRect;

    private Sprite conveyorBeltMiddle2Sprite;
    private Rect conveyorBeltMiddle2SourceRect;
    private Rect conveyorBeltMiddle2DestinationRect;

    private Sprite conveyorBeltMiddle3Sprite;
    private Rect conveyorBeltMiddle3SourceRect;
    private Rect conveyorBeltMiddle3DestinationRect;

    private Sprite conveyorBeltMiddle4Sprite;
    private Rect conveyorBeltMiddle4SourceRect;
    private Rect conveyorBeltMiddle4DestinationRect;

    private Sprite conveyorBeltBottomSprite;
    private Rect conveyorBeltBottomSourceRect;
    private Rect conveyorBeltBottomDestinationRect;

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

    private int conveyorBeltRightX = (int)((getWidth() - conveyorBeltDestinationWidth) / 1.8);
    private int conveyorBeltLeftX = (int) ((getWidth() - conveyorBeltDestinationWidth) / 2.2);

    // Merdivenler ile ilgili değişkenler.

    // Orta platform ile ilgili değişkenler.
    private Sprite middlePlatfromSprite;
    private Rect middlePlatformSourceRect;
    private Rect middlePlatformDestinationRect;

    // Alt kısımdaki blok(ürünlerin çıktığı) ile ilgili değişkenler.
    //private Sprite block;
   // private int blockDimension = 300 ;

    // Orta platformun üstündeki top ile ilgili değişkenler tanımlanıyor.
    private Sprite ballSprite;
    private Rect ballSourceRect;
    private Rect ballDestinationRect;
    private int ballDimention = 200;


    // Pipe ile ilgili değişkenler tanımlanıyor.
    private Sprite pipeSprite;
    private Rect pipeSourceRect;
    private Rect pipeDestinationRect;

    // ImageSet denemesi
    //private ImageSet imageSet;

    //private Rect imageSetDestination;

    private long startAnim, finishAnim;

    private Sprite testSprite;
    private Rect testSourceRect;
    private Rect testDestinationRect;
    private ImageSet testImageSet;
    private NgAnimation testAnimation;

    //private Sprite testSprite;

    public GameCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void setup() {

        // Arkaplan ile ilgili nesnelere ilk değer atamaları yapılıyor.
        backgroundSourceRect = new Rect(0, 20, 1020, 2040);
        backgroundDestinationRect = new Rect(0, 0, getWidth(), getHeight());
        backgroundSprite = new Sprite(root, "background.png", backgroundSourceRect, backgroundDestinationRect);

        // Sağ alt platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        platformRightBottomSourceRect = new Rect(platformRightSourceX, platformRightSourceY, platformSourceWidth, platformSourceHeight);
        platformRightBottomDestinationRect = new Rect(platformRightX, platformRightBottomY, platformRightX + platformDestinationWidth, platformRightBottomY + platformDestinationHeight);
        platformRightBottomSprite = new Sprite(root, platformSpriteSetFilePath, platformRightBottomSourceRect, platformRightBottomDestinationRect);

        // Sağ orta platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        platformRightMiddleSourceRect = new Rect(platformRightSourceX, platformRightSourceY, platformSourceWidth, platformSourceHeight);
        platformRightMiddleDestinationRect = new Rect(platformRightX, platformRightMiddleY, platformRightX + platformDestinationWidth, platformRightMiddleY + platformDestinationHeight);
        platformRightMiddleSprite = new Sprite(root, platformSpriteSetFilePath, platformRightMiddleSourceRect, platformRightMiddleDestinationRect);

        // Sağ üst platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        platformRightTopSourceRect = new Rect(platformRightSourceX, platformRightSourceY, platformSourceWidth, platformSourceHeight);
        platformRightTopDestinationRect = new Rect(platformRightX, platformRightTopY, platformRightX + platformDestinationWidth, platformRightTopY + platformDestinationHeight);
        platformRightTopSprite = new Sprite(root, platformSpriteSetFilePath, platformRightTopSourceRect, platformRightTopDestinationRect);

        // Sol alt platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        platformLeftBottomSourceRect = new Rect(platformLeftSourceX, platformLeftSourceY, platformLeftSourceX + platformSourceWidth, platformLeftSourceY + platformSourceHeight);
        platformLeftBottomDestinationRect = new Rect(platformLeftX, platformLeftBottomY, platformLeftX + platformDestinationWidth, platformLeftBottomY + platformDestinationHeight);
        platformLeftBottomSprite = new Sprite(root, platformSpriteSetFilePath, platformLeftBottomSourceRect, platformLeftBottomDestinationRect);

        // Sol orta platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        platformLeftMiddleSourceRect = new Rect(platformLeftSourceX, platformLeftSourceY, platformLeftSourceX + platformSourceWidth, platformLeftSourceY + platformSourceHeight);
        platformLeftMiddleDestinationRect = new Rect(platformLeftX, platformLeftMiddleY, platformLeftX + platformDestinationWidth, platformLeftMiddleY + platformDestinationHeight);
        platformLeftMiddleSprite = new Sprite(root, platformSpriteSetFilePath, platformLeftMiddleSourceRect, platformLeftMiddleDestinationRect);

        // Sol üst platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        platformLeftTopSourceRect = new Rect(platformLeftSourceX, platformLeftSourceY, platformLeftSourceX + platformSourceWidth, platformLeftSourceY + platformSourceHeight);
        platformLeftTopDestinationRect = new Rect(platformLeftX, platformLeftTopY, platformLeftX + platformDestinationWidth, platformLeftTopY + platformDestinationHeight);
        platformLeftTopSprite = new Sprite(root, platformSpriteSetFilePath, platformLeftTopSourceRect, platformLeftTopDestinationRect);

        // Arkaplanda çalacak olan ses dosyası oynatıcısının ilk değer atamaları yapılıyor.
        setupMediaPlayer("sounds/bgm_action_5.mp3");

        // Oyuncular ile ilgili nesnelere ilk değer atamaları yapılıyor.

        // Soldaki oyuncu ile ilgili nesnelere ilk değer atamaları yapılıyor.
        playerLeftSourceRect = new Rect(0, 0, 328, 296);
        playerLeftDestinationRect = new Rect(platformLeftX, platformLeftBottomY - 148, platformLeftX + 164, platformLeftBottomY);
        playerLeftSprite = new Sprite(root, playerLeftSpriteSetFilePath, playerLeftSourceRect, playerLeftDestinationRect);

        // Sağdaki oyuncu ile ilgili nesnelere ilk değer atamaları yapılıyor.
        playerRightSourceRect = new Rect(0, 0, 328, 296);
        playerRightDestinationRect = new Rect(getWidth() - 164, platformRightBottomY - 148,getWidth(), platformRightBottomY);
        playerRightSprite = new Sprite(root, playerRightSpriteSetFilePath, playerRightSourceRect, playerRightDestinationRect);


        // Yürüyen bantlar ile ilgili nesnelere ilk değer atamaları yapılıyor.

        // Üstteki yürüyen bantla ilgili değişkenlere ilk değer atamaları yapılıyor.
        conveyorBeltTopSourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        conveyorBeltTopDestinationRect = new Rect(conveyorBeltLeftX, conveyorBeltTopY, conveyorBeltLeftX + conveyorBeltDestinationWidth, conveyorBeltTopY + conveyorBeltDestinationHeight);
        conveyorBeltTopSprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltTopSourceRect, conveyorBeltTopDestinationRect);

        conveyorBeltMiddle1SourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        conveyorBeltMiddle1DestinationRect = new Rect(conveyorBeltRightX, conveyorBeltMiddleY, conveyorBeltRightX + conveyorBeltDestinationWidth, conveyorBeltMiddleY + conveyorBeltDestinationHeight);
        conveyorBeltMiddle1Sprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltMiddle1SourceRect, conveyorBeltMiddle1DestinationRect);

        conveyorBeltMiddle2SourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        conveyorBeltMiddle2DestinationRect = new Rect(conveyorBeltLeftX, conveyorBeltMiddle2Y, conveyorBeltLeftX + conveyorBeltDestinationWidth, conveyorBeltMiddle2Y + conveyorBeltDestinationHeight);
        conveyorBeltMiddle2Sprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltMiddle2SourceRect, conveyorBeltMiddle2DestinationRect);

        conveyorBeltMiddle3SourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        conveyorBeltMiddle3DestinationRect = new Rect(conveyorBeltRightX, conveyorBeltMiddle3Y, conveyorBeltRightX + conveyorBeltDestinationWidth, conveyorBeltMiddle3Y + conveyorBeltDestinationHeight);
        conveyorBeltMiddle3Sprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltMiddle3SourceRect, conveyorBeltMiddle3DestinationRect);

        conveyorBeltMiddle4SourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        conveyorBeltMiddle4DestinationRect = new Rect(conveyorBeltLeftX, conveyorBeltBottomY, conveyorBeltLeftX + conveyorBeltDestinationWidth, conveyorBeltBottomY + conveyorBeltDestinationHeight);
        conveyorBeltMiddle4Sprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltMiddle4SourceRect, conveyorBeltMiddle4DestinationRect);

        conveyorBeltBottomSourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        conveyorBeltBottomDestinationRect = new Rect(conveyorBeltRightX, conveyorBeltBottom2Y, conveyorBeltRightX + conveyorBeltDestinationWidth, conveyorBeltBottom2Y + conveyorBeltDestinationHeight);
        conveyorBeltBottomSprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltBottomSourceRect, conveyorBeltBottomDestinationRect);

        // Orta platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        middlePlatformSourceRect = new Rect( 0, 0, 360, 360);
        middlePlatformDestinationRect = new Rect(0, 0, 120, 120);
        middlePlatfromSprite = new Sprite(root, "middlePlatformWithLightAndShadow.png", middlePlatformSourceRect, middlePlatformDestinationRect);
        middlePlatfromSprite.setPosition(centerX, middlePlatfromSprite.getDestinationHeight() / 2);

        // Orta platform üstünde bulunan top nesnesi ile ilgili değişkenlere ilk değer atamaları yapılıyor.
        ballSourceRect = new Rect(0, 0, 380, 380);
        ballDestinationRect = new Rect(0, ballDimention, ballDimention, ballDimention + ballDimention);
        ballSprite = new Sprite(root, "ball.png", ballSourceRect, ballDestinationRect);
        ballSprite.setPosition(centerX, ballDimention);


        pipeSourceRect = new Rect(0, 0, 384, 384);
        pipeDestinationRect = new Rect(0, getHeight() - 400, 350, getHeight() - 50);
        pipeSprite = new Sprite(root, "pipeActs.png", pipeSourceRect, pipeDestinationRect);
        /*
        imageSet = new ImageSet(root, "pipeActs.png");
        List<Rect> imageSetRect = imageSet.divideBy(384, 384);
        int i = 0;
        for(Rect r : imageSetRect) {
            Log.i(TAG,"Rect " + i + " x: " + r.left + "," + " y: " + r.top);
            i++;
        }
        */
        startAnim = System.currentTimeMillis();

        testSourceRect = new Rect(0, 0, 384, 384);
        testDestinationRect = new Rect(0, 0, 384, 384);
        testImageSet = new ImageSet(root, "pipeActs.png");
        testImageSet.divideBy(384, 384);
        testAnimation = new NgAnimation(root, "testAnimation", testImageSet, 0, 9);
        testSprite = new Sprite(root, "pipeActs.png", testSourceRect, testDestinationRect, testAnimation);


    }

    public void setupMediaPlayer(String assetFilePath) {
        player = new NgMediaPlayer(root);
        player.load(assetFilePath);
        player.prepare();
        player.setLooping(true);
        player.start();
    }

    public void update() {


        finishAnim =  System.currentTimeMillis();
        conveyorBeltAnimation(conveyorBeltTopSprite);
        conveyorBeltAnimation(conveyorBeltMiddle1Sprite);
        conveyorBeltAnimation(conveyorBeltMiddle2Sprite);
        conveyorBeltAnimation(conveyorBeltMiddle3Sprite);
        conveyorBeltAnimation(conveyorBeltMiddle4Sprite);
        conveyorBeltAnimation(conveyorBeltBottomSprite);

        if(finishAnim - startAnim > 70)
            startAnim = System.currentTimeMillis();


        if(pipeSprite.getSourceX() < pipeSprite.getSourceWidth()*9) {
            pipeSprite.setSourceX(pipeSprite.getSourceWidth() + pipeSprite.getSourceX());
        } else {
            pipeSprite.setSourceX(0);
        }
        Log.i(TAG, "testSprite source rect: x: " + testSprite.getSourceX() + ", y:" + testSprite.getSourceY());
        testSprite.playAnimationWithName("testAnimation");

    }

    private void conveyorBeltAnimation(Sprite conveyorBelt) {

        if(finishAnim - startAnim < 70) {
            return;
        }

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
        backgroundSprite.draw(canvas);

        platformRightBottomSprite.draw(canvas);
        platformRightMiddleSprite.draw(canvas);
        platformRightTopSprite.draw(canvas);

        platformLeftBottomSprite.draw(canvas);
        platformLeftMiddleSprite.draw(canvas);
        platformLeftTopSprite.draw(canvas);

        playerLeftSprite.draw(canvas);
        playerRightSprite.draw(canvas);

        conveyorBeltTopSprite.draw(canvas);
        conveyorBeltMiddle1Sprite.draw(canvas);
        conveyorBeltMiddle2Sprite.draw(canvas);
        conveyorBeltMiddle3Sprite.draw(canvas);
        conveyorBeltMiddle4Sprite.draw(canvas);
        conveyorBeltBottomSprite.draw(canvas);

        //drawConveroyBelt(canvas, 5, centerX, centerY);

        //converoyBeltBottom.draw(canvas);
        //converoyBeltMiddle.draw(canvas);
        //converoyBeltMiddle2.draw(canvas);
        //converoyBeltMiddle3.draw(canvas);
        //converoyBeltTop.draw(canvas);

        // Orta platform erkarana çiziliyor.
        for(int i = ballDimention; i < getHeight(); i = i + middlePlatfromSprite.getDestinationHeight()){
            middlePlatfromSprite.setDestinationY(i);
            middlePlatfromSprite.draw(canvas);
        }

        /*
        for(int i = 0; i < blockDimension * 2; i += blockDimension) {
            block.setDestinationX(i);
            block.draw(canvas);
        }


        for(int i = 0; i < imageSet.getImageRectCount(); i++) {

        }
        */
        ballSprite.draw(canvas);


        // Pipe sprite'ı ekrana çizdiriliyor.
        pipeSprite.draw(canvas);


        testSprite.draw(canvas);

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
            Log.i(TAG, "playerRight.getDestinationY:" + playerRightSprite.getDestinationY());
            Log.i(TAG, "platformTopY - playerRight.getDestinationHeight:" + playerRightSprite.getDestinationY());

            if(!(playerRightSprite.getDestinationY() < (platformRightTopY - playerRightSprite.getDestinationHeight()) || playerRightSprite.getDestinationY() > (platformRightBottomY - playerRightSprite.getDestinationHeight()))) {
                // Ekranın sağ tarafındaki player konum değiştirecek.
                if (Math.abs(diffrentX) > Math.abs(diffrentY)) {
                    // X düzlemindeki parmak hareketi Y düzlemindekinden fazla ise burası çalışır.
                } else {
                    // Y düzlemindeki parmak hareketi X düzlemindekinden fazla ise burası çalışır.
                    if (diffrentY < 0) {
                        if(!(playerRightSprite.getDestinationY() == (platformRightTopY - playerRightSprite.getDestinationHeight()))) {
                            playerRightSprite.setDestinationY(playerRightSprite.getDestinationY() + (platformRightMiddleY - platformRightBottomY));
                        }
                    } else if(diffrentY > 0) {
                        if(!(playerRightSprite.getDestinationY() == (platformRightBottomY - playerRightSprite.getDestinationHeight()))) {
                            playerRightSprite.setDestinationY(playerRightSprite.getDestinationY() - (platformRightMiddleY - platformRightBottomY));
                        }
                    }
                }
            }
        } else {

            Log.i(TAG, "playerLeft.getDestinationY:" + playerLeftSprite.getDestinationY());
            Log.i(TAG, "platformTopY - playerLeft.getDestinationHeight:" + playerLeftSprite.getDestinationY());

            if(!(playerLeftSprite.getDestinationY() < (platformLeftTopY - playerLeftSprite.getDestinationHeight()) || playerLeftSprite.getDestinationY() > (platformLeftBottomY - playerLeftSprite.getDestinationHeight()))) {
                // Ekranın sol tarafındaki player konum değiştirecek.
                if (Math.abs(diffrentX) > Math.abs(diffrentY)) {
                    // X düzlemindeki parmak hareketi Y düzlemindekinden fazla ise burası çalışır.
                } else {

                    // Y düzlemindeki parmak hareketi X düzlemindekinden fazla ise burası çalışır.
                    if (diffrentY < 0) {
                        if(!(playerLeftSprite.getDestinationY() == (platformLeftTopY - playerLeftSprite.getDestinationHeight()))) {
                            playerLeftSprite.setDestinationY(playerLeftSprite.getDestinationY() + (platformLeftMiddleY - platformLeftBottomY));
                        }
                    } else if(diffrentY > 0) {
                        if(!(playerLeftSprite.getDestinationY() == (platformLeftBottomY - playerLeftSprite.getDestinationHeight()))) {
                            playerLeftSprite.setDestinationY(playerLeftSprite.getDestinationY() - (platformLeftMiddleY - platformLeftBottomY));
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
