package com.example.encounterviewpager;/**
 * Created by Md Minhajul Islam on 13/03/2023.
 */
public class PojoData {
    public boolean isRealItem;
    String description;
    int imageId;


    public PojoData(String description, int imageId, boolean isRealItem) {
        this.description = description;
        this.imageId = imageId;
        this.isRealItem = isRealItem;
    }

    public boolean isRealItem() {
        return isRealItem;
    }

    public void setRealItem(boolean realItem) {
        isRealItem = realItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
