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

import com.github.socialc0de.gsw.R;
import com.github.socialc0de.gsw.adapter.CardItemAdapter;
import com.github.socialc0de.gsw.adapter.RecyclerItemClickListener;
import com.github.socialc0de.gsw.customClasses.CardItem;

import java.util.ArrayList;

/**
 * Created by patricebecker on 07/11/15.
 */
public class EmergencyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.emergency, container, false);


        // Initialize RecyclerView
        RecyclerView recList = (RecyclerView) view.findViewById(R.id.emergency);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);


        CardItem sampleCard = new CardItem(R.drawable.faq, getResources().getString(R.string.example));

        ArrayList<CardItem> cardItemArrayList = new ArrayList<CardItem>();
//@Todo have to read the lenght of the list dynamic out of the json/Backend
        cardItemArrayList.add(sampleCard);
        cardItemArrayList.add(sampleCard);


/*        public ArrayList<EmergencyItem> getEmergencyItemRest()
        {
            RestClient client = new RestClient("http://www.example.com/demo.php");  //Write your url here
            client.addParam("Name", "Bhavit"); //Here I am adding key-value parameters
            client.addParam("Age", "23");

            client.addHeader("content-type", "application/json"); // Here I am specifying that the key-value pairs are sent in the JSON format

            try {
                String response = client.executePost(); // In case your server sends any response back, it will be saved in this response string.

            } catch (Exception e) {
                e.printStackTrace();
            }


        } */


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
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        FaqDetailFragment faqDetailFragment = new FaqDetailFragment();
                        fragmentTransaction.replace(R.id.container, faqDetailFragment, getResources().getString(R.string.faqdetail));
                        fragmentTransaction.addToBackStack(getResources().getString(R.string.faqdetail));
                        fragmentTransaction.commit();
                    }
                })
        );
        return view;
    }

}
