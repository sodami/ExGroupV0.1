package com.google.slashb410.exgroup.ui.group.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.SearchData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Tacademy on 2017-02-16.
 */

public class SearchListAdapter extends BaseAdapter {

    Context context;
    ArrayList<SearchData> searchData;
    LayoutInflater inflater;

    public SearchListAdapter(Context context,  ArrayList<SearchData> searchData) {
        this.context = context;
        this.searchData = searchData;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
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

        View view = convertView;
        ViewHolder viewHolder = new ViewHolder();

        if(view==null) {
         view= inflater.inflate(R.layout.cell_search_list, parent, false);

            viewHolder.groupTitle = (TextView) view.findViewById(R.id.group_title_search);
            viewHolder.gourpStartday = (TextView) view.findViewById(R.id.group_start_search);
            viewHolder.countMem = (TextView) view.findViewById(R.id.nowMem);
            viewHolder.maxMem = (TextView) view.findViewById(R.id.maxMem);
            viewHolder.groupProfile = (CircleImageView) view.findViewById(R.id.group_profileImg_search);

        }
        if(searchData!=null){
            Picasso.with(context).load(searchData.get(position).getResult().getPicUrl())
                    .fit().centerCrop().into(viewHolder.groupProfile);

            viewHolder.groupTitle.setText(searchData.get(position).getResult().getGroupTitle());
            viewHolder.gourpStartday.setText(searchData.get(position).getResult().getStartDate());
            viewHolder.countMem.setText(searchData.get(position).getResult().getCountNum());
            viewHolder.maxMem.setText(searchData.get(position).getResult().getMaxNum());

        }
        return view;
    }


    class ViewHolder{
        CircleImageView groupProfile;
        TextView groupTitle;
        TextView gourpStartday;
        TextView maxMem;
        TextView countMem;
    }

}
