package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.ResStandard;
import com.google.slashb410.exgroup.model.group.group.BoardData;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.util.U;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tacademy on 2017-02-03.
 */

public class ShotsAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<BoardData> datas;


    public ShotsAdapter(Context context, ArrayList<BoardData> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ShotsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_shot_card, parent, false);
        return new ShotsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((ShotsHolder)holder).bindOnCard(context, datas.get(position));

        ((ShotsHolder) holder).delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("정말로 삭제하시겠습니까?")
                        .setMessage("삭제된 내용과 댓글을 복구할 수 없습니다.\n추가된 활동지수 역시 취소됩니다.")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //해당 내용 삭제
                                int boardId = datas.get(position).getBoard_id();

                                Call<ResStandard> resDeleteBoard = NetSSL.getInstance().getGroupImpFactory().deleteBoard(boardId);
                                resDeleteBoard.enqueue(new Callback<ResStandard>() {
                                    @Override
                                    public void onResponse(Call<ResStandard> call, Response<ResStandard> response) {
                                        if(response.isSuccessful()){
                                            if(response.body()!=null){
                                                U.getInstance().popSimpleDialog(null, null, "삭제 성공!", null);
                                                deleteShot(position);
                                            }else{
                                                U.getInstance().myLog("resDeleteBoard Body is null : "+response.message());
                                            }
                                        }else{
                                            U.getInstance().myLog("resDeleteBoard is NOT successful : "+response.message());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResStandard> call, Throwable t) {

                                    }
                                });

                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return this.datas.size();
    }


    public void deleteShot(int idx){
        datas.remove(idx);
        datas.notifyAll();
    }
}

