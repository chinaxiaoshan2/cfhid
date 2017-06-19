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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.materialtest.HeaderTaskActivity;
import com.example.materialtest.MyTaskDetailActivity;
import com.example.materialtest.PhotoUploadActivity;
import com.example.materialtest.R;
import com.example.materialtest.dataprocessing.GsonUtil;
import com.example.materialtest.dataprocessing.MyApplication;
import com.example.materialtest.model.InterchangeNotice;

import java.net.URL;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyTaskAdapter extends RecyclerView.Adapter<MyTaskAdapter.ViewHolder>{

    private Context mContext;
    private List<InterchangeNotice> mFruitList;
    private InterchangeNotice interchangeNotice;
    private String user_id;
    private String dlUsers;
    private String project_name;
    private String project_number;
    private String responseData=null;
    private String data=null;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView taskImage;
        TextView taskName;
        TextView taskDetail;
        TextView taskDate;
        TextView proName;
        TextView ministorName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            taskImage = (ImageView) view.findViewById(R.id.task_image);
            taskName = (TextView) view.findViewById(R.id.task_name);
            taskDetail = (TextView) view.findViewById(R.id.task_detail);
            taskDate = (TextView) view.findViewById(R.id.task_date);
            proName=(TextView) view.findViewById(R.id.pro_name);
            ministorName=(TextView) view.findViewById(R.id.task_ministorname);
        }
    }

    public MyTaskAdapter(List<InterchangeNotice> icnList,String userId) {
        mFruitList = icnList;
        user_id=userId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.mytask_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                interchangeNotice = mFruitList.get(position);
               if(interchangeNotice.getDep_type().equals("1")){  //经营交流
                if(interchangeNotice.getHeader_user().equals(user_id)){ //经营交流负责人
                    getProByNCID();
                }else {  //经营交流非负责人
                    Intent intent = new Intent(mContext, MyTaskDetailActivity.class);
                    intent.putExtra("user_id", user_id);
                    intent.putExtra("interchangeNotice", interchangeNotice);
                    mContext.startActivity(intent);
                }
               }

               else if(interchangeNotice.getDep_type().equals("2")){     //技术报价
                   Intent intent = new Intent(mContext, PhotoUploadActivity.class);
                   intent.putExtra("userId", user_id);
                   intent.putExtra("uid", interchangeNotice.getIc_id());
                   intent.putExtra("icu_id", interchangeNotice.getId());
                   mContext.startActivity(intent);
               }

               else if(interchangeNotice.getDep_type().equals("3")) {    //投标
                   Toast.makeText(MyApplication.getContext(),"正在建设中",Toast.LENGTH_LONG).show();
                }
            }
        });
        return holder;
    }

    public void getProByNCID(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String path = mContext.getResources().getString(R.string.serverIp) + "pm/interchange/getProByNCID.action";
                    URL uri = new URL(path);
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("noticeId", interchangeNotice.getId())
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
                    ConcurrentHashMap<String,Object> map = GsonUtil.getMapFromJson(data);
                    dlUsers=(String)map.get("dlUsers");
                    project_name=(String)map.get("project_name");
                    project_number=(String)map.get("project_number");

                    Intent intent = new Intent(mContext, HeaderTaskActivity.class);
                    intent.putExtra("dlUsers",dlUsers);
                    intent.putExtra("project_name",project_name);
                    intent.putExtra("project_number",project_number);
                    intent.putExtra("user_id", user_id);
                    intent.putExtra("interchangeNotice", interchangeNotice);
                    mContext.startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InterchangeNotice interchangeNotice  = mFruitList.get(position);
        String proName=null;
        if(interchangeNotice.getDep_type().equals("1")){
            proName="经营交流";
        }else if(interchangeNotice.getDep_type().equals("2")){
            proName="技术报价";
        }else if(interchangeNotice.getDep_type().equals("3")){
            proName="投标";
        }
        holder.taskName.setText(proName);
        holder.proName.setText(interchangeNotice.getPro_name());
        holder.taskDetail.setText(interchangeNotice.getIc_desc());
        holder.ministorName.setText(interchangeNotice.getMinstor_name());
        holder.taskDate.setText(interchangeNotice.getIcu_create_date());
        Glide.with(mContext).load(interchangeNotice.getImageId()).into(holder.taskImage);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

}
