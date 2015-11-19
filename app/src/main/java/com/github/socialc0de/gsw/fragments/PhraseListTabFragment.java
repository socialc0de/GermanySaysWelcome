package com.github.socialc0de.gsw.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.socialc0de.gsw.R;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_list_tab, container, false);

        return viewRoot;
    }
}
