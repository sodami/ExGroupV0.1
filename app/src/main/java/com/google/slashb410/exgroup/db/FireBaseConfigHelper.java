package com.google.slashb410.exgroup.db;

import android.support.annotation.NonNull;

import com.firebase.ui.database.BuildConfig;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.slashb410.exgroup.util.U;

/**
 * Created by Tacademy on 2017-03-03.
 */

public class FireBaseConfigHelper {
    private static final FireBaseConfigHelper ourInstance = new FireBaseConfigHelper();

    public static FireBaseConfigHelper getInstance() {
        return ourInstance;
    }

    private FireBaseConfigHelper() {
    }

    FirebaseRemoteConfig mFirebaseRemoteConfig;

    public String getConfig(String key) {

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        final String[] value = {""};

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings settings =
                new FirebaseRemoteConfigSettings.Builder()
                        .setDeveloperModeEnabled(BuildConfig.DEBUG)
                        .build();

        mFirebaseRemoteConfig.setConfigSettings(settings);


        U.getInstance().myLog("싱글톤 들어옴");
        mFirebaseRemoteConfig.fetch(0).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(!task.isSuccessful()) {
                    U.getInstance().myLog("실패");
                    return;
                }
                U.getInstance().myLog("파베들어왔음");
                mFirebaseRemoteConfig.activateFetched();
                value[0] = mFirebaseRemoteConfig.getString(key);
                U.getInstance().myLog(value[0]);
            }
        });

        U.getInstance().myLog(value[0]);
        return value[0];

    }
}
