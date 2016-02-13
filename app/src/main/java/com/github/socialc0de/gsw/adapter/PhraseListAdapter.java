package com.github.socialc0de.gsw.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.socialc0de.gsw.R;
import com.github.socialc0de.gsw.customClasses.api.PhraseEntry;

import java.util.ArrayList;

/**
 * Created by roman on 02.01.2016.
 */

public class PhraseListAdapter extends ArrayAdapter<PhraseEntry> {

    private int targetedLayout;
    private ArrayList<PhraseEntry> arrayList;
    private Typeface typeface1;
    private Context context;

    public PhraseListAdapter(Context context, int resource, ArrayList<PhraseEntry> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.targetedLayout = resource;
        this.arrayList = arrayList;

        // Define Font for PhraseListItem titles
        typeface1 = Typeface.createFromAsset(context.getAssets(), "ralewaybold.ttf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            // get layout from mobile.xml
            v = inflater.inflate(R.layout.phrase_row, null);
            Log.d("getView called, ", "convertView == null");
            //Log.e("Convertview == ", "null");
        } else {
            v = convertView;
            //Log.e("Convertview =/= ", "null");
            Log.d("getView called, ", "View = convertView");

        }
        PhraseEntry currentData = arrayList.get(position);


        if (currentData.getTranslations().getDe() != null)
            ((TextView) v.findViewById(R.id.german_phrase)).setText(currentData.getTranslations().getDe().getPhrase());

        if (currentData.getTranslations().getEn() != null)
            ((TextView) v.findViewById(R.id.english_phrase)).setText(currentData.getTranslations().getEn().getPhrase());

        if (currentData.getTranslations().getAr() != null)
            ((TextView) v.findViewById(R.id.arabic_phrase)).setText(currentData.getTranslations().getAr().getPhrase());


        if (currentData.getTranslations().getFr() != null)
            ((TextView) v.findViewById(R.id.francais_phrase)).setText(currentData.getTranslations().getFr().getPhrase());
        return v;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }
}
