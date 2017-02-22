package com.google.slashb410.exgroup.net;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tacademy on 2017-02-20.
 */
public class Network {
    private static Network ourInstance = new Network();

    public static Network getInstance() {
        return ourInstance;
    }

    private Network() {
    }


    ////////////////////////////////////////////////////////////////////////

    Retrofit retrofit;

    public Retrofit getRetrofit(Context context) {

        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new AddCookiesInterceptor(context)); // VERY VERY IMPORTANT
        builder.addInterceptor(new ReceivedCookiesInterceptor(context)); // VERY VERY IMPORTANT
        client = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-52-78-98-243.ap-northeast-2.compute.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

    ////////////////////////////////////////////////////////////////////////
    MemberImpFactory memberImpFactory;

    public MemberImpFactory getMemberImpFactory(Context context) {
        if (memberImpFactory == null) {
            retrofit= getRetrofit(context);
            memberImpFactory = retrofit.create(MemberImpFactory.class);
        }
        return memberImpFactory;
    }

    GroupImpFactory groupImpFactory;

    public GroupImpFactory getGroupImpFactory(Context context) {
        if (groupImpFactory == null) {
            retrofit= getRetrofit(context);
            groupImpFactory = retrofit.create(GroupImpFactory.class);
        }
        return groupImpFactory;
    }
    ////////////////////////////////////////////////////////////////////////
}


