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
    final private String popUpBackgroundImagePath = "popUpBigBackground.png";

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
    private ImageSet conveyorBeltTopImageSet;
    private NgAnimation conveyorBeltTopAnimation;

    private Sprite conveyorBeltMiddle1Sprite;
    private Rect conveyorBeltMiddle1SourceRect;
    private Rect conveyorBeltMiddle1DestinationRect;
    private ImageSet conveyorBeltMiddle1ImageSet;
    private NgAnimation conveyorBeltMiddle1Animation;

    private Sprite conveyorBeltMiddle2Sprite;
    private Rect conveyorBeltMiddle2SourceRect;
    private Rect conveyorBeltMiddle2DestinationRect;
    private ImageSet conveyorBeltMiddle2ImageSet;
    private NgAnimation conveyorBeltMiddle2Animation;

    private Sprite conveyorBeltMiddle3Sprite;
    private Rect conveyorBeltMiddle3SourceRect;
    private Rect conveyorBeltMiddle3DestinationRect;
    private ImageSet conveyorBeltMiddle3ImageSet;
    private NgAnimation conveyorBeltMiddle3Animation;

    private Sprite conveyorBeltMiddle4Sprite;
    private Rect conveyorBeltMiddle4SourceRect;
    private Rect conveyorBeltMiddle4DestinationRect;
    private ImageSet conveyorBeltMiddle4ImageSet;
    private NgAnimation conveyorBeltMiddle4Animation;

    private Sprite conveyorBeltBottomSprite;
    private Rect conveyorBeltBottomSourceRect;
    private Rect conveyorBeltBottomDestinationRect;
    private ImageSet conveyorBeltBottomImageSet;
    private NgAnimation conveyorBeltBottomAnimation;

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
    private ImageSet pipeImageSet;
    private NgAnimation pipeAnimation;
    private boolean pipeAnimationState;

    private long startAnim, finishAnim;
    private int gameSpeed;

    private boolean musicState;
    private boolean soundState;

    private Sprite pauseButtonSprite;
    private Rect pauseButtonSourceRect;
    private Rect pauseButtonDestinationRect;
    private ImageSet pauseButtonImageSet;
    private NgAnimation pauseButtonAnimation;
    private boolean isGamePaused;
    //private boolean pauseState;

    // Pop Up ile ilgili nesneler tanımlanıyor.

    // Pause butonuna basınca ekranda gözükecek pop up ile ilgili nesneler ve değişkenler tanımlanıyor.
    private Sprite popUpMenuSprite;
    private Rect popUpMenuSourceRect;
    private Rect popUpMenuDestinationRect;
    private int popUpX;
    private int popUpY;
    private int popUpWidth;
    private int popUpHeight;
    //private boolean popUpState = false;

    // Pop up üstünde bulunacak play butonu ile ilgili nesneler tanımlanıyor.
    private Sprite popUpPlayButtonSprite;
    private Rect popUpPlayButtonSourceRect;
    private Rect popUpPlayButtonDestinationRect;
    private ImageSet popUpPlayButtonImageSet;
    private NgAnimation popUpPlayButtonAnimation;
    private int popUpPlayButtonX;
    private int popUpPlayButtonY;
    private int popUpPlayButtonWidth;
    private int popUpPlayButtonHeight;
    private String popUpPlayButtonImagePath;

    // Pop up üstünde bulunacak menu butonu ile ilgili nesneler tanımlanıyor.
    private Sprite popUpMenuButtonSprite;
    private Rect popUpMenuButtonSourceRect;
    private Rect popUpMenuButtonDestinationRect;
    private ImageSet popUpMenuButtonImageSet;
    private NgAnimation popUpMenuButtonAnimation;
    private int popUpMenuButtonX;
    private int popUpMenuButtonY;
    private int popUpMenuButtonWidth;
    private int popUpMenuButtonHeight;
    private String popUpMenuButtonImagePath;

    // Pop up üstünde bulunacak retry butonu ile ilgili nesneler tanımlanıyor.
    private Sprite popUpRetryButtonSprite;
    private Rect popUpRetryButtonSourceRect;
    private Rect popUpRetryButtonDestinationRect;
    private ImageSet popUpRetryButtonImageSet;
    private NgAnimation popUpRetryButtonAnimation;
    private int popUpRetryButtonX;
    private int popUpRetryButtonY;
    private int popUpRetryButtonWidth;
    private int popUpRetryButtonHeight;
    private String popUpRetryButtonImagePath;

    public GameCanvas(NgApp ngApp, boolean musicState, boolean soundState) {
        super(ngApp);
        this.musicState = musicState;
        this.soundState = soundState;
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
        if(musicState) {
            setupMediaPlayer("sounds/bgm_action_5.mp3");
        }


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

        // Üstteki yürüyen bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        conveyorBeltTopImageSet = new ImageSet(root, converoyBeltSpriteSetFilePath);
        conveyorBeltTopImageSet.divideBy(conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        conveyorBeltTopAnimation = new NgAnimation(root, "work", conveyorBeltTopImageSet, 0, 1);
        conveyorBeltTopSourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        conveyorBeltTopDestinationRect = new Rect(conveyorBeltLeftX, conveyorBeltTopY, conveyorBeltLeftX + conveyorBeltDestinationWidth, conveyorBeltTopY + conveyorBeltDestinationHeight);
        conveyorBeltTopSprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltTopSourceRect, conveyorBeltTopDestinationRect, conveyorBeltTopAnimation);

        // Ortadaki yürüyen bantlardan 1. bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        conveyorBeltMiddle1ImageSet = new ImageSet(root, converoyBeltSpriteSetFilePath);
        conveyorBeltMiddle1ImageSet.divideBy(conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        conveyorBeltMiddle1Animation = new NgAnimation(root, "work", conveyorBeltMiddle1ImageSet, 0, 1);
        conveyorBeltMiddle1SourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        conveyorBeltMiddle1DestinationRect = new Rect(conveyorBeltRightX, conveyorBeltMiddleY, conveyorBeltRightX + conveyorBeltDestinationWidth, conveyorBeltMiddleY + conveyorBeltDestinationHeight);
        conveyorBeltMiddle1Sprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltMiddle1SourceRect, conveyorBeltMiddle1DestinationRect, conveyorBeltMiddle1Animation);

        // Ortadaki yürüyen bantlardan 2. bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        conveyorBeltMiddle2SourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        conveyorBeltMiddle2DestinationRect = new Rect(conveyorBeltLeftX, conveyorBeltMiddle2Y, conveyorBeltLeftX + conveyorBeltDestinationWidth, conveyorBeltMiddle2Y + conveyorBeltDestinationHeight);
        conveyorBeltMiddle2Sprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltMiddle2SourceRect, conveyorBeltMiddle2DestinationRect, conveyorBeltTopAnimation);

        // Ortadaki yürüyen bantlardan 3. bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        conveyorBeltMiddle3SourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        conveyorBeltMiddle3DestinationRect = new Rect(conveyorBeltRightX, conveyorBeltMiddle3Y, conveyorBeltRightX + conveyorBeltDestinationWidth, conveyorBeltMiddle3Y + conveyorBeltDestinationHeight);
        conveyorBeltMiddle3Sprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltMiddle3SourceRect, conveyorBeltMiddle3DestinationRect, conveyorBeltTopAnimation);

        // Ortadaki yürüyen bantlardan 4. bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        conveyorBeltMiddle4SourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        conveyorBeltMiddle4DestinationRect = new Rect(conveyorBeltLeftX, conveyorBeltBottomY, conveyorBeltLeftX + conveyorBeltDestinationWidth, conveyorBeltBottomY + conveyorBeltDestinationHeight);
        conveyorBeltMiddle4Sprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltMiddle4SourceRect, conveyorBeltMiddle4DestinationRect, conveyorBeltTopAnimation);

        // Alttaki yürüyen bantla ilgili nesnelere ilk değer atamaları yapılıyor.
        conveyorBeltBottomSourceRect = new Rect(0, 0, conveyorBeltSourceWidth, conveyorBeltSourceHeight);
        conveyorBeltBottomDestinationRect = new Rect(conveyorBeltRightX, conveyorBeltBottom2Y, conveyorBeltRightX + conveyorBeltDestinationWidth, conveyorBeltBottom2Y + conveyorBeltDestinationHeight);
        conveyorBeltBottomSprite = new Sprite(root, converoyBeltSpriteSetFilePath, conveyorBeltBottomSourceRect, conveyorBeltBottomDestinationRect, conveyorBeltTopAnimation);

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
        pipeImageSet = new ImageSet(root, "pipeActs.png");
        pipeImageSet.divideBy(384, 384);
        pipeAnimation = new NgAnimation(root, "dropOut", pipeImageSet, 0, 9, true);
        pipeSprite = new Sprite(root, "pipeActs.png", pipeSourceRect, pipeDestinationRect, pipeAnimation);
        pipeAnimationState = true;

        startAnim = System.currentTimeMillis();
        gameSpeed = 100;

        isGamePaused = false;
        pauseButtonSourceRect = new Rect(0,0,360,360);
        pauseButtonDestinationRect = new Rect(getWidth() - 150, 50, getWidth() - 50, 150);
        pauseButtonImageSet = new ImageSet(root, "stopButtonImageSet.png");
        pauseButtonImageSet.divideBy(360, 360);
        pauseButtonAnimation = new NgAnimation(root, "touchUpInside", pauseButtonImageSet, 0, 1);
        pauseButtonSprite = new Sprite(root, "stopButtonImageSet.png", pauseButtonSourceRect, pauseButtonDestinationRect, pauseButtonAnimation);

        popUpWidth = (int)(1069 / 1.5);
        popUpHeight = (int)(1069 / 1.5);
        popUpX = (getWidth() - popUpWidth) / 2;
        popUpY = (getHeight() - popUpHeight) / 2;
        popUpMenuSourceRect = new Rect(0, 0, 1088, 1069);
        popUpMenuDestinationRect = new Rect(popUpX, popUpY, popUpWidth + popUpX, popUpHeight + popUpY);
        popUpMenuSprite = new Sprite(root, popUpBackgroundImagePath, popUpMenuSourceRect, popUpMenuDestinationRect);

        popUpPlayButtonImagePath = "playButtonSet.png";
        popUpPlayButtonWidth = 200;
        popUpPlayButtonHeight = 200;
        popUpPlayButtonX = (getWidth() - popUpPlayButtonWidth) / 2;
        popUpPlayButtonY = (getHeight() - popUpPlayButtonHeight) / 2;
        popUpPlayButtonSourceRect = new Rect(0, 0, 360, 360);
        popUpPlayButtonDestinationRect = new Rect(popUpPlayButtonX, popUpPlayButtonY, popUpPlayButtonX + popUpPlayButtonWidth, popUpPlayButtonY + popUpPlayButtonHeight);
        popUpPlayButtonImageSet = new ImageSet(root, popUpPlayButtonImagePath);
        popUpPlayButtonImageSet.divideBy(360, 360);
        popUpPlayButtonAnimation = new NgAnimation(root, "click", popUpPlayButtonImageSet, 0, 1);
        popUpPlayButtonSprite = new Sprite(root, popUpPlayButtonImagePath, popUpPlayButtonSourceRect, popUpPlayButtonDestinationRect, popUpPlayButtonAnimation);

        popUpMenuButtonImagePath = "menuButtonImageSet.png";
        popUpMenuButtonWidth = 200;
        popUpMenuButtonHeight = 200;
        popUpMenuButtonX = (getWidth() - popUpMenuButtonWidth) / 2 - popUpPlayButtonWidth - 25;
        popUpMenuButtonY = (getHeight() - popUpMenuButtonHeight) / 2;
        popUpMenuButtonSourceRect = new Rect(0, 0, 360, 360);
        popUpMenuButtonDestinationRect = new Rect(popUpMenuButtonX, popUpMenuButtonY, popUpMenuButtonX + popUpMenuButtonWidth, popUpMenuButtonY + popUpMenuButtonHeight);
        popUpMenuButtonImageSet = new ImageSet(root, popUpMenuButtonImagePath);
        popUpMenuButtonImageSet.divideBy(360, 360);
        popUpMenuButtonAnimation = new NgAnimation(root, "click", popUpMenuButtonImageSet, 0, 1);
        popUpMenuButtonSprite = new Sprite(root, popUpMenuButtonImagePath, popUpMenuButtonSourceRect, popUpMenuButtonDestinationRect, popUpMenuButtonAnimation);

        popUpRetryButtonImagePath = "retryButtonImageSet.png";
        popUpRetryButtonWidth = 200;
        popUpRetryButtonHeight = 200;
        popUpRetryButtonX = (getWidth() - popUpRetryButtonWidth) / 2 + popUpPlayButtonWidth + 25;
        popUpRetryButtonY = (getHeight() - popUpRetryButtonHeight) / 2;
        popUpRetryButtonSourceRect = new Rect(0, 0, 360, 360);
        popUpRetryButtonDestinationRect = new Rect(popUpRetryButtonX, popUpRetryButtonY, popUpRetryButtonX + popUpRetryButtonWidth, popUpRetryButtonY + popUpRetryButtonHeight);
        popUpRetryButtonImageSet = new ImageSet(root, popUpRetryButtonImagePath);
        popUpRetryButtonImageSet.divideBy(360, 360);
        popUpRetryButtonAnimation = new NgAnimation(root, "click", popUpRetryButtonImageSet, 0, 1);
        popUpRetryButtonSprite = new Sprite(root, popUpRetryButtonImagePath, popUpRetryButtonSourceRect, popUpRetryButtonDestinationRect, popUpRetryButtonAnimation);
    }

    public void setupMediaPlayer(String assetFilePath) {
        player = new NgMediaPlayer(root);
        player.load(assetFilePath);
        player.prepare();
        player.setLooping(true);
        player.start();
    }

    public void update() {
        if(!isGamePaused) {

            finishAnim =  System.currentTimeMillis();

            timeBoundAnimation();

            if(finishAnim - startAnim > gameSpeed)
                startAnim = System.currentTimeMillis();

        }

    }

    private void timeBoundAnimation() {

        if(finishAnim - startAnim < gameSpeed) {
            return;
        }

        conveyorBeltTopSprite.playAnimationWithName("work");
        conveyorBeltMiddle1Sprite.playAnimationWithName("work");
        conveyorBeltMiddle2Sprite.playAnimationWithName("work");
        conveyorBeltMiddle3Sprite.playAnimationWithName("work");
        conveyorBeltMiddle4Sprite.playAnimationWithName("work");
        conveyorBeltBottomSprite.playAnimationWithName("work");

        if (pipeAnimationState) {
            if (pipeSprite.getAnimationWithName("dropOut").animationState()) {
                pipeSprite.playAnimationWithName("dropOut");
            } else {
                pipeAnimationState = !pipeAnimationState;
            }
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

        // Orta platform erkarana çiziliyor.
        for(int i = ballDimention; i < getHeight(); i = i + middlePlatfromSprite.getDestinationHeight()){
            middlePlatfromSprite.setDestinationY(i);
            middlePlatfromSprite.draw(canvas);
        }

        ballSprite.draw(canvas);

        // Pipe sprite'ı ekrana çizdiriliyor.
        pipeSprite.draw(canvas);

        pauseButtonSprite.draw(canvas);

        //} else {
        if(isGamePaused) {
            popUpMenuSprite.draw(canvas);
            popUpPlayButtonSprite.draw(canvas);
            popUpMenuButtonSprite.draw(canvas);
            popUpRetryButtonSprite.draw(canvas);
        }
        //}



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

        if(popUpPlayButtonSprite.isTouchedUp(x, y)) {
            popUpPlayButtonSprite.playAnimationWithName("click");
        }

        if(popUpMenuButtonSprite.isTouchedUp(x, y)) {
            popUpMenuButtonSprite.playAnimationWithName("click");
        }

        if(popUpRetryButtonSprite.isTouchedUp(x, y)) {
            popUpRetryButtonSprite.playAnimationWithName("click");
        }

    }

    public void touchMove(int x, int y, int id) {
    }

    public void touchUp(int x, int y, int id) {
        int diffrentX = x - touchDownX;
        int diffrentY = y - touchDownY;

        if(!isGamePaused) {

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

        if(popUpPlayButtonSprite.isTouchedUp(x, y)) {
            popUpPlayButtonSprite.playAnimationWithName("click");
            isGamePaused = !isGamePaused;
        }

        if(popUpMenuButtonSprite.isTouchedUp(x, y)) {
            popUpMenuButtonSprite.playAnimationWithName("click");
            goToMainCanvas();

        }

        if(popUpRetryButtonSprite.isTouchedUp(x, y)) {
            popUpRetryButtonSprite.playAnimationWithName("click");
            root.canvasManager.setCurrentCanvas(new GameCanvas(root,musicState,soundState));
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

        player.stop();

        MenuCanvas menuCanvas = new MenuCanvas(root);
        root.canvasManager.setCurrentCanvas(menuCanvas);
    }

}
