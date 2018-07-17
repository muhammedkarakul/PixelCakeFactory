package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;

import static android.content.ContentValues.TAG;

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

    public void divideBy(int width, int height) {

        for(int x = 0; x < image.getWidth(); x += width) {
            for(int y = 0; y < image.getHeight(); y += height){
                Rect tempRect = new Rect(x, y, x + width, y + height);
                imageRects.add(tempRect);
            }
        }

    }

    public int getImageRectCount() {
        return imageRects.size();
    }

    public List<Rect> getImageRects() { return imageRects; }

    public Rect getImageRectWithIndex(int index) { return imageRects.get(index); }

    public void printRectsData() {
        for(int i = 0; i < imageRects.size(); i++) {
            Log.i(TAG, "Rect " + i + "= x: " + imageRects.get(i).left + ", y: " + imageRects.get(i).top + ", width: " + imageRects.get(i).width() + ", height: " + imageRects.get(i).height());
        }

    }
}
