package com.google.slashb410.exgroup.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Tacademy on 2017-01-20.
 */
public class U {
    private static U ourInstance = new U();

    public static U getInstance() {
        return ourInstance;
    }

    private U() {
    }

    public void myLog(String msg){
        Log.i("U*", "=====================================");
        Log.i("U*", ""+msg); //null 출력하면 죽으니까 ""+
        Log.i("U*", "=====================================");
    }

    //통신큐
    RequestQueue requestQueue;
    public RequestQueue getRequestQueue(Context context){
        if(requestQueue == null) requestQueue = Volley.newRequestQueue(context);
        return requestQueue;
    }



    //다음 액티비티로 + 슬라이드 애니메이션(true, false)
    public void goNext(Context context, Class nextActivity, boolean isSliding){
        Intent intent = new Intent(context, nextActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        context.startActivity(intent);
        if(isSliding){
            ((Activity) context).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
    }

    //작업진행중 창 띄우기
    public void onProgress(Handler handler, Context context, String msg){

        ProgressDialog progressDialog = new ProgressDialog(context);

        progressDialog.setMessage(msg);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
                progressDialog.dismiss();
            }
        }).start();
    }

    //단순 다이얼로그
    public void popSimpleDialog(Handler handler, Context context, String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if(title!=null) {
            builder.setTitle(title);
        }
        if(msg!=null){
            builder.setMessage(msg);
        }
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(handler!=null){
                    handler.sendEmptyMessage(0);
                }
                dialog.dismiss();
            }
        }).show();
    }

    public String[] currentYYmmDD(){

        String[] today = {"",  "", ""};
        long now = System.currentTimeMillis();

        Date date = new Date(now);

        SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        today[0] = curYearFormat.format(date);
        today[1] = curMonthFormat.format(date);
        today[2] = curDayFormat.format(date);

        return today;
    }

    public String[] currentTime(){

        String[] currentTime = {"", "", ""};
        long now = System.currentTimeMillis();
        Date date = new Date(now);

        SimpleDateFormat currentHour = new SimpleDateFormat("HH", Locale.KOREA);
        SimpleDateFormat currentMin = new SimpleDateFormat("mm", Locale.KOREA);
        SimpleDateFormat currentSec = new SimpleDateFormat("ss", Locale.KOREA);

        currentTime[0] = currentHour.format(date);
        currentTime[1] = currentMin.format(date);
        currentTime[2] = currentSec.format(date);

        return currentTime;
    }

}
