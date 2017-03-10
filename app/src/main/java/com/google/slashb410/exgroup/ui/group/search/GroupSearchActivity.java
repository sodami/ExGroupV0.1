package com.google.slashb410.exgroup.ui.group.search;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.ResStandard;
import com.google.slashb410.exgroup.model.group.group.GroupSearchData;
import com.google.slashb410.exgroup.model.group.group.ResGroupSearch;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.util.U;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupSearchActivity extends AppCompatActivity {


    @BindView(R.id.searchBtn)
    ImageView searchBtn;
    @BindView(R.id.search_groupId)
    EditText inputId;
    @BindView(R.id.search_noti)
    TextView infoText;
    @BindView(R.id.search_card)
    CardView searchCard;

    //카드뷰 내부 바인딩
    @BindView(R.id.title_search_card)
    TextView titleSearch;
    @BindView(R.id.date_search_card)
    TextView dateSearch;
    @BindView(R.id.nowNum_search_card)
    TextView nowNumSearch;
    @BindView(R.id.maxNum_search_card)
    TextView maxNumSearch;
    @BindView(R.id.join_groupBtn)
    ImageButton joinBtn;
    @BindView(R.id.group_profile_search_card)
    ImageView profileImg;
    @BindView(R.id.period_search_card)
    TextView periodTxt;

    GroupSearchData searchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_search);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        ButterKnife.bind(this);


        inputId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    goSearch(inputId);
                    return true;
                } else {
                    return false;

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.searchBtn)
    public void goSearch(View view) {


//        View view = this.getWindow().getDecorView().getRootView();
        String keyword = inputId.getText().toString();

        if (keyword.equals("")) {
            Snackbar.make(view, "검색할 그룹 ID를 입력해 주세요.", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if(!Pattern.matches("^[0-9]+$", keyword)){
            U.getInstance().myLog("숫자아님");
            Snackbar.make(view, "그룹 ID는 1 이상의 정수입니다.", Snackbar.LENGTH_SHORT).show();
            return;
        }
        Call<ResGroupSearch> resSearchCall = NetSSL.getInstance().getGroupImpFactory().searchGroup(keyword);
        resSearchCall.enqueue(new Callback<ResGroupSearch>() {
            @Override
            public void onResponse(Call<ResGroupSearch> call, Response<ResGroupSearch> response) {
                if (response.isSuccessful()) {
                    if (response.body() == null) {
                        U.getInstance().myLog("body is null");
                        return;
                    }
                    if (response.body().getResultCode() == 1) {
                        U.getInstance().myLog(response.body().getResult().toString());
                        searchData = new GroupSearchData(
                                response.body().getResult().getId(),
                                response.body().getResult().getNowNum(),
                                response.body().getResult().getMaxNum(),
                                response.body().getResult().getGroupTitle(),
                                response.body().getResult().getGroupPicUrl(),
                                response.body().getResult().getExPeriod(),
                                response.body().getResult().getStartDate(),
                                response.body().getResult().getGoalDate(),
                                response.body().getResult().getGroupCreateDate(),
                                response.body().getResult().getActivation(),
                                response.body().getResult().getCtime(),
                                response.body().getResult().getUtime(),
                                response.body().getResult().getBtnAppear()
                        );
                        //검색결과 카드뷰 세팅
                        setSearchCard();

                    } else {
                        //resultCode == 0
                        searchCard.setVisibility(View.GONE);
                        infoText.setVisibility(View.VISIBLE);
                        infoText.setText("해당 ID로 검색되는 그룹이 없습니다.");
                    }

                } else {
                    U.getInstance().myLog("ResSearch : " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResGroupSearch> call, Throwable t) {
                U.getInstance().myLog("접근실패 resGroupSearch : " + t.toString());
            }

        });

    }

    private void setSearchCard() {
        U.getInstance().myLog(searchData.toString());
        searchCard.setVisibility(View.VISIBLE);
        infoText.setVisibility(View.GONE);

        joinBtn.setBackgroundResource(R.drawable.enter_pink);
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResStandard> resJoinGroup = NetSSL.getInstance().getGroupImpFactory().joinGroup(searchData.getId()+"");
                resJoinGroup.enqueue(new Callback<ResStandard>() {
                    @Override
                    public void onResponse(Call<ResStandard> call, Response<ResStandard> response) {
                        if(response.isSuccessful()){
                            if(response.body()!=null){
                                U.getInstance().myLog("resJoinGroup 들어옴");
                                U.getInstance().popSimpleDialog(null, GroupSearchActivity.this, null, response.body().getResult());
                            }else{
                                U.getInstance().myLog("resJoinGroup Body is null : "+response.message());
                            }

                        }else{
                            U.getInstance().myLog("resJoinGroup is not Successful : "+response.message());
                            Snackbar.make(v, "죄송합니다. 다시 시도해 주세요.", Snackbar.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResStandard> call, Throwable t) {
                        U.getInstance().myLog("resJoinGroup Fail : "+t.toString());
                    }
                });
            }
        });

        //1. 가입불가면 그룹 버튼 재설정
        if (searchData.getBtnAppear() == 0) {
            joinBtn.setBackgroundResource(R.drawable.no_enter_gray);
            joinBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    U.getInstance().popSimpleDialog(null, GroupSearchActivity.this, null, "가입할 수 없는 그룹입니다.");
                }
            });
        }

        //2. 데이터 박기
        titleSearch.setText(searchData.getGroupTitle());
        nowNumSearch.setText(searchData.getNowNum() + "");
        maxNumSearch.setText(searchData.getMaxNum() + "");
        periodTxt.setText(searchData.getExPeriod()+"");
        Picasso.with(this).load(searchData.getGroupPicUrl())
                .fit().centerCrop().into(profileImg);

        String customDate = U.getInstance().customDate(searchData.getGroupCreateDate());
        dateSearch.setText(customDate);

    }
}
