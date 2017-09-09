package com.jacob.top100.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Jacob Ho
 */
public class MobileAppDetailFeed {
    @SerializedName("results")
    @Expose
    private List<MobileApp> mEntries = null;

    public List<MobileApp> getEntries() {
        return mEntries;
    }

    public void setEntries(List<MobileApp> entries) {
        mEntries = entries;
    }
}
