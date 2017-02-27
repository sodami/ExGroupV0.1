package com.google.slashb410.exgroup.net;

import com.google.slashb410.exgroup.model.group.ResStandard;
import com.google.slashb410.exgroup.model.group.group.ReqComment;
import com.google.slashb410.exgroup.model.group.group.ReqUpload;
import com.google.slashb410.exgroup.model.group.group.ResBoardList;
import com.google.slashb410.exgroup.model.group.group.ResGroupList;
import com.google.slashb410.exgroup.model.group.group.ResGroupSearch;
import com.google.slashb410.exgroup.model.group.group.ResUncertifiedMem;
import com.google.slashb410.exgroup.model.group.group.ResUpload;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

/**
 * Created by Tacademy on 2017-02-21.
 */

public interface GroupImpFactory {

    //B_1. 그룹 생성하기
    @Multipart
    @POST("groups")
    Call<ResStandard> makeGroup (@PartMap Map<String, RequestBody> requestBodyMap);

//    @POST("groups")
//    Call<ResStandard> makeGroup (@Body ReqMakeGroup reqMakeGroup);

    //B_2. 그룹 검색하기
    @GET("groups/{searchId}")
    Call<ResGroupSearch> searchGroup (@Path("searchId") String searchId);

    //C_1. 그룹에서 최초몸무게 존재 여부
    @GET("groups/{groupId}/firstweight")
    Call<ResStandard> groupState (@Path("groupId") String groupId);

    //C_3. 그룹에 가입했지만 인증하지 않은 사람 나타내기
    @GET("groups/{groupid}/uncertifiedMem")
    Call<ResUncertifiedMem> uncertifiedMem (@Path("groupid") String groupId);

    //D_2. 가입 그룹 리스트 불러오기
    @GET("groups")
    Call<ResGroupList> groupList();

    //F_1. 그룹 게시글 등록하기
    @POST("boards")
    Call<ResUpload> upload(@Body ReqUpload reqUpload);

    //F_3. 그룹 게시글 불러오기
    @GET("groups/{groupId}/boards")
    Call<ResBoardList> boardList(@Path("groupId") String groupId);

    //F_4. 게시물에 댓글달기
    @POST("comments")
    Call<String> addComment(@Body ReqComment reqComment);

}
