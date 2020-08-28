package com.muhammadsabeelahmed.entv;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Global {
    public static KProgressHUD mKProgressHUD;
    public static boolean isBackFunctionally = false;
    public static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static boolean useFontFotViews = true;
    public static String appFontNameLIGHT = "Roboto-Light.ttf";
    public static String appFontNameREGULAR = "Roboto-Regular.ttf";
    public static String device_back_tag = "";
    public static boolean started = false;
    public static boolean prepared = false;
    public static void SetView(Context mContext, android.widget.Button selectedView, boolean isBold) {
        if (useFontFotViews) {
            String fontName = Global.appFontNameLIGHT;
            if (isBold) {
                fontName = Global.appFontNameREGULAR;
            }
            android.graphics.Typeface font = android.graphics.Typeface.createFromAsset(mContext.getAssets(), fontName);
            selectedView.setTypeface(font);
        }
    }

    public static void SetView(Context mContext, android.widget.TextView selectedView, boolean isBold) {
        if (useFontFotViews) {
            String fontName = Global.appFontNameLIGHT;
            if (isBold) {
                fontName = Global.appFontNameREGULAR;
            }
            android.graphics.Typeface font = android.graphics.Typeface.createFromAsset(mContext.getAssets(), fontName);
            selectedView.setTypeface(font);
        }
    }

    public static void SetView(Context mContext, android.widget.EditText selectedView, boolean isBold) {
        if (useFontFotViews) {
            String fontName = Global.appFontNameLIGHT;
            if (isBold) {
                fontName = Global.appFontNameREGULAR;
            }
            android.graphics.Typeface font = android.graphics.Typeface.createFromAsset(mContext.getAssets(), fontName);
            selectedView.setTypeface(font);
        }
    }

    public static void ReleaseMemoryOnDestory() {
        try {
            System.gc();
        } catch (Exception ee) {
        }

        try {
            Runtime.getRuntime().gc();
        } catch (Exception ee) {
        }
    }

    public static void HideKeyBoard(Context mContext, android.view.View clickedView) {
        try {
            android.view.inputmethod.InputMethodManager inputManager = (android.view.inputmethod.InputMethodManager)
                    mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(clickedView.getWindowToken(),
                    android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }


    public static int roundValue(float f) {
        int c = (int) ((f) + 0.5f);
        float n = f + 0.5f;
        return (n - c) % 2 == 0 ? (int) f : c;
    }


    public static void changeActivity(Context context, Activity activity) {

        Intent in = new Intent();
        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        in.setClass(context, activity.getClass());
        context.startActivity(in);

    }

    public static void changeFragment(Context context, Fragment fragment) {
        FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein,
                R.anim.fadeout);
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(device_back_tag);
        transaction.commit();
    }

    public static void changeFragmentHome(Context context, Fragment fragment) {
        FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein,
                R.anim.fadeout);
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }


    public static void savedatatostorage(Context context, String data, String file_name) {
        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(context.openFileOutput(file_name, Context.MODE_PRIVATE));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            out.write(data);
            out.write('\n');
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }

    public static void OpenKeybord(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }


    public static void FragmentBackButtonClick(Activity mActivity) {
        Log.d("back_frag", Global.device_back_tag);
        mActivity.getFragmentManager().popBackStack(Global.device_back_tag,
                FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }


    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}

