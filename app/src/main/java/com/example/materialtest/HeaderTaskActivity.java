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

public class HeaderTaskActivity extends AppCompatActivity {

    private static List<InterchangeNotice> icnLst=new ArrayList<>() ;
    public InterchangeNotice interchangeNotice;
    //获取日期格式器对象
    DateFormat fmtDate = new java.text.SimpleDateFormat("yyyy-MM-dd");
    //获取一个日历对象
    Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
    TextView headertask_proname_text=null;
    TextView headertask_pronum_text=null;
    TextView headertask_start_time_text=null;
    TextView headertask_end_time_text=null;
    EditText headertask_place_text=null;

    EditText headertask_client_text=null;
    TextView headertask_employee_text=null;
    EditText headertask_companyemployee_text=null;
    EditText headertask_clientmoney_text=null;
    EditText headertask_cooperation_text=null;
    EditText headertask_interchange_text=null;
    EditText headertask_suggest_text=null;
    EditText headertask_competitor_text=null;
    EditText headertask_summary_text=null;

    String user_id=null;
    String message;
    String type;
    private String dlUsers;
    private String project_name;
    private String project_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headertaskdetail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        ImageView fruitImageView = (ImageView) findViewById(R.id.myTask_image_view);

        headertask_proname_text=(TextView) findViewById(R.id.headertask_proname_text);
        headertask_pronum_text=(TextView) findViewById(R.id.headertask_pronum_text);
        headertask_start_time_text=(TextView) findViewById(R.id.headertask_start_time_text);
        headertask_end_time_text=(TextView) findViewById(R.id.headertask_end_time_text);
        headertask_place_text=(EditText) findViewById(R.id.headertask_place_text);
        headertask_client_text=(EditText) findViewById(R.id.headertask_client_text);
        headertask_employee_text=(TextView) findViewById(R.id.headertask_employee_text);
        headertask_companyemployee_text=(EditText) findViewById(R.id.headertask_companyemployee_text);
        headertask_clientmoney_text=(EditText) findViewById(R.id.headertask_clientmoney_text);
        headertask_cooperation_text=(EditText) findViewById(R.id.headertask_cooperation_text);
        headertask_interchange_text=(EditText) findViewById(R.id.headertask_interchange_text);
        headertask_suggest_text=(EditText) findViewById(R.id.headertask_suggest_text);
        headertask_competitor_text=(EditText) findViewById(R.id.headertask_competitor_text);
        headertask_summary_text=(EditText) findViewById(R.id.headertask_summary_text);

        headertask_start_time_text.setClickable(true);
        headertask_start_time_text.setFocusable(true);
        headertask_start_time_text.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog  dateDlg = new DatePickerDialog(HeaderTaskActivity.this,
                        d1,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                dateDlg.show();
            }
        });

        headertask_end_time_text.setClickable(true);
        headertask_end_time_text.setFocusable(true);
        headertask_end_time_text.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog  dateDlg = new DatePickerDialog(HeaderTaskActivity.this,
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
        Intent intent = getIntent();
        interchangeNotice = (InterchangeNotice) intent.getSerializableExtra("interchangeNotice");
        user_id= intent.getStringExtra("user_id");
        dlUsers= intent.getStringExtra("dlUsers");
        project_name= intent.getStringExtra("project_name");
        project_number= intent.getStringExtra("project_number");

        String fruitName =interchangeNotice.getPro_name();
        int fruitImageId =interchangeNotice.getImageId();

        headertask_proname_text.setText(project_name);
        headertask_pronum_text.setText(project_number);
        headertask_employee_text.setText(dlUsers);

        collapsingToolbar.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(fruitImageView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.headerexchange_fab);
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
                                        addex();
                                        type="1"; //负责人
                                        message=MyTask.taskToMinstor(user_id,interchangeNotice.getId(),type);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                if(message.length()>4){
                                                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                        icnLst=MyTask.listMyTask(user_id);
                                        Intent intent= new Intent();
                                        intent.putExtra("icnLst", (Serializable) (icnLst));
                                        intent.putExtra("user_id",user_id);
                                        intent.setClass(HeaderTaskActivity.this, MyTaskActivity.class);
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
            headertask_start_time_text.setText(fmtDate.format(dateAndTime.getTime()));
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
            headertask_end_time_text.setText(fmtDate.format(dateAndTime.getTime()));

        }
    };

    /*****上传附件****/
    public void upload_file(){
        try {
            String path = getResources().getString(R.string.serverIp)+"pm/UploadBinaryServlet";
            URL url = new URL(path);
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("user_id",user_id)
                    .add("noticeId",interchangeNotice.getId())
                    .add("uid",user_id)
                    .add("ic_id",interchangeNotice.getIc_id())
                    .add("fid","123")
                    .build();
            Request request = new Request.Builder()
                    .url(url).post(requestBody).build();
            client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


public void addex(){  //保存交流日期
    try {
        String path = getResources().getString(R.string.serverIp)+"pm/interchange/addex.action";
        URL url = new URL(path);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("user_id",user_id)
                .add("re_id",interchangeNotice.getId())
                .add("pro_name1",headertask_proname_text.getText().toString().trim())
                .add("pro_background",headertask_clientmoney_text.getText().toString().trim())
                .add("customer_credit",headertask_clientmoney_text.getText().toString().trim())
                .add("pro_cooperation",headertask_cooperation_text.getText().toString().trim())
                .add("begin_date",headertask_start_time_text.getText().toString().trim())
                .add("end_date",headertask_end_time_text.getText().toString().trim())
                .add("participant",headertask_client_text.getText().toString().trim())
                .add("place",headertask_place_text.getText().toString().trim())
                .add("advice",headertask_suggest_text.getText().toString().trim())
                .add("difficulty","")
                .add("contract_sign",headertask_summary_text.getText().toString().trim())
                .add("summary",headertask_interchange_text.getText().toString().trim())
                .add("remark","")
                .add("exchange","")
                .add("attachment","")
                .add("yzHerader",headertask_companyemployee_text.getText().toString().trim())
                .add("competitor",headertask_competitor_text.getText().toString().trim())
                .build();
        Request request = new Request.Builder()
                .url(url).post(requestBody).build();
        client.newCall(request).execute();
    } catch (Exception e) {
        e.printStackTrace();
    }

}
  //主菜单 返回键
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
