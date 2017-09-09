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
    private String category;
    @SerializedName("averageUserRatingForCurrentVersion")
    @Expose
    private Double mRating;
    @SerializedName("userRatingCountForCurrentVersion")
    @Expose
    private Integer mRatingCount;

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
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getRating() {
        return mRating;
    }

    public void setRating(Double rating) {
        mRating = rating;
    }

    public Integer getRatingCount() {
        return mRatingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        mRatingCount = ratingCount;
    }
}
