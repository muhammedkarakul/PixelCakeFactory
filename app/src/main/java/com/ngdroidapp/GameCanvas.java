package com.ngdroidapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;

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

    // Yer çekimi sabiti
    final private float gravity = 9.8f;

    // Oyuncuların üstünde durduğu platform görsellerinin çekildiği dosya yolu.
    final private String platformSpriteSetFilePath = "candyPlatformSpriteSet.png";

    // Sprite görsellerinin dosya yollarını tutmak için değişken tanımlaması yapılıyor.
    final private String playerLeftSpriteSetFilePath = "dilara.png";
    final private String playerRightSpriteSetFilePath = "dilara.png";
    final private String converoyBeltSpriteSetFilePath = "converoyBeltSpriteSetLongVer2.png";
    final private String gamePausedBackgroundImagePath = "gamePausedBackground.png";
    final private String gameOverBackgroundImagePath = "gameOverBackground.png";

    // Yürüyen bantlar arasındaki boşluklar ayarlanıyor.
    final private int converoyBeltSpaces = 300;

    /***    DEĞİŞKENLER    ***/

    // Arkaplan görseli ile ilgili nesneler tanımlanıyor.
    private Sprite backgroundSprite;

    // Görsellerden çekilecek sprite boyutları ile ilgili değişkenler tanımlanıyor.
    private int platformSourceWidth = 380;
    private int platformSourceHeight = 220;
    private int platformDestinationWidth = 120;
    private int platformDestinationHeight = 75;

    // Platformlar ile ilgili değişkenler.

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

    // Bantların sağ ve sol için yüksekliklerini tutan enum değişkenler.
    /*
    private enum LeftConveyorBelt {
        BOTTOM,
        MIDDLE,
        TOP;
    }
    */

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
    private int popUpPlayButtonX;
    private int popUpPlayButtonY;
    private int popUpPlayButtonWidth;
    private int popUpPlayButtonHeight;
    private String popUpPlayButtonImagePath;

    // Pop up üstünde bulunacak menu butonu ile ilgili nesneler tanımlanıyor.
    private Sprite popUpMenuButtonSprite;
    private int popUpMenuButtonX;
    private int popUpMenuButtonY;
    private int popUpMenuButtonWidth;
    private int popUpMenuButtonHeight;
    private String popUpMenuButtonImagePath;

    // Pop up üstünde bulunacak retry butonu ile ilgili nesneler tanımlanıyor.
    private Sprite popUpRetryButtonSprite;
    private int popUpRetryButtonX;
    private int popUpRetryButtonY;
    private int popUpRetryButtonWidth;
    private int popUpRetryButtonHeight;
    private String popUpRetryButtonImagePath;

    // Banda düşecek oyuncak nesnesi ile ilgili tanımlamalar yapılıyor.
    private Sprite cakeSprite;
    private boolean isCakeHidden;
    private boolean isCakeChangeConveyorBelt;
    private boolean isCakeFallingDown;
    private int cakeVelocity;

    private int score;

    Paint paint = new Paint();
    // Ekrana skoru yazdırmak için nesneler tanımlanıyor.

    //Paint paint = new Paint();


    public GameCanvas(NgApp ngApp, boolean musicState, boolean soundState) {
        super(ngApp);
        this.musicState = musicState;
        this.soundState = soundState;
    }

    public void setup() {

        isGameOver = false;

        startAnim = System.currentTimeMillis();
        gameSpeed = 100;

        score = 0;
        paint.setColor(Color.BLACK);
        paint.setTextSize(64);

        // Arkaplan ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Rect backgroundSourceRect = new Rect(0, 20, 1020, 2040);
        Rect backgroundDestinationRect = new Rect(0, 0, getWidth(), getHeight());
        backgroundSprite = new Sprite(root, "background.png", backgroundSourceRect, backgroundDestinationRect);

        // Sağ alt platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Rect platformRightBottomSourceRect = new Rect(platformRightSourceX, platformRightSourceY, platformSourceWidth, platformSourceHeight);
        Rect platformRightBottomDestinationRect = new Rect(platformRightX, platformRightBottomY, platformRightX + platformDestinationWidth, platformRightBottomY + platformDestinationHeight);
        platformRightBottomSprite = new Sprite(root, platformSpriteSetFilePath, platformRightBottomSourceRect, platformRightBottomDestinationRect);

        // Sağ orta platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Rect platformRightMiddleSourceRect = new Rect(platformRightSourceX, platformRightSourceY, platformSourceWidth, platformSourceHeight);
        Rect platformRightMiddleDestinationRect = new Rect(platformRightX, platformRightMiddleY, platformRightX + platformDestinationWidth, platformRightMiddleY + platformDestinationHeight);
        platformRightMiddleSprite = new Sprite(root, platformSpriteSetFilePath, platformRightMiddleSourceRect, platformRightMiddleDestinationRect);

        // Sağ üst platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Rect platformRightTopSourceRect = new Rect(platformRightSourceX, platformRightSourceY, platformSourceWidth, platformSourceHeight);
        Rect platformRightTopDestinationRect = new Rect(platformRightX, platformRightTopY, platformRightX + platformDestinationWidth, platformRightTopY + platformDestinationHeight);
        platformRightTopSprite = new Sprite(root, platformSpriteSetFilePath, platformRightTopSourceRect, platformRightTopDestinationRect);

        // Sol alt platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Rect platformLeftBottomSourceRect = new Rect(platformLeftSourceX, platformLeftSourceY, platformLeftSourceX + platformSourceWidth, platformLeftSourceY + platformSourceHeight);
        Rect platformLeftBottomDestinationRect = new Rect(platformLeftX, platformLeftBottomY, platformLeftX + platformDestinationWidth, platformLeftBottomY + platformDestinationHeight);
        platformLeftBottomSprite = new Sprite(root, platformSpriteSetFilePath, platformLeftBottomSourceRect, platformLeftBottomDestinationRect);

        // Sol orta platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Rect platformLeftMiddleSourceRect = new Rect(platformLeftSourceX, platformLeftSourceY, platformLeftSourceX + platformSourceWidth, platformLeftSourceY + platformSourceHeight);
        Rect platformLeftMiddleDestinationRect = new Rect(platformLeftX, platformLeftMiddleY, platformLeftX + platformDestinationWidth, platformLeftMiddleY + platformDestinationHeight);
        platformLeftMiddleSprite = new Sprite(root, platformSpriteSetFilePath, platformLeftMiddleSourceRect, platformLeftMiddleDestinationRect);

        // Sol üst platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Rect platformLeftTopSourceRect = new Rect(platformLeftSourceX, platformLeftSourceY, platformLeftSourceX + platformSourceWidth, platformLeftSourceY + platformSourceHeight);
        Rect platformLeftTopDestinationRect = new Rect(platformLeftX, platformLeftTopY, platformLeftX + platformDestinationWidth, platformLeftTopY + platformDestinationHeight);
        platformLeftTopSprite = new Sprite(root, platformSpriteSetFilePath, platformLeftTopSourceRect, platformLeftTopDestinationRect);

        // Arkaplanda çalacak olan ses dosyası oynatıcısının ilk değer atamaları yapılıyor.

        if(musicState) {
            musicMediaPlayer = setupMediaPlayer(musicMediaPlayer, "sounds/bgm_action_5.mp3", true, true);
        }

        if(soundState) {
            soundMediaPlayer = setupMediaPlayer(soundMediaPlayer, "sounds/click3.wav", false, false);
        }

        // Oyuncular ile ilgili nesnelere ilk değer atamaları yapılıyor.

        // Soldaki oyuncu ile ilgili nesnelere ilk değer atamaları yapılıyor.
        ImageSet playerLeftImageSet = new ImageSet(root, "playerLeftImageSet.png");
        playerLeftImageSet.divideBy(264, 304);
        NgAnimation playerLeftIdleAnimation = new NgAnimation(root, "idle", playerLeftImageSet, 0, 1);
        NgAnimation playerLeftBlinkAnimation = new NgAnimation(root, "bink", playerLeftImageSet, 2, 3);
        Rect playerLeftSourceRect = new Rect(0, 0, 264, 304);
        Rect playerLeftDestinationRect = new Rect(platformLeftX, platformLeftBottomY - 148, platformLeftX + 164, platformLeftBottomY);
        playerLeftSprite = new Sprite(root, "playerLeftImageSet.png", playerLeftSourceRect, playerLeftDestinationRect);
        playerLeftSprite.addAnimation(playerLeftIdleAnimation);
        playerLeftSprite.addAnimation(playerLeftBlinkAnimation);

        // Sağdaki oyuncu ile ilgili nesnelere ilk değer atamaları yapılıyor.

        ImageSet playerRightImageSet = new ImageSet(root, "playerRightImageSet.png");
        playerRightImageSet.divideBy(264, 224);
        NgAnimation playerRightIdleAnimation = new NgAnimation(root, "idle", playerRightImageSet, 0, 1);
        NgAnimation playerRightBlinkAnimation = new NgAnimation(root, "blink", playerRightImageSet, 2, 3);
        Rect playerRightSourceRect = new Rect(0, 0, 264, 224);
        Rect playerRightDestinationRect = new Rect(getWidth() - 164, platformRightBottomY - 148, getWidth(), platformRightBottomY);
        playerRightSprite = new Sprite(root, "playerRightImageSet.png", playerRightSourceRect, playerRightDestinationRect);
        playerRightSprite.addAnimation(playerRightIdleAnimation);
        playerRightSprite.addAnimation(playerRightBlinkAnimation);

        // Yürüyen bantlar ile ilgili nesnelere ilk değer atamaları yapılıyor.

        // Üstteki yürüyen bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        ImageSet conveyorBeltLeftTopImageSet = new ImageSet(root, converoyBeltSpriteSetFilePath);
        conveyorBeltLeftTopImageSet.divideBy(conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        NgAnimation conveyorBeltLeftTopAnimation = new NgAnimation(root, "work", conveyorBeltLeftTopImageSet, 0, 1);
        Rect conveyorBeltLeftTopSourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        Rect conveyorBeltLeftTopDestinationRect = new Rect(conveyorBeltLeftX, conveyorBeltLeftTopY, conveyorBeltLeftX + conveyorBeltDestinationWidth, conveyorBeltLeftTopY + conveyorBeltDestinationHeight);
        conveyorBeltLeftTopSprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltLeftTopSourceRect, conveyorBeltLeftTopDestinationRect, conveyorBeltLeftTopAnimation);

        // Ortadaki yürüyen bantlardan 1. bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        ImageSet conveyorBeltRightTopImageSet = new ImageSet(root, converoyBeltSpriteSetFilePath);
        conveyorBeltRightTopImageSet.divideBy(conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        NgAnimation conveyorBeltRightTopAnimation = new NgAnimation(root, "work", conveyorBeltRightTopImageSet, 0, 1);
        Rect conveyorBeltRightTopSourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        Rect conveyorBeltRightTopDestinationRect = new Rect(conveyorBeltRightX, conveyorBeltRightTopY, conveyorBeltRightX + conveyorBeltDestinationWidth, conveyorBeltRightTopY + conveyorBeltDestinationHeight);
        conveyorBeltRightTopSprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltRightTopSourceRect, conveyorBeltRightTopDestinationRect, conveyorBeltRightTopAnimation);

        // Ortadaki yürüyen bantlardan 2. bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        ImageSet conveyorBeltLeftMiddleImageSet = new ImageSet(root, converoyBeltSpriteSetFilePath);
        conveyorBeltLeftMiddleImageSet.divideBy(conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        NgAnimation conveyorBeltLeftMiddleAnimation = new NgAnimation(root, "work", conveyorBeltLeftMiddleImageSet, 0, 1);
        Rect conveyorBeltLeftMiddleSourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        Rect conveyorBeltLeftMiddleDestinationRect = new Rect(conveyorBeltLeftX, conveyorBeltLeftMiddleY, conveyorBeltLeftX + conveyorBeltDestinationWidth, conveyorBeltLeftMiddleY + conveyorBeltDestinationHeight);
        conveyorBeltLeftMiddleSprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltLeftMiddleSourceRect, conveyorBeltLeftMiddleDestinationRect, conveyorBeltLeftMiddleAnimation);

        // Ortadaki yürüyen bantlardan 3. bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        ImageSet conveyorBeltRightMiddleImageSet = new ImageSet(root, converoyBeltSpriteSetFilePath);
        conveyorBeltRightMiddleImageSet.divideBy(conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        NgAnimation conveyorBeltRightMiddleAnimation = new NgAnimation(root, "work", conveyorBeltRightMiddleImageSet, 0, 1);
        Rect conveyorBeltRightMiddleSourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        Rect conveyorBeltRightMiddleDestinationRect = new Rect(conveyorBeltRightX, conveyorBeltRightMiddleY, conveyorBeltRightX + conveyorBeltDestinationWidth, conveyorBeltRightMiddleY + conveyorBeltDestinationHeight);
        conveyorBeltRightMiddleSprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltRightMiddleSourceRect, conveyorBeltRightMiddleDestinationRect, conveyorBeltRightMiddleAnimation);

        // Ortadaki yürüyen bantlardan 4. bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        ImageSet conveyorBeltLeftBottomImageSet = new ImageSet(root, converoyBeltSpriteSetFilePath);
        conveyorBeltLeftBottomImageSet.divideBy(conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        NgAnimation conveyorBeltLeftBottomAnimation = new NgAnimation(root, "work", conveyorBeltLeftBottomImageSet, 0, 1);
        Rect conveyorBeltLeftBottomSourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        Rect conveyorBeltLeftBottomDestinationRect = new Rect(conveyorBeltLeftX, conveyorBeltLeftBottomY, conveyorBeltLeftX + conveyorBeltDestinationWidth, conveyorBeltLeftBottomY + conveyorBeltDestinationHeight);
        conveyorBeltLeftBottomSprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltLeftBottomSourceRect, conveyorBeltLeftBottomDestinationRect, conveyorBeltLeftBottomAnimation);

        // Alttaki yürüyen bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        ImageSet conveyorBeltRightBottomImageSet = new ImageSet(root, converoyBeltSpriteSetFilePath);
        conveyorBeltRightBottomImageSet.divideBy(conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        NgAnimation conveyorBeltRightBottomAnimation = new NgAnimation(root, "work", conveyorBeltRightBottomImageSet, 0, 1);
        Rect conveyorBeltRightBottomSourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        Rect conveyorBeltRightBottomDestinationRect = new Rect(conveyorBeltRightX, conveyorBeltRightBottomY, conveyorBeltRightX + conveyorBeltDestinationWidth, conveyorBeltRightBottomY + conveyorBeltDestinationHeight);
        conveyorBeltRightBottomSprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltRightBottomSourceRect, conveyorBeltRightBottomDestinationRect, conveyorBeltRightBottomAnimation);

        // Orta platform ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Rect middlePlatformSourceRect = new Rect( 0, 0, 360, 360);
        Rect middlePlatformDestinationRect = new Rect(0, 0, 120, 120);
        middlePlatfromSprite = new Sprite(root, "middlePlatformWithLightAndShadow.png", middlePlatformSourceRect, middlePlatformDestinationRect);
        middlePlatfromSprite.setPosition(centerX, middlePlatfromSprite.getDestinationHeight() / 2);

        // Orta platform üstünde bulunan top nesnesi ile ilgili değişkenlere ilk değer atamaları yapılıyor.
        Rect ballSourceRect = new Rect(0, 0, 380, 380);
        Rect ballDestinationRect = new Rect(0, ballDimention, ballDimention, ballDimention + ballDimention);
        ballSprite = new Sprite(root, "ball.png", ballSourceRect, ballDestinationRect);
        ballSprite.setPosition(centerX, ballDimention);


        Rect pipeSourceRect = new Rect(0, 0, 384, 384);
        Rect pipeDestinationRect = new Rect(0, getHeight() - 400, 350, getHeight() - 50);
        ImageSet pipeImageSet = new ImageSet(root, "pipeActs.png");
        pipeImageSet.divideBy(384, 384);
        NgAnimation pipeAnimation = new NgAnimation(root, "dropOut", pipeImageSet, 0, 9, false);
        pipeSprite = new Sprite(root, "pipeActs.png", pipeSourceRect, pipeDestinationRect, pipeAnimation);
        pipeAnimationState = true;

        isGamePaused = false;
        Rect pauseButtonSourceRect = new Rect(0,0,360,360);
        Rect pauseButtonDestinationRect = new Rect(getWidth() - 150, 50, getWidth() - 50, 150);
        ImageSet pauseButtonImageSet = new ImageSet(root, "stopButtonImageSet.png");
        pauseButtonImageSet.divideBy(360, 360);
        NgAnimation pauseButtonAnimation = new NgAnimation(root, "touchUpInside", pauseButtonImageSet, 0, 1);
        pauseButtonSprite = new Sprite(root, "stopButtonImageSet.png", pauseButtonSourceRect, pauseButtonDestinationRect, pauseButtonAnimation);

        // Oyun durdurulunca ekranda gözükecek pop up ile ilgili nesne ve değişkenlere ilk değer atamaları yapılıyor.
        int gamePausedPopUpWidth = (int)(1256 / 1.5);
        int gamePausedPopUpHeight = (int)(1069 / 1.5);
        int gamePausedPopUpX = (getWidth() - gamePausedPopUpWidth) / 2;
        int gamePausedPopUpY = (getHeight() - gamePausedPopUpHeight) / 2;
        Rect gamePausedPopUpSourceRect = new Rect(0, 0, 1256, 1069);
        Rect gamePausedPopUpDestinationRect = new Rect(gamePausedPopUpX, gamePausedPopUpY, gamePausedPopUpWidth + gamePausedPopUpX, gamePausedPopUpHeight + gamePausedPopUpY);
        gamePausedPopUpSprite = new Sprite(root, gamePausedBackgroundImagePath, gamePausedPopUpSourceRect, gamePausedPopUpDestinationRect);

        // Oyun bitiğinde ekranda gözükecek pop up ile ilgili nesne ve değişkenlere ilk değer atamaları yapılıyor.
        int gameOverPopUpWidth = (int)(1256 / 1.5);
        int gameOverPopUpHeight = (int)(1069 / 1.5);
        int gameOverPopUpX = (getWidth() - gameOverPopUpWidth) / 2;
        int gameOverPopUpY = (getHeight() - gameOverPopUpHeight) / 2;
        Rect gameOverPopUpSourceRect = new Rect(0, 0, 1256, 1069);
        Rect gameOverPopUpDestinationRect = new Rect(gameOverPopUpX, gameOverPopUpY, gameOverPopUpWidth + gameOverPopUpX, gameOverPopUpHeight + gameOverPopUpY);
        gameOverPopUpSprite = new Sprite(root, gameOverBackgroundImagePath, gameOverPopUpSourceRect, gameOverPopUpDestinationRect);

        popUpPlayButtonImagePath = "playButtonSet.png";
        popUpPlayButtonWidth = 200;
        popUpPlayButtonHeight = 200;
        popUpPlayButtonX = (getWidth() - popUpPlayButtonWidth) / 2;
        popUpPlayButtonY = (getHeight() - popUpPlayButtonHeight) / 2;
        Rect popUpPlayButtonSourceRect = new Rect(0, 0, 360, 360);
        Rect popUpPlayButtonDestinationRect = new Rect(popUpPlayButtonX, popUpPlayButtonY, popUpPlayButtonX + popUpPlayButtonWidth, popUpPlayButtonY + popUpPlayButtonHeight);
        ImageSet popUpPlayButtonImageSet = new ImageSet(root, popUpPlayButtonImagePath);
        popUpPlayButtonImageSet.divideBy(360, 360);
        NgAnimation popUpPlayButtonAnimation = new NgAnimation(root, "click", popUpPlayButtonImageSet, 0, 1);
        popUpPlayButtonSprite = new Sprite(root, popUpPlayButtonImagePath, popUpPlayButtonSourceRect, popUpPlayButtonDestinationRect, popUpPlayButtonAnimation);

        popUpMenuButtonImagePath = "menuButtonImageSet.png";
        popUpMenuButtonWidth = 200;
        popUpMenuButtonHeight = 200;
        popUpMenuButtonX = (getWidth() - popUpMenuButtonWidth) / 2 - popUpPlayButtonWidth - 25;
        popUpMenuButtonY = (getHeight() - popUpMenuButtonHeight) / 2;
        Rect popUpMenuButtonSourceRect = new Rect(0, 0, 360, 360);
        Rect popUpMenuButtonDestinationRect = new Rect(popUpMenuButtonX, popUpMenuButtonY, popUpMenuButtonX + popUpMenuButtonWidth, popUpMenuButtonY + popUpMenuButtonHeight);
        ImageSet popUpMenuButtonImageSet = new ImageSet(root, popUpMenuButtonImagePath);
        popUpMenuButtonImageSet.divideBy(360, 360);
        NgAnimation popUpMenuButtonAnimation = new NgAnimation(root, "click", popUpMenuButtonImageSet, 0, 1);
        popUpMenuButtonSprite = new Sprite(root, popUpMenuButtonImagePath, popUpMenuButtonSourceRect, popUpMenuButtonDestinationRect, popUpMenuButtonAnimation);

        popUpRetryButtonImagePath = "retryButtonImageSet.png";
        popUpRetryButtonWidth = 200;
        popUpRetryButtonHeight = 200;
        popUpRetryButtonX = (getWidth() - popUpRetryButtonWidth) / 2 + popUpPlayButtonWidth + 25;
        popUpRetryButtonY = (getHeight() - popUpRetryButtonHeight) / 2;
        Rect popUpRetryButtonSourceRect = new Rect(0, 0, 360, 360);
        Rect popUpRetryButtonDestinationRect = new Rect(popUpRetryButtonX, popUpRetryButtonY, popUpRetryButtonX + popUpRetryButtonWidth, popUpRetryButtonY + popUpRetryButtonHeight);
        ImageSet popUpRetryButtonImageSet = new ImageSet(root, popUpRetryButtonImagePath);
        popUpRetryButtonImageSet.divideBy(360, 360);
        NgAnimation popUpRetryButtonAnimation = new NgAnimation(root, "click", popUpRetryButtonImageSet, 0, 1);
        popUpRetryButtonSprite = new Sprite(root, popUpRetryButtonImagePath, popUpRetryButtonSourceRect, popUpRetryButtonDestinationRect, popUpRetryButtonAnimation);


        // Oyuncak ile ilgili nesnelere ilk değer atamaları yapılıyor.
        Rect cakeSourceRect = new Rect(0, 0, 360, 360);
        Rect cakeDestinationRect = new Rect(pipeSprite.destination.right, pipeSprite.destination.top , pipeSprite.destination.right + 150, pipeSprite.destination.top + 150);
        cakeSprite = new Sprite(root, "cakeWithPlateImageSet.png", cakeSourceRect, cakeDestinationRect);
        isCakeHidden = true;
        isCakeChangeConveyorBelt = false;
        cakeVelocity = 1000/gameSpeed;
        cakeSprite.setVelocityX(cakeVelocity);
        cakeSprite.setIndicatorX(1);
        cakeSprite.setIndicatorY(1);
        isCakeFallingDown = false;
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

    public void update() {

        if(!isGamePaused && !isGameOver) {

            if(cakeSprite.destination.right <= middlePlatfromSprite.destination.right + 20 && cakeSprite.destination.left >= middlePlatfromSprite.destination.left - 20 && isCakeChangeConveyorBelt) {
                // Kek ekranın tam ortasında mı?
                cakeSprite.setSourceX(cakeSprite.getSourceX() + 360);
                isCakeChangeConveyorBelt = false;
            }

            if(cakeSprite.destination.bottom >= conveyorBeltRightBottomSprite.destination.top){
                cakeSprite.destination.bottom = conveyorBeltRightBottomSprite.destination.top;
                cakeSprite.setIndicatorY(0);
            }


            if(!isCakeHidden) {
                cakeSprite.setIndicatorY(cakeSprite.getIndicatorY());
                cakeSprite.setVelocityY(cakeSprite.getVelocityY() + gravity);
                cakeSprite.moveToY(cakeSprite.getVelocityY(), cakeSprite.getIndicatorY());
            } else{
                cakeSprite.setIndicatorX(1);
                cakeSprite.setDestinationY(conveyorBeltRightBottomSprite.getDestinationY() - cakeSprite.getDestinationHeight());
                cakeSprite.setVelocityX(cakeVelocity);
                cakeSprite.moveToX();
            }

            if(!isCakeHidden) {
                cakeSprite.setVelocityX(cakeVelocity);
                cakeSprite.moveToX();
            }

            // Kek band˝n sonuna ula˛t˝ m˝?
            if(cakeSprite.destination.right - cakeSprite.getDestinationWidth()/3 >= conveyorBeltRightX + conveyorBeltDestinationWidth && !isCakeFallingDown) {
                // Kek dursun.

                cakeSprite.setIndicatorX(0);


                // Kek hangi bantta?
                if(cakeSprite.destination.bottom > conveyorBeltRightMiddleSprite.destination.top && playerRightSprite.destination.bottom > platformRightMiddleSprite.destination.top) {
                    //kek  sa alt bantta ise buras˝ Áal˝˛
                    //kek sa alt bantta ise sa karakter de sa platformdaysa keki left bottoma ta˛˝.
                    isCakeChangeConveyorBelt = true;
                    cakeSprite.setDestinationY(conveyorBeltLeftBottomSprite.getDestinationY() - cakeSprite.getDestinationHeight());
                    cakeSprite.setVelocityX(cakeVelocity);
                    //sum = cakeSprite.getDestinationX();
                    cakeSprite.setIndicatorX(-1);

                } else if(cakeSprite.destination.bottom == conveyorBeltRightMiddleSprite.destination.top && playerRightSprite.destination.bottom == platformRightMiddleSprite.destination.top) {
                    // Sa karakter sa orta platformda ise ve ayn˝ zamanda kek de sa orta bantta ise buras˝ Áal˝˛˝r.
                    isCakeChangeConveyorBelt = true;
                    cakeSprite.setDestinationY(conveyorBeltLeftMiddleSprite.getDestinationY() - cakeSprite.getDestinationHeight());
                    cakeSprite.setVelocityX(cakeVelocity);
                    //sum = cakeSprite.getDestinationX();
                    cakeSprite.setIndicatorX(-1);
                    Log.i("Bant","SaOrtaBant");

                } else if(cakeSprite.destination.bottom < conveyorBeltRightMiddleSprite.destination.top && playerRightSprite.destination.bottom < platformRightMiddleSprite.destination.top) {
                    // Sa karakter sa ¸st platformda ise ve ayn˝ zamanda kek de sa ¸st bantta ise buras˝ Áal˝˛˝r.
                    isCakeChangeConveyorBelt = true;
                    cakeSprite.setDestinationY(conveyorBeltLeftTopSprite.getDestinationY() - cakeSprite.getDestinationHeight());
                    cakeSprite.setVelocityX(cakeVelocity);
                    //sum = cakeSprite.getDestinationX();
                    cakeSprite.setIndicatorX(-1);
                    Log.i("Bant","Sa‹stBant");
                } else {
                    isGameOver = true;

                    Log.i(TAG, "Game Over");
                }


            }

            //kek bant˝n ba˛˝nda m˝ ?
            if (cakeSprite.destination.left + cakeSprite.getDestinationWidth()/3 <= conveyorBeltLeftX && !isCakeFallingDown ){

                // Kek dursun.
                cakeSprite.setIndicatorX(0);

                if(cakeSprite.destination.bottom > conveyorBeltLeftMiddleSprite.destination.top && playerLeftSprite.destination.bottom > platformLeftMiddleSprite.destination.top) {

                    isCakeChangeConveyorBelt = true;
                    cakeSprite.setDestinationY(conveyorBeltRightMiddleSprite.getDestinationY()- cakeSprite.getDestinationHeight());
                    cakeSprite.setVelocityX(cakeVelocity);
                    //sum = cakeSprite.getDestinationX();
                    cakeSprite.setIndicatorX(1);

                } else if(cakeSprite.destination.bottom == conveyorBeltLeftMiddleSprite.destination.top && playerLeftSprite.destination.bottom == platformLeftMiddleSprite.destination.top) {

                    isCakeChangeConveyorBelt = true;
                    cakeSprite.setDestinationY(conveyorBeltRightTopSprite.getDestinationY()- cakeSprite.getDestinationHeight());
                    cakeSprite.setVelocityX(cakeVelocity);
                    //sum = cakeSprite.getDestinationX();
                    cakeSprite.setIndicatorX(1);

                } else if(cakeSprite.destination.bottom < conveyorBeltLeftMiddleSprite.destination.top && playerLeftSprite.destination.bottom < platformLeftMiddleSprite.destination.top) {
                    //Log.i(TAG, "Skoru artır.");

                    isCakeHidden = true;
                    score = score + 100;
                    cakeSprite.setSourceX(0);
                    pipeSprite.getAnimationWithName("dropOut").setAnimationState(true);
                    pipeAnimationState = true;
                    if(gameSpeed != 40) {
                        gameSpeed = gameSpeed - 10;
                    } else {
                        gameSpeed = 100;
                    }
                    cakeVelocity = 1000/gameSpeed;

                } else {

                    isGameOver = true;
                    Log.i(TAG, "Game Over");
                }
            }

            finishAnim = System.currentTimeMillis();

            timeBoundAnimation();

            if (finishAnim - startAnim > gameSpeed) {
                startAnim = System.currentTimeMillis();
            }

        }

    }

    private void timeBoundAnimation() {

        if(finishAnim - startAnim < gameSpeed) {
            return;
        }

        conveyorBeltLeftTopSprite.playAnimationWithName("work");
        conveyorBeltRightTopSprite.playAnimationWithName("work");
        conveyorBeltLeftMiddleSprite.playAnimationWithName("work");
        conveyorBeltRightMiddleSprite.playAnimationWithName("work");
        conveyorBeltLeftBottomSprite.playAnimationWithName("work");
        conveyorBeltRightBottomSprite.playAnimationWithName("work");

        if (pipeAnimationState) {
            if (pipeSprite.getAnimationWithName("dropOut").getAnimationState()) {
                pipeSprite.playAnimationWithName("dropOut");
            } else {
                pipeAnimationState = !pipeAnimationState;
                isCakeHidden = false;
            }
        }

        playerLeftSprite.playAnimationWithName("idle");

        playerRightSprite.playAnimationWithName("idle");
    }

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

        conveyorBeltLeftTopSprite.draw(canvas);
        conveyorBeltRightTopSprite.draw(canvas);
        conveyorBeltLeftMiddleSprite.draw(canvas);
        conveyorBeltRightMiddleSprite.draw(canvas);
        conveyorBeltLeftBottomSprite.draw(canvas);
        conveyorBeltRightBottomSprite.draw(canvas);

        if(!isCakeHidden) {
            cakeSprite.draw(canvas);
        }

        // Orta platform erkarana çiziliyor.
        for(int i = ballDimention; i < getHeight(); i = i + middlePlatfromSprite.getDestinationHeight()){
            middlePlatfromSprite.setDestinationY(i);
            middlePlatfromSprite.draw(canvas);
        }

        ballSprite.draw(canvas);

        // Pipe sprite'ı ekrana çizdiriliyor.
        pipeSprite.draw(canvas);

        pauseButtonSprite.draw(canvas);

        if(isGamePaused) {
            gamePausedPopUpSprite.draw(canvas);
            popUpPlayButtonSprite.draw(canvas);
            popUpMenuButtonSprite.draw(canvas);
            popUpRetryButtonSprite.draw(canvas);
        }

        if(isGameOver) {
            gameOverPopUpSprite.draw(canvas);
            popUpMenuButtonSprite.draw(canvas);
            popUpRetryButtonSprite.draw(canvas);
        }

        // Ekrana skoru yazdır.
        canvas.drawText("Score: " + score, 20, 50, paint);

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

        if(pauseButtonSprite.isTouchedUp(x, y)) {
            pauseButtonSprite.playAnimationWithName("touchUpInside");
        }

        if(isGamePaused || isGameOver) {

            if (popUpPlayButtonSprite.isTouchedUp(x, y)) {
                popUpPlayButtonSprite.playAnimationWithName("click");
            }

            if (popUpMenuButtonSprite.isTouchedUp(x, y)) {
                popUpMenuButtonSprite.playAnimationWithName("click");
            }

            if (popUpRetryButtonSprite.isTouchedUp(x, y)) {
                popUpRetryButtonSprite.playAnimationWithName("click");
            }
        }

    }

    public void touchMove(int x, int y, int id) {
    }

    public void touchUp(int x, int y, int id) {
        int diffrentX = x - touchDownX;
        int diffrentY = y - touchDownY;

        if(!isGamePaused || !isGameOver) {

            if (screenSide) {
                Log.i(TAG, "playerRight.getDestinationY:" + playerRightSprite.getDestinationY());
                Log.i(TAG, "platformTopY - playerRight.getDestinationHeight:" + playerRightSprite.getDestinationY());

                if (!(playerRightSprite.getDestinationY() < (platformRightTopY - playerRightSprite.getDestinationHeight()) || playerRightSprite.getDestinationY() > (platformRightBottomY - playerRightSprite.getDestinationHeight()))) {
                    // Ekranın sağ tarafındaki player konum değiştirecek.
                    if (Math.abs(diffrentX) > Math.abs(diffrentY)) {
                        // X düzlemindeki parmak hareketi Y düzlemindekinden fazla ise burası çalışır.
                    } else {
                        // Y düzlemindeki parmak hareketi X düzlemindekinden fazla ise burası çalışır.
                        if (diffrentY < 0) {
                            if (!(playerRightSprite.getDestinationY() == (platformRightTopY - playerRightSprite.getDestinationHeight()))) {
                                playerRightSprite.setDestinationY(playerRightSprite.getDestinationY() + (platformRightMiddleY - platformRightBottomY));
                            }
                        } else if (diffrentY > 0) {
                            if (!(playerRightSprite.getDestinationY() == (platformRightBottomY - playerRightSprite.getDestinationHeight()))) {
                                playerRightSprite.setDestinationY(playerRightSprite.getDestinationY() - (platformRightMiddleY - platformRightBottomY));
                            }
                        }
                    }
                }
            } else {

                Log.i(TAG, "playerLeft.getDestinationY:" + playerLeftSprite.getDestinationY());
                Log.i(TAG, "platformTopY - playerLeft.getDestinationHeight:" + playerLeftSprite.getDestinationY());

                if (!(playerLeftSprite.getDestinationY() < (platformLeftTopY - playerLeftSprite.getDestinationHeight()) || playerLeftSprite.getDestinationY() > (platformLeftBottomY - playerLeftSprite.getDestinationHeight()))) {
                    // Ekranın sol tarafındaki player konum değiştirecek.
                    if (Math.abs(diffrentX) > Math.abs(diffrentY)) {
                        // X düzlemindeki parmak hareketi Y düzlemindekinden fazla ise burası çalışır.
                    } else {

                        // Y düzlemindeki parmak hareketi X düzlemindekinden fazla ise burası çalışır.
                        if (diffrentY < 0) {
                            if (!(playerLeftSprite.getDestinationY() == (platformLeftTopY - playerLeftSprite.getDestinationHeight()))) {
                                playerLeftSprite.setDestinationY(playerLeftSprite.getDestinationY() + (platformLeftMiddleY - platformLeftBottomY));
                            }
                        } else if (diffrentY > 0) {
                            if (!(playerLeftSprite.getDestinationY() == (platformLeftBottomY - playerLeftSprite.getDestinationHeight()))) {
                                playerLeftSprite.setDestinationY(playerLeftSprite.getDestinationY() - (platformLeftMiddleY - platformLeftBottomY));
                            }
                        }

                    }
                }
            }
        }

        if(pauseButtonSprite.isTouchedUp(x, y)) {
            pauseButtonSprite.playAnimationWithName("touchUpInside");
            isGamePaused = !isGamePaused;
        }

        if(isGamePaused || isGameOver) {

            if (popUpPlayButtonSprite.isTouchedUp(x, y)) {
                popUpPlayButtonSprite.playAnimationWithName("click");
                isGamePaused = !isGamePaused;
            }

            if (popUpMenuButtonSprite.isTouchedUp(x, y)) {
                popUpMenuButtonSprite.playAnimationWithName("click");
                goToMainCanvas();

            }

            if (popUpRetryButtonSprite.isTouchedUp(x, y)) {
                popUpRetryButtonSprite.playAnimationWithName("click");
                soundMediaPlayer.stop();
                musicMediaPlayer.stop();
                root.canvasManager.setCurrentCanvas(new GameCanvas(root, musicState, soundState));
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

    public void goToMainCanvas() {

        if(musicState) {
            musicMediaPlayer.stop();
        }

        if(soundState) {
            soundMediaPlayer.stop();
        }

        MenuCanvas menuCanvas = new MenuCanvas(root, musicState, soundState);
        root.canvasManager.setCurrentCanvas(menuCanvas);
    }

}
