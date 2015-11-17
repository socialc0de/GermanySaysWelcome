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
    private String asylumStep, languageSetting;
    private String KEY_LANGUAGE = "languageSetting";
    private String KEY_ASYLUMSTEP = "asylumStep";
    private String KEY_BACKGROUNDUPDATE = "backgroundUpdate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(), "SettingsActivity started", Toast.LENGTH_LONG).show();
        addPreferencesFromResource(R.xml.preferences);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(KEY_LANGUAGE)) {
            Preference languagePref = findPreference(key);
            // Set summary to be the user-description for the selected value
            Log.d("SETTINGS ","languagePref: "+sharedPreferences.getString(KEY_LANGUAGE,""));
            languagePref.setSummary(sharedPreferences.getString(key, ""));
        }
        else if (key.equals(KEY_ASYLUMSTEP)){
            Preference asylumPref = findPreference(key);
            Log.d("SETTINGS ","asylumPref: "+sharedPreferences.getString(KEY_ASYLUMSTEP,""));
            asylumPref.setSummary("Step: "+sharedPreferences.getString(key,""));
        }
    }
}
