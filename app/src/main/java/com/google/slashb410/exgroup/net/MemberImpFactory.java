package com.google.slashb410.exgroup.net;

import com.google.slashb410.exgroup.model.group.ResStandard;
import com.google.slashb410.exgroup.model.group.group.ResGroupData;
import com.google.slashb410.exgroup.model.group.home.ReqJoin;
import com.google.slashb410.exgroup.model.group.home.ReqLogin;
import com.google.slashb410.exgroup.model.group.home.ResAttend;
import com.google.slashb410.exgroup.model.group.home.ResInitInfo;
import com.google.slashb410.exgroup.model.group.home.ResJoin;
import com.google.slashb410.exgroup.model.group.home.ResLogin;
import com.google.slashb410.exgroup.model.group.home.ResLogout;
import com.google.slashb410.exgroup.model.group.home.ResMe;
import com.google.slashb410.exgroup.model.group.home.ResSessionOut;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;


/**
 * Created by Tacademy on 2017-02-20.
 */

/**
 * 회원관련 모든 API 구현
 1. 카카오톡 로그인 및 최초 회원등록_
 2. 로컬 회원 최초 가입_
 3. 로컬 회원 로그인_
 4. 로컬 회원 정보 변경_
 5. 회원 탈퇴하기_
 6. 회원 로그아웃_
 7. 회원 상세 정보 보기_
 8. 회원 본인 모든 기록 보기_
 9. 회원 본인 점수 등록_
 10. 회원 본인 점수 수정_
 11. 회원 본인 점수 삭제_
 */


public interface MemberImpFactory {

    //A_1. 페이스북 로그인 및 최초 사용자 등록
    @POST("facebook/token/{access_token}")
    Call<ResStandard> facebookLogin(@Path("access_token") String token);

    //A_2. 기초 정보 등록
    @Multipart
    @PUT("users")
    Call<ResInitInfo> initInfo(@PartMap Map<String, RequestBody> params);

    //A_3. 자신의 정보 보기
    @GET("users/me")
    Call<ResMe> userMe();

    //A_4. 로그아웃
    @GET("auth/local/logout")
    Call<ResLogout> logout();

    //A_5. 회원 탈퇴하기
    @DELETE("users")
    Call<ResSessionOut> sessionout();

    //A_6. 최초 회원가입
    @POST("users")
    Call<ResJoin> join(@Body ReqJoin reqJoin);

    //A_7. 로그인
    @POST("auth/local/login")
    Call<ResLogin> login(@Body ReqLogin reqLogin);

    //D_2. 가입 그룹 리스트 불러오기
    @GET("groups")
    Call<ResGroupData> groupData();

    //D_3. 출석하기
    @POST("users/me/attendance")
    Call<ResAttend> attend (@Body String attendDate);

}


