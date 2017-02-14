package com.google.slashb410.exgroup.db;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Tacademy on 2016-12-30.
 */
public class StorageHelper {
    private static StorageHelper ourInstance = new StorageHelper();

    public static StorageHelper getInstance() {
        return ourInstance;
    }

    private StorageHelper() {
    }

    public void setString(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences(E.KEY.STORAGE_KEY, 0).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(Context context, String key) {

        return context.getSharedPreferences(E.KEY.STORAGE_KEY, 0).getString(key, null);

    }

    public void setBoolean(Context context, String key, boolean value){

        SharedPreferences.Editor editor = context.getSharedPreferences(E.KEY.STORAGE_KEY, 0).edit();

        editor.putBoolean(key, value);
        editor.commit();
    }


    public boolean getBoolean(Context context, String key){

        return context.getSharedPreferences(E.KEY.STORAGE_KEY, 0).getBoolean(key, false);

    }


}
