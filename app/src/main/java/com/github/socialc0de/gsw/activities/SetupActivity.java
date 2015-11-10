package com.github.socialc0de.gsw.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListAdapter;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;
import com.github.socialc0de.gsw.R;

public class SetupActivity extends ActionBarActivity implements View.OnClickListener {
    private MaterialSimpleListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Button chooseLanguageButton = (Button) findViewById(R.id.choose_language);
        chooseLanguageButton.setOnClickListener(this);

        adapter = new MaterialSimpleListAdapter(this);
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content("Syria")
                .backgroundColor(Color.WHITE)
                .icon(R.drawable.sy)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content("Afghanistan")
                .backgroundColor(Color.WHITE)
                .icon(R.drawable.af)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content("Eritrea")
                .icon(R.drawable.er)
                .backgroundColor(Color.WHITE)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content("Somalia")
                .icon(R.drawable.so)
                .backgroundColor(Color.WHITE)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content("Iraq")
                .icon(R.drawable.ir)
                .backgroundColor(Color.WHITE)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content("Russia")
                .icon(R.drawable.ru)
                .backgroundColor(Color.WHITE)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content("Germany")
                .icon(R.drawable.de)
                .backgroundColor(Color.WHITE)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content("France")
                .icon(R.drawable.fr)
                .backgroundColor(Color.WHITE)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content("Great Britain")
                .icon(R.drawable.gb)
                .backgroundColor(Color.WHITE)
                .build());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setup, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_language:
                new MaterialDialog.Builder(this)
                        .title("Choose Your Language")
                        .adapter(adapter, new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                MaterialSimpleListItem item = adapter.getItem(which);
                                Log.d("Item Selected: " + text, "");
                                // TODO
                            }
                        })
                        .positiveText("Done")
                        .show();
                break;
        }
    }
}
