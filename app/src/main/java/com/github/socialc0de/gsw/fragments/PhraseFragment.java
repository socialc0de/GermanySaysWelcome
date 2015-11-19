package com.github.socialc0de.gsw.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.socialc0de.gsw.R;
import com.github.socialc0de.gsw.SlidingTabLayout;
import com.github.socialc0de.gsw.adapter.PhraseViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Roman on 11.11.2015.
 */
public class PhraseFragment extends Fragment {
    public PhraseFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_phrase_tab, container, false);

        ArrayList<PhraseListTabFragment> tbs = new ArrayList<PhraseListTabFragment>();
        tbs.add(new PhraseListTabFragment().setTitle(getString(R.string.basic_conversation)));
        tbs.add(new PhraseListTabFragment().setTitle(getString(R.string.medical_terms)));
        tbs.add(new PhraseListTabFragment().setTitle(getString(R.string.food)));
        tbs.add(new PhraseListTabFragment().setTitle(getString(R.string.clothing)));
        tbs.add(new PhraseListTabFragment().setTitle(getString(R.string.children)));
        tbs.add(new PhraseListTabFragment().setTitle(getString(R.string.profession)));
        tbs.add(new PhraseListTabFragment().setTitle(getString(R.string.animals)));
        tbs.add(new PhraseListTabFragment().setTitle(getString(R.string.children)));
        tbs.add(new PhraseListTabFragment().setTitle(getString(R.string.nature_weather)));
        tbs.add(new PhraseListTabFragment().setTitle(getString(R.string.basic_adjectives)));
        tbs.add(new PhraseListTabFragment().setTitle(getString(R.string.colours)));
        tbs.add(new PhraseListTabFragment().setTitle(getString(R.string.direction_places_transport)));
        tbs.add(new PhraseListTabFragment().setTitle(getString(R.string.sexual_gender_identity)));
        tbs.add(new PhraseListTabFragment().setTitle(getString(R.string.misc)));
        PhraseViewPagerAdapter adapter = new PhraseViewPagerAdapter(getChildFragmentManager(), tbs);

        // Assigning ViewPager View and setting the adapter
        ViewPager pager = (ViewPager) viewRoot.findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        SlidingTabLayout tabs = (SlidingTabLayout) viewRoot.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.accentColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);


        return viewRoot;
    }

}
