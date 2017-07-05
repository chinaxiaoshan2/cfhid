package com.example.materialtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.materialtest.dataprocessing.MyApplication;
import com.example.materialtest.dataprocessing.MyTask;
import com.example.materialtest.model.JointQueryInfo;
import com.example.materialtest.model.Users;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class KdUpdateIcActivity extends AppCompatActivity {


    private JointQueryInfo jointQueryInfo;
    private String  user_id;
    private Spinner fuzery_id_text;
    private String fuzery_id;
    private List<Users> userLst;
    private ArrayAdapter<String> user_adapter;
    private List<String> user_list=new ArrayList<>();
    private static List<JointQueryInfo> icList=new ArrayList<>() ;
    private String message="no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kdupdateic);
        Intent intent = getIntent();
        jointQueryInfo = (JointQueryInfo) intent.getSerializableExtra("jointQueryInfo");
        user_id= intent.getStringExtra("user_id");
        userLst= (List<Users>) intent.getSerializableExtra("userLst");
        String fruitName =jointQueryInfo.getXiangmumc();
        int fruitImageId=jointQueryInfo.getImageId();

        ImageView fruitImageView = (ImageView) findViewById(R.id.fruit_image_view);
        TextView fruitContentText = (TextView) findViewById(R.id.xiangmumc_text);
        TextView yonghu_text = (TextView) findViewById(R.id.yonghu_text);
        TextView xinxijib_text=(TextView) findViewById(R.id.xinxijib_text);
        TextView jinzhanjd_text=(TextView) findViewById(R.id.jinzhanjd_text);
        TextView yujizb_text=(TextView) findViewById(R.id.yujizb_text);
        TextView suoshuly_text=(TextView) findViewById(R.id.suoshuly_text);
        TextView xinxilb_text=(TextView) findViewById(R.id.xinxilb_text);
        TextView fuzebm_text=(TextView) findViewById(R.id.fuzebm_text);
        TextView fuzery_text=(TextView) findViewById(R.id.fuzery_text);
        TextView shebeizl_text=(TextView) findViewById(R.id.shebeizl_text);
        TextView yujihete_text=(TextView) findViewById(R.id.yujihete_text);
        TextView genzongqk_text=(TextView) findViewById(R.id.genzongqk_text);
        fuzery_id_text=(Spinner) findViewById(R.id.fuzery_id_text);
        //适配器
        for(int i=0;i<userLst.size();i++){
            user_list.add(userLst.get(i).getUser_name());
        }
        user_adapter= new ArrayAdapter<>(KdUpdateIcActivity.this, android.R.layout.simple_spinner_item, user_list);
        //设置样式
        user_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        fuzery_id_text.setAdapter(user_adapter);

        if(jointQueryInfo.getFuzery_id()==null || jointQueryInfo.getFuzery_id().equals("0")){
            user_list.add("请选择负责人");
        }else{
            fuzery_id=jointQueryInfo.getFuzery_id();
            user_list.add(jointQueryInfo.getFuzery());
            message="yes";
        }
        fuzery_id_text.setSelection(userLst.size(),true);//此处要true
        fuzery_id_text.setOnItemSelectedListener(new UserSelectedListener());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        fruitContentText.setText(jointQueryInfo.getXiangmumc());
        yonghu_text.setText(jointQueryInfo.getYonghu());
        xinxijib_text.setText(jointQueryInfo.getXinxijib());
        jinzhanjd_text.setText(jointQueryInfo.getJinzhanjd());
        yujizb_text.setText(jointQueryInfo.getYujizb());
        suoshuly_text.setText(jointQueryInfo.getSuoshuly());
        xinxilb_text.setText(jointQueryInfo.getXinxilb());
        fuzebm_text.setText(jointQueryInfo.getFuzebm());
        fuzery_text.setText(jointQueryInfo.getFuzery());
        shebeizl_text.setText(jointQueryInfo.getShebeizl());
        yujihete_text.setText(jointQueryInfo.getYujihte());
        genzongqk_text.setText(jointQueryInfo.getGenzongqk());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.yes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "确定批准吗", Snackbar.LENGTH_LONG)
                        .setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new Thread(new Runnable() {

                                    @Override
                                    public void run() {
                                        ic_kdapprove();
                                        icList.clear();
                                        icList= MyTask.findIc("9",user_id);
                                        Intent intent= new Intent();
                                        intent.putExtra("operType","9");
                                        intent.putExtra("icList", (Serializable) (icList));
                                        intent.putExtra("user_id",user_id);
                                        intent.setClass(KdUpdateIcActivity.this, IcActivity.class);
                                        startActivity(intent);
                                    }
                                }).start();
                            }
                        })
                        .show();
            }
        });



        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.no);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "确定驳回吗", Snackbar.LENGTH_LONG)
                        .setAction("驳回", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new Thread(new Runnable() {
                                    @Override
                                        public void run() {
                                            ic_no_kdapprove();
                                            icList.clear();
                                            icList= MyTask.findIc("9",user_id);
                                        Intent intent= new Intent();
                                        intent.putExtra("operType","9");
                                        intent.putExtra("icList", (Serializable) (icList));
                                        intent.putExtra("user_id",user_id);
                                        intent.setClass(KdUpdateIcActivity.this, IcActivity.class);
                                        startActivity(intent);
                                        }
                                    }).start();
                            }
                        })
                        .show();
            }
        });
    }





    class UserSelectedListener implements OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            if(arg2==userLst.size()){
                message="no";
                Toast.makeText(MyApplication.getContext(),"请重新选择",Toast.LENGTH_LONG).show();
            }else {
                message="yes";
                fuzery_id = userLst.get(arg2).getUser_id();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }


    public void ic_kdapprove() {
        try {
            String path = getResources().getString(R.string.serverIp) + "pm/pm_ic/saveTo_kd.action";
            URL url = new URL(path);
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("xuhao", jointQueryInfo.getXuhao())
                    .add("login_id",user_id)
                    .add("fuzery_id",fuzery_id)
                    .build();
            Request request = new Request.Builder()
                    .url(url).post(requestBody).build();
            client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ic_no_kdapprove() {
        try {
            String path = getResources().getString(R.string.serverIp) + "pm/pm_ic/ic_no_kdapprove.action";
            URL url = new URL(path);
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("xuhao", jointQueryInfo.getXuhao())
                    .add("login_id",user_id)
                    .build();
            Request request = new Request.Builder()
                    .url(url).post(requestBody).build();
            client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
