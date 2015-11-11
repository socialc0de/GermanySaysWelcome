package com.github.socialc0de.gsw.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.github.socialc0de.gsw.fragments.PhraseListTabFragment;

import java.util.ArrayList;

/**
 * Created by hp1 on 21-01-2015.
 */
public class PhraseViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<PhraseListTabFragment> fragments; // This will Store the Titles of the Tabs which are Going to be passed when PhraseViewPagerAdapter is created
    int numOfTabs; // Store the number of tabs, this will also be passed when the PhraseViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public PhraseViewPagerAdapter(FragmentManager fm, ArrayList<PhraseListTabFragment> frag) {
        super(fm);
        this.fragments = frag;
        numOfTabs = fragments.size();

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return numOfTabs;
    }
}