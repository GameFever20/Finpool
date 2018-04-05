package utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by bunny on 16/10/17.
 */

public class SettingManager {



    public static void setPatternLock(Context mContext , String pattern) {
        SharedPreferences prefs = mContext.getSharedPreferences("settings", 0);


        SharedPreferences.Editor editor = prefs.edit();

        // Increment launch counter

        editor.putString("pattern", pattern);



        editor.apply();
    }

    public static String getPatternLock(Context mContext ) {
        SharedPreferences prefs = mContext.getSharedPreferences("settings", 0);

        return prefs.getString("pattern", "a");

    }


    public static void setLoggedIn(Context mContext , boolean loggedIn) {
        SharedPreferences prefs = mContext.getSharedPreferences("settings", 0);


        SharedPreferences.Editor editor = prefs.edit();

        // Increment launch counter

        editor.putBoolean("loggedIn", loggedIn);



        editor.apply();
    }

    public static boolean getLoggedIn(Context mContext ) {
        SharedPreferences prefs = mContext.getSharedPreferences("settings", 0);

        return prefs.getBoolean("loggedIn", false);

    }



}
