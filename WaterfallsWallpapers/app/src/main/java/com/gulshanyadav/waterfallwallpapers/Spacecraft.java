package com.gulshanyadav.waterfallwallpapers;

/*
This project is developed by Gulshan Yadav and is copyrighted to use without permission.
 */

import android.graphics.Bitmap;

public class Spacecraft {
    int image;
    String name,propellant,id;
    Bitmap bmp;
    byte[] imagebyte;
    String pagename;

    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
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


    public String getPropellant() {
        return propellant;
    }
    public void setPropellant(String propellant) {
        this.propellant = propellant;
    }

    public byte[] getImageByte(){return imagebyte;}
    public void setImageByte(byte[] imagebyte){this.imagebyte = imagebyte;}

    public String getPageName() {
        return pagename;
    }
    public void setPageName(String pagename) {
        this.pagename = pagename;
    }


}