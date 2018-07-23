package com.ngdroidapp;

import android.graphics.Rect;

public class NgAnimation {
    private NgApp root;
    //private Canvas canvas;
    private String name;
    private int startFrameNumber;
    private int endFrameNumber;
    private ImageSet sourceImageSet;
    private int currentFrameNumber;
    private boolean isTheAnimationEnded;
    private boolean loop;

    NgAnimation() {
        this.root = null;
        sourceImageSet = new ImageSet();
        currentFrameNumber = 0;
        startFrameNumber = 0;
        endFrameNumber = 0;
        isTheAnimationEnded = true;
        loop = true;
    }

    NgAnimation(NgApp root, String name, ImageSet animationSourceImageSet) {
        this.root = root;
        this.name = name;
        this.sourceImageSet = animationSourceImageSet;
        currentFrameNumber = 0;
        startFrameNumber = 0;
        endFrameNumber = 0;
        isTheAnimationEnded = true;
        loop = true;
    }

    NgAnimation(NgApp root, String name, ImageSet animationSourceImageSet, int startFrameNumber, int endFrameNumber) {
        this.root = root;
        this.name = name;
        this.sourceImageSet = animationSourceImageSet;
        currentFrameNumber = 0;
        this.startFrameNumber = startFrameNumber;
        this.endFrameNumber = endFrameNumber;
        isTheAnimationEnded = true;
        loop = true;
    }

    NgAnimation(NgApp root, String name, ImageSet animationSourceImageSet, int startFrameNumber, int endFrameNumber, boolean loop) {
        this.root = root;
        this.name = name;
        this.sourceImageSet = animationSourceImageSet;
        currentFrameNumber = 0;
        this.startFrameNumber = startFrameNumber;
        this.endFrameNumber = endFrameNumber;
        isTheAnimationEnded = true;
        this.loop = loop;
    }


    public Rect getAnimationCurrentFrame() {
        if(currentFrameNumber < endFrameNumber) {
            //isTheAnimationEnded = false;
            currentFrameNumber = currentFrameNumber + 1;
            return  sourceImageSet.getImageRectWithIndex(currentFrameNumber);
        } else {
            if (!loop) {
                isTheAnimationEnded = false;
            }
            currentFrameNumber = startFrameNumber;
            return  sourceImageSet.getImageRectWithIndex(currentFrameNumber);
        }
    }

    public String getName() { return name; }

    public boolean animationState() {
        return isTheAnimationEnded;
    }


}