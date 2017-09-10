package com.jacob.top100.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Jacob Ho
 */
public class MobileApp {
    @SerializedName("trackId")
    @Expose
    private int mId;
    @SerializedName("trackName")
    @Expose
    private String mName;
    @SerializedName("artworkUrl100")
    @Expose
    private String mIcon;
    @SerializedName("primaryGenreName")
    @Expose
    private String mCategory;
    @SerializedName("averageUserRatingForCurrentVersion")
    @Expose
    private Float mRating;
    @SerializedName("userRatingCountForCurrentVersion")
    @Expose
    private Integer mRatingCount;

    public MobileApp() {
    }

    public MobileApp(int id, String name, String icon, String category, Float rating, Integer ratingCount) {
        mId = id;
        mName = name;
        mIcon = icon;
        mCategory = category;
        mRating = rating;
        mRatingCount = ratingCount;
    }

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

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
    }

    public Float getRating() {
        return mRating;
    }

    public void setRating(Float rating) {
        mRating = rating;
    }

    public Integer getRatingCount() {
        return mRatingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        mRatingCount = ratingCount;
    }
}
