package com.github.socialc0de.gsw.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.github.socialc0de.gsw.R;
import com.github.socialc0de.gsw.adapter.CardItemAdapter;
import com.github.socialc0de.gsw.adapter.RecyclerItemClickListener;
import com.github.socialc0de.gsw.customClasses.CardItem;

import java.util.ArrayList;

/**
 * Created by patricebecker on 07/11/15.
 */
public class FaqFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_faq, container, false);


        // Initialize RecyclerView
        RecyclerView recList = (RecyclerView) view.findViewById(R.id.faq_recyclerview);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);


        CardItem sampleCard = new CardItem(R.drawable.faq,"Beispielkategorie");

        ArrayList<CardItem> cardItemArrayList = new ArrayList<CardItem>();
        cardItemArrayList.add(sampleCard);
        cardItemArrayList.add(sampleCard);
        cardItemArrayList.add(sampleCard);
        cardItemArrayList.add(sampleCard);
        cardItemArrayList.add(sampleCard);
        cardItemArrayList.add(sampleCard);


        CardItemAdapter ca = new CardItemAdapter(cardItemArrayList);
        recList.setAdapter(ca);
        recList.addOnItemTouchListener(
                new RecyclerItemClickListener(view.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                                               /*
                        FAQCategoryFragment faqCategoryFragment = new FAQCategoryFragment(list.get(position).getId());
                        //((MaterialNavigationDrawer) getActivity()).setFragmentChild(faqCategoryFragment,"Answers");
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, faqCategoryFragment).addToBackStack(null).commit();
                        MainActivity.getMainActivity().getmDrawer().setSelection(-1, false);
                        */
                        FragmentTransaction fragmentTransaction =  getActivity().getSupportFragmentManager().beginTransaction();
                        FaqDetailFragment faqDetailFragment= new FaqDetailFragment();
                        fragmentTransaction.replace(R.id.container, faqDetailFragment, "FAQ Detail Fragment");
                        fragmentTransaction.addToBackStack("FAQ Detail Fragment");
                        fragmentTransaction.commit();
                    }
                })
        );
        return view;
    }

}
