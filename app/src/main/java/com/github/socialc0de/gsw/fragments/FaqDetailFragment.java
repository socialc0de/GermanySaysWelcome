package com.github.socialc0de.gsw.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.socialc0de.gsw.R;
import com.github.socialc0de.gsw.customClasses.CustomCard;
import com.melnykov.fab.FloatingActionButton;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.ViewToClickToExpand;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FaqDetailFragment extends Fragment implements View.OnClickListener {


    public FaqDetailFragment() {
    }

    public CustomCard createCustomCard(String question, String category, String answer) {
        CustomCard customCard = new CustomCard(getActivity(), question);

        ViewToClickToExpand viewToClickToExpand =
                ViewToClickToExpand.builder()
                        .highlightView(false)
                        .setupCardElement(ViewToClickToExpand.CardElementUI.CARD);
        customCard.setViewToClickToExpand(viewToClickToExpand);

        // Set onClick listener
        customCard.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                card.doToogleExpand();
            }
        });


        CardExpand cardExpand = new CardExpand(getActivity());
        cardExpand.setTitle(answer);
        customCard.addCardExpand(cardExpand);

        CardHeader cardHeader = new CardHeader(getActivity());
        cardHeader.setTitle(category);
        cardHeader.setButtonExpandVisible(true);

        customCard.addCardHeader(cardHeader);
        customCard.setCardElevation(4.0f);

        return customCard;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq_detail, container, false);

        FloatingActionButton newQuestion = (FloatingActionButton) view.findViewById(R.id.questionButton);
        newQuestion.setColorNormalResId(R.color.accentColor);
        newQuestion.setColorRippleResId(R.color.accentColor);
        newQuestion.setColorPressedResId(R.color.accentColor);
        newQuestion.setOnClickListener(this);

        ArrayList<Card> cards = new ArrayList<Card>();

        Card customCard = createCustomCard("How to manage my finances? How to manage my finances? How to manage my finances? How to manage my finances?", "Law & Order", "Ask local authorities. Ask local authorities. Ask local authorities. Ask local authorities. Ask local authorities");
        Card customCard1 = createCustomCard("How to manage my asylum application?", "Asylum", "Ask local authorities");
        Card customCard2 = createCustomCard("How to manage my job application?", "Education & Work", "Ask local authorities");


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


    @Override
    public void onClick(View view) {
        new MaterialDialog.Builder(getActivity())
                .title(getResources().getString(R.string.newquestion))
                .content(getResources().getString(R.string.enterquestion))
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(getResources().getString(R.string.hinttextquestion), "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        // Do something
                    }
                }).show();
    }
}