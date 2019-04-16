package com.gulshanyadav.waterfallwallpapers;

/*
This project is developed by Gulshan Yadav and is copyrighted to use without permission.
 */

import android.graphics.Bitmap;

public class Spacecraft {
    int image;
    String id;
    Bitmap bmp;
    byte[] imagebyte;

    public Spacecraft() {
    }

    public Spacecraft(String id, int image) {
        this.image = image;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Bitmap getImageBitmap() {
        return bmp;
    }
    public void setImageBitmap(Bitmap bmp) {
        this.bmp = bmp;
    }


    public byte[] getImageByte(){return imagebyte;}
    public void setImageByte(byte[] imagebyte){this.imagebyte = imagebyte;}

}