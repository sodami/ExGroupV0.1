package com.google.slashb410.exgroup.ui.group.search;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.InnerSearchData;
import com.google.slashb410.exgroup.model.group.SearchData;
import com.google.slashb410.exgroup.util.U;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupSearchActivity extends AppCompatActivity {

    ArrayList<SearchData> searchDatas;
    @BindView(R.id.search_list)
    ListView searchList;
    SearchListAdapter searchListAdapter;
    @BindView(R.id.searchBtn)
    ImageButton searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_search);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        ButterKnife.bind(this);

        searchDatas = new ArrayList<>();
        searchList = new ListView(this);
        searchList.findViewById(R.id.search_list);

        InnerSearchData data1 = new InnerSearchData(1, "슬비네그룹", "2017년 2월 16일", "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTljSpp2dT2sosNYj5qVV7UkgZP-S8mNRJ-kSyEWU_3IRa16PazCQGGKg", 3, 5);
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
    public void goSearch(){
        U.getInstance().myLog("들어오긴함");
        InnerSearchData data2 = new InnerSearchData(2, "소담이네그룹", "2017년 2월 10일", "http://image.fmkorea.com/files/attach/new/20160714/486616/2489100/413010944/99b983892094b5c6d2fc3736e15da7d1.jpg", 2, 3);
        SearchData searchData2 = new SearchData();
        searchData2.setResult(data2);
        searchDatas.add(1, searchData2);

        searchListAdapter.notifyDataSetChanged();
    }
}
