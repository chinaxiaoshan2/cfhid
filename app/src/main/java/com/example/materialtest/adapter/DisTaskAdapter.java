package com.example.materialtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.materialtest.DisMainPersonActivity;
import com.example.materialtest.R;
import com.example.materialtest.model.InterchangeNotice;
import com.example.materialtest.model.MinstorTaskView;
import com.example.materialtest.model.Test;
import com.example.materialtest.model.Users;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.materialtest.dataprocessing.GsonUtil.getTestFromJson;

public class DisTaskAdapter extends RecyclerView.Adapter<DisTaskAdapter.ViewHolder>{

    private Context mContext;
    private List<MinstorTaskView> mFruitList;
    private MinstorTaskView minstorTaskView;
    private String user_id;
    private String dep_name;
    private String responseData=null;
    private String data=null;
    private List<InterchangeNotice> icnLst=new ArrayList<>();
    private List<Users> userLst=new ArrayList<>();
    private String icDepartment=null;
    private String ic_task_id=null;
    private String dep_type=null;
    private String needFuzery="0"; //0不需要负责人 1需要负责人

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView taskImage;
        TextView distask_createtime_text;
        TextView distask_proname_text;
        TextView distask_type_text;
        TextView distask_desc_text;
        TextView distask_dep_text;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            taskImage = (ImageView) view.findViewById(R.id.task_image);
            distask_createtime_text = (TextView) view.findViewById(R.id.distask_createtime_text);
            distask_proname_text = (TextView) view.findViewById(R.id.distask_proname_text);
            distask_type_text = (TextView) view.findViewById(R.id.distask_type_text);
            distask_desc_text=(TextView) view.findViewById(R.id.distask_desc_text);
            distask_dep_text=(TextView) view.findViewById(R.id.distask_dep_text);
        }
    }

    public DisTaskAdapter(List<MinstorTaskView> mtList, String userId) {
        mFruitList = mtList;
        user_id=userId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.distask_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                minstorTaskView = mFruitList.get(position);
                selTaskExector();
            }
        });
        return holder;
    }

    public void selTaskExector(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String path = mContext.getResources().getString(R.string.serverIp) + "pm/interchange/selTaskExector.action";
                    URL uri = new URL(path);
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("user_id", user_id)
                            .add("ic_task_id", minstorTaskView.getIc_task_id())
                            .add("depType",minstorTaskView.getDep_type())
                            .build();
                    Request request = new Request.Builder()
                            .url(uri).post(requestBody).build();
                    Response response = client.newCall(request).execute();
                     responseData = response.body().string();
                     Message  message=new Message();
                     message.what= 1;
                     Bundle bundle=new Bundle();
                     bundle.putString("responseData", responseData);
                     message.setData(bundle);//bundle传值，耗时，效率低
                     handler.sendMessage(message);//发送message信息
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();

    }

    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case 1:
                    data=msg.getData().getString("responseData");
                    Test test=getTestFromJson(data);
                    dep_name=test.getDep_name();
                    userLst=test.getUserLst();
                    icnLst=test.getIcnLst();
                    icDepartment=test.getIcDepartment();
                    ic_task_id=test.getIc_task_id();
                    dep_type=test.getDepType();

                    if(minstorTaskView.getDep_type().equals("1") && icDepartment.equals(dep_name)){
                        needFuzery="1";
                    }
                        Intent intent = new Intent(mContext, DisMainPersonActivity.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("userLst", (Serializable) userLst);
                        intent.putExtra("icnLst", (Serializable) icnLst);
                        intent.putExtra("ic_task_id", ic_task_id);
                        intent.putExtra("dep_type", dep_type);
                        intent.putExtra("needFuzery",needFuzery);
                        mContext.startActivity(intent);

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MinstorTaskView minstorTaskView  = mFruitList.get(position);

        holder.distask_createtime_text.setText(minstorTaskView.getIc_createDate());
        holder.distask_proname_text.setText(minstorTaskView.getPro_name());
        holder.distask_type_text.setText(minstorTaskView.getDep_type_name());
        holder.distask_desc_text.setText(minstorTaskView.getIc_desc());
        if(minstorTaskView.getDep_type().equals("1")){
            holder.distask_dep_text.setText(minstorTaskView.getIc_header());
        }else {
            holder.distask_dep_text.setText(minstorTaskView.getIc_header_name());
        }
        Glide.with(mContext).load(minstorTaskView.getImageId()).into(holder.taskImage);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

}
