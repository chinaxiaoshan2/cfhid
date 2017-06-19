package com.example.materialtest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.materialtest.dataprocessing.MyApplication;
import com.example.materialtest.dataprocessing.MyTask;
import com.example.materialtest.model.InterchangeNotice;
import com.example.materialtest.model.MinstorTaskView;
import com.example.materialtest.model.Users;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class DisMainPersonActivity extends AppCompatActivity {

    private List<InterchangeNotice> icnLst;
    private static List<MinstorTaskView> mtLst=new ArrayList<>() ;
    private List<Users> userLst;

    private String user_id;

    private Button button;
    private Button  mpButton;
    private RadioOnClick OnClick = new RadioOnClick(1);

    private ListView areaListView;

    private String[] areas;
    private String dpName="";
    private TextView main_person;
    private TextView dp_person;
    private String[] areas2;
    private boolean[] area2State;
    private ListView areaCheckListView;
    private String[] user;
    private String headerUName="";
    private String ic_task_id=null;
    private String dep_type=null;
    private int k=0;
    private String  needFuzery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dismainperson);
        Toolbar toolbar = (Toolbar) findViewById(R.id.dismainperson_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        button = (Button) findViewById(R.id.Button);
        button.setOnClickListener(new RadioClickListener());
        mpButton = (Button) findViewById(R.id.mpButton);
        mpButton.setOnClickListener(new CheckBoxClickListener());

        main_person = (TextView) findViewById(R.id.main_person);
        dp_person = (TextView) findViewById(R.id.dp_person);


        dp_person.setText(dpName);
        Intent intent = getIntent();
        userLst = (List<Users>) intent.getSerializableExtra("userLst");
        user_id = intent.getStringExtra("user_id");
        icnLst = (List<InterchangeNotice>) intent.getSerializableExtra("icnLst");
        ic_task_id=intent.getStringExtra("ic_task_id");
        dep_type=intent.getStringExtra("dep_type");
        needFuzery=intent.getStringExtra("needFuzery");

        for (int i = 0; i < icnLst.size(); i++) {
            dpName = dpName + "   " + icnLst.get(i).getUser_name();
        }
        dp_person.setText(dpName);
        areas = new String[userLst.size()];
        for (int i = 0; i < userLst.size(); i++) {
            areas[i] = userLst.get(i).getUser_name();
        }
        areas2 = new String[userLst.size()];
        area2State = new boolean[userLst.size()];
        for (int i = 0; i < userLst.size(); i++) {
            area2State[i] = false;
        }
        for (int i = 0; i < userLst.size(); i++) {
            areas2[i] = userLst.get(i).getUser_name();
            for (int j = 0; j < icnLst.size(); j++) {
                if (userLst.get(i).getUser_id().equals(icnLst.get(j).getUser_id())) {
                    area2State[i] = true;
                }
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.dismainperson_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( main_person.getText().equals("") &&  dp_person.getText().equals("")){
                    Toast.makeText(MyApplication.getContext(),"您必须选择人员",Toast.LENGTH_LONG).show();
                }else if(needFuzery.equals("1") && main_person.getText().equals(""))
                {
                   Toast.makeText(MyApplication.getContext(),"您必须选定负责人",Toast.LENGTH_LONG).show();
                }else {
                    Snackbar.make(view, "人员确定吗", Snackbar.LENGTH_SHORT)
                            .setAction("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            saveICUNotice();
                                            endTaskExector();
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(getApplicationContext(), "派发成功", Toast.LENGTH_LONG).show();
                                                }
                                            });
                                            mtLst = MyTask.listDisTask(getResources().getString(R.string.serverIp), user_id);
                                            Intent intent = new Intent();
                                            intent.putExtra("mtLst", (Serializable) (mtLst));
                                            intent.putExtra("user_id", user_id);
                                            intent.setClass(DisMainPersonActivity.this, DistributeTaskActivity.class);
                                            startActivity(intent);
                                        }
                                    }).start();
                                }
                            })
                            .show();
                }
            }
        });
 }
    public void endTaskExector(){  //结束派发任务
        try {
            String path = getResources().getString(R.string.serverIp)+"pm/interchange/endTaskExector.action";
            URL url = new URL(path);
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("user_id",user_id)
                    .add("ic_task_id",ic_task_id)
                    .build();
            Request request = new Request.Builder()
                    .url(url).post(requestBody).build();
            client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveICUNotice(){
        try {
            String path = getResources().getString(R.string.serverIp)+"pm/interchange/saveICUNotice.action";
            URL url = new URL(path);
            OkHttpClient client = new OkHttpClient();

            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < user.length; i++){
                if(i==0){
                    sb. append(user[i]);
                }else{
                sb. append(","+user[i]);}
            }
            String userString = sb.toString();

            RequestBody requestBody = new FormBody.Builder()
                    .add("user_id",user_id)
                    .add("depType",dep_type)
                    .add("headerUName",headerUName)
                    .add("ic_task_id",ic_task_id)
                    .add("userString",userString)
                    .build();
            Request request = new Request.Builder()
                    .url(url).post(requestBody).build();
            client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class CheckBoxClickListener implements OnClickListener{
        @Override
        public void onClick(View v) {
            AlertDialog ad = new AlertDialog.Builder(DisMainPersonActivity.this)
                    .setTitle("选择人员")
                    .setMultiChoiceItems(areas2,area2State,new DialogInterface.OnMultiChoiceClickListener(){
                        public void onClick(DialogInterface dialog,int whichButton, boolean isChecked){
                            //点击某个区域
                        }
                    }).setPositiveButton("确定",new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog,int whichButton){
                             k=0;
                            dp_person.setText("");
                            dpName="";
                            if (areaCheckListView.getCheckedItemPositions().size() > 0){
                                user=new String[areaCheckListView.getCheckedItemPositions().size()];
                            }

                            for (int i = 0; i < areas2.length; i++){
                                if (areaCheckListView.getCheckedItemPositions().get(i)){
                                    dpName=dpName+"   "+userLst.get(i).getUser_name();
                                    user[k]=userLst.get(i).getUser_id();
                                    k=k+1;
                                }else{
                                    areaCheckListView.getCheckedItemPositions().get(i,false);
                                }
                            }
                            if (areaCheckListView.getCheckedItemPositions().size() > 0){
                                dp_person.setText(dpName);

                            }else{
                                dp_person.setText("");
                                Toast.makeText(DisMainPersonActivity.this, "您没有选择人员", Toast.LENGTH_LONG).show();
                            }

                            dialog.dismiss();
                        }
                    }).setNegativeButton("取消", null).create();
            areaCheckListView = ad.getListView();
            ad.show();
        }
    }

/*    class AlertClickListener implements OnClickListener{
        @Override
        public void onClick(View v) {
            new AlertDialog.Builder(DisMainPersonActivity.this).setTitle("选择区域").setItems(areas2,new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    Toast.makeText(DisMainPersonActivity.this, "您已经选择了: " + which + ":" + areas2[which],Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }).show();
        }
    }*/


    class RadioClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            if(needFuzery.equals("1")) {
                AlertDialog ad = new AlertDialog.Builder(DisMainPersonActivity.this).setTitle("选择区域")
                        .setSingleChoiceItems(areas, OnClick.getIndex(), OnClick).create();
                areaListView = ad.getListView();
                ad.show();
            }else{
                Toast.makeText(MyApplication.getContext(),"您不需要指定负责人",Toast.LENGTH_LONG).show();
            }
        }
    }

    class RadioOnClick implements DialogInterface.OnClickListener{
        private int index;

        public RadioOnClick(int index){
            this.index = index;
        }
        public void setIndex(int index){
            this.index=index;
        }
        public int getIndex(){
            return index;
        }

        public void onClick(DialogInterface dialog, int whichButton){
            setIndex(whichButton);
            Toast.makeText(DisMainPersonActivity.this, "您已经选择了 " +  ":" + areas[index]+"  为负责人", Toast.LENGTH_LONG).show();
            headerUName=userLst.get(index).getUser_id();
            main_person.setText(areas[index]);
            dialog.dismiss();
        }
    }











    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
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
