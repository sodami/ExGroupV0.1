//package com.google.slashb410.exgroup;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.support.v7.widget.CardView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.google.slashb410.exgroup.db.E;
//import com.google.slashb410.exgroup.model.group.group.actGroup;
//import com.google.slashb410.exgroup.ui.group.create.GroupAddActivity;
//import com.google.slashb410.exgroup.ui.group.room.GroupHomeActivity;
//import com.google.slashb410.exgroup.util.U;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
///**
// * Created by Tacademy on 2017-02-10.
// */
//
//public class GridAdapter extends BaseAdapter {
//
//    Context context;
//    ArrayList<actGroup> actGroup;
//    ArrayList<actGroup> unActGroup;
//    int layout;
//    LayoutInflater inflater;
//
//    public GridAdapter(Context context, int layout, ArrayList<actGroup> actGroup, ArrayList<actGroup> unActGroup) {
//        this.context = context;
//        this.layout = layout;
//        this.actGroup = actGroup;
//        this.unActGroup = unActGroup;
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public int getCount() {
//        return (actGroup.size() + unActGroup.size() + 1);
//    }
//
//    @Override
//    public Object getItem(int position) {
////
////        if (actGroup.size() == position) return null;
////        return actGroup.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null)
//            convertView = inflater.inflate(layout, null);
//
//        TextView textView;
//        TextView textView2;
//        ImageView imageView;
//        CardView cardView = (CardView) convertView.findViewById(R.id.group_add_cardview);
//        CardView groupCardview = (CardView) convertView.findViewById(R.id.group_cardview);
//
//        if (position == E.KEY.GROUP_MAX) {
//            groupCardview.setVisibility(View.GONE);
//            cardView.setVisibility(View.VISIBLE);
//            cardView.setClickable(false);
//            cardView.setBackgroundResource(R.color.babyPink);
//            imageView = (ImageView) convertView.findViewById(R.id.group_add_img);
//            imageView.setVisibility(View.GONE);
//            textView = (TextView) convertView.findViewById(R.id.group_addTxt);
//            textView.setText("그룹은 최대 5개까지 소속될 수 있습니다.");
//            textView.setTextColor(Color.WHITE);
//
//
//        } else if (position == actGroup.size()) {
//            groupCardview.setVisibility(View.GONE);
//            cardView.setVisibility(View.VISIBLE);
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    U.getInstance().goNext(v.getContext(), GroupAddActivity.class, false, false);
//                }
//            });
//        } else {
//            groupCardview.setVisibility(View.VISIBLE);
//            cardView.setVisibility(View.GONE);
//            textView = (TextView) convertView.findViewById(R.id.group_name_card);
//            imageView = (ImageView) convertView.findViewById(R.id.group_cardImg);
//            textView2 = (TextView) convertView.findViewById(R.id.group_term);
//
//            textView.setText(actGroup.get(position).getGroupTitle());
//            Picasso.with(context)
//                    .load(actGroup.get(position).getGroupPicUrl())
//                    .fit()
//                    .centerCrop()
//                    .into(imageView);
//
//            //   FireBaseStorageHelper.getInstance().getImage(convertView.getContext(), actGroup.get(position).getGroupImgPath(), imageView);
//
//            groupCardview.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(v.getContext(), GroupHomeActivity.class);
//                    intent.putExtra("title", actGroup.get(position).getGroupTitle());
//                    intent.putExtra("image", actGroup.get(position).getGroupPicUrl());
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    v.getContext().startActivity(intent);
//                }
//            });
//        }
//
//
//        return convertView;
//
//    }
//}