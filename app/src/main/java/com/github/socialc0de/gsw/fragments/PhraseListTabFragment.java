package com.github.socialc0de.gsw.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by Roman on 11.11.2015.
 */
public class PhraseListTabFragment extends Fragment {

    private String title = "";

    public PhraseListTabFragment() {
    }

    public String getTitle() {
        return title;
    }

    public PhraseListTabFragment setTitle(String title) {
        this.title = title;
        return this;
    }
}
