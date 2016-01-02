package com.github.socialc0de.gsw.customClasses;

/**
 * Created by patricebecker on 07/11/15.
 */
public class CardItem {
    private int image;
    private String categoryName;
    private int id = 0;

    public CardItem(int image, String categoryName, int id) {
        this.image = image;
        this.categoryName = categoryName;
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
