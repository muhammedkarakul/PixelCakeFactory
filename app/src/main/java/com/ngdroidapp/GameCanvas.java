package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.Image;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.core.NgMediaPlayer;
import istanbul.gamelab.ngdroid.util.Utils;

/**
 * Created by noyan on 24.06.2016.
 * Nitra Games Ltd.
 */

public class GameCanvas extends BaseCanvas {

    /***    SABİTLER    ***/

    // Ekranın orta noktasının koordinatlarını tutan değişkenler.
    final private int centerX = getWidth() / 2;
    final private int centerY = getHeight() / 2;

    // Yer çekimi sabiti
    final private float gravity = 9.8f;

    // Yürüyen bantlar arasındaki boşluklar ayarlanıyor.
    final private int converoyBeltSpaces = 300;

    /***    DEĞİŞKENLER    ***/

    // Arkaplan görseli ile ilgili nesneler tanımlanıyor.
    private Sprite backgroundSprite;

    // Platformlar ile ilgili değişkenler.

    private int platformSourceWidth = 380;
    private int platformSourceHeight = 220;
    private int platformDestinationWidth = 120;
    private int platformDestinationHeight = 75;

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

    // Soldaki platformlar ile ilgili değişken tanımlamaları yapılıyor.

    // Sol alt platform ile ilgili nesneler tanımlanıyor.
    private Sprite platformLeftBottomSprite;

    // Sol orta platform ile ilgili nesneler tanımlanıyor.
    private Sprite platformLeftMiddleSprite;

    // Sol üst platform ile ilgili nesneler tanımlanıyor.
    private Sprite platformLeftTopSprite;

    // Sağdaki platformlar için sprite nesnesi tanımlamaları yapılıyor.

    // Sağ alt platform ile ilgili nesneler tanımlanıyor.
    private Sprite platformRightBottomSprite;

    // Sağ orta platform ile ilgili nesneler tanımlanıyor.
    private Sprite platformRightMiddleSprite;

    // Sağ üst platform ile ilgili nesneler tanımlanıyor.
    private Sprite platformRightTopSprite;

    // Müzik ile ilgili nesne tanımlanıyor.
    private NgMediaPlayer musicMediaPlayer;

    // Ses ile ilgili nesne tanımlanıyor.
    private NgMediaPlayer soundMediaPlayer;

    // Playerlar ile ilgili nesne tanımlamaları yapılıyor.

    // Soldaki oyuncu ile ilgili nesne tanımlamaları yapılıyor.
    private Sprite playerLeftSprite;

    // Sağdaki oyuncu ile ilgili nesne tanımlamaları yapılıyor.
    private Sprite playerRightSprite;

    // Swipe gesture kontrolü için dokunma anının koordinatlarını tutacak değişkenler tanımlanıyor.
    private int touchDownX = 0;
    private int touchDownY = 0;

    // Ekranın hangi tarafına dokunulduğunu tutacak değişken tanımlanıyor(false: sol, true: sağ).
    private  boolean screenSide = false;

    // Yürüyen bantlar ile ilgili nesneler tanımlanıyor.

    // Üstteki yürüyen bantla ilgili nesne tanımlamaları yapılıyor.
    private Sprite conveyorBeltLeftTopSprite;

    private Sprite conveyorBeltRightTopSprite;

    private Sprite conveyorBeltLeftMiddleSprite;

    private Sprite conveyorBeltRightMiddleSprite;

    private Sprite conveyorBeltLeftBottomSprite;

    private Sprite conveyorBeltRightBottomSprite;

    // Yürüyen bantların ölçüleri ile ilgili değişkenler.
    private int conveyorBeltDestinationWidth = 720;
    private int conveyorBeltDestinationHeight = 90;

    private int conveyorBeltSourceWidth = 1440;
    private int conveyorBeltSourceHeight = 160;

    private int conveyorBeltLeftTopY = converoyBeltSpaces;
    private int conveyorBeltRightTopY = converoyBeltSpaces * 2;
    private int conveyorBeltLeftMiddleY = converoyBeltSpaces * 3;
    private int conveyorBeltRightMiddleY = converoyBeltSpaces * 4;
    private int conveyorBeltLeftBottomY = converoyBeltSpaces * 5;
    private int conveyorBeltRightBottomY = converoyBeltSpaces * 6;


    private int conveyorBeltRightX = (int)((getWidth() - conveyorBeltDestinationWidth) / 1.8);
    private int conveyorBeltLeftX = (int) ((getWidth() - conveyorBeltDestinationWidth) / 2.2);

    // Merdivenler ile ilgili değişkenler.

    // Orta platform ile ilgili değişkenler.
    private Sprite middlePlatfromSprite;

    // Orta platformun üstündeki top ile ilgili değişkenler tanımlanıyor.
    private Sprite ballSprite;
    private int ballDimention = 200;

    // Pipe ile ilgili değişkenler tanımlanıyor.
    private Sprite pipeSprite;
    private boolean pipeAnimationState;

    private long startAnim, finishAnim;
    private int gameSpeed;

    private boolean musicState;
    private boolean soundState;

    private Sprite pauseButtonSprite;
    private boolean isGamePaused;
    private boolean isGameOver;
    //private boolean pauseState;

    // Pop Up ile ilgili nesneler tanımlanıyor.

    // Pause butonuna basınca ekranda gözükecek pop up ile ilgili sprite nesnesi tanımlanıyor.
    private Sprite gamePausedPopUpSprite;

    // Oyun bitince ekranda gözükecek pop up ile ilgili sprite nesnesi tanımlanıyor.
    private Sprite gameOverPopUpSprite;

    // Pop up üstünde bulunacak play butonu ile ilgili nesneler tanımlanıyor.
    private Sprite popUpPlayButtonSprite;

    // Pop up üstünde bulunacak menu butonu ile ilgili nesneler tanımlanıyor.
    private Sprite popUpMenuButtonSprite;

    // Pop up üstünde bulunacak retry butonu ile ilgili nesneler tanımlanıyor.
    private Sprite popUpRetryButtonSprite;

    // Oyun seviyesini tutması için değişken tanımlanıyor.
    private int level;

    // Ürün sayısını tutmamızı sağlayan değişken tanımlanıyor.
    private  int productCounter;

    // Toplam ürün sayısını tutmamızı sağlayan değişkeni tanımlıyoruz.
    private int totalNumberOfProducts;

    // Banda düşecek nesne ile ilgili tanımlamalar yapılıyor.
    /*
    private Sprite cakeSprite;
    private boolean isCakeHidden;
    private boolean isCakeChangeConveyorBelt;
    private int cakeVelocity;
    */

    // Banda düşecek ürünler ile ilgili nesne tanımlaması yapılıyor.
    private ArrayList<Product> products;

    // Oyuncu skoru
    private int score;

    // Skor ile ilgili paint nesnesi.
    private Paint paint = new Paint();



    public GameCanvas(NgApp ngApp, boolean musicState, boolean soundState) {
        super(ngApp);
        this.musicState = musicState;
        this.soundState = soundState;
    }

    public void setup() {

        totalNumberOfProducts = 0;

        level = 0;

        productCounter = 0;

        isGameOver = false;

        startAnim = System.currentTimeMillis();
        gameSpeed = 100;

        score = 0;
        Typeface face = Typeface.createFromAsset(ContextClass.getActivity().getAssets(), "fonts/pixelmix.ttf");
        paint.setTypeface(face);
        paint.setColor(Color.BLACK);
        paint.setTextSize(64);

        // Arkaplan ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap backgroundBitmap = Utils.loadImage(root, "background.png");
        Rect backgroundSourceRect = new Rect(0, 20, 1020, 2040);
        Rect backgroundDestinationRect = new Rect(0, 0, getWidth(), getHeight());
        backgroundSprite = new Sprite(backgroundBitmap, backgroundSourceRect, backgroundDestinationRect);

        String platformSpriteSetFilePath = "candyPlatformSpriteSet.png";

        // Sağ alt platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap platformRightBottomBitmap = Utils.loadImage(root, platformSpriteSetFilePath);
        Rect platformRightBottomSourceRect = new Rect(platformRightSourceX, platformRightSourceY, platformSourceWidth, platformSourceHeight);
        Rect platformRightBottomDestinationRect = new Rect(platformRightX, platformRightBottomY, platformRightX + platformDestinationWidth, platformRightBottomY + platformDestinationHeight);
        platformRightBottomSprite = new Sprite(platformRightBottomBitmap, platformRightBottomSourceRect, platformRightBottomDestinationRect);

        // Sağ orta platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap platformRightMiddleBitmap = Utils.loadImage(root, platformSpriteSetFilePath);
        Rect platformRightMiddleSourceRect = new Rect(platformRightSourceX, platformRightSourceY, platformSourceWidth, platformSourceHeight);
        Rect platformRightMiddleDestinationRect = new Rect(platformRightX, platformRightMiddleY, platformRightX + platformDestinationWidth, platformRightMiddleY + platformDestinationHeight);
        platformRightMiddleSprite = new Sprite(platformRightMiddleBitmap, platformRightMiddleSourceRect, platformRightMiddleDestinationRect);

        // Sağ üst platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap platformRightTopBitmap = Utils.loadImage(root, platformSpriteSetFilePath);
        Rect platformRightTopSourceRect = new Rect(platformRightSourceX, platformRightSourceY, platformSourceWidth, platformSourceHeight);
        Rect platformRightTopDestinationRect = new Rect(platformRightX, platformRightTopY, platformRightX + platformDestinationWidth, platformRightTopY + platformDestinationHeight);
        platformRightTopSprite = new Sprite(platformRightTopBitmap, platformRightTopSourceRect, platformRightTopDestinationRect);

        // Sol alt platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap platformLeftBottomBitmap = Utils.loadImage(root, platformSpriteSetFilePath);
        Rect platformLeftBottomSourceRect = new Rect(platformLeftSourceX, platformLeftSourceY, platformLeftSourceX + platformSourceWidth, platformLeftSourceY + platformSourceHeight);
        Rect platformLeftBottomDestinationRect = new Rect(platformLeftX, platformLeftBottomY, platformLeftX + platformDestinationWidth, platformLeftBottomY + platformDestinationHeight);
        platformLeftBottomSprite = new Sprite(platformLeftBottomBitmap, platformLeftBottomSourceRect, platformLeftBottomDestinationRect);

        // Sol orta platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap platformLeftMiddleBitmap = Utils.loadImage(root, platformSpriteSetFilePath);
        Rect platformLeftMiddleSourceRect = new Rect(platformLeftSourceX, platformLeftSourceY, platformLeftSourceX + platformSourceWidth, platformLeftSourceY + platformSourceHeight);
        Rect platformLeftMiddleDestinationRect = new Rect(platformLeftX, platformLeftMiddleY, platformLeftX + platformDestinationWidth, platformLeftMiddleY + platformDestinationHeight);
        platformLeftMiddleSprite = new Sprite(platformLeftMiddleBitmap, platformLeftMiddleSourceRect, platformLeftMiddleDestinationRect);

        // Sol üst platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap platformLeftTopBitmap = Utils.loadImage(root, platformSpriteSetFilePath);
        Rect platformLeftTopSourceRect = new Rect(platformLeftSourceX, platformLeftSourceY, platformLeftSourceX + platformSourceWidth, platformLeftSourceY + platformSourceHeight);
        Rect platformLeftTopDestinationRect = new Rect(platformLeftX, platformLeftTopY, platformLeftX + platformDestinationWidth, platformLeftTopY + platformDestinationHeight);
        platformLeftTopSprite = new Sprite(platformLeftTopBitmap, platformLeftTopSourceRect, platformLeftTopDestinationRect);

        // Arkaplanda çalacak olan ses dosyası oynatıcısının ilk değer atamaları yapılıyor.

        if(musicState) {
            musicMediaPlayer = setupMediaPlayer("sounds/bgm_action_5.mp3", true, true);
        }

        if(soundState) {
            soundMediaPlayer = setupMediaPlayer("sounds/click3.wav", false, false);
        }

        // Oyuncular ile ilgili nesnelere ilk değer atamaları yapılıyor.

        // Soldaki oyuncu ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap playerLeftBitmap = Utils.loadImage(root, "playerLeftImageSet.png");
        ImageSet playerLeftImageSet = new ImageSet(playerLeftBitmap);
        playerLeftImageSet.divideBy(264, 304);
        NgAnimation playerLeftIdleAnimation = new NgAnimation("idle", playerLeftImageSet, 0, 1);
        NgAnimation playerLeftBlinkAnimation = new NgAnimation("bink", playerLeftImageSet, 2, 3);
        NgAnimation playerLeftCarryAnimation = new NgAnimation("carry", playerLeftImageSet, 4, 5);
        Rect playerLeftSourceRect = new Rect(0, 0, 264, 304);
        Rect playerLeftDestinationRect = new Rect(platformLeftX, platformLeftBottomY - 148, platformLeftX + 164, platformLeftBottomY);
        playerLeftSprite = new Sprite(playerLeftBitmap, playerLeftSourceRect, playerLeftDestinationRect);
        playerLeftSprite.addAnimation(playerLeftIdleAnimation);
        playerLeftSprite.addAnimation(playerLeftBlinkAnimation);
        playerLeftSprite.addAnimation(playerLeftCarryAnimation);

        // Sağdaki oyuncu ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap playerRightBitmap = Utils.loadImage(root, "playerRightImageSet.png");
        ImageSet playerRightImageSet = new ImageSet(playerRightBitmap);
        playerRightImageSet.divideBy(264, 224);
        NgAnimation playerRightIdleAnimation = new NgAnimation("idle", playerRightImageSet, 0, 1);
        NgAnimation playerRightBlinkAnimation = new NgAnimation("blink", playerRightImageSet, 2, 3);
        Rect playerRightSourceRect = new Rect(0, 0, 264, 224);
        Rect playerRightDestinationRect = new Rect(getWidth() - 164, platformRightBottomY - 148, getWidth(), platformRightBottomY);
        playerRightSprite = new Sprite(playerRightBitmap, playerRightSourceRect, playerRightDestinationRect);
        playerRightSprite.addAnimation(playerRightIdleAnimation);
        playerRightSprite.addAnimation(playerRightBlinkAnimation);

        // Yürüyen bantlar ile ilgili nesnelere ilk değer atamaları yapılıyor.

        String converoyBeltSpriteSetFilePath = "converoyBeltSpriteSetLongVer2.png";

        // Üstteki yürüyen bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap conveyorBeltLeftTopBitmap = Utils.loadImage(root, converoyBeltSpriteSetFilePath);
        ImageSet conveyorBeltLeftTopImageSet = new ImageSet(conveyorBeltLeftTopBitmap);
        conveyorBeltLeftTopImageSet.divideBy(conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        NgAnimation conveyorBeltLeftTopAnimation = new NgAnimation("work", conveyorBeltLeftTopImageSet, 0, 1);
        Rect conveyorBeltLeftTopSourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        Rect conveyorBeltLeftTopDestinationRect = new Rect(conveyorBeltLeftX, conveyorBeltLeftTopY, conveyorBeltLeftX + conveyorBeltDestinationWidth, conveyorBeltLeftTopY + conveyorBeltDestinationHeight);
        conveyorBeltLeftTopSprite = new Sprite(conveyorBeltLeftTopBitmap, conveyorBeltLeftTopSourceRect, conveyorBeltLeftTopDestinationRect, conveyorBeltLeftTopAnimation);

        // Ortadaki yürüyen bantlardan 1. bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap conveyorBeltRightTopBitmap = Utils.loadImage(root, converoyBeltSpriteSetFilePath);
        ImageSet conveyorBeltRightTopImageSet = new ImageSet(conveyorBeltRightTopBitmap);
        conveyorBeltRightTopImageSet.divideBy(conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        NgAnimation conveyorBeltRightTopAnimation = new NgAnimation("work", conveyorBeltRightTopImageSet, 0, 1);
        Rect conveyorBeltRightTopSourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        Rect conveyorBeltRightTopDestinationRect = new Rect(conveyorBeltRightX, conveyorBeltRightTopY, conveyorBeltRightX + conveyorBeltDestinationWidth, conveyorBeltRightTopY + conveyorBeltDestinationHeight);
        conveyorBeltRightTopSprite = new Sprite(conveyorBeltRightTopBitmap, conveyorBeltRightTopSourceRect, conveyorBeltRightTopDestinationRect, conveyorBeltRightTopAnimation);

        // Ortadaki yürüyen bantlardan 2. bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap conveyorBeltLeftMiddleBitmap = Utils.loadImage(root, converoyBeltSpriteSetFilePath);
        ImageSet conveyorBeltLeftMiddleImageSet = new ImageSet(conveyorBeltLeftMiddleBitmap);
        conveyorBeltLeftMiddleImageSet.divideBy(conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        NgAnimation conveyorBeltLeftMiddleAnimation = new NgAnimation("work", conveyorBeltLeftMiddleImageSet, 0, 1);
        Rect conveyorBeltLeftMiddleSourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        Rect conveyorBeltLeftMiddleDestinationRect = new Rect(conveyorBeltLeftX, conveyorBeltLeftMiddleY, conveyorBeltLeftX + conveyorBeltDestinationWidth, conveyorBeltLeftMiddleY + conveyorBeltDestinationHeight);
        conveyorBeltLeftMiddleSprite = new Sprite(conveyorBeltLeftMiddleBitmap, conveyorBeltLeftMiddleSourceRect, conveyorBeltLeftMiddleDestinationRect, conveyorBeltLeftMiddleAnimation);

        // Ortadaki yürüyen bantlardan 3. bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap conveyorBeltRightMiddleBitmap = Utils.loadImage(root, converoyBeltSpriteSetFilePath);
        ImageSet conveyorBeltRightMiddleImageSet = new ImageSet(conveyorBeltRightMiddleBitmap);
        conveyorBeltRightMiddleImageSet.divideBy(conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        NgAnimation conveyorBeltRightMiddleAnimation = new NgAnimation("work", conveyorBeltRightMiddleImageSet, 0, 1);
        Rect conveyorBeltRightMiddleSourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        Rect conveyorBeltRightMiddleDestinationRect = new Rect(conveyorBeltRightX, conveyorBeltRightMiddleY, conveyorBeltRightX + conveyorBeltDestinationWidth, conveyorBeltRightMiddleY + conveyorBeltDestinationHeight);
        conveyorBeltRightMiddleSprite = new Sprite(conveyorBeltRightMiddleBitmap, conveyorBeltRightMiddleSourceRect, conveyorBeltRightMiddleDestinationRect, conveyorBeltRightMiddleAnimation);

        // Ortadaki yürüyen bantlardan 4. bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap conveyorBeltLeftBottomBitmap = Utils.loadImage(root, converoyBeltSpriteSetFilePath);
        ImageSet conveyorBeltLeftBottomImageSet = new ImageSet(conveyorBeltLeftBottomBitmap);
        conveyorBeltLeftBottomImageSet.divideBy(conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        NgAnimation conveyorBeltLeftBottomAnimation = new NgAnimation("work", conveyorBeltLeftBottomImageSet, 0, 1);
        Rect conveyorBeltLeftBottomSourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        Rect conveyorBeltLeftBottomDestinationRect = new Rect(conveyorBeltLeftX, conveyorBeltLeftBottomY, conveyorBeltLeftX + conveyorBeltDestinationWidth, conveyorBeltLeftBottomY + conveyorBeltDestinationHeight);
        conveyorBeltLeftBottomSprite = new Sprite(conveyorBeltLeftBottomBitmap, conveyorBeltLeftBottomSourceRect, conveyorBeltLeftBottomDestinationRect, conveyorBeltLeftBottomAnimation);

        // Alttaki yürüyen bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap conveyorBeltRightBottomBitmap = Utils.loadImage(root, converoyBeltSpriteSetFilePath);
        ImageSet conveyorBeltRightBottomImageSet = new ImageSet(conveyorBeltRightBottomBitmap);
        conveyorBeltRightBottomImageSet.divideBy(conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        NgAnimation conveyorBeltRightBottomAnimation = new NgAnimation("work", conveyorBeltRightBottomImageSet, 0, 1);
        Rect conveyorBeltRightBottomSourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        Rect conveyorBeltRightBottomDestinationRect = new Rect(conveyorBeltRightX, conveyorBeltRightBottomY, conveyorBeltRightX + conveyorBeltDestinationWidth, conveyorBeltRightBottomY + conveyorBeltDestinationHeight);
        conveyorBeltRightBottomSprite = new Sprite(conveyorBeltRightBottomBitmap, conveyorBeltRightBottomSourceRect, conveyorBeltRightBottomDestinationRect, conveyorBeltRightBottomAnimation);

        // Orta platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap middlePlatformBitmap = Utils.loadImage(root, "middlePlatformWithLightAndShadow.png");
        Rect middlePlatformSourceRect = new Rect( 0, 0, 360, 360);
        Rect middlePlatformDestinationRect = new Rect(0, 0, 120, 120);
        middlePlatfromSprite = new Sprite(middlePlatformBitmap, middlePlatformSourceRect, middlePlatformDestinationRect);
        middlePlatfromSprite.setPosition(centerX, middlePlatfromSprite.getDestinationHeight() / 2);

        // Orta platform üstünde bulunan top nesnesi ile ilgili değişkenlere ilk değer atamaları yapılıyor.
        Bitmap ballBitmap = Utils.loadImage(root, "ball.png");
        Rect ballSourceRect = new Rect(0, 0, 380, 380);
        Rect ballDestinationRect = new Rect(0, ballDimention, ballDimention, ballDimention + ballDimention);
        ballSprite = new Sprite(ballBitmap, ballSourceRect, ballDestinationRect);
        ballSprite.setPosition(centerX, ballDimention);

        // Kekin içinden çıktığı boru nesnesi ile ilgili ilk değer atamaları yapılıyor.
        Bitmap pipeBitmap = Utils.loadImage(root, "pipeActs.png");
        Rect pipeSourceRect = new Rect(0, 0, 384, 384);
        Rect pipeDestinationRect = new Rect(0, getHeight() - 400, 350, getHeight() - 50);
        ImageSet pipeImageSet = new ImageSet(pipeBitmap);
        pipeImageSet.divideBy(384, 384);
        NgAnimation pipeAnimation = new NgAnimation("dropOut", pipeImageSet, 0, 9, false);
        pipeSprite = new Sprite(pipeBitmap, pipeSourceRect, pipeDestinationRect, pipeAnimation);
        pipeAnimationState = true;

        // Oyunu durdurmak için kullanılan sağ üst köşedeki durdurma butonu nesnesi ile ilgili ilk değer atamaları yapılıyor.
        Bitmap pauseButtonBitmap = Utils.loadImage(root, "stopButtonImageSet.png");
        Rect pauseButtonSourceRect = new Rect(0,0,360,360);
        Rect pauseButtonDestinationRect = new Rect(getWidth() - 150, 50, getWidth() - 50, 150);
        ImageSet pauseButtonImageSet = new ImageSet(pauseButtonBitmap);
        pauseButtonImageSet.divideBy(360, 360);
        NgAnimation pauseButtonAnimation = new NgAnimation("touchUpInside", pauseButtonImageSet, 0, 1);
        pauseButtonSprite = new Sprite(pauseButtonBitmap, pauseButtonSourceRect, pauseButtonDestinationRect, pauseButtonAnimation);
        isGamePaused = false;

        // Oyun durdurulunca ekranda gözükecek pop up ile ilgili nesne ve değişkenlere ilk değer atamaları yapılıyor.
        Bitmap gamePausePopUpBitmap = Utils.loadImage(root, "gamePausedBackground.png");
        int gamePausedPopUpWidth = (int)(1256 / 1.5);
        int gamePausedPopUpHeight = (int)(1069 / 1.5);
        int gamePausedPopUpX = (getWidth() - gamePausedPopUpWidth) / 2;
        int gamePausedPopUpY = (getHeight() - gamePausedPopUpHeight) / 2;
        Rect gamePausedPopUpSourceRect = new Rect(0, 0, 1256, 1069);
        Rect gamePausedPopUpDestinationRect = new Rect(gamePausedPopUpX, gamePausedPopUpY, gamePausedPopUpWidth + gamePausedPopUpX, gamePausedPopUpHeight + gamePausedPopUpY);
        gamePausedPopUpSprite = new Sprite(gamePausePopUpBitmap, gamePausedPopUpSourceRect, gamePausedPopUpDestinationRect);

        // Oyun bitiğinde ekranda gözükecek pop up ile ilgili nesne ve değişkenlere ilk değer atamaları yapılıyor.
        Bitmap gameOverBackgroundBitmap = Utils.loadImage(root, "gameOverBackground.png");
        int gameOverPopUpWidth = (int)(1256 / 1.5);
        int gameOverPopUpHeight = (int)(1069 / 1.5);
        int gameOverPopUpX = (getWidth() - gameOverPopUpWidth) / 2;
        int gameOverPopUpY = (getHeight() - gameOverPopUpHeight) / 2;
        Rect gameOverPopUpSourceRect = new Rect(0, 0, 1256, 1069);
        Rect gameOverPopUpDestinationRect = new Rect(gameOverPopUpX, gameOverPopUpY, gameOverPopUpWidth + gameOverPopUpX, gameOverPopUpHeight + gameOverPopUpY);
        gameOverPopUpSprite = new Sprite(gameOverBackgroundBitmap, gameOverPopUpSourceRect, gameOverPopUpDestinationRect);

        // Play butonu ile ilgili değişkenlere ve nesnelere ilk değer atamaları yapılıyor.
        Bitmap popUpPlayButtonBitmap = Utils.loadImage(root,"playButtonSet.png");
        int popUpPlayButtonWidth = 200;
        int popUpPlayButtonHeight = 200;
        int popUpPlayButtonX = (getWidth() - popUpPlayButtonWidth) / 2;
        int popUpPlayButtonY = (getHeight() - popUpPlayButtonHeight) / 2;
        Rect popUpPlayButtonSourceRect = new Rect(0, 0, 360, 360);
        Rect popUpPlayButtonDestinationRect = new Rect(popUpPlayButtonX, popUpPlayButtonY, popUpPlayButtonX + popUpPlayButtonWidth, popUpPlayButtonY + popUpPlayButtonHeight);
        ImageSet popUpPlayButtonImageSet = new ImageSet(popUpPlayButtonBitmap);
        popUpPlayButtonImageSet.divideBy(360, 360);
        NgAnimation popUpPlayButtonAnimation = new NgAnimation("click", popUpPlayButtonImageSet, 0, 1);
        popUpPlayButtonSprite = new Sprite(popUpPlayButtonBitmap, popUpPlayButtonSourceRect, popUpPlayButtonDestinationRect, popUpPlayButtonAnimation);

        // Menu butonu ile ilgili değişkenlere ve nesnelere ilk değer atamaları yapılıyor.
        Bitmap popUpMenuButtonBitmap = Utils.loadImage(root, "menuButtonImageSet.png");
        int popUpMenuButtonWidth = 200;
        int popUpMenuButtonHeight = 200;
        int popUpMenuButtonX = (getWidth() - popUpMenuButtonWidth) / 2 - popUpPlayButtonWidth - 25;
        int popUpMenuButtonY = (getHeight() - popUpMenuButtonHeight) / 2;
        Rect popUpMenuButtonSourceRect = new Rect(0, 0, 360, 360);
        Rect popUpMenuButtonDestinationRect = new Rect(popUpMenuButtonX, popUpMenuButtonY, popUpMenuButtonX + popUpMenuButtonWidth, popUpMenuButtonY + popUpMenuButtonHeight);
        ImageSet popUpMenuButtonImageSet = new ImageSet(popUpMenuButtonBitmap);
        popUpMenuButtonImageSet.divideBy(360, 360);
        NgAnimation popUpMenuButtonAnimation = new NgAnimation("click", popUpMenuButtonImageSet, 0, 1);
        popUpMenuButtonSprite = new Sprite(popUpMenuButtonBitmap, popUpMenuButtonSourceRect, popUpMenuButtonDestinationRect, popUpMenuButtonAnimation);

        // Retry butonu ile ilgili değişkenlere ve nesnelere ilk değer atamaları yapılıyor.
        Bitmap popUpRetryButtonBitmap = Utils.loadImage(root, "retryButtonImageSet.png");
        int popUpRetryButtonWidth = 200;
        int popUpRetryButtonHeight = 200;
        int popUpRetryButtonX = (getWidth() - popUpRetryButtonWidth) / 2 + popUpPlayButtonWidth + 25;
        int popUpRetryButtonY = (getHeight() - popUpRetryButtonHeight) / 2;
        Rect popUpRetryButtonSourceRect = new Rect(0, 0, 360, 360);
        Rect popUpRetryButtonDestinationRect = new Rect(popUpRetryButtonX, popUpRetryButtonY, popUpRetryButtonX + popUpRetryButtonWidth, popUpRetryButtonY + popUpRetryButtonHeight);
        ImageSet popUpRetryButtonImageSet = new ImageSet(popUpRetryButtonBitmap);
        popUpRetryButtonImageSet.divideBy(360, 360);
        NgAnimation popUpRetryButtonAnimation = new NgAnimation("click", popUpRetryButtonImageSet, 0, 1);
        popUpRetryButtonSprite = new Sprite(popUpRetryButtonBitmap, popUpRetryButtonSourceRect, popUpRetryButtonDestinationRect, popUpRetryButtonAnimation);

        // Banda düşecek ürünler ile ilgili nesnelere ilk değer ataması yapılıyor.
        products = new ArrayList<Product>();
        addProduct();


        // Oyuncak ile ilgili nesnelere ilk değer atamaları yapılıyor.
        /*
        Bitmap cakeBitmap = Utils.loadImage(root, "cakeWithPlateImageSet.png");
        Rect cakeSourceRect = new Rect(0, 0, 360, 360);
        Rect cakeDestinationRect = new Rect(pipeSprite.destination.right, pipeSprite.destination.top , pipeSprite.destination.right + 150, pipeSprite.destination.top + 150);
        cakeSprite = new Sprite(cakeBitmap, cakeSourceRect, cakeDestinationRect);
        isCakeHidden = true;
        isCakeChangeConveyorBelt = false;
        cakeVelocity = 1000/gameSpeed;
        cakeSprite.setVelocityX(cakeVelocity);
        cakeSprite.setIndicatorX(1);
        cakeSprite.setIndicatorY(1);
        */
    }

    private void addProduct() {
        // Ürün ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap productBitmap = Utils.loadImage(root, "cakeWithPlateImageSet.png");
        Rect productSourceRect = new Rect(0, 0, 360, 360);
        Rect productDestinationRect = new Rect(pipeSprite.destination.right, pipeSprite.destination.top , pipeSprite.destination.right + 150, pipeSprite.destination.top + 150);
        Sprite productSprite = new Sprite(productBitmap, productSourceRect, productDestinationRect);
        Product product = new Product(productSprite);
        product.setHiddenState(true);
        product.setConveyorBeltState(false);
        product.getSprite().setVelocityX(1000/gameSpeed);
        product.getSprite().setIndicatorX(1);
        product.getSprite().setIndicatorY(1);
        products.add(product);
    }

    private Product createProduct() {
        // Ürün ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Bitmap productBitmap = Utils.loadImage(root, "cakeWithPlateImageSet.png");
        Rect productSourceRect = new Rect(0, 0, 360, 360);
        Rect productDestinationRect = new Rect(pipeSprite.destination.right, pipeSprite.destination.top , pipeSprite.destination.right + 150, pipeSprite.destination.top + 150);
        Sprite productSprite = new Sprite(productBitmap, productSourceRect, productDestinationRect);
        Product product = new Product(productSprite);
        product.setHiddenState(true);
        product.setConveyorBeltState(false);
        product.getSprite().setVelocityX(1000/gameSpeed);
        product.getSprite().setIndicatorX(1);
        product.getSprite().setIndicatorY(1);
        return  product;
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

    private void productControls(Product product) {
        // Ürün ekranın orta kısmından geçiyor mu?
        if(product.getSprite().destination.right <= middlePlatfromSprite.destination.right + 20 && product.getSprite().destination.left >= middlePlatfromSprite.destination.left - 20 && product.getConveyorBeltState()) {
            // Kek ekranın tam ortasında ise burası çalışır ve kekin görünümü değişir.
            product.getSprite().setSourceX(product.getSprite().getSourceX() + 360);
            product.setConveyorBeltState(false);
        }

        // Kek en alt bant ile çarpıştı mı?
        if(product.getSprite().destination.bottom >= conveyorBeltRightBottomSprite.destination.top){
            // Kekin alt koordinatı bandın üst koordinatına eşitlensin.
            product.getSprite().setDestinationY(conveyorBeltRightBottomSprite.getDestinationY() - product.getSprite().getDestinationHeight());

            // Kek'in dikeydeki düşüş hareketi dursun ve yatayda sağa doğru harekete başlasın.
            product.getSprite().setIndicatorY(0);
            product.getSprite().setIndicatorX(1);
        }

        // Kek sağ bandın sonuna ulaştı mı?
        if(product.getSprite().destination.right - product.getSprite().getDestinationWidth()/3 >= conveyorBeltRightX + conveyorBeltDestinationWidth) {
            // Kek dursun.
            product.getSprite().setIndicatorX(0);

            // Kek hangi bantta?
            if(product.getSprite().destination.bottom > conveyorBeltRightMiddleSprite.destination.top && playerRightSprite.destination.bottom > platformRightMiddleSprite.destination.top) {
                // Kek bir üst banda çıksın ve ters yönde hareketine devam etsin.
                product.setConveyorBeltState(true);
                product.getSprite().setDestinationY(conveyorBeltLeftBottomSprite.getDestinationY() - product.getSprite().getDestinationHeight());
                product.getSprite().setIndicatorX(-1);

            } else if(product.getSprite().destination.bottom == conveyorBeltRightMiddleSprite.destination.top && playerRightSprite.destination.bottom == platformRightMiddleSprite.destination.top) {
                // Kek bir üst banda çıksın ve ters yönde hareketine devam etsin.
                product.setConveyorBeltState(true);
                product.getSprite().setDestinationY(conveyorBeltLeftMiddleSprite.getDestinationY() - product.getSprite().getDestinationHeight());
                product.getSprite().setIndicatorX(-1);

            } else if(product.getSprite().destination.bottom < conveyorBeltRightMiddleSprite.destination.top && playerRightSprite.destination.bottom < platformRightMiddleSprite.destination.top) {
                // Kek bir üst banda çıksın ve ters yönde hareketine devam etsin.
                product.setConveyorBeltState(true);
                product.getSprite().setDestinationY(conveyorBeltLeftTopSprite.getDestinationY() - product.getSprite().getDestinationHeight());
                product.getSprite().setIndicatorX(-1);
            } else {
                isGameOver = true;
            }


        }

        //Kek sol bantın sonunda mı?
        if (product.getSprite().destination.left + product.getSprite().getDestinationWidth()/3 <= conveyorBeltLeftX ){
            // Kek'in yataydaki hareketi dursun.
            product.getSprite().setIndicatorX(0);

            if(product.getSprite().destination.bottom > conveyorBeltLeftMiddleSprite.destination.top && playerLeftSprite.destination.bottom > platformLeftMiddleSprite.destination.top) {
                // Kek bir üst banda çıksın ve ters yönde hareketine devam etsin.
                product.setConveyorBeltState(true);
                product.getSprite().setDestinationY(conveyorBeltRightMiddleSprite.getDestinationY()- product.getSprite().getDestinationHeight());
                product.getSprite().setIndicatorX(1);

            } else if(product.getSprite().destination.bottom == conveyorBeltLeftMiddleSprite.destination.top && playerLeftSprite.destination.bottom == platformLeftMiddleSprite.destination.top) {
                // Kek bir üst banda çıksın ve ters yönde hareketine devam etsin.
                product.setConveyorBeltState(true);
                product.getSprite().setDestinationY(conveyorBeltRightTopSprite.getDestinationY() - product.getSprite().getDestinationHeight());
                product.getSprite().setIndicatorX(1);

            } else if(product.getSprite().destination.bottom < conveyorBeltLeftMiddleSprite.destination.top && playerLeftSprite.destination.bottom < platformLeftMiddleSprite.destination.top && !product.getHiddenState()) {
                // Kek son bandın sonuna ulaşır. Kutu kaybolur, skor artar, oyun hızı artırılır ve oyun döngüsü başa döner.

                product.setHiddenState(true);

                product.getSprite().setSourceX(0);

                score = score + 100;

                Log.i("GameCanvas", "Score: " + score);

                if(productCounter < level) {
                    productCounter += 1;
                }

                if(gameSpeed > 50) {
                    gameSpeed = gameSpeed - 10;
                } else {
                    gameSpeed = 100;
                    level += 1;
                    productCounter = level;
                    addProduct();
                }
                product.getSprite().setVelocityX(1000/gameSpeed);

            } else {
                // Karakter ve kek'i almak için uygun platformda değilse ve kek sona geldiyse burası çalışır.
                isGameOver = true;
            }
        }

        if(product.getHiddenState()) {
            //cakeSprite.setDestinationY(conveyorBeltRightBottomSprite.getDestinationY() - cakeSprite.getDestinationHeight());
            product.getSprite().setDestinationY(pipeSprite.getDestinationY());
            product.getSprite().setDestinationX(pipeSprite.getDestinationWidth());
            product.getSprite().setIndicatorY(1);
            product.getSprite().setIndicatorX(1);
            product.getSprite().setVelocityY(0);
        } else{
            product.getSprite().setVelocityY(product.getSprite().getVelocityY() + gravity);
            product.getSprite().moveToY(product.getSprite().getVelocityY(), product.getSprite().getIndicatorY());
            product.getSprite().setIndicatorX(product.getSprite().getIndicatorX());
            //product.getSprite().setVelocityX(cakeVelocity);
            product.getSprite().moveToX();
        }
    }

    public void update() {

        if(!isGamePaused && !isGameOver) {
        // Oyun durdurulmadıysa ve bitmediyse burası çalışır.


            for(int i = 0; i < products.size(); i++) {
                productControls(products.get(i));
            }

            // Animasyonun bitiş süresi alınıyor.
            finishAnim = System.currentTimeMillis();

            // Zamana bağlı güncelleme işlemleri burada yapılır.
            timeBoundAnimation();

            // Animasyonun bitiş süresi başlangıç süresinden istenildiği kadar fark oluşturduysa(istenilen bekleme süresi geçtiyse)
            if (finishAnim - startAnim > gameSpeed) {
                // Animasyonun başlangıç süresi şimdi zamana eşitleniyor.
                startAnim = System.currentTimeMillis();
            }

        }

    }

    private void timeBoundAnimation() {

        // İstenilen bekleme süresi geçmediyse kodun geri kalanını çalıştırma.
        if(finishAnim - startAnim < gameSpeed) {
            return;
        }

        // Yürüyen bantların çalışma animasyonları oynatılıyor.
        conveyorBeltLeftTopSprite.playAnimationWithName("work");
        conveyorBeltRightTopSprite.playAnimationWithName("work");
        conveyorBeltLeftMiddleSprite.playAnimationWithName("work");
        conveyorBeltRightMiddleSprite.playAnimationWithName("work");
        conveyorBeltLeftBottomSprite.playAnimationWithName("work");
        conveyorBeltRightBottomSprite.playAnimationWithName("work");

        // Borudan pastanın çıkması animasyonu oyntılıyor.

        //if (pipeAnimationState) {
            if (pipeSprite.getAnimationWithName("dropOut").getAnimationState()) {
                pipeSprite.playAnimationWithName("dropOut");
            } else {

                if(products.get(productCounter).getHiddenState()) {
                    products.get(productCounter).setHiddenState(false);
                }

                if(productCounter > 0) {
                    productCounter -= 1;
                    pipeSprite.getAnimationWithName("dropOut").setAnimationState(true);
                }

            }

        //} else {

            //pipeAnimationState = !pipeAnimationState;
            //if(products.get(productCounter).getHiddenState()) {
            //    products.get(productCounter).setHiddenState(false);
            //}

        //}

        // Oyuncuların durma animasyonu oynatılıyor.
        playerLeftSprite.playAnimationWithName("idle");
        playerRightSprite.playAnimationWithName("idle");
    }

    public void draw(Canvas canvas) {
        // Arkaplan görseli çiziliyor.
        backgroundSprite.draw(canvas);

        // Karakterlerin üstünde durduğu ekranın sağındaki platformlar çiziliyor.
        platformRightBottomSprite.draw(canvas);
        platformRightMiddleSprite.draw(canvas);
        platformRightTopSprite.draw(canvas);

        // Karakterlerin üstünde durduğu ekranın solundaki platformlar çiziliyor.
        platformLeftBottomSprite.draw(canvas);
        platformLeftMiddleSprite.draw(canvas);
        platformLeftTopSprite.draw(canvas);

        // Karakterler ekrana çiziliyor.
        playerLeftSprite.draw(canvas);
        playerRightSprite.draw(canvas);

        // Yürüyen bantlar ekrana çiziliyor.
        conveyorBeltLeftTopSprite.draw(canvas);
        conveyorBeltRightTopSprite.draw(canvas);
        conveyorBeltLeftMiddleSprite.draw(canvas);
        conveyorBeltRightMiddleSprite.draw(canvas);
        conveyorBeltLeftBottomSprite.draw(canvas);
        conveyorBeltRightBottomSprite.draw(canvas);

        // Kek gizlenmediyse ekrana çizilir.
        /*
        if(!isCakeHidden) {
            cakeSprite.draw(canvas);
        }
        */

        for(int i = 0; i < products.size(); i++) {
            if(!products.get(i).getHiddenState()) {
                products.get(i).getSprite().draw(canvas);
            }
        }

        // Ekranı ortadan ikiye ayıran orta platform erkarana çiziliyor.
        for(int i = ballDimention; i < getHeight(); i = i + middlePlatfromSprite.getDestinationHeight()){
            middlePlatfromSprite.setDestinationY(i);
            middlePlatfromSprite.draw(canvas);
        }

        // Orta platformun üstündeki top ekrana çiziliyor.
        ballSprite.draw(canvas);

        // Pipe sprite'ı ekrana çizdiriliyor.
        pipeSprite.draw(canvas);

        // Oyunu duraklatma butonu ekrana çiziliyor.
        pauseButtonSprite.draw(canvas);

        // Eğer oyun durdurulduysa ekrana pop up menü ve üstüne devam et, yeniden başlat ve menü butonlarını çiz.
        if(isGamePaused) {
            gamePausedPopUpSprite.draw(canvas);
            popUpPlayButtonSprite.draw(canvas);
            popUpMenuButtonSprite.draw(canvas);
            popUpRetryButtonSprite.draw(canvas);
        }

        // Oyun bittiyse ekrana pop up menüyü çiz ve üstüne yeniden başlat ve menü butonlarını çiz.
        if(isGameOver) {
            gameOverPopUpSprite.draw(canvas);
            popUpMenuButtonSprite.draw(canvas);
            popUpRetryButtonSprite.draw(canvas);
        }

        // Ekrana skoru yazdır.
        canvas.drawText("Score: " + score, 20, 60, paint);

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

        // Ekrana dokunulduğu andaki x ve y koordinatlarını global değişkenlere ata.
        touchDownX = x;
        touchDownY = y;

        // Ekranın hangi tarafına dokunulduğunu algıla.
        if(touchDownX > getWidth() / 2) {
            // Ekranın sağ tarafına dokunuluyorsa burası çalışır.
            screenSide = true;
        } else {
            // Ekranın sol tarafına dokunuluyorsa burası çalışır.
            screenSide = false;
        }

        // Oyunu duraklatma butonuna basıldıysa butonun tıklanma animasyonunu oynat.
        if(pauseButtonSprite.isTouchedUp(x, y)) {
            pauseButtonSprite.playAnimationWithName("touchUpInside");
        }

        // Oyun durdurulduysa veya oyun bittiyse aşağıdaki kod bloğu çalışır.
        if(isGamePaused || isGameOver) {
            // play butonuna basıldıysa butonun click animasyonu oynar.
            if (popUpPlayButtonSprite.isTouchedUp(x, y)) {
                popUpPlayButtonSprite.playAnimationWithName("click");
            }

            // menu butonuna basıldıysa butonun click animasyonu oynar.
            if (popUpMenuButtonSprite.isTouchedUp(x, y)) {
                popUpMenuButtonSprite.playAnimationWithName("click");
            }

            // retry butonuna basıldıysa butonun click animasyonu oynar.
            if (popUpRetryButtonSprite.isTouchedUp(x, y)) {
                popUpRetryButtonSprite.playAnimationWithName("click");
            }
        }

    }

    public void touchMove(int x, int y, int id) {
    }

    public void touchUp(int x, int y, int id) {

        // Ekrana dokunulduğu andaki x ve y konumları ile parmağın ekrandan kalktığı andaki x ve y koordinatlarının farkları alınıyor.
        int diffrentX = x - touchDownX;
        int diffrentY = y - touchDownY;

        // Oyun durdurulmadıysa veya oyun bitmediyse burası çalışır.
        if(!isGamePaused && !isGameOver) {
            // Ekranın sağ tarafında parmak kaydırma işlemi yapıldıysa burası çalışır.
            if (screenSide) {
                if (!(playerRightSprite.getDestinationY() < (platformRightTopY - playerRightSprite.getDestinationHeight()) || playerRightSprite.getDestinationY() > (platformRightBottomY - playerRightSprite.getDestinationHeight()))) {
                    // Ekranın sağ tarafındaki player konum değiştirecek.
                    if (Math.abs(diffrentX) > Math.abs(diffrentY)) {
                        // X düzlemindeki parmak hareketi Y düzlemindekinden fazla ise burası çalışır.
                    } else {
                        // Y düzlemindeki parmak hareketi X düzlemindekinden fazla ise burası çalışır.
                        if (diffrentY < 0) {
                            // Parmak yukarı yöne doğru kaydırıldıysa burası çalışır.
                            if (!(playerRightSprite.getDestinationY() == (platformRightTopY - playerRightSprite.getDestinationHeight()))) {
                                playerRightSprite.setDestinationY(playerRightSprite.getDestinationY() + (platformRightMiddleY - platformRightBottomY));
                            }
                        } else if (diffrentY > 0) {
                            // Parmak aşağı yöne doğru kaydırıldıysa burası çalışır.
                            if (!(playerRightSprite.getDestinationY() == (platformRightBottomY - playerRightSprite.getDestinationHeight()))) {
                                playerRightSprite.setDestinationY(playerRightSprite.getDestinationY() - (platformRightMiddleY - platformRightBottomY));
                            }
                        }
                    }
                }
            } else {
                // Ekranın sol tarafında parmak kaydırma işlemi yapıldıysa burası çalışır.
                if (!(playerLeftSprite.getDestinationY() < (platformLeftTopY - playerLeftSprite.getDestinationHeight()) || playerLeftSprite.getDestinationY() > (platformLeftBottomY - playerLeftSprite.getDestinationHeight()))) {
                    // Ekranın sol tarafındaki player konum değiştirecek.
                    if (Math.abs(diffrentX) > Math.abs(diffrentY)) {
                        // X düzlemindeki parmak hareketi Y düzlemindekinden fazla ise burası çalışır.
                    } else {
                        // Y düzlemindeki parmak hareketi X düzlemindekinden fazla ise burası çalışır.
                        if (diffrentY < 0) {
                            // Parmak yukarı yöne doğru kaydırıldıysa burası çalışır.
                            if (!(playerLeftSprite.getDestinationY() == (platformLeftTopY - playerLeftSprite.getDestinationHeight()))) {
                                // Sol karakter en üst platformda değilse burası çalışır ve karakter bir üst platforma geçer.
                                playerLeftSprite.setDestinationY(playerLeftSprite.getDestinationY() + (platformLeftMiddleY - platformLeftBottomY));
                            }
                        } else if (diffrentY > 0) {
                            // Parmak aşağı yöne doğru kaydırıldıysa burası çalışır.
                            if (!(playerLeftSprite.getDestinationY() == (platformLeftBottomY - playerLeftSprite.getDestinationHeight()))) {
                                // Soldaki karakter en alt platformda değilse burası çalışır ve karakter bir alt platforma geçer.
                                playerLeftSprite.setDestinationY(playerLeftSprite.getDestinationY() - (platformLeftMiddleY - platformLeftBottomY));
                            }
                        }

                    }
                }
            }
        }

        // pause butonuna basıldıysa butonun click animasyonu oynar ve oyunun duraklatma durumunu tutan boolean değişkenin durunmu terslenir.
        if(pauseButtonSprite.isTouchedUp(x, y)) {
            pauseButtonSprite.playAnimationWithName("touchUpInside");
            isGamePaused = !isGamePaused;
        }

        // Oyun duraklatıldıysa veya oyun bitti ise aşağıdaki kod bloğu çalışır.
        if(isGamePaused || isGameOver) {

            // Play butonuna basıldıysa butonun click animasyonunu oynat ve oyunun duraklatma durumunu tutan boolean değişkeni tersle.
            if (popUpPlayButtonSprite.isTouchedUp(x, y)) {
                popUpPlayButtonSprite.playAnimationWithName("click");
                isGamePaused = !isGamePaused;
            }

            // Menu butonuna basıldıysa butonun click animasyonunu oynat ve menüye geri dön.
            if (popUpMenuButtonSprite.isTouchedUp(x, y)) {
                popUpMenuButtonSprite.playAnimationWithName("click");
                // Ana menüye geri dön.
                goToMainCanvas();
            }

            // Retry butonuna basıldıysa butonun click animasyonunu oynat ve oyunu yeniden başlat.
            if (popUpRetryButtonSprite.isTouchedUp(x, y)) {
                popUpRetryButtonSprite.playAnimationWithName("click");
                // Oyunu yeniden başlat.
                restartGame();
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

    /**
     *  Ses ve müzik dosyası çalınıyorsa durdurur ve ana menüye geri döner.
     */
    private void goToMainCanvas() {

        stopSoundAndMusic();

        MenuCanvas menuCanvas = new MenuCanvas(root, musicState, soundState);
        root.canvasManager.setCurrentCanvas(menuCanvas);
    }

    /**
     * Ses ve müzik dosyası çalınıyorsa durdurur ve ana menüye geri döner.
     */
    private void restartGame() {

        stopSoundAndMusic();

        root.canvasManager.setCurrentCanvas(new GameCanvas(root, musicState, soundState));
    }

    /**
     * Ses ve müzik medya oynatıcısı dosya çalıyorsa durdurur.
     */
    private void stopSoundAndMusic() {

        if(soundState) {
            // Ses dosyası çalınıyorsa durdur.
            soundMediaPlayer.stop();
        }

        if(musicState) {
            // Müzik dosyası çalınıyorsa durdur.
            musicMediaPlayer.stop();
        }

    }

}
