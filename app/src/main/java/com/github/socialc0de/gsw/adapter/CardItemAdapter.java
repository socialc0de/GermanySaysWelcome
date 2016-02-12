package com.github.socialc0de.gsw.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.socialc0de.gsw.R;
import com.github.socialc0de.gsw.activities.MainActivity;
import com.github.socialc0de.gsw.customClasses.CardItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by patricebecker on 07/11/15.
 */
public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.CardItemViewHolder> {
    private ArrayList<CardItem> cardItemArrayList;

    public CardItemAdapter(ArrayList<CardItem> cardItemArrayList) {
        this.cardItemArrayList = cardItemArrayList;
    }

    @Override
    public CardItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_view, parent, false);

        return new CardItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardItemViewHolder holder, int position) {
        CardItem cardItem = cardItemArrayList.get(position);
        holder.dashboardText.setText(cardItem.getCategoryName());
        if(!cardItem.getImage().equals(""))
            Picasso.with(MainActivity.getMainActivity()).load(cardItem.getImage()).into(holder.dashboardImage);
        else
            Picasso.with(MainActivity.getMainActivity()).load(cardItem.getImageID()).into(holder.dashboardImage);

    }

    @Override
    public int getItemCount() {
        return cardItemArrayList.size();
    }

    public class CardItemViewHolder extends RecyclerView.ViewHolder {
        private TextView dashboardText;
        private ImageView dashboardImage;

        public CardItemViewHolder(View v) {
            super(v);
            dashboardText = (TextView) v.findViewById(R.id.cardViewText);
            dashboardImage = (ImageView) v.findViewById(R.id.cardViewImage);
        }

    }
}