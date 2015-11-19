package com.github.socialc0de.gsw.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;
import com.github.socialc0de.gsw.R;

/**
 * Created by patricebecker on 16/11/15.
 */
public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private SharedPreferences sharedPreferences;
    private String asylumStep = "asylumStep";
    private String languageSetting = "languageSetting";
    private String KEY_LANGUAGE = "languageSetting";
    private String KEY_ASYLUMSTEP = "asylumStep";
    private String KEY_BACKGROUNDUPDATE = "backgroundUpdate";
    private Preference asylumPref, languagePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(), "SettingsActivity started", Toast.LENGTH_LONG).show();
        addPreferencesFromResource(R.xml.preferences);
        asylumPref = findPreference(KEY_ASYLUMSTEP);
        languagePref = findPreference(KEY_LANGUAGE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

          setAsylumPref();
        setLanguageSetting();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d("SETTINGS", "Key pressed: "+key);
        if (key.equals(KEY_LANGUAGE)) {
            // Set summary to be the user-description for the selected value
            Log.d("SETTINGS ","languagePref: "+sharedPreferences.getString(KEY_LANGUAGE,""));
            setLanguageSetting();
        }
        else if (key.equals(KEY_ASYLUMSTEP)) {

            Log.d("SETTINGS ", "asylumPref: " + sharedPreferences.getString(KEY_ASYLUMSTEP, ""));
            setAsylumPref();

        }
    }

    public void setAsylumPref(){
        if (sharedPreferences.getString(KEY_ASYLUMSTEP, "").equals("0")) {
            asylumPref.setSummary("View all information");
        } else {
            asylumPref.setSummary("Step: " + sharedPreferences.getString(KEY_ASYLUMSTEP, ""));
        }
    }

    public void setLanguageSetting(){
        if (sharedPreferences.getString(KEY_LANGUAGE,"").equals("de")){
            languagePref.setSummary("Deutsch");
        }
        else if (sharedPreferences.getString(KEY_LANGUAGE,"").equals("en")){
            languagePref.setSummary("English");
        }
        else if (sharedPreferences.getString(KEY_LANGUAGE,"").equals("ar")){
            languagePref.setSummary("Arabic");
        }
    }
}
