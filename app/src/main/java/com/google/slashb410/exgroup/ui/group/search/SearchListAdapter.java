package com.google.slashb410.exgroup.ui.group.search;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.ResStandard;
import com.google.slashb410.exgroup.model.group.group.GroupSearchData;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.util.U;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tacademy on 2017-02-16.
 */

public class SearchListAdapter extends BaseAdapter {

    Context context;
    ArrayList<GroupSearchData> searchData;
    LayoutInflater inflater;
    View view;

    public SearchListAdapter(Context context, ArrayList<GroupSearchData> searchData) {
        this.context = context;
        this.searchData = searchData;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        U.getInstance().myLog("검색데이터 size : "+ searchData.size());
        return searchData.size();
    }

    @Override
    public Object getItem(int position) {
        return searchData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        view = convertView;
        ViewHolder viewHolder = new ViewHolder();

        if (view == null)
            view = inflater.inflate(R.layout.cell_search_list, parent, false);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setMessage(searchData.get(position).getGroupTitle()+"에 가입하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                joinGroup(searchData.get(position).getId());
                            }
                        }).show();

                return true;
            }
        });
        viewHolder.groupTitle = (TextView) view.findViewById(R.id.group_title_search);
        viewHolder.gourpStartday = (TextView) view.findViewById(R.id.group_start_search);
        viewHolder.countMem = (TextView) view.findViewById(R.id.nowMem);
        viewHolder.maxMem = (TextView) view.findViewById(R.id.maxMem);
        viewHolder.groupProfile = (CircleImageView) view.findViewById(R.id.group_profileImg_search);

        Picasso.with(context).load(searchData.get(position).getGroupPicUrl())
                .fit().centerCrop().into(viewHolder.groupProfile);
        viewHolder.groupTitle.setText(searchData.get(position).getGroupTitle());

        String customDate = U.getInstance().customDate(searchData.get(position).getGroupCreateDate());

        viewHolder.gourpStartday.setText(customDate);
        viewHolder.countMem.setText(searchData.get(position).getNowNum()+"");
        viewHolder.maxMem.setText(searchData.get(position).getMaxNum()+"");

        return view;
    }

    class ViewHolder {
        CircleImageView groupProfile;
        TextView groupTitle;
        TextView gourpStartday;
        TextView maxMem;
        TextView countMem;
    }


    private void joinGroup(int groupId) {

        Call<ResStandard> joinGroup = NetSSL.getInstance().getGroupImpFactory().joinGroup(String.valueOf(groupId));
        joinGroup.enqueue(new Callback<ResStandard>() {
            @Override
            public void onResponse(Call<ResStandard> call, Response<ResStandard> response) {
                if(response.body()==null) {
                    Snackbar.make(view, "죄송합니다. 다시 시도해 주세요.", Snackbar.LENGTH_SHORT).show();
                    U.getInstance().myLog("resJoinGroup body is null");
                    return;
                }
                else{
                    U.getInstance().popSimpleDialog(null, context, null, response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<ResStandard> call, Throwable t) {
                Snackbar.make(view, "죄송합니다. 다시 시도해 주세요.", Snackbar.LENGTH_SHORT).show();
                U.getInstance().myLog("joinGroup 접근실패 : "+t.toString());
                return;
            }
        });

    }


}
