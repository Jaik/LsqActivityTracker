package com.leadsquared.library;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jainendra Kumar on 7/3/2015.
 */
public class SharedPreferenceManager {
    SharedPreferences mPrefs;

    public SharedPreferenceManager(Context context, String name) {
        mPrefs = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public void storeStringPreference(String preferenceName, String value) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(preferenceName, value);
        prefsEditor.commit();
    }

    public String getStringPreference(String preferenceName) {
        return mPrefs.getString(preferenceName, "");
    }

    public String getStringPreference(String preferenceName, String defaultValue) {
        return mPrefs.getString(preferenceName, defaultValue);
    }

    public void clearSharedPreference(String preferenceName) {
        mPrefs.edit().remove(preferenceName).commit();
    }
}
