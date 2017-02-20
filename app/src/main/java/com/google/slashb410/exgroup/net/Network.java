package com.google.slashb410.exgroup.net;

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
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ec2-52-78-98-243.ap-northeast-2.compute.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Retrofit getRetrofit() {
        return retrofit;
    }

    ////////////////////////////////////////////////////////////////////////
    MemberImpFactory memberImpFactory;

    public MemberImpFactory getMemberImpFactory() {
        if (memberImpFactory == null) {
            memberImpFactory = retrofit.create(MemberImpFactory.class);
        }
        return memberImpFactory;
    }
    ////////////////////////////////////////////////////////////////////////
}


