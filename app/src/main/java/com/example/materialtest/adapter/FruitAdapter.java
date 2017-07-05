package com.example.materialtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.materialtest.IcDetailActivity;
import com.example.materialtest.IcUpdateActivity;
import com.example.materialtest.KdUpdateIcActivity;
import com.example.materialtest.R;
import com.example.materialtest.dataprocessing.MyTask;
import com.example.materialtest.model.JointQueryInfo;
import com.example.materialtest.model.Users;

import java.io.Serializable;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{

    private static final String TAG = "FruitAdapter";

    private Context mContext;

    private List<JointQueryInfo> mFruitList;

    private String mOperType;

    String mUser_id;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;


        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }

    public FruitAdapter(List<JointQueryInfo> icList,String operType,String user_id) {
        mFruitList = icList;
        mOperType=operType;
        mUser_id=user_id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                final JointQueryInfo jointQueryInfo = mFruitList.get(position);
                new Thread(new Runnable() {
                    @Override
                public void run() {
                Intent intent=new Intent();
                if(mOperType.equals("3")) {  //领导审阅
                intent = new Intent(mContext, IcDetailActivity.class);
               }else if(mOperType.equals("9")){ //部长选择负责人或者更改
                    intent = new Intent(mContext, KdUpdateIcActivity.class);
                    List<Users> userLst= MyTask.findUserLst(jointQueryInfo.getKeduan_id());
                    intent.putExtra("userLst", (Serializable)userLst);
                }else if(mOperType.equals("1")){
                    int aa=jointQueryInfo.getTo_kdministor();
                    int bb=jointQueryInfo.getRecorder_status();
                    int cc=jointQueryInfo.getMinistor_status();
                    int dd=jointQueryInfo.getKdministor_status();
                    String fuzery_id=jointQueryInfo.getFuzery_id();

                       if( !fuzery_id.equals("0")  && (aa==0 || ( (bb==1 || bb==2) && cc==1 && dd==1 ))){ //未上报过
                           intent = new Intent(mContext, IcUpdateActivity.class);
                       }else{  //不可以上报
                           intent = new Intent(mContext, IcDetailActivity.class);
                       }

                        }
                intent.putExtra("user_id",mUser_id);
                intent.putExtra("jointQueryInfo", jointQueryInfo);
                mContext.startActivity(intent);
                }
            }).start();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JointQueryInfo jointQueryInfo = mFruitList.get(position);
        holder.fruitName.setText(jointQueryInfo.getXiangmumc());
        Glide.with(mContext).load(jointQueryInfo.getImageId()).into(holder.fruitImage);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

}
