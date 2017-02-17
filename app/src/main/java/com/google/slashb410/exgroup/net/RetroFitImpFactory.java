package com.google.slashb410.exgroup.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tacademy on 2017-02-14.
 */
public class RetroFitImpFactory
{
    private static RetroFitImpFactory ourInstance = new RetroFitImpFactory();
    public static RetroFitImpFactory getInstance() {
        return ourInstance;
    }
    private RetroFitImpFactory() {
    }
    // 1회만 만든다
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://52.79.48.225:3000/")//애믈"http://10.0.2.2:3000/"
            .addConverterFactory(GsonConverterFactory.create())// 파싱등록
            .build();
    // 1회 요청 API 인터페이스 구현
    Test service = retrofit.create(Test.class);
    public Retrofit getRetrofit() {
        return retrofit;
    }
    public Test getService() {
        return service;
    }
}

