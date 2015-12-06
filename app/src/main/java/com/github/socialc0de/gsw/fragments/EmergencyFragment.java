package com.github.socialc0de.gsw.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.socialc0de.gsw.R;
import com.github.socialc0de.gsw.customClasses.CustomCard;

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
                intent.setData(Uri.parse("tel:"+ num));
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

        ArrayList<Card> cards = new ArrayList<Card>();

        Card customCard = createCustomCard("Click to call", "Police 100", "Germany Police acts as executive governmental force.","110");
        Card customCard1 = createCustomCard("If you need Ambulance", "Ambulance", "That's a test Paragraph","112");
        Card customCard2 = createCustomCard("Just Testing", "next emergency call number", "that's an example too","800");


        cards.add(customCard);
        cards.add(customCard1);
        cards.add(customCard2);

        CardArrayRecyclerViewAdapter mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), cards);

        //Staggered grid view
        CardRecyclerView mRecyclerView = (CardRecyclerView) view.findViewById(R.id.carddemo_recyclerview);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Set the empty view
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(mCardArrayAdapter);
        }
        return view;
    }


}