package com.google.slashb410.exgroup.ui.group.search;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.InnerSearchData;
import com.google.slashb410.exgroup.model.group.SearchData;
import com.google.slashb410.exgroup.model.group.group.ResGroupSearch;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.util.U;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupSearchActivity extends AppCompatActivity {

    ArrayList<SearchData> searchDatas;
    @BindView(R.id.search_list)
    ListView searchList;
    SearchListAdapter searchListAdapter;
    @BindView(R.id.searchBtn)
    ImageButton searchBtn;
    @BindView(R.id.search_groupId)
    EditText inputId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_search);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        ButterKnife.bind(this);

        searchDatas = new ArrayList<>();
        searchList.findViewById(R.id.search_list);

        InnerSearchData data1 = new InnerSearchData(1, "슬비네그룹", "2017년 2월 16일", "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTljSpp2dT2sosNYj5qVV7UkgZP-S8mNRJ-kSyEWU_3IRa16PazCQGGKg", 5, 3);
        SearchData searchdata1 = new SearchData();
        searchdata1.setResult(data1);

        searchDatas.add(0, searchdata1);

        searchListAdapter = new SearchListAdapter(this, searchDatas);
        searchList.setAdapter(searchListAdapter);


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
    public void goSearch(View view){
        U.getInstance().myLog("검색버튼 들어오긴함");
        String keyword = inputId.getText().toString();
        if(keyword.equals("")){
            Snackbar.make(view, "검색할 그룹 ID를 입력해 주세요.", Snackbar.LENGTH_SHORT).show();
            return;
        }
        Call<ResGroupSearch> resSearchCall = NetSSL.getInstance().getGroupImpFactory().searchGroup(keyword);
        resSearchCall.enqueue(new Callback<ResGroupSearch>() {
            @Override
            public void onResponse(Call<ResGroupSearch> call, Response<ResGroupSearch> response) {
                if (response.body() == null) {
                    U.getInstance().myLog("body is null");
                    return;
                }
                if (response.body().getResultCode() == 1) {
                    U.getInstance().myLog(response.body().getResult().get(0).toString());

                } else {
                    //resultCode == 0
                    Snackbar.make(view, "죄송합니다. 다시 시도해 주세요.", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResGroupSearch> call, Throwable t) {

            }

        });

    }
}
