package com.google.slashb410.exgroup.db;

/**
 * Created by Tacademy on 2017-02-09.
 */
public class FireBaseStorageHelper {
    private static FireBaseStorageHelper ourInstance = new FireBaseStorageHelper();

    public static FireBaseStorageHelper getInstance() {
        return ourInstance;
    }

    private FireBaseStorageHelper() {
    }
}
