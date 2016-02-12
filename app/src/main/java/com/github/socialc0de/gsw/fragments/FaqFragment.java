package com.github.socialc0de.gsw.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.socialc0de.gsw.R;
import com.github.socialc0de.gsw.activities.MainActivity;
import com.github.socialc0de.gsw.activities.SetupActivity;
import com.github.socialc0de.gsw.adapter.CardItemAdapter;
import com.github.socialc0de.gsw.adapter.RecyclerItemClickListener;
import com.github.socialc0de.gsw.api.LoadManager_;
import com.github.socialc0de.gsw.api.interfaces.RestArrayRequestCallBack;
import com.github.socialc0de.gsw.customClasses.CardItem;
import com.github.socialc0de.gsw.customClasses.api.FaqCategory;
import com.github.socialc0de.gsw.customClasses.api.Language;

import java.util.ArrayList;

/**
 * Created by patricebecker on 07/11/15.
 */
public class FaqFragment extends Fragment {

    private ArrayList<CardItem> cardItemArrayList = new ArrayList<CardItem>();
    private RecyclerView recList;
    private View myView;
    private SharedPreferences mPrefs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_faq, container, false);


        // Initialize RecyclerView
        recList = (RecyclerView) myView.findViewById(R.id.faq_recyclerview);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(myView.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.getMainActivity());

        final String lngCode = mPrefs.getString(SetupActivity.LANGUAGE_CODE, Language.LanguageCodes.EN.toString());


        cardItemArrayList.clear();
        LoadManager_.getInstance_(MainActivity.getMainActivity()).loadFaqCategoryResults(
                new RestArrayRequestCallBack() {
                    @Override
                    public void onRestResults(int state, ArrayList<?> results) {
                        for (FaqCategory category : (ArrayList<FaqCategory>) results) {

                            if (lngCode.equals(Language.LanguageCodes.DE.toString())) {
                                cardItemArrayList.add(new CardItem(category.getImage(), category.getTranslations().getDe().getName(), category.getId()));
                            } else if (lngCode.equals(Language.LanguageCodes.EN.toString())) {
                                cardItemArrayList.add(new CardItem(category.getImage(), category.getTranslations().getEn().getName(), category.getId()));
                            } else if (lngCode.equals(Language.LanguageCodes.FR.toString())) {
                                cardItemArrayList.add(new CardItem(category.getImage(), category.getTranslations().getFr().getName(), category.getId()));
                            } else if (lngCode.equals(Language.LanguageCodes.AR.toString())) {
                                cardItemArrayList.add(new CardItem(category.getImage(), category.getTranslations().getAr().getName(), category.getId()));
                            }
                        }
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
        );


        recList.addOnItemTouchListener(
                new RecyclerItemClickListener(myView.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
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
                        faqDetailFragment.setCategoryId(cardItemArrayList.get(position).getId());
                        fragmentTransaction.replace(R.id.container, faqDetailFragment, getResources().getString(R.string.faqdetail));
                        fragmentTransaction.addToBackStack(getResources().getString(R.string.faqdetail));
                        fragmentTransaction.commit();
                    }
                })
        );
        return myView;
    }

    public void reload() {
        CardItemAdapter ca = new CardItemAdapter(cardItemArrayList);
        recList.setAdapter(ca);
        //recList.invalidate();
        //invalidate();
    }

    public void invalidate() {
        myView.post(new Runnable() {
            @Override
            public void run() {
                myView.invalidate();
            }
        });
    }

}
