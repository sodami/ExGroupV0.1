package com.google.slashb410.exgroup.net;

import com.google.slashb410.exgroup.model.group.group.ResGroupData;
import com.google.slashb410.exgroup.model.group.home.ReqInitInfo;
import com.google.slashb410.exgroup.model.group.home.ReqJoin;
import com.google.slashb410.exgroup.model.group.home.ReqLogin;
import com.google.slashb410.exgroup.model.group.home.ReqUsers;
import com.google.slashb410.exgroup.model.group.home.ResInitInfo;
import com.google.slashb410.exgroup.model.group.home.ResJoin;
import com.google.slashb410.exgroup.model.group.home.ResLogin;
import com.google.slashb410.exgroup.model.group.home.ResMe;
import com.google.slashb410.exgroup.model.group.home.ResStandard;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;


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

    // 2. 로컬 회원 최초 가입
    @POST("users")
    Call<ResStandard> userInfo(@Body ReqUsers reqUsers);

    //A_2. 기초 정보 등록
    @PUT("users")
    Call<ResInitInfo> initInfo(@Body ReqInitInfo reqInitInfo);

//    A_3. 자신의 정보 보기
    @GET("users/me")
    Call<ResMe> userMe();

    //A_6. 최초 회원가입
    @POST("users")
    Call<ResJoin> join(@Body ReqJoin reqJoin);

    //A_7. 로그인
    @POST("auth/local/login")
    Call<ResLogin> login(@Body ReqLogin reqLogin);

    //D_2. 가입 그룹 리스트 불러오기
    @GET("groups")
    Call<ResGroupData> groupData();



}


