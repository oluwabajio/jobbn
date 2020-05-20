package com.example.jobbn.utils;

import android.content.SharedPreferences;

import static com.example.jobbn.utils.MyApplication.getSharedPreferences;

public class SessionManager {



    private final SharedPreferences pref = getSharedPreferences();
    public static final String EMAIL = "EMAIL";
    public static final String DISPLAY_NAME = "DISPLAY_NAME";
    public static final String UUID = "UUID";
    public static final String AGE =  "AGE";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String BEDRIFTER_DISPLAY_NAME = "BEDRIFTER_DISPLAY_NAME";
    public static final String BEDRIFTER_EMAIL = "BEDRIFTER_EMAIL";


    private void setIntPreference(String name, int value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(name, value);
        editor.apply();
    }

    private void setBooleanPreference(String name, boolean value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    private long getLongPreference(String name) {
        if (pref.contains(name)) {
            return pref.getLong(name, 0);
        } else {
            return 0;
        }
    }

    private void setLongPreference(String name, long value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(name, value);
        editor.apply();
    }


    private void setStringPreference(String name, String value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(name, value);
        editor.apply();
    }

    private void setFloatPreference(String name, float value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat(name, value);
        editor.apply();
    }

    private Integer getIntPreference(String name) {
        if (pref.contains(name)) {
            return pref.getInt(name, 0);
        } else {
            return 0;
        }
    }

    private boolean getBooleanPreference(String name) {
        return pref.contains(name) && pref.getBoolean(name, false);
    }

    private float getFloatPreference(String name) {
        if (pref.contains(name)) {
            return pref.getFloat(name, 0);
        } else {
            return 0;
        }
    }

    private String getStringPreference(String name) {
        if (pref.contains(name)) {
            return pref.getString(name, "");
        } else {
            return null;
        }
    }



    public String getEmail() {
        return getStringPreference(EMAIL);
    }

    public void setEmail(String email) {
        setStringPreference(EMAIL, email);
    }

    public String getDisplayName() {
        return getStringPreference(DISPLAY_NAME);
    }

    public void setDisplayName(String name) {
        setStringPreference(DISPLAY_NAME, name);
    }

    public void setUuid(String uuid) {
        setStringPreference(UUID, uuid);
    }

    public String getUuid() {
        return getStringPreference(UUID);
    }

    public void setAge(String age) {
        setStringPreference(AGE, age);
    }

    public String getAge() {
        return getStringPreference(AGE);
    }

    public void setDescription(String description) {
        setStringPreference(DESCRIPTION, description);
    }

    public String getDescription() {
        return getStringPreference(DESCRIPTION);
    }


    public String getBedrifterEmail() {
        return getStringPreference(BEDRIFTER_EMAIL);
    }

    public void setBedrifterEmail(String email) {
        setStringPreference(BEDRIFTER_EMAIL, email);
    }

    public String getBedrifterDisplayName() {
        return getStringPreference(BEDRIFTER_DISPLAY_NAME);
    }

    public void setBedrifterDisplayName(String name) {
        setStringPreference(BEDRIFTER_DISPLAY_NAME, name);
    }



}
