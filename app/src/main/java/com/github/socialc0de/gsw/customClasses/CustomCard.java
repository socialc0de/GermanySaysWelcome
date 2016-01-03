package com.github.socialc0de.gsw.customClasses;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.socialc0de.gsw.R;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by patricebecker on 10/11/15.
 */
public class CustomCard extends Card {

    protected TextView headerView;

    protected String mTitleHeader;

    public CustomCard(Context context, String titleHeader) {
        super(context, R.layout.row_card);
        this.mTitleHeader = titleHeader;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        // TODO Auto-generated method stub
        headerView = (TextView) view
                .findViewById(R.id.card_main_inner_simple_header);

        headerView.setText(mTitleHeader);

    }
}