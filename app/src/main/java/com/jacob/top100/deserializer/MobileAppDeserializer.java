package com.jacob.top100.deserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jacob.top100.model.MobileApp;
import com.jacob.top100.model.MobileAppFeed;
import com.jacob.top100.model.MobileAppImage;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author Jacob Ho
 */
public class MobileAppDeserializer implements JsonDeserializer<MobileAppFeed> {
    @Override
    public MobileAppFeed deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        MobileAppFeed mobileAppFeed = new MobileAppFeed();
        ArrayList<MobileApp> mobileApps = new ArrayList<>();
        mobileAppFeed.setEntries(mobileApps);
        JsonArray entries = json.getAsJsonObject().getAsJsonObject("feed").getAsJsonArray("entry");
        for (int i = 0; i < entries.size(); i++) {
            MobileApp mobileApp = new MobileApp();
            JsonObject entry = entries.get(i).getAsJsonObject();
            mobileApp.setId(entry.getAsJsonObject("id").getAsJsonObject("attributes").get("im:id").getAsInt());
            mobileApp.setName(entry.getAsJsonObject("im:name").get("label").getAsString());
            for (int j = 0; entry.has("im:image") && j < entry.getAsJsonArray("im:image").size(); j++) {
                JsonObject image = entry.getAsJsonArray("im:image").get(j).getAsJsonObject();
                MobileAppImage mobileAppImage = new MobileAppImage();
                mobileAppImage.setHeight(image.get("attributes").getAsJsonObject().get("height").getAsInt());
                mobileAppImage.setUrl(image.get("label").getAsString());
                mobileApp.getImages().add(mobileAppImage);
            }
            mobileApps.add(mobileApp);
        }
        return mobileAppFeed;
    }
}
