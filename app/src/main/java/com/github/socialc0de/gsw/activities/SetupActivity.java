package com.github.socialc0de.gsw.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.socialc0de.gsw.R;

import java.util.Locale;

public class SetupActivity extends ActionBarActivity implements View.OnClickListener {
    private TextView chooseText, stepViewer, instructionText, nextButtonText;
    private Button chooseButton;
    private LinearLayout nextButton;
    private SharedPreferences mPrefs;
    private final String asylumStep = "asylumStep";
    private final String languageSetting = "languageSetting";
    private Locale myLocale;
    private boolean steponedone = false;

    private CharSequence[] items1;
    private CharSequence[] items2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        items1 = new CharSequence[]{getResources().getString(R.string.arabic), getResources().getString(R.string.english), getResources().getString(R.string.german)};
        items2 = new CharSequence[]{getResources().getString(R.string.step1), getResources().getString(R.string.step2), getResources().getString(R.string.step3)};

        chooseButton = (Button) findViewById(R.id.choose_button);
        chooseButton.setOnClickListener(this);

        nextButtonText = (TextView) findViewById(R.id.nextButtonText);

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
                if (stepViewer.getText().equals(getResources().getString(R.string.setupstep1))) {
                    new MaterialDialog.Builder(this)
                            .title(getResources().getString(R.string.setupstep1title))
                            .items(items1)
                            .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                @Override
                                public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                    String langCode;

                                    switch (which){
                                        case 0:
                                            langCode = "de";
                                            break;
                                        case 1:
                                            langCode = "en";
                                            break;
                                        case 2:
                                            langCode = "ar";
                                            break;
                                        default:
                                            langCode = "en";
                                            break;

                                    }
                                    setLocale(langCode);

                                    mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor editor = mPrefs.edit();
                                    editor.putString(languageSetting, langCode);
                                    editor.commit();

                                    chooseText.setText(text);
                                    Log.d("[SETUP] ", "Language : " + which);
                                    return true;
                                }
                            })
                            .positiveText(getResources().getString(R.string.done))
                            .show();
                } else {
                    new MaterialDialog.Builder(this)
                            .title(getResources().getString(R.string.setupstep2title))
                            .items(items2)
                            .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                @Override
                                public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                    String stepText = (String) text;
                                    int step = Integer.parseInt(stepText.replaceAll("[\\D]", ""));

                                    Log.d("[SETUP] ", "Step selected: " + step);
                                    chooseText.setText(text);

                                    mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor editor = mPrefs.edit();
                                    editor.putString(asylumStep, step+"");
                                    editor.commit();

                                    return true;
                                }
                            })
                            .positiveText(getResources().getString(R.string.done))
                            .show();
                }
                break;
            case R.id.nextButton:
                if (!steponedone) {
                    chooseText.setText("");
                    chooseButton.setText(getResources().getString(R.string.setupstep2button));
                    instructionText.setText(getResources().getString(R.string.setupstep2description));
                    stepViewer.setText(getResources().getString(R.string.setupstep2));
                    nextButtonText.setText(getResources().getString(R.string.next));
                    steponedone = true;
                } else {
                    Intent myIntent = new Intent(SetupActivity.this, MainActivity.class);
                    SetupActivity.this.startActivity(myIntent);
                }
                break;
        }
    }

    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }
}
