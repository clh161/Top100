package com.jacob.top100.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacob Ho
 */
public class MobileApp {
    private int mId;
    private String mName;
    private List<MobileAppImage> mImages = new ArrayList<>();

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<MobileAppImage> getImages() {
        return mImages;
    }

    public void setImages(List<MobileAppImage> images) {
        mImages = images;
    }
}
