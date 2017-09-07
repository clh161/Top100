package com.jacob.top100.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacob Ho
 */
public class MobileAppFeed {
    public List<MobileApp> mEntries = new ArrayList<>();

    public List<MobileApp> getEntries() {
        return mEntries;
    }

    public void setEntries(List<MobileApp> entries) {
        mEntries = entries;
    }
}
