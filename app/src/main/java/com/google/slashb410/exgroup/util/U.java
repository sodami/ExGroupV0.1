package com.google.slashb410.exgroup.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.slashb410.exgroup.db.E;
import com.miguelbcr.ui.rx_paparazzo.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo.entities.size.ScreenSize;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

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
    public void goNext(Context context, Class nextActivity, boolean isSliding, boolean kill){
        Intent intent = new Intent(context, nextActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (kill) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

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

    public void uploadFireBase(Context context, String folderPath, String path){

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl("gs://exgroup-1faeb.appspot.com");

        Uri file = Uri.fromFile(new File(path));
        StorageReference imgRef = storageReference.child(folderPath+file.getLastPathSegment());
        UploadTask uploadTask = imgRef.putFile(file);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                U.getInstance().popSimpleDialog(null, context, "업로드에 실패했습니다. 다시 시도해주세요.", null);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                U.getInstance().popSimpleDialog(null, context, "업로드에 성공했습니다.", null);
            }
        });


    }



    public void onCamera(Activity activity, ImageView imageView){

        RxPaparazzo.takeImage(activity)
                .size(new ScreenSize())
                .usingCamera()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {// See response.resultCode() doc

                    if (response.resultCode() != RESULT_OK) {
                        return;
                    }

                    Log.i("CAM", "들어옴");
                    U.getInstance().myLog("onCamera response : "+response.data());
                    String path  =  response.data();
                    U.getInstance().myLog("path : "+path);

                    E.KEY.TEMP_PIC_URI = path;
                    if(imageView!=null) loadImage(activity, response.data(), imageView);
                });

    }

    public void onGallery(Activity activity, ImageView imageView){
        RxPaparazzo.takeImage(activity)
                .usingGallery()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {// See response.resultCode() doc
                    if (response.resultCode() != RESULT_OK) {
                       return;
                    }
                    String path  =  "file://" + response.data();
                    U.getInstance().myLog("path : "+path);

                    E.KEY.TEMP_PIC_URI = path;
                    if(imageView!=null) loadImage(activity, response.data(), imageView);
                });
    }

    public void loadImage(Activity activity, String path, ImageView imageView) {
        String url = "file://" + path;

        Picasso.with(activity).setLoggingEnabled(true);
        Picasso.with(activity).invalidate(url);
        Picasso.with(activity).load(url).into(imageView);

    }
    public String customDateNTime(){


        String date[] = U.getInstance().currentYYmmDD();
        String time[] = U.getInstance().currentTime();

        return date[0]+"년 "+date[1]+"월 "+date[2]+"일 "+time[0]+"시 "+time[1]+"분";

    }

    public String customDate(String date){

        String[] splitDate = date.split("-");
        String year = splitDate[0];
        String month = splitDate[1];
        String day = splitDate[2].substring(0,2);

        return year+"년 "+month+"월 "+day+"일";

    }


//    public String customDateNTime() {
//
//    }
}
