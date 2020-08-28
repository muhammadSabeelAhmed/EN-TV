package com.muhammadsabeelahmed.entv;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHandler {

    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;
    private static final String IMG = "pause";

    public PreferencesHandler(Context context) {
        pref = context.getSharedPreferences("entv", Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public String getImg() {
        return pref.getString(IMG, "pause");
    }

    public void setImg(String animation) {
        editor.putString(IMG, animation);
        editor.apply();
        editor.commit();
    }
}

