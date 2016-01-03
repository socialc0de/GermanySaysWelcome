package com.github.socialc0de.gsw.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;
import com.github.socialc0de.gsw.R;
import com.github.socialc0de.gsw.customClasses.api.Language;

import java.util.Locale;

/**
 * Created by patricebecker on 16/11/15.
 */
public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private SharedPreferences sharedPreferences;
    private String KEY_LANGUAGE = "languageSetting";
    private String KEY_ASYLUMSTEP = "asylumStep";
    private String KEY_BACKGROUNDUPDATE = "backgroundUpdate";
    private Preference asylumPref, languagePref;
    private Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.settingstoast), Toast.LENGTH_LONG).show();
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
        Log.d("SETTINGS", "Key pressed: " + key);

        if (key.equals(KEY_LANGUAGE)) {
            // Set summary to be the user-description for the selected value
            Log.d("SETTINGS ", "languagePref: " + sharedPreferences.getString(KEY_LANGUAGE, ""));
            setLanguageSetting();
            setLocale();
        } else if (key.equals(KEY_ASYLUMSTEP)) {

            Log.d("SETTINGS ", "asylumPref: " + sharedPreferences.getString(KEY_ASYLUMSTEP, ""));
            setAsylumPref();

        }
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, MainActivity.class);
        startActivity(setIntent);
        super.onBackPressed();
        return;
    }

    public void setAsylumPref() {
        if (sharedPreferences.getString(KEY_ASYLUMSTEP, "").equals("0")) {
            asylumPref.setSummary(getResources().getString(R.string.allinfo));
        } else {
            asylumPref.setSummary(getResources().getString(R.string.settingsstep) + sharedPreferences.getString(KEY_ASYLUMSTEP, ""));
        }
    }

    public void setLanguageSetting() {
        if (sharedPreferences.getString(KEY_LANGUAGE, "").equals(Language.LanguageCodes.DE.toString())) {
            languagePref.setSummary(getResources().getString(R.string.german));
        } else if (sharedPreferences.getString(KEY_LANGUAGE, "").equals(Language.LanguageCodes.EN.toString())) {
            languagePref.setSummary(getResources().getString(R.string.english));
        } else if (sharedPreferences.getString(KEY_LANGUAGE, "").equals(Language.LanguageCodes.AR.toString())) {
            languagePref.setSummary(getResources().getString(R.string.arabic));
        }
    }

    public void setLocale() {
        String lang = sharedPreferences.getString(KEY_LANGUAGE, Language.LanguageCodes.EN.toString());
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
