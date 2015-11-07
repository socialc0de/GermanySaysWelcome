package com.github.socialc0de.gsw.customClasses;

/**
 * Created by patricebecker on 07/11/15.
 */
public class CardItem {
    private int image;
    private String categoryName;

    public CardItem(int image, String categoryName) {
        this.image = image;
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getImage() {
        return image;
    }
}
