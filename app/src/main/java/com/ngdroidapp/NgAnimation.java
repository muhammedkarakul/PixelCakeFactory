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
    private String name;
    private int startFrameNumber;
    private int endFrameNumber;
    private ImageSet sourceImageSet;
    private int currentFrameNumber;

    NgAnimation() {
        this.root = null;
        sourceImageSet = new ImageSet();
        currentFrameNumber = 0;
        startFrameNumber = 0;
        endFrameNumber = 0;
    }

    NgAnimation(NgApp root, String name, ImageSet animationSourceImageSet) {
        this.root = root;
        this.name = name;
        this.sourceImageSet = animationSourceImageSet;
        currentFrameNumber = 0;
        startFrameNumber = 0;
        endFrameNumber = 0;
    }

    NgAnimation(NgApp root, String name, ImageSet animationSourceImageSet, int startFrameNumber, int endFrameNumber) {
        this.root = root;
        this.name = name;
        this.sourceImageSet = animationSourceImageSet;
        currentFrameNumber = 0;
        this.startFrameNumber = startFrameNumber;
        this.endFrameNumber = endFrameNumber;

    }


    public Rect getAnimationCurrentFrame() {
        if(currentFrameNumber < endFrameNumber) {
            currentFrameNumber = currentFrameNumber + 1;
            return  sourceImageSet.getImageRectWithIndex(currentFrameNumber);
        } else {
            currentFrameNumber = startFrameNumber;
            return  sourceImageSet.getImageRectWithIndex(currentFrameNumber);
        }
    }

    public String getName() { return name; }


}
