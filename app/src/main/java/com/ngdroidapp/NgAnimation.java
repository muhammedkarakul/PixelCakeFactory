package com.ngdroidapp;

import android.graphics.Rect;

public class NgAnimation {
    private String name;
    private int startFrameNumber;
    private int endFrameNumber;
    private ImageSet sourceImageSet;
    private int currentFrameNumber;
    private boolean isTheAnimationEnded;
    private boolean loop;

    NgAnimation() {
        sourceImageSet = new ImageSet();
        currentFrameNumber = 0;
        startFrameNumber = 0;
        endFrameNumber = 0;
        isTheAnimationEnded = true;
        loop = true;
    }

    NgAnimation(String name, ImageSet animationSourceImageSet) {
        this.name = name;
        this.sourceImageSet = animationSourceImageSet;
        currentFrameNumber = 0;
        startFrameNumber = 0;
        endFrameNumber = 0;
        isTheAnimationEnded = true;
        loop = true;
    }

    NgAnimation(String name, ImageSet animationSourceImageSet, int startFrameNumber, int endFrameNumber) {
        this.name = name;
        this.sourceImageSet = animationSourceImageSet;
        currentFrameNumber = 0;
        this.startFrameNumber = startFrameNumber;
        this.endFrameNumber = endFrameNumber;
        isTheAnimationEnded = true;
        loop = true;
    }

    NgAnimation(String name, ImageSet animationSourceImageSet, int startFrameNumber, int endFrameNumber, boolean loop) {
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

    public boolean getAnimationState() {
        return isTheAnimationEnded;
    }

    public void setAnimationState(boolean state) {
        isTheAnimationEnded = state;
    }


}
