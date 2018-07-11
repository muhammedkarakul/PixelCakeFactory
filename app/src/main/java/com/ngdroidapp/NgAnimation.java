package com.ngdroidapp;


import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.Image;

import java.util.Date;
import java.util.List;
import java.util.logging.Handler;

public class NgAnimation {
    private NgApp root;
    //private Canvas canvas;
    private int animationStartFrameNumber;
    private int animationEndFrameNumber;
    private ImageSet animationSourceImageSet;
    private int currentFrameNumber;

    NgAnimation() {
        animationStartFrameNumber = 0;
        animationEndFrameNumber = 0;
        currentFrameNumber = 0;
        animationSourceImageSet = new ImageSet();
    }

    NgAnimation(NgApp root, ImageSet animationSourceImageSet) {
        this.root = root;
        this.animationSourceImageSet = animationSourceImageSet;
        currentFrameNumber = 0;
        animationStartFrameNumber = 0;
        animationEndFrameNumber = 0;
    }

    NgAnimation(NgApp root, ImageSet animationSourceImageSet, int animationStartFrameNumber, int animationEndFrameNumber) {
        this.animationStartFrameNumber = animationStartFrameNumber;
        this.animationEndFrameNumber = animationEndFrameNumber;
        this.animationSourceImageSet = animationSourceImageSet;
        currentFrameNumber = 0;
    }

    /*
    public Rect play() {
        return this.animationSourceImageSet.getImageRectWithIndex(currentFrameNumber);
    }

    public void updateFrame() {
        if(currentFrameNumber < animationEndFrameNumber) {
            currentFrameNumber += 1;
        } else {
            currentFrameNumber = animationStartFrameNumber;
        }
    }
    */

}
