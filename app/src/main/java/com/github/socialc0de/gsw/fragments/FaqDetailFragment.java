package com.github.socialc0de.gsw.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.socialc0de.gsw.R;
import com.github.socialc0de.gsw.activities.MainActivity;
import com.github.socialc0de.gsw.activities.SetupActivity;
import com.github.socialc0de.gsw.api.LoadManager_;
import com.github.socialc0de.gsw.api.interfaces.RestArrayRequestCallBack;
import com.github.socialc0de.gsw.customClasses.CardItem;
import com.github.socialc0de.gsw.customClasses.CustomCard;
import com.github.socialc0de.gsw.customClasses.api.FaqCategory;
import com.github.socialc0de.gsw.customClasses.api.FaqEntry;
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

    public int categoryId = 0;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private CardRecyclerView mRecyclerView;
    private SharedPreferences mPrefs;

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
        mPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.getMainActivity());
        final String lngCode = mPrefs.getString(SetupActivity.LANGUAGE_CODE, "en");
        cards.clear();

        LoadManager_.getInstance_(MainActivity.getMainActivity()).loadFaqEntriesByCategoryResults(
                new RestArrayRequestCallBack() {
                    @Override
                    public void onRestResults(int state, ArrayList<?> results) {
                        for(FaqEntry entry : (ArrayList<FaqEntry>)results){

                            if(lngCode.equals("de")){
                                cards.add(createCustomCard(entry.getCreated(), entry.getTranslations().getDe().getQuestion(), entry.getTranslations().getDe().getAnswer()));
                            } else if(lngCode.equals("en")){
                                cards.add(createCustomCard(entry.getCreated(), entry.getTranslations().getEn().getQuestion(), entry.getTranslations().getEn().getAnswer()));
                            } else if(lngCode.equals("fr")){
                                cards.add(createCustomCard(entry.getCreated(), entry.getTranslations().getFr().getQuestion(), entry.getTranslations().getFr().getAnswer()));
                            } else if(lngCode.equals("ar")){
                                cards.add(createCustomCard(entry.getCreated(), entry.getTranslations().getAr().getQuestion(), entry.getTranslations().getAr().getAnswer()));
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
                , categoryId);

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
        }
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}