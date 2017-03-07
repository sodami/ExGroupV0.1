package com.google.slashb410.exgroup.net;

import com.google.slashb410.exgroup.model.group.ResStandard;
import com.google.slashb410.exgroup.model.group.group.ReqComment;
import com.google.slashb410.exgroup.model.group.group.ResBoardList;
import com.google.slashb410.exgroup.model.group.group.ResGroupCalendar;
import com.google.slashb410.exgroup.model.group.group.ResGroupGraph;
import com.google.slashb410.exgroup.model.group.group.ResGroupList;
import com.google.slashb410.exgroup.model.group.group.ResGroupSearch;
import com.google.slashb410.exgroup.model.group.group.ResUncertifiedMem;
import com.google.slashb410.exgroup.model.group.group.ResUpload;

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
 * Created by Tacademy on 2017-02-21.
 */

public interface GroupImpFactory {

    //B_1. 그룹 생성하기
    @Multipart
    @POST("groups")
    Call<ResStandard> makeGroup (@PartMap() Map<String, RequestBody> requestBodyMap);

    //B_2. 그룹 검색하기
    @GET("groups/{searchId}")
    Call<ResGroupSearch> searchGroup (@Path("searchId") String searchId);

    //B_3. 그룹 가입하기 [resultCode] 1:성공 2:가입개수제한 3:이미가입한곳에재가입불가
    @POST("groups/{groupId}")
    Call<ResStandard> joinGroup (@Path("groupId") String groupId);

    //C_1. 그룹에서 최초몸무게 존재 여부
    @GET("groups/{groupId}/firstweight")
    Call<ResStandard> groupState (@Path("groupId") String groupId);

    //C_2. 그룹에서 최초몸무게 작성하기
    @Multipart
    @PUT("group/{groupId}/firstweight")
    Call<ResStandard> firstweight (@Path("groupId") String groupId, @PartMap Map<String, RequestBody> map);

    //C_3. 그룹에 가입했지만 인증하지 않은 사람 나타내기
    @GET("groups/{groupid}/uncertifiedMem")
    Call<ResUncertifiedMem> uncertifiedMem (@Path("groupid") String groupId);

    //D_1. 미니프로필 보기 -> A_3으로 처리

    //D_2. 가입 그룹 리스트 불러오기
    @GET("groups")
    Call<ResGroupList> groupList();

    //E_1. 전체 그룹원 순위 보기

    //E_2. 그룹그래프 정보보기
    @GET("groups/{groupId}/lossweight")
    Call<ResGroupGraph> groupGraph(@Path("groupId") String groupId);

    //E_3. 그룹 캘린더에서 해당 날짜 정보 보기
    @GET("groups/{groupId}/calendar/{date}")
    Call<ResGroupCalendar> groupCalendar(@Path("groupId") String groupId, @Path("date") String date);

    //F_1. 그룹 게시글 등록하기
    @Multipart
    @POST("boards")
    Call<ResUpload> upload(@PartMap Map<String, RequestBody> map);

    //F_3. 그룹 게시글 불러오기
    @GET("groups/{groupId}/boards")
    Call<ResBoardList> boardList(@Path("groupId") String groupId);

    //F_4. 게시물에 댓글달기
    @POST("comments")
    Call<String> addComment(@Body ReqComment reqComment);

    //F_5. 게시물에 달린 댓글 모두 보기 -> F_3

    //F_6. 게시물에 달린 댓글 삭제하기

    //F_7. 좋아요 하기
    @POST("favorites")
    Call<ResStandard> favorite(@Body String boardId);

    //F_8. 좋아요 취소하기
    @DELETE("favorites/{favoriteId}")
    Call<ResStandard> unFavorite(@Path("favoriteId") String favoriteId);

}
