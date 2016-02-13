package com.github.socialc0de.gsw.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.socialc0de.gsw.R;
import com.github.socialc0de.gsw.SlidingTabLayout;
import com.github.socialc0de.gsw.activities.MainActivity;
import com.github.socialc0de.gsw.activities.SetupActivity;
import com.github.socialc0de.gsw.adapter.PhraseViewPagerAdapter;
import com.github.socialc0de.gsw.api.LoadManager_;
import com.github.socialc0de.gsw.api.interfaces.RestArrayRequestCallBack;
import com.github.socialc0de.gsw.customClasses.api.Language;
import com.github.socialc0de.gsw.customClasses.api.PhraseCategory;

import java.util.ArrayList;

/**
 * Created by Roman on 11.11.2015.
 */
public class PhraseFragment extends Fragment {

    private SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.getMainActivity());
    private final String lngCode = mPrefs.getString(SetupActivity.LANGUAGE_CODE, "en");

    public PhraseFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View viewRoot = inflater.inflate(R.layout.fragment_phrase_tab, container, false);

        final ArrayList<PhraseListTabFragment> tbs = new ArrayList<PhraseListTabFragment>();
        LoadManager_.getInstance_(MainActivity.getMainActivity()).loadPhraseCategoryResults(
                new RestArrayRequestCallBack() {
                    @Override
                    public void onRestResults(int state, ArrayList<?> results) {
                        for (PhraseCategory category : (ArrayList<PhraseCategory>) results) {


                            if (lngCode.equals(Language.LanguageCodes.de.toString())) {
                                tbs.add(new PhraseListTabFragment().setTitle(category.getTranslations().getDe().getName()).setCategory(category.getId()));
                            } else if (lngCode.equals(Language.LanguageCodes.en.toString())) {
                                tbs.add(new PhraseListTabFragment().setTitle(category.getTranslations().getEn().getName()).setCategory(category.getId()));
                            } else if (lngCode.equals(Language.LanguageCodes.fr.toString())) {
                                tbs.add(new PhraseListTabFragment().setTitle(category.getTranslations().getFr().getName()).setCategory(category.getId()));
                            } else if (lngCode.equals(Language.LanguageCodes.ar.toString())) {
                                tbs.add(new PhraseListTabFragment().setTitle(category.getTranslations().getAr().getName()).setCategory(category.getId()));
                            }
                        }

                        MainActivity.getMainActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
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
                            }
                        });

                    }

                    @Override
                    public boolean isDestroyed() {
                        return false;
                    }
                }
        );

        return viewRoot;
    }

}
