package com.github.socialc0de.gsw.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.github.socialc0de.gsw.MainActivity;
import com.github.socialc0de.gsw.R;
import com.github.socialc0de.gsw.adapter.CardItemAdapter;
import com.github.socialc0de.gsw.adapter.RecyclerItemClickListener;
import com.github.socialc0de.gsw.customClasses.CardItem;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends android.support.v4.app.Fragment {


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_dashboard, container, false);

        RecyclerView recList = (RecyclerView) view.findViewById(R.id.dashboard_recyclerview);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);


        CardItem faqCard = new CardItem(R.drawable.faq,"Frequently Asked Questions");
        CardItem mapCard = new CardItem(R.drawable.map, "Map");
        CardItem phrasebookCard = new CardItem(R.drawable.phrasebook, "Phrasebook");

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
                        Toast.makeText(view.getContext(), "Netter Versuch, Karl!",
                                Toast.LENGTH_LONG).show();                        /*
                        FAQCategoryFragment faqCategoryFragment = new FAQCategoryFragment(list.get(position).getId());
                        //((MaterialNavigationDrawer) getActivity()).setFragmentChild(faqCategoryFragment,"Answers");
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, faqCategoryFragment).addToBackStack(null).commit();
                        MainActivity.getMainActivity().getmDrawer().setSelection(-1, false);
                        */
                    }
                })
        );

        return view;
    }

}
