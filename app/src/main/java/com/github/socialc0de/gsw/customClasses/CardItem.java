package com.github.socialc0de.gsw.customClasses;

import com.github.socialc0de.gsw.activities.MainActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by patricebecker on 07/11/15.
 */
public class CardItem {
    private String image = "";
    private int imageID;
    private String categoryName;
    private int id = 0;

    public CardItem(String image, String categoryName, int id) {
        this.image = image;
        this.categoryName = categoryName;
        this.id = id;
    }

    public CardItem(int imageID, String categoryName, int id) {
        this.imageID = imageID;
        this.categoryName = categoryName;
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
