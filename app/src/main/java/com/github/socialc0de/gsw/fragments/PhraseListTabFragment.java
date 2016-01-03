package com.github.socialc0de.gsw.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.socialc0de.gsw.R;
import com.github.socialc0de.gsw.activities.MainActivity;
import com.github.socialc0de.gsw.adapter.PhraseListAdapter;
import com.github.socialc0de.gsw.api.LoadManager_;
import com.github.socialc0de.gsw.api.interfaces.RestArrayRequestCallBack;
import com.github.socialc0de.gsw.customClasses.api.PhraseEntry;

import java.util.ArrayList;

/**
 * Created by Roman on 11.11.2015.
 */
public class PhraseListTabFragment extends Fragment {

    private String title = "";
    private int category = 0;
    private ArrayList<PhraseEntry> phraseEntries = new ArrayList<PhraseEntry>();
    private ListView listView;

    public PhraseListTabFragment() {
    }

    public static void getTotalHeightofListView(ListView listView, PhraseListAdapter listAdapter) {

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View mView = listAdapter.getView(i, null, listView);

            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),

                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            totalHeight += mView.getMeasuredHeight();
            Log.w("HEIGHT" + i, String.valueOf(totalHeight));

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    public String getTitle() {
        return title;
    }

    public PhraseListTabFragment setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getCategory() {
        return category;
    }

    public PhraseListTabFragment setCategory(int category) {
        this.category = category;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_list_tab, container, false);

        listView = (ListView) viewRoot.findViewById(R.id.listView);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*
                //((MaterialNavigationDrawer) getActivity()).setFragmentChild(new PhraseDetailFragment(arrayList.get(position)),"Phrase");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new PhraseDetailFragment(arrayList.get(position))).addToBackStack(null).commit();
                MainActivity.getMainActivity().getmDrawer().setSelection(-1, false);
                */

            }
        });


        LoadManager_.getInstance_(MainActivity.getMainActivity()).loadPhraseEntriesByCategoryResults(
                new RestArrayRequestCallBack() {
                    @Override
                    public void onRestResults(int state, ArrayList<?> results) {
                        phraseEntries = (ArrayList<PhraseEntry>) results;
                        MainActivity.getMainActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                reload();
                            }
                        });

                    }

                    @Override
                    public boolean isDestroyed() {
                        return false;
                    }
                }
                , category);

        return viewRoot;
    }

    public void reload() {
        PhraseListAdapter listAdapter = new PhraseListAdapter(getActivity(), R.layout.list_layout, phraseEntries);
        listView.setAdapter(listAdapter);
        // getTotalHeightofListView(listView, listAdapter);
    }
}
