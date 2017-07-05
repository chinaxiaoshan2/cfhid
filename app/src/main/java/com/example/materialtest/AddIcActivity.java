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
import com.example.materialtest.model.Pm_department;

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

public class AddIcActivity extends AppCompatActivity {

    //获取日期格式器对象
    private DateFormat fmtDate = new java.text.SimpleDateFormat("yyyy-MM-dd");
    //获取一个日历对象
    Calendar dateAndTime ;
    private Spinner suoshuly_text;
    private Spinner xinxilb_text ;
    private Spinner fuzebm_text ;
    private EditText fuzery_text ;
    private EditText shebeizl_text;
    private EditText yujihete_text;
    private EditText jingzhengds_text;
    private ImageView fruitImageView ;
    private EditText fruitContentText;
    private Spinner xinxijib_text;
    private Spinner jinzhanjd_text;
    private TextView yujizb_text;
    private EditText genzongqk_text;
    private Spinner  xinxijg_text;
    private Spinner  diqu_text;
    private EditText jiaohuoqyq_text;
    private EditText zhaobiaofs_text;
    private Spinner shifouxyxt_text;
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


    private String user_id;
    private List<String> data_list=new ArrayList<>();
    private ArrayAdapter<String> arr_adapter;
    private List<String> dep_list=new ArrayList<>();
    private int dep_id;
    private ArrayAdapter<String> dep_adapter;
    private static List<Pm_department> depLst=new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addic);
        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        depLst= (List<Pm_department>) intent.getSerializableExtra("depLst");

        //int fruitImageId=R.id;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        fruitImageView = (ImageView) findViewById(R.id.fruit_image_view);
        fruitContentText = (EditText) findViewById(R.id.addic_xiangmumc_text);
        xinxijib_text = (Spinner) findViewById(R.id.addic_xinxijib_text);
        jinzhanjd_text = (Spinner) findViewById(R.id.addic_jinzhanjd_text);
        diqu_text= (Spinner) findViewById(R.id.addic_diqu_text);
        yujizb_text = (TextView) findViewById(R.id.addic_yujizb_text);
        yujizb_text.setClickable(true);
        yujizb_text.setFocusable(true);
        yujizb_text.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dateAndTime = Calendar.getInstance(Locale.CHINA);
                DatePickerDialog dateDlg = new DatePickerDialog(AddIcActivity.this,
                        d1,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                dateDlg.show();
            }
        });
         suoshuly_text = (Spinner) findViewById(R.id.addic_suoshuly_text);
         suoshuly_text.setOnItemSelectedListener(new SpinnerSelectedListener());
         xinxilb_text = (Spinner) findViewById(R.id.addic_xinxilb_text);
         fuzebm_text = (Spinner) findViewById(R.id.addic_fuzebm_text);
        //适配器
        for(int i=0;i<depLst.size();i++){
            dep_list.add(depLst.get(i).getDep_name());
        }
        dep_adapter= new ArrayAdapter<>(AddIcActivity.this, android.R.layout.simple_spinner_item, dep_list);
        //设置样式
        dep_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        fuzebm_text.setAdapter(dep_adapter);
        fuzebm_text.setOnItemSelectedListener(new DepSelectedListener());
        jingzhengds_text=(EditText) findViewById(R.id.addic_jingzhengds_text);
        shebeizl_text = (EditText) findViewById(R.id.addic_shebeizl_text);
        yujihete_text = (EditText) findViewById(R.id.addic_yujihete_text);
        genzongqk_text=(EditText) findViewById(R.id.addic_genzongqk_text);
        xinxijg_text = (Spinner) findViewById(R.id.addic_xinxijg_text);
        jiaohuoqyq_text=(EditText) findViewById(R.id.addic_jiaohuoqyq_text);
        zhaobiaofs_text=(EditText) findViewById(R.id.addic_zhaobiaofs_text);
        shifouxyxt_text=(Spinner) findViewById(R.id.addic_shifouxyxt_text);
        yonghu_text = (EditText) findViewById(R.id.addic_yonghu_text);

       yonghulxr_text= (EditText) findViewById(R.id.addic_yonghulxr_text);
       yonghulxdh_text= (EditText) findViewById(R.id.addic_yonghulxdh_text);
       yonghuemail_text= (EditText) findViewById(R.id.addic_yonghuemail_text);
       zhongjians_text= (EditText) findViewById(R.id.addic_zhongjians_text);
       zhongjianslxr_text= (EditText) findViewById(R.id.addic_zhongjianslxr_text);
       zhongjiansdh_text= (EditText) findViewById(R.id.addic_zhongjiansdh_text);
       zhongjiansemail_text= (EditText) findViewById(R.id.addic_zhongjiansemail_text);
       xiangmugk_text= (EditText) findViewById(R.id.addic_xiangmugk_text);


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
                  addIc();
                  saveTo_kdministor();
                  runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                  Toast.makeText(getApplicationContext(), "录入成功！", Toast.LENGTH_LONG).show();
                   }
                  });
                  Intent intent = new Intent();
                  intent.putExtra("user_id", user_id);
                  intent.setClass(AddIcActivity.this, MainActivity.class);
                  startActivity(intent);
                   }
                                }).start();
                    }
                })
              .show();
            }
        });
    }

    class DepSelectedListener implements OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            dep_id=depLst.get(arg2).getDep_id();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    class SpinnerSelectedListener implements OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            data_list.clear();
            //利用数组中的对应位置取得values中需要的值
            //   int Id = values[arg2];
            int id=arg2;
            if(id==0){
                data_list.add("石化、煤化工产品总包");
                data_list.add("石化、煤化工容器产品");
                data_list.add("石化成套产品专用工具");
                data_list.add("核电成套产品");
                data_list.add("核电成套产品专用工具");
                data_list.add("核电单件产品");
            }else if(id==1){
                data_list.add("轧制产品");
                data_list.add("连铸产品");
                data_list.add("冶炼产品");
                data_list.add("轧制产品（工程）总包");
                data_list.add("连铸产品（工程）总包");
                data_list.add("锻压产品");
                data_list.add("矿山产品");
                data_list.add("冶金备件");
                data_list.add("车床产品");
                data_list.add("支撑辊产品");
                data_list.add("工作辊产品");
            }else if(id==2){
                data_list.add("海水淡化产品");
                data_list.add("海工产品");
                data_list.add("环保产品");
                data_list.add("风能产品");
            }else if(id==3){
                data_list.add("电站铸锻件");
                data_list.add("基础铸锻件");
            }else if(id==4){
                data_list.add("国防装备专项产品");
            }else if(id==5){
                data_list.add("其它产品");
            }
            //适配器
            arr_adapter= new ArrayAdapter<>(AddIcActivity.this, android.R.layout.simple_spinner_item, data_list);
            //设置样式
            arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //加载适配器
            xinxilb_text.setAdapter(arr_adapter);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            data_list.clear();
        }
    }


        //上报录入信息
        public void addIc(){
            try {
                String path = getResources().getString(R.string.serverIp)+"pm/pm_ic/addIc.action";
                URL url = new URL(path);
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("user_id",user_id)
                        .add("yonghu",yonghu_text.getText().toString())
                        .add("xiangmumc",fruitContentText.getText().toString())
                        .add("xiangmugk",xiangmugk_text.getText().toString())
                        .add("genzongqk",genzongqk_text.getText().toString())
                        .add("yujizb",yujizb_text.getText().toString())
                        .add("jingzhengds",jingzhengds_text.getText().toString())
                        .add("xinxijib",xinxijib_text.getSelectedItem().toString())
                        .add("fuzery","")
                        .add("jinzhanjd",jinzhanjd_text.getSelectedItem().toString())
                        .add("xinxilb",xinxilb_text.getSelectedItem().toString())
                        .add("xinxijg",xinxijg_text.getSelectedItem().toString())
                        .add("beizhu","")
                        .add("keduan_id",String.valueOf(dep_id))
                        .add("diqu",diqu_text.getSelectedItem().toString())
                        .add("shebeizl",shebeizl_text.getText().toString())
                        .add("yujihte", yujihete_text.getText().toString())
                        .add("jiaohuoqyq",jiaohuoqyq_text.getText().toString())
                        .add("zhaobiaofs",zhaobiaofs_text.getText().toString())
                        .add("shifouxyxt", shifouxyxt_text.getSelectedItem().toString())
                        .add("yonghulxr",yonghulxr_text.getText().toString())
                        .add("yonghulxdh",yonghulxdh_text.getText().toString())
                        .add("yonghuemail",yonghuemail_text.getText().toString())
                        .add("weizhongbyy","")
                        .add("zhongjians",zhongjians_text.getText().toString())
                        .add("zhongjianslxr",zhongjianslxr_text.getText().toString())
                        .add("zhongjiansdh",zhongjiansdh_text.getText().toString())
                        .add("zhongjiansemail",zhongjiansemail_text.getText().toString())
                        .add("suoshuly",suoshuly_text.getSelectedItem().toString())
                        .add("login_id",user_id)
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

    public void saveTo_kdministor(){
        try {
            String path = getResources().getString(R.string.serverIp)+"pm/pm_ic/saveTo_kdministor.action";
            URL url = new URL(path);
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("xuhao",xuhao)
                    .build();
            Request request = new Request.Builder()
                    .url(url).post(requestBody).build();
            client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
