package com.google.slashb410.exgroup.net;


import com.google.slashb410.exgroup.model.group.ReqSendFCM;
import com.google.slashb410.exgroup.model.group.ResSendFCM;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 레트로핏으로 통신하는 api를 담는 인터페이스
 */
public interface Test
{
    @POST("sendFCM")
    Call<ResSendFCM> sendFCM(@Body ReqSendFCM reqSendFCM);
}
