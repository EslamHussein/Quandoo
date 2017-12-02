package com.quandoo.utils;


import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.StringRes;

import com.quandoo.App;


public class TextUtils {

    private static final String EMPTY_STRING_PATTERN = "^$|\\s+";

    public static String getString(@StringRes int resId) {
        return App.get().getString(resId);
    }


    public static float getColor(@ColorRes int resId) {
        return App.get().getResources().getColor(resId);
    }

    public static boolean isEmptyString(String str) {
        if (str == null || str.length() == 0 ||
                str.matches(EMPTY_STRING_PATTERN)) {
            return true;
        }
        return false;
    }
}
