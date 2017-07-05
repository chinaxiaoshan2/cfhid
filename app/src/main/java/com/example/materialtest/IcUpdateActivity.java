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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.materialtest.dataprocessing.GsonUtil;
import com.example.materialtest.dataprocessing.MyTask;
import com.example.materialtest.model.JointQueryInfo;

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
import okhttp3.Response;

public class IcUpdateActivity extends AppCompatActivity {

    //获取日期格式器对象
    private DateFormat fmtDate = new java.text.SimpleDateFormat("yyyy-MM-dd");
    //获取一个日历对象
    Calendar dateAndTime ;
    private TextView suoshuly_text;
    private TextView xinxilb_text ;
    private TextView fuzebm_text ;
    private EditText shebeizl_text;
    private EditText yujihete_text;
    private EditText jingzhengds_text;
    private ImageView fruitImageView ;
    private EditText fruitContentText;
    private TextView xinxijib_text;
    private Spinner jinzhanjd_text;
    private TextView yujizb_text;
    private EditText genzongqk_text;
    private Spinner  xinxijg_text;
    private TextView  diqu_text;
    private EditText jiaohuoqyq_text;
    private EditText zhaobiaofs_text;
    private TextView shifouxyxt_text;
    private EditText yonghu_text;
    private EditText yonghulxr_text;
    private EditText yonghulxdh_text;
    private EditText yonghuemail_text;
    private EditText zhongjians_text;
    private EditText zhongjianslxr_text;
    private EditText zhongjiansdh_text;
    private EditText zhongjiansemail_text;
    private EditText xiangmugk_text;
    private  String xuhao;
    private TextView fuzery_text;
    private EditText wzbyy_text;

    private String beizhu;
    private String wzbyy;
    private String user_id;
    private ArrayAdapter<String> jinzhanjd_adapter;
    private List<String> jinzhanjd_list=new ArrayList<>();
    private ArrayAdapter<String> xinxijg_adapter;
    private List<String> xinxijg_list=new ArrayList<>();
    private JointQueryInfo jointQueryInfo;
    private static List<JointQueryInfo> icList=new ArrayList<>() ;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icupdate);
        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        jointQueryInfo = (JointQueryInfo) intent.getSerializableExtra("jointQueryInfo");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        fruitImageView = (ImageView) findViewById(R.id.fruit_image_view);
        //项目名称
        fruitContentText = (EditText) findViewById(R.id.addic_xiangmumc_text);
        fruitContentText.setText(jointQueryInfo.getXiangmumc());
        //项目概括
        xiangmugk_text= (EditText) findViewById(R.id.addic_xiangmugk_text);
        xiangmugk_text.setText(jointQueryInfo.getXiangmugk());
        //进展阶段
        jinzhanjd_text = (Spinner) findViewById(R.id.addic_jinzhanjd_text);
        jinzhanjd_list.add(jointQueryInfo.getJinzhanjd());
        jinzhanjd_list.add("信息跟踪");
        jinzhanjd_list.add("投标准备");
        jinzhanjd_list.add("商务谈判");
        jinzhanjd_list.add("等待结果");
        jinzhanjd_list.add("完成招标");
        jinzhanjd_adapter= new ArrayAdapter<>(IcUpdateActivity.this, android.R.layout.simple_spinner_item, jinzhanjd_list);
        jinzhanjd_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jinzhanjd_text.setAdapter(jinzhanjd_adapter);
        jinzhanjd_text.setOnItemSelectedListener(new JinzhanjdSelectedListener());

        //预计招标
        yujizb_text = (TextView) findViewById(R.id.addic_yujizb_text);
        yujizb_text.setText(jointQueryInfo.getYujizb());
        yujizb_text.setClickable(true);
        yujizb_text.setFocusable(true);
        yujizb_text.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dateAndTime = Calendar.getInstance(Locale.CHINA);
                DatePickerDialog dateDlg = new DatePickerDialog(IcUpdateActivity.this,
                        d1,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                dateDlg.show();
            }
        });
        //所属领域
        suoshuly_text = (TextView) findViewById(R.id.addic_suoshuly_text);
        suoshuly_text.setText(jointQueryInfo.getSuoshuly());
        //信息类别
        xinxilb_text = (TextView) findViewById(R.id.addic_xinxilb_text);
        xinxilb_text.setText(jointQueryInfo.getXinxilb());
        //信息级别
        xinxijib_text = (TextView) findViewById(R.id.addic_xinxijib_text);
        xinxijib_text.setText(jointQueryInfo.getXinxijib());
        //负责人
        fuzery_text= (TextView) findViewById(R.id.addic_fuzery_text);
        fuzery_text.setText(jointQueryInfo.getFuzery());
        //设备重量
        shebeizl_text = (EditText) findViewById(R.id.addic_shebeizl_text);
        shebeizl_text.setText(jointQueryInfo.getShebeizl());
        //预计合同额
        yujihete_text = (EditText) findViewById(R.id.addic_yujihete_text);
        yujihete_text.setText(jointQueryInfo.getYujihte());
        //跟踪情况 (不能为空)
        genzongqk_text=(EditText) findViewById(R.id.addic_genzongqk_text);
        //信息结果
        xinxijg_text = (Spinner) findViewById(R.id.addic_xinxijg_text);
        xinxijg_list.add(jointQueryInfo.getXinxijg());
        xinxijg_list.add("正在跟踪");
        xinxijg_list.add("中标");
        xinxijg_list.add("未中标");
        xinxijg_list.add("信息结束");
        xinxijg_adapter= new ArrayAdapter<>(IcUpdateActivity.this, android.R.layout.simple_spinner_item, xinxijg_list);
        xinxijg_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        xinxijg_text.setAdapter(xinxijg_adapter);
        xinxijg_text.setOnItemSelectedListener(new XinxijgSelectedListener());

        //负责部门
        fuzebm_text = (TextView) findViewById(R.id.addic_fuzebm_text);
        fuzebm_text.setText(jointQueryInfo.getFuzebm());
        //竞争对手
        jingzhengds_text=(EditText) findViewById(R.id.addic_jingzhengds_text);
        jingzhengds_text.setText(jointQueryInfo.getJingzhengds());
        //地区
        diqu_text= (TextView) findViewById(R.id.addic_diqu_text);
        diqu_text.setText(jointQueryInfo.getDiqu());

        //交货期要求
        jiaohuoqyq_text=(EditText) findViewById(R.id.addic_jiaohuoqyq_text);
        jiaohuoqyq_text.setText(jointQueryInfo.getJiaohuoqyq());
        //招标方式
        zhaobiaofs_text=(EditText) findViewById(R.id.addic_zhaobiaofs_text);
        zhaobiaofs_text.setText(jointQueryInfo.getZhaobiaofs());
        //是否需要协调
        shifouxyxt_text=(TextView) findViewById(R.id.addic_shifouxyxt_text);
        shifouxyxt_text.setText(jointQueryInfo.getShifouxyxt());
        //未中标原因
        wzbyy_text=(EditText) findViewById(R.id.addic_wzbyy_text);
        wzbyy_text.setText(jointQueryInfo.getWeizhongbyy());
        //用户和中间商
        yonghu_text = (EditText) findViewById(R.id.addic_yonghu_text);
        yonghu_text.setText(jointQueryInfo.getYonghu());
        yonghulxr_text= (EditText) findViewById(R.id.addic_yonghulxr_text);
        yonghulxr_text.setText(jointQueryInfo.getYonghulxr());
        yonghulxdh_text= (EditText) findViewById(R.id.addic_yonghulxdh_text);
        yonghulxdh_text.setText(jointQueryInfo.getYonghulxdh());
        yonghuemail_text= (EditText) findViewById(R.id.addic_yonghuemail_text);
        yonghuemail_text.setText(jointQueryInfo.getYonghuemail());
        zhongjians_text= (EditText) findViewById(R.id.addic_zhongjians_text);
        zhongjians_text.setText(jointQueryInfo.getZhongjians());
        zhongjianslxr_text= (EditText) findViewById(R.id.addic_zhongjianslxr_text);
        zhongjianslxr_text.setText(jointQueryInfo.getZhongjianslxr());
        zhongjiansdh_text= (EditText) findViewById(R.id.addic_zhongjiansdh_text);
        zhongjiansdh_text.setText(jointQueryInfo.getZhongjiansdh());
        zhongjiansemail_text= (EditText) findViewById(R.id.addic_zhongjiansemail_text);
        zhongjiansemail_text.setText(jointQueryInfo.getZhongjiansemail());

        if(jointQueryInfo.getBeizhu()==null){
            jointQueryInfo.setBeizhu("");
        }
        if(jointQueryInfo.getWeizhongbyy()==null){
            jointQueryInfo.setWeizhongbyy("");
        }


        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle("信息上报");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addic);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "确认上报信息吗", Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                new Thread(new Runnable() {
                @Override
                 public void run() {
                    ic_save2();
                    message = saveTo_Ministor2();

                  runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      if(message.length()>1) {
                          Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                      }else{
                          Toast.makeText(getApplicationContext(), "上报失败", Toast.LENGTH_LONG).show();}
                  }
                  });


                    icList = MyTask.findIc("1", user_id);
                    Intent intent = new Intent();
                    intent.putExtra("operType", "1");
                    intent.putExtra("icList", (Serializable) (icList));
                    intent.putExtra("user_id", user_id);
                    intent.setClass(IcUpdateActivity.this, IcActivity.class);
                    startActivity(intent);
                }
                }).start();
                    }
                })
              .show();
            }
        });
    }

    class JinzhanjdSelectedListener implements OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    class XinxijgSelectedListener implements OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

        //上报录入信息
        public void ic_save2(){
            try {
                String path = getResources().getString(R.string.serverIp)+"pm/pm_ic/ic_save2.action";
                URL url = new URL(path);
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("xuhao",jointQueryInfo.getXuhao())
                        .add("yonghu",yonghu_text.getText().toString())
                        .add("xiangmumc",fruitContentText.getText().toString())
                        .add("xiangmugk",xiangmugk_text.getText().toString())
                        .add("genzongqk",genzongqk_text.getText().toString())
                        .add("yujizb",yujizb_text.getText().toString())
                        .add("jingzhengds",jingzhengds_text.getText().toString())
                        .add("xinxijib",xinxijib_text.getText().toString())
                        .add("fuzebm",jointQueryInfo.getFuzebm())
                        .add("jinzhanjd",jinzhanjd_text.getSelectedItem().toString())
                        .add("xinxilb",xinxilb_text.getText().toString())
                        .add("chuangjiansj",jointQueryInfo.getChuangjiansj())
                        .add("xinxijg",xinxijg_text.getSelectedItem().toString())
                        .add("beizhu",jointQueryInfo.getBeizhu())
                        .add("keduan",jointQueryInfo.getKeduan())
                        .add("lurur",jointQueryInfo.getLurur())

                        .add("diqu",diqu_text.getText().toString())
                        .add("shebeizl",shebeizl_text.getText().toString())
                        .add("yujihte", yujihete_text.getText().toString())
                        .add("jiaohuoqyq",jiaohuoqyq_text.getText().toString())
                        .add("zhaobiaofs",zhaobiaofs_text.getText().toString())
                        .add("shifouxyxt", shifouxyxt_text.getText().toString())
                        .add("yonghulxr",yonghulxr_text.getText().toString())
                        .add("yonghulxdh",yonghulxdh_text.getText().toString())
                        .add("yonghuemail",yonghuemail_text.getText().toString())
                        .add("weizhongbyy",wzbyy_text.getText().toString())
                        .add("zhongjians",zhongjians_text.getText().toString())
                        .add("zhongjianslxr",zhongjianslxr_text.getText().toString())
                        .add("zhongjiansdh",zhongjiansdh_text.getText().toString())
                        .add("zhongjiansemail",zhongjiansemail_text.getText().toString())
                        .add("suoshuly",suoshuly_text.getText().toString())
                        .add("login_id",user_id)
                        .add("recorder_status",String.valueOf(jointQueryInfo.getRecorder_status()))
                        .add("modify_status",String.valueOf(jointQueryInfo.getModify_status()))

                        .add("to_ministor",String.valueOf(jointQueryInfo.getTo_ministor()))
                        .add("to_recorder",String.valueOf(jointQueryInfo.getTo_recorder()))
                        .add("ministor_id",String.valueOf(jointQueryInfo.getMinistor_id()))
                        .add("caiji_id",String.valueOf(jointQueryInfo.getCaiji_id()))
                        .add("fuzery_id",String.valueOf(jointQueryInfo.getFuzery_id()))
                        .add("create_id",String.valueOf(jointQueryInfo.getCreate_id()))
                        .add("kdministor_id",String.valueOf(jointQueryInfo.getKdministor_id()))
                        .add("kdministor_status",String.valueOf(jointQueryInfo.getKdministor_status()))
                        .add("ministor_status",String.valueOf(jointQueryInfo.getMinistor_status()))
                        .add("to_kdministor",String.valueOf(jointQueryInfo.getTo_kdministor()))
                        .add("keduan_id",String.valueOf(jointQueryInfo.getKeduan_id()))
                        .add("status","0")
                        .build();
                Request request = new Request.Builder()
                        .url(url).post(requestBody).build();
                Response response =client.newCall(request).execute();
                final String responseData = response.body().string();
                xuhao= GsonUtil.getMessageFromJson(responseData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public String saveTo_Ministor2(){
        String jsonMessage=null;
        try {
            String path = getResources().getString(R.string.serverIp)+"pm/pm_ic/saveTo_Ministor2.action";
            URL url = new URL(path);
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("xuhao",xuhao)
                    .build();
            Request request = new Request.Builder()
                    .url(url).post(requestBody).build();
            Response response = client.newCall(request).execute();
            final String responseData = response.body().string();
            jsonMessage = GsonUtil.getMessageFromJson(responseData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonMessage;
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
            yujizb_text.setText(fmtDate.format(dateAndTime.getTime()));
        }
    };

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
