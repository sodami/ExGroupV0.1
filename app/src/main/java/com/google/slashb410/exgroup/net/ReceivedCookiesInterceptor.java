package com.google.slashb410.exgroup.net;

import android.content.Context;

import com.google.slashb410.exgroup.db.StorageHelper;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Tacademy on 2017-02-22.
 */
public class ReceivedCookiesInterceptor implements Interceptor {

    Context context;
    public ReceivedCookiesInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            } // Preference에 cookies를 넣어주는 작업을 수행
            StorageHelper.getInstance().setSetString(context, "Cookie", cookies);
        }
        return originalResponse;
    }
}
