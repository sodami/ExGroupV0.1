//package com.google.slashb410.exgroup.net;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.google.gson.Gson;
//import com.google.slashb410.exgroup.util.U;
//
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
///**
// * Created by Tacademy on 2017-01-20.
// */
//public class Network {
//    private static Network ourInstance = new Network();
//
//    public static Network getInstance() {
//        return ourInstance;
//    }
//
//    private Network() {
//    }
//
//    /////////////////////////////
//    //통신 큐
//
//
//    ///////////////////////////
//    //통신 API
//    public void sendAddress(Context context, ArrayList<AddressModel> cc) {
//        U.getInstance().myLog("서버 전송");
//
//        //1.파라미터 구성
//        ReqAdd json = new ReqAdd();
//        ReqAdd.header header = json.new header();
//        json.setBody(cc);
//
//        Log.i("CC", new Gson().toJson(json));
//        //2.요청 구성
//
//
//    try
//
//    {
//        JsonObjectRequest jsonObjectRequest =
//                new JsonObjectRequest(
//                        Request.Method.POST,
//                        "http://ec2-52-79-35-166.ap-northeast-2.compute.amazonaws.com:3000/insertAddr",
//                        //"http://10.0.2.2:3000/insertAddr",
//                        new JSONObject(new Gson().toJson(json)),
//                        new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                // 4. 응답처리
//                                U.getInstance().myLog(response.toString());
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                            }
//                        }
//                );
//        // 3. 요청 (타임아웃 설정 추가 필요)
//        U.getInstance().getRequestQueue(context).add(jsonObjectRequest);
//    }
//
//    catch(
//    Exception e
//    ){}
//
//    //3.통신
//    //4.응답(성공, 실패)
//}
//
//
//    public void searchHp(Context context, String tel){
//        //1파라미터 구성
//        ReqHp reqHp = new ReqHp();
//        ReqHp.header header = reqHp.new header();
//        ReqHp.body body = reqHp.new body(tel, ContactsService.uid);
//
//        header.setCode("SH");
//
//
//
//
//        //2요청생성
//
//        try
//
//        {
//            JsonObjectRequest jsonObjectRequest =
//                    new JsonObjectRequest(
//                            Request.Method.POST,
//                            "http://ec2-52-79-35-166.ap-northeast-2.compute.amazonaws.com:3000/selectTel",
//                            //"http://10.0.2.2:3000/insertAddr",
//                            new JSONObject(new Gson().toJson(reqHp)),
//                            new Response.Listener<JSONObject>() {
//                                @Override
//                                public void onResponse(JSONObject response) {
//                                    // 4. 응답처리
//                                    U.getInstance().myLog(response.toString());
//                                    ResHp resHp = new Gson().fromJson(response.toString(), ResHp.class);
//                                }
//                            },
//                            new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                }
//                            }
//                    );
//            // 3. 요청 (타임아웃 설정 추가 필요)
//            U.getInstance().getRequestQueue(context).add(jsonObjectRequest);
//        }
//
//        catch(
//                Exception e
//                ){}
//
//        //3요청큐에 요청추가
//        //4응답
//    }
//}
