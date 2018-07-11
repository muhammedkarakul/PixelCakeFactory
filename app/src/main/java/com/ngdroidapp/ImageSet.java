package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

import istanbul.gamelab.ngdroid.util.Utils;

public class ImageSet {

    private NgApp root;
    private Bitmap image;
    private List<Rect> imageRects;

    ImageSet() {
        image = null;
        imageRects = new ArrayList<Rect>();
    }

    ImageSet(NgApp root, String imageSetFilePath) {
        image = Utils.loadImage(root, imageSetFilePath);
        this.root = root;
        imageRects = new ArrayList<Rect>();
    }

    public List<Rect> divideBy(int width, int height) {
        //List<Rect> imageSetRectList = new ArrayList<Rect>();

        for(int x = 0; x < image.getWidth(); x += width) {
            for(int y = 0; y < image.getHeight(); y += height){
                Rect tempRect = new Rect(x, y, width, height);
                //imageSetRectList.add(tempRect);
                imageRects.add(tempRect);
            }
        }
        //return imageSetRectList;
        return imageRects;
    }

    public int getImageRectCount() {
        return imageRects.size();
    }

    public List<Rect> getImageRects() { return imageRects; }

    public Rect getImageRectWithIndex(int index) { return imageRects.get(index); }
}
