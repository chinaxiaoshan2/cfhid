package com.example.materialtest;

import android.app.DatePickerDialog;
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
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.materialtest.dataprocessing.MyTask;
import com.example.materialtest.model.InterchangeNotice;

import java.io.Serializable;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MyTaskDetailActivity extends AppCompatActivity {

    private static List<InterchangeNotice> icnLst=new ArrayList<>() ;
    public InterchangeNotice interchangeNotice;
    //获取日期格式器对象
    DateFormat fmtDate = new java.text.SimpleDateFormat("yyyy-MM-dd");
    //获取一个日历对象
    Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
    TextView start_time_text=null;
    TextView end_time_text=null;
    String user_id=null;
    EditText exchange_text=null;
    String jsonMessage;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytaskdetail);
        Intent intent = getIntent();
        interchangeNotice = (InterchangeNotice) intent.getSerializableExtra("interchangeNotice");
        user_id= intent.getStringExtra("user_id");
        String fruitName =interchangeNotice.getPro_name();
        int fruitImageId=interchangeNotice.getImageId();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        ImageView fruitImageView = (ImageView) findViewById(R.id.myTask_image_view);
        //TextView fruitContentText = (TextView) findViewById(R.id.xiangmumc_text);
        TextView noticeId_text = (TextView) findViewById(R.id.noticeId_text);
        exchange_text=(EditText) findViewById(R.id.exchange_text);
        start_time_text=(TextView) findViewById(R.id.start_time_text);
        start_time_text.setClickable(true);
        start_time_text.setFocusable(true);
        start_time_text.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog  dateDlg = new DatePickerDialog(MyTaskDetailActivity.this,
                        d1,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                dateDlg.show();
            }
        });
        end_time_text=(TextView) findViewById(R.id.end_time_text);
        end_time_text.setClickable(true);
        end_time_text.setFocusable(true);
        end_time_text.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog  dateDlg = new DatePickerDialog(MyTaskDetailActivity.this,
                        d2,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                dateDlg.show();
            }
        });

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        //fruitContentText.setText(interchangeNotice.getPro_name());
        noticeId_text.setText(interchangeNotice.getId());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.mytaskdetail_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "确定上报吗", Snackbar.LENGTH_SHORT)
                        .setAction("上报", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        save_exchange();
                                        type="10"; //非负责人
                                        jsonMessage= MyTask.taskToMinstor(user_id,interchangeNotice.getId(),type);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                if(jsonMessage.length()>4){
                                                    Toast.makeText(getApplicationContext(),jsonMessage,Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                        icnLst=MyTask.listMyTask(user_id);
                                        Intent intent= new Intent();
                                        intent.putExtra("icnLst", (Serializable) (icnLst));
                                        intent.putExtra("user_id",user_id);
                                        intent.setClass(MyTaskDetailActivity.this, MyTaskActivity.class);
                                        startActivity(intent);
                                    }
                                }).start();
                            }
                        })
                        .show();
            }
        });

    }

    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener d1= new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //将页面TextView的显示更新为最新时间
            start_time_text.setText(fmtDate.format(dateAndTime.getTime()));
        }
    };


    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener d2= new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //将页面TextView的显示更新为最新时间
            end_time_text.setText(fmtDate.format(dateAndTime.getTime()));
        }
    };



public void save_exchange(){  //保存交流日期
    try {
        String path = getResources().getString(R.string.serverIp)+"pm/interchange/saveExchangeDate.action";
        URL url = new URL(path);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("user_id",user_id)
                .add("noticeId",interchangeNotice.getId())
                .add("begin_date",start_time_text.getText().toString().trim())
                .add("end_date",end_time_text.getText().toString().trim())
                .add("exchange",exchange_text.getText().toString().trim())
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
