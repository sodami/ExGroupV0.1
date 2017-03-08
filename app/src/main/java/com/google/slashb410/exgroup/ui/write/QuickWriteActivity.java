package com.google.slashb410.exgroup.ui.write;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.db.E;
import com.google.slashb410.exgroup.model.group.group.ReqUpload;
import com.google.slashb410.exgroup.model.group.group.ResUpload;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.util.U;
import com.miguelbcr.ui.rx_paparazzo.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo.entities.size.ScreenSize;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class QuickWriteActivity extends AppCompatActivity {
    @BindView(R.id.cameraView)
    LinearLayout cameraView;
    @BindView(R.id.pictureThumbnail)
    ImageView pictureThumbnail;
    @BindView(R.id.pictureTitle)
    TextView pictureTitle;
    @BindView(R.id.content_weight)
    EditText content;
    @BindView(R.id.menu_icon)
    ImageView menuIcon;
    @BindView(R.id.summaryTxt)
    TextView summaryTxt;
    @BindView(R.id.summaryInput)
    EditText summaryInput;
    @BindView(R.id.summaryInput_weight)
    EditText summaryInput_weight;
    @BindView(R.id.kgTxt)
    TextView kgTxt;

    boolean[] checkGroups;
    String dateNTime;

    int category;
    ArrayList groupIds;
    String[] groupTitles;

    ReqUpload reqUpload;
    String picPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_write);
        ButterKnife.bind(this);

        // # 메뉴에 따라 레이아웃 구성
        // [categorytype ] 0:에러 1:체중 2:운동 3:식단
        Intent intent = getIntent();
        category = intent.getIntExtra("menu", 0);
        groupIds = intent.getIntegerArrayListExtra("actGroups");
        groupTitles = intent.getStringArrayExtra("actTitles");
        picPath = intent.getStringExtra("picPath");

        int length = groupIds.size();
        checkGroups = new boolean[length];

        for (int i = 0; i < length; i++) {
            checkGroups[i] = true;
        }

        switch (category) {
            case 0:
                Toast.makeText(this, "다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                menuIcon.setImageResource(R.drawable.scale_black);
                summaryInput.setVisibility(View.GONE);
                summaryInput_weight.setVisibility(View.VISIBLE);
                break;
            case 2:
                menuIcon.setImageResource(R.drawable.exercise_black);
                summaryTxt.setText("운동 한줄요약 : ");
                kgTxt.setVisibility(View.GONE);
                break;
            case 3:
                menuIcon.setImageResource(R.drawable.meal_black);
                summaryTxt.setText("식단 한줄요약 : ");
                kgTxt.setVisibility(View.GONE);
                break;
        }


    }


    @OnClick(R.id.cameraBtn)
    public void onCamera() {

        U.getInstance().myLog("onCamera 들어옴");
        RxPaparazzo.takeImage(this)
                .size(new ScreenSize())
                .usingCamera()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {// See response.resultCode() doc
                    if (response.resultCode() != RESULT_OK) {
                        return;
                    }
                    picPath = response.data();
                    popUpProgress(response.data());
                });
    }

    private void popUpProgress(String path) {

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    goUpload(path);
                }
            }
        };

        U.getInstance().onProgress(handler, this, "사진 업로드 중");
    }

    public void goUpload(String path) {

        U.getInstance().myLog("goUpload 들어옴");
        cameraView.setVisibility(View.GONE);

        Picasso.with(this).setLoggingEnabled(true);
        Picasso.with(this).invalidate(path);

        Picasso.with(this).load("file://" + path).into(pictureThumbnail);

        String pictureRename = renamePicture("weight");
        pictureTitle.setText(pictureRename);

    }

    private String renamePicture(String menu) {

        String nameMenu = menu;
        String[] currentYYmmDD = U.getInstance().currentYYmmDD();
        String[] hhMMss = U.getInstance().currentTime();

        String fullname = category + "_" + currentYYmmDD[0] + currentYYmmDD[1] + currentYYmmDD[2] + hhMMss[0] + hhMMss[1] + hhMMss[2];

        return fullname;
    }

    @OnClick(R.id.pic_deleteBtn)
    public void onDelete() {
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

    @OnClick(R.id.uploadBtn)
    public void onUpload(View view) {
        pickGroup(view);

    }

    public void pickGroup(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("게시할 그룹을 선택하세요.");
        alert.setMultiChoiceItems(groupTitles, checkGroups, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkGroups[which] = isChecked;
            }
        }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //1. reqUpload 세팅
                String final_groupIds = getGroupIds();
                Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                map.put("groupId", RequestBody.create(MediaType.parse("multipart/form-data"), final_groupIds));
                map.put("categoryNum", RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(category)));
                map.put("content", RequestBody.create(MediaType.parse("multipart/form-data"), content.getText().toString()));
                map.put("summary", RequestBody.create(MediaType.parse("multipart/form-data"), getSummary()));

                File file = new File(picPath);
                U.getInstance().myLog(file.getAbsolutePath() + "++" + file.canRead());

                RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                map.put("photo", fileBody);

                //2. 서버 요청
                Call<ResUpload> resUpload = NetSSL.getInstance().getGroupImpFactory().upload(map);
                resUpload.enqueue(new Callback<ResUpload>() {
                    @Override
                    public void onResponse(Call<ResUpload> call, Response<ResUpload> response) {

                        if(response.body()==null) {
                            Snackbar.make(view, "죄송합니다. 다시 시도해 주세요.", Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                        //3. 글쓰기 액티비티 종료
                        if (response.body().getResultCode() == 1) {
                            Handler handler = new Handler() {
                                @Override
                                public void handleMessage(Message msg) {
                                    if (msg.what == 0) {

                                        AlertDialog.Builder builder = new AlertDialog.Builder(QuickWriteActivity.this);
                                        builder.setTitle("업로드를 완료하였습니다!")
                                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        QuickWriteActivity.this.finish();
                                                    }
                                                }).show();
                                    }

                                }
                            };
                            U.getInstance().onProgress(handler, QuickWriteActivity.this, "게시하는 중입니다.");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResUpload> call, Throwable t) {
                        U.getInstance().myLog("ResUpload 접근실패 : " + t);
                    }

                });
            }


        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    private String getSummary() {
        if (category == 1) return summaryInput_weight.getText().toString();
        else return summaryInput.getText().toString();
    }

    private String getGroupIds() {
        String checkGroupIds = "";
        for (int i = 0; i < groupIds.size(); ++i) {
            if (checkGroups[i]) {
                checkGroupIds += groupIds.get(i) + ",";
            }
        }
        return checkGroupIds.substring(1, checkGroupIds.length() - 1);

    }

    @OnClick(R.id.cancelBtn)
    public void onCancel() {
        finish();
    }
}
