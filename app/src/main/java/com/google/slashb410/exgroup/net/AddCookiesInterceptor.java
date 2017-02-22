package com.google.slashb410.exgroup.net;

import android.content.Context;

import com.google.slashb410.exgroup.db.StorageHelper;

import java.io.IOException;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Tacademy on 2017-02-22.
 */

public class AddCookiesInterceptor implements Interceptor {

    Context context;
    public AddCookiesInterceptor(Context context) {
        this.context = context;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Set<String> preferences = StorageHelper.getInstance().getSetString(context, "cookie");
        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
        }
        // Web,Android,iOS 구분을 위해 User-Agent세팅
        builder.removeHeader("User-Agent").addHeader("User-Agent", "Android");

        return chain.proceed(builder.build());
    }
}

