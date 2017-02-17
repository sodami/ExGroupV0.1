package com.google.slashb410.exgroup.ui.group.create;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.util.U;
import com.miguelbcr.ui.rx_paparazzo.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo.entities.size.ScreenSize;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeightCheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_check);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.cam_weightBtn)
    public void goCam(){
//        UCrop.Options options = new UCrop.Options();
//        options.setToolbarColor(ContextCompat.getColor(this, R.color.colorAccent));
//        options.setMaxBitmapSize(1024 * 1024); //1메가

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
            uploadLocal(response.data());
        });
    }



    public void uploadLocal(String path) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle(R.string.input_weight);
        alert.setMessage("체중을 직접 입력해주세요.");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                value.toString();

                U.getInstance().goNext(getApplicationContext(), GroupInviteActivity.class, false, false);
                finish();
            }
        });
//
//        alert.setNegativeButton("Cancel",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        // Canceled.
//                    }
//                });

        alert.show();


    }
}
