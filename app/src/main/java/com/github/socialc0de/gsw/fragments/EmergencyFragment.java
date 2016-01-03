package com.github.socialc0de.gsw.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.socialc0de.gsw.R;
import com.github.socialc0de.gsw.activities.MainActivity;
import com.github.socialc0de.gsw.activities.SetupActivity;
import com.github.socialc0de.gsw.api.LoadManager_;
import com.github.socialc0de.gsw.api.interfaces.RestArrayRequestCallBack;
import com.github.socialc0de.gsw.customClasses.CustomCard;
import com.github.socialc0de.gsw.customClasses.api.EmergencyEntry;
import com.github.socialc0de.gsw.customClasses.api.FaqEntry;
import com.github.socialc0de.gsw.customClasses.api.Language;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.ViewToClickToExpand;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmergencyFragment extends Fragment {

    private ArrayList<Card> cards = new ArrayList<Card>();
    private CardRecyclerView mRecyclerView;
    private SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.getMainActivity());
    private final String lngCode = mPrefs.getString(SetupActivity.LANGUAGE_CODE, "EN");

    public EmergencyFragment() {
    }

    public CustomCard createCustomCard(String question, String category, String answer, String telNummer) {
        CustomCard customCard = new CustomCard(getActivity(), question);
        final String num = telNummer;


        ViewToClickToExpand viewToClickToExpand =
                ViewToClickToExpand.builder()
                        .highlightView(false)
                        .setupCardElement(ViewToClickToExpand.CardElementUI.CARD);
        customCard.setViewToClickToExpand(viewToClickToExpand);

//Set onClick listener
        customCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + num));
                startActivity(intent);
            }
        });

        CardHeader cardHeader = new CardHeader(getActivity());
        cardHeader.setTitle(category);
        cardHeader.setButtonExpandVisible(true);

        CardExpand cardExpand = new CardExpand(getContext());
        cardExpand.setTitle(answer);
        customCard.addCardExpand(cardExpand);


        customCard.addCardHeader(cardHeader);
        customCard.setCardElevation(4.0f);

        return customCard;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.emergency, container, false);

        LoadManager_.getInstance_(MainActivity.getMainActivity()).loadEmergencyEntriesResults(
                new RestArrayRequestCallBack() {
                    @Override
                    public void onRestResults(int state, ArrayList<?> results) {
                        for (EmergencyEntry entry : (ArrayList<EmergencyEntry>) results) {

                            if (lngCode.equals(Language.LanguageCodes.DE.toString())) {
                                cards.add(createCustomCard(entry.getNumber(), entry.getTranslations().getDe().getName(), entry.getTranslations().getDe().getDescription(), entry.getNumber()));
                            } else if (lngCode.equals(Language.LanguageCodes.EN.toString())) {
                                cards.add(createCustomCard(entry.getNumber(), entry.getTranslations().getEn().getName(), entry.getTranslations().getEn().getDescription(), entry.getNumber()));
                            } else if (lngCode.equals(Language.LanguageCodes.FR.toString())) {
                                cards.add(createCustomCard(entry.getNumber(), entry.getTranslations().getFr().getName(), entry.getTranslations().getFr().getDescription(), entry.getNumber()));
                            } else if (lngCode.equals(Language.LanguageCodes.AR.toString())) {
                                cards.add(createCustomCard(entry.getNumber(), entry.getTranslations().getAr().getName(), entry.getTranslations().getAr().getDescription(), entry.getNumber()));
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
                });


        //Staggered grid view
        mRecyclerView = (CardRecyclerView) view.findViewById(R.id.carddemo_recyclerview);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }

    private void reload(){
        CardArrayRecyclerViewAdapter mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), cards);
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(mCardArrayAdapter);
            mRecyclerView.invalidate();
        }
    }

}