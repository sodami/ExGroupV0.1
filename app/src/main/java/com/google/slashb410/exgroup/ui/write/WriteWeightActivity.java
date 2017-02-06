package com.google.slashb410.exgroup.ui.write;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.util.U;
import com.miguelbcr.ui.rx_paparazzo.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo.entities.size.ScreenSize;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WriteWeightActivity extends AppCompatActivity {
    @BindView(R.id.cameraView)
    LinearLayout cameraView;
    @BindView(R.id.pictureThumbnail)
    ImageView pictureThumbnail;
    @BindView(R.id.pictureTitle)
    TextView pictureTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_weight);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.cameraBtn)
    public void onCamera(){
        RxPaparazzo.takeImage(this)
                .size(new ScreenSize())
                .usingCamera()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {// See response.resultCode() doc
                    if (response.resultCode() != RESULT_OK) {
                        //  response.targetUI().showUserCanceled();
                        return;
                    }
                    popUpProgress(response.data());

                });
    }

    private void popUpProgress(String path) {

        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0){
                    goUpload(path);
                }
            }
        };

        U.getInstance().onProgress(handler, this, "사진 업로드 중");
    }

    public void goUpload(String path){
        cameraView.setVisibility(View.GONE);

        String url = "file://" + path;

        Picasso.with(this).setLoggingEnabled(true);
        Picasso.with(this).invalidate(url);

        Picasso.with(this).load(url).into(pictureThumbnail);

    }

    @OnClick(R.id.pic_deleteBtn)
    public void onDelete(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("사진 입력을 취소하시겠습니까?");
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cameraView.setVisibility(View.VISIBLE);

                dialog.dismiss();
            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        }).show();
    }
}
