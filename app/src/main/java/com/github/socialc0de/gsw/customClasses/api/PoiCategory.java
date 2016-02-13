package com.github.socialc0de.gsw.customClasses.api;


import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.github.socialc0de.gsw.activities.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PoiCategory {

    private Integer id;
    private Translations translations;
    private String icon;
    private ImageView iconView;
    private ArrayList<PoiEntry> poiEntries;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The translations
     */
    public Translations getTranslations() {
        return translations;
    }

    /**
     * @param translations The translations
     */
    public void setTranslations(Translations translations) {
        this.translations = translations;
    }


    public ImageView getIconView() {
        return iconView;
    }

    public Drawable getIcon() {
        return iconView.getDrawable();
    }

    public void loadIconView() {

        if(MainActivity.getMainActivity() != null)
        if(!icon.equals(""))
            MainActivity.getMainActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Picasso.with(MainActivity.getMainActivity()).load(icon).into(iconView);
                }
            });
    }

    public void setIconView(ImageView iconView) {
        this.iconView = iconView;
    }

    public ArrayList<PoiEntry> getPoiEntries() {
        return poiEntries;
    }

    public void setPoiEntries(ArrayList<PoiEntry> poiEntries) {
        this.poiEntries = poiEntries;
    }
}
