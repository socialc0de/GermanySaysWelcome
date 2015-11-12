package com.github.socialc0de.gsw.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListAdapter;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;
import com.github.socialc0de.gsw.R;

public class SetupActivity extends ActionBarActivity implements View.OnClickListener {
    private TextView chooseText, stepViewer, instructionText;
    private Button chooseButton;
    private LinearLayout nextButton;

    private CharSequence[] items1 = {"Deutsch","English","\u200Fاللغة العربية","/ˈɟuha ˈʃcipɛ/","فارسی\u200E","درى\u200E","ትግርኛ","српски","русский язык"};
    private CharSequence[] items2 = {"Step 1: You just arrived in Germany (will be translated to chosen language)","Step 2: You wait for asylum confirmation (will be translated to chosen language)","Step 3: You'll start finding a job (will be translated to chosen language)"};


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);


        chooseButton = (Button) findViewById(R.id.choose_button);
        chooseButton.setOnClickListener(this);

        nextButton = (LinearLayout) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);

        chooseText = (TextView) findViewById(R.id.chooseText);
        stepViewer = (TextView) findViewById(R.id.stepViewer);
        instructionText = (TextView) findViewById(R.id.instructions);


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
            case R.id.choose_button:
                if (stepViewer.getText().equals("Step (1/2)")) {
                    new MaterialDialog.Builder(this)
                            .title("Choose Your Asylum Status")
                            .items(items1)
                            .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                @Override
                                public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                    chooseText.setText(text);
                                    return true;
                                }
                            })
                            .positiveText("Done")
                            .show();
                } else {
                    new MaterialDialog.Builder(this)
                            .title("Choose Your Asylum Status")
                            .items(items2)
                            .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                @Override
                                public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                    chooseText.setText(text.subSequence(0,6));
                                    return true;
                                }
                            })
                            .positiveText("Done")
                            .show();
                }
                break;
            case R.id.nextButton:
                if (stepViewer.getText().equals("Step (1/2)")) {
                    chooseText.setText("Step 1");
                    chooseButton.setText("Choose Step");
                    instructionText.setText("To show you according information, we'd like to ask you for your asylum status. Enter your step:");
                    stepViewer.setText("Step (2/2)");
                    Toast.makeText(getApplicationContext(), "@Hamid Hier wird die Sprache angepasst, wenn ich die Übersetzungen habe.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent myIntent = new Intent(SetupActivity.this, MainActivity.class);
                    SetupActivity.this.startActivity(myIntent);
                }
                break;
        }
    }
}
