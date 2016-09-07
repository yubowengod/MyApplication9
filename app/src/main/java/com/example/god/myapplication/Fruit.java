package com.example.god.myapplication;

import android.graphics.Bitmap;

/**
 * Created by GOD on 2016/9/6.
 */
public class Fruit {

    private String name;

    private Bitmap imageId;

    public Fruit(String name, Bitmap imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public Bitmap getImageId() {
        return imageId;
    }

}
