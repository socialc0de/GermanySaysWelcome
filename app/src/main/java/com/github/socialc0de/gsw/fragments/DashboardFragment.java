package com.github.socialc0de.gsw.fragments;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.socialc0de.gsw.R;
import com.github.socialc0de.gsw.activities.MainActivity;
import com.github.socialc0de.gsw.adapter.CardItemAdapter;
import com.github.socialc0de.gsw.adapter.RecyclerItemClickListener;
import com.github.socialc0de.gsw.api.LoadManager_;
import com.github.socialc0de.gsw.api.interfaces.RestArrayRequestCallBack;
import com.github.socialc0de.gsw.customClasses.CardItem;
import com.github.socialc0de.gsw.customClasses.api.De;
import com.github.socialc0de.gsw.customClasses.api.FaqEntry;
import com.github.socialc0de.gsw.customClasses.api.Language;
import com.github.socialc0de.gsw.customClasses.api.Translations;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by patricebecker on 10/11/15.
 */
public class DashboardFragment extends android.support.v4.app.Fragment {


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        RecyclerView recList = (RecyclerView) view.findViewById(R.id.dashboard_recyclerview);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);


        final CardItem faqCard = new CardItem(R.drawable.faq, getResources().getString(R.string.faq), 0);
        CardItem mapCard = new CardItem(R.drawable.map, getResources().getString(R.string.map), 1);
        CardItem phrasebookCard = new CardItem(R.drawable.phrasebook, getResources().getString(R.string.phrasebook), 2);

        ArrayList<CardItem> cardItemArrayList = new ArrayList<CardItem>();
        cardItemArrayList.add(faqCard);
        cardItemArrayList.add(mapCard);
        cardItemArrayList.add(phrasebookCard);

        CardItemAdapter ca = new CardItemAdapter(cardItemArrayList);
        recList.setAdapter(ca);
        recList.addOnItemTouchListener(
                new RecyclerItemClickListener(view.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        switch (position) {
                            case 0:
                                FaqFragment faqFragment = new FaqFragment();
                                fragmentTransaction.replace(R.id.container, faqFragment, getResources().getString(R.string.faq));
                                fragmentTransaction.addToBackStack(getResources().getString(R.string.faq));
                                fragmentTransaction.commit();
                                break;
                            case 1:
                                FaqEntry faqEntry = new FaqEntry();
                                faqEntry.setCounty(2222);
                                faqEntry.setTranslations(new Translations().setDe(((De)(new De().setQuestion("karl")))));
                                LoadManager_.getInstance_(MainActivity.getMainActivity()).addFaqEntry(faqEntry);

                                LoadManager_.getInstance_(MainActivity.getMainActivity()).loadEmergencyEntriesResults(
                                        new RestArrayRequestCallBack() {
                                            @Override
                                            public void onRestResults(int state, ArrayList<?> results) {
                                                Log.d("dd","");
                                            }

                                            @Override
                                            public boolean isDestroyed() {
                                                return false;
                                            }
                                        }
                                );

                                break;
                            case 2:
                                PhraseFragment phraseFragment = new PhraseFragment();
                                fragmentTransaction.replace(R.id.container, phraseFragment, getResources().getString(R.string.phrasebook));
                                fragmentTransaction.addToBackStack(getResources().getString(R.string.phrasebook));
                                fragmentTransaction.commit();
                                break;
                        }
                        Log.d("Position = ", "" + position);
                        /*
                        FaqDetailFragment faqDetailFragment= new FaqDetailFragment();
                        fragmentTransaction.replace(R.id.container, faqDetailFragment, "FAQ Detail Fragment");
                        fragmentTransaction.addToBackStack("FAQ Detail Fragment");
                        fragmentTransaction.commit();*/
                    }
                })
        );

        return view;
    }


}
