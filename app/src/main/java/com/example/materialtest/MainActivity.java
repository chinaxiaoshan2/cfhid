package com.example.materialtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.materialtest.dataprocessing.ActivityCollector;
import com.example.materialtest.dataprocessing.DownLoadManager;
import com.example.materialtest.dataprocessing.GsonUtil;
import com.example.materialtest.dataprocessing.MyTask;
import com.example.materialtest.dataprocessing.Update;
import com.example.materialtest.model.InterchangeNotice;
import com.example.materialtest.model.JointQueryInfo;
import com.example.materialtest.model.MinstorTaskView;
import com.example.materialtest.model.UpdateInfo;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

	private GridView gview;
	private List<ConcurrentHashMap<String, Object>> data_list;
	private SimpleAdapter sim_adapter;
	private   String user_id;
	private static List<JointQueryInfo> icList=new ArrayList<>() ;
	private static List<InterchangeNotice> icnLst=new ArrayList<>() ;
	private static List<MinstorTaskView> mtLst=new ArrayList<>() ;
   //版本升级用
	private final String TAG = this.getClass().getName();
	private final int UPDATA_NONEED = 0;
	private final int UPDATA_CLIENT = 1;
	private final int GET_UNDATAINFO_ERROR = 2;
	private final int SDCARD_NOMOUNTED = 3;
	private final int DOWN_ERROR = 4;
	private UpdateInfo info;
	private int localVersion;




	// 图片封装为一个数组
	private int[] icon = { R.drawable.address_book, R.drawable.calendar,
			R.drawable.camera, R.drawable.clock, R.drawable.weather,
            R.drawable.camera , R.drawable.grape/* R.drawable.settings,
            R.drawable.speech_balloon, R.drawable.weather, R.drawable.world,
            R.drawable.youtube*/ };
	private String[] iconName = { "我的任务", "派发任务", "信息采集", "信息推送", "天气预报","更新版本","上传附件"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = getIntent();
		@SuppressWarnings("unchecked")
		HashMap<String, Object> map= (HashMap<String,Object>) intent.getSerializableExtra("map");
		user_id=(String) map.get("user_id");


		gview = (GridView) findViewById(R.id.gview);
		//新建List
		data_list = new ArrayList<>();
		//获取数据
		getData();
		//新建适配器
		String[] from ={"image","text"};
		int [] to = {R.id.image,R.id.text};
		sim_adapter = new SimpleAdapter(this, data_list, R.layout.item, from, to);
		//配置适配器
		gview.setAdapter(sim_adapter);
		gview.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position){
			case 0:
				// 我的任务
				new Thread(new Runnable() {
					@Override
					public void run() {
						icnLst=listMyTask();
						Intent intent= new Intent();
						intent.putExtra("icnLst", (Serializable) (icnLst));
						intent.putExtra("user_id",user_id);
						intent.setClass(MainActivity.this, MyTaskActivity.class);
						startActivity(intent);
					}
				}).start();
            break;
			case 1:
				// 分配任务
				new Thread(new Runnable() {
					@Override
					public void run() {
						mtLst= MyTask.listDisTask(getResources().getString(R.string.serverIp),user_id);
						Intent intent= new Intent();
						intent.putExtra("mtLst", (Serializable) (mtLst));
						intent.putExtra("user_id",user_id);
						intent.setClass(MainActivity.this, DistributeTaskActivity.class);
						startActivity(intent);
					}
				}).start();
				break;
			case 2:
				// 信息采集
				new Thread(new Runnable() {
					@Override
					public void run() {
						icList=managerFindIc();
						Intent intent= new Intent();
						intent.putExtra("icList", (Serializable) (icList));
						intent.setClass(MainActivity.this, IcActivity.class);
						startActivity(intent);
					}
				}).start();
				break;

			case 3:
				// 消息推送
				new Thread(new Runnable() {
					@Override
					public void run() {
						Intent intent= new Intent();
						intent.setClass(MainActivity.this, PushDemoActivity.class);
						startActivity(intent);
					}
				}).start();
				break;

			case 4:
				//天气预报
				new Thread(new Runnable() {
					@Override
					public void run() {
						Intent intent= new Intent();
						intent.setClass(MainActivity.this, WeatherMainActivity.class);
						startActivity(intent);
					}
				}).start();
				break;

			case 5:
				//版本更新
					try {
							localVersion = Update.getVerCode(this.getBaseContext());
							CheckVersionTask cv = new CheckVersionTask();
							new Thread(cv).start();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
				break;

			case 6:
				//上传文件
				new Thread(new Runnable() {
					@Override
					public void run() {
						Intent intent= new Intent();
						intent.setClass(MainActivity.this, PhotoUploadActivity.class);
						startActivity(intent);
					}
				}).start();
				break;

			default:
				break;
		}
	}


	public List<ConcurrentHashMap<String, Object>> getData(){
		//cion和iconName的长度是相同的，这里任选其一都可以
		for(int i=0;i<icon.length;i++){
			ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();
			map.put("image", icon[i]);
			map.put("text", iconName[i]);
			data_list.add(map);
		}
		return data_list;
	}

	public class CheckVersionTask implements Runnable {
		InputStream is;
		public void run() {
			try {
				String path = getResources().getString(R.string.serverIp)+"version.xml";
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");
				int responseCode = conn.getResponseCode();
				if (responseCode == 200) {
					// 从服务器获得一个输入流
					is = conn.getInputStream();
				}
				info = Update.getUpdataInfo(is);
				if (Integer.parseInt(info.getVersion())<=localVersion) {
					Log.i(TAG, "不需要更新");
					Message msg = new Message();
					msg.what = 0;
					handler.sendMessage(msg);
					// LoginMain();
				} else {
					Log.i(TAG, "本地版本小，需要更新");
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);
				}
			} catch (Exception e) {
				Message msg = new Message();
				msg.what = GET_UNDATAINFO_ERROR;
				handler.sendMessage(msg);
				e.printStackTrace();
			}
		}
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
				case 0:
					Toast.makeText(getApplicationContext(), "不需要更新",
							Toast.LENGTH_SHORT).show();
					break;
				case 1:
					//对话框通知用户升级程序
					showUpdataDialog();
					break;
				case GET_UNDATAINFO_ERROR:
					//服务器超时
					Toast.makeText(getApplicationContext(), "获取服务器更新信息失败", Toast.LENGTH_LONG).show();
					break;
				case DOWN_ERROR:
					//下载apk失败
					Toast.makeText(getApplicationContext(), "下载新版本失败", Toast.LENGTH_LONG).show();
					break;
			}
		}
	};
	/*
            *
            * 弹出对话框通知用户更新程序
            *
            * 弹出对话框的步骤：
            *  1.创建alertDialog的builder.
            *  2.要给builder设置属性, 对话框的内容,样式,按钮
            *  3.通过builder 创建一个对话框
            *  4.对话框show()出来
            */
	protected void showUpdataDialog() {
		AlertDialog.Builder builer = new AlertDialog.Builder(this);
		builer.setTitle("版本升级");
		builer.setMessage(info.getDescription());
		//当点确定按钮时从服务器上下载 新的apk 然后安装   װ
		builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Log.i(TAG, "下载apk,更新");
				downLoadApk();
			}
		});
		builer.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//do sth
			}
		});
		AlertDialog dialog = builer.create();
		dialog.show();
	}

	/*
         * 从服务器中下载APK
         */
	protected void downLoadApk() {
		final ProgressDialog pd;    //进度条对话框
		pd = new  ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMessage("正在下载更新");
		pd.show();
		new Thread(){
			@Override
			public void run() {
				try {
					File file = DownLoadManager.getFileFromServer(info.getUrl(), pd);
					sleep(3000);
					installApk(file);
					pd.dismiss(); //结束掉进度条对话框
				} catch (Exception e) {
					Message msg = new Message();
					msg.what = DOWN_ERROR;
					handler.sendMessage(msg);
					e.printStackTrace();
				}
			}}.start();
	}

	//安装apk
	protected void installApk(File file) {
		Intent intent = new Intent();
		//执行动作
		intent.setAction(Intent.ACTION_VIEW);
		//执行的数据类型
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		startActivity(intent);
	}

	public  List<JointQueryInfo> managerFindIc(){ //3领导审阅PM_IC
		try {
			String path = getResources().getString(R.string.serverIp)+"pm/pm_ic/queryIcInfo3.action";
			URL url = new URL(path);
			OkHttpClient client = new OkHttpClient();
			RequestBody requestBody = new FormBody.Builder()
					.add("operType","3")
					.build();
			Request request = new Request.Builder()
					.url(url).post(requestBody).build();
			Response response = client.newCall(request).execute();
			final String responseData = response.body().string();
			icList = GsonUtil.getIcListFromJson(responseData);
			for(int i=0;i<icList.size();i++){
				icList.get(i).setImageId(R.drawable.apple);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return icList;
	}


		public List<InterchangeNotice> listMyTask() { //我的任务
			try {
				String path = getResources().getString(R.string.serverIp) + "pm/interchange/taskQuery1.action";
				URL url = new URL(path);
				OkHttpClient client = new OkHttpClient();
				RequestBody requestBody = new FormBody.Builder()
						.add("user_id", user_id)
						.build();
				Request request = new Request.Builder()
						.url(url).post(requestBody).build();
				Response response = client.newCall(request).execute();
				final String responseData = response.body().string();
				icnLst = GsonUtil.getIcnListFromJson(responseData);
				for (int i = 0; i < icnLst.size(); i++) {
					icnLst.get(i).setImageId(R.drawable.banana);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return icnLst;
		}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK )
		{
			// 创建退出对话框
			AlertDialog isExit = new AlertDialog.Builder(this).create();
			// 设置对话框标题
			isExit.setTitle("系统提示");
			// 设置对话框消息
			isExit.setMessage("确定要退出吗");
			// 添加选择按钮并注册监听
			isExit.setButton("确定", listener);
			isExit.setButton2("取消", listener);
			// 显示对话框
			isExit.show();

		}

		return false;

	}
	/**监听对话框里面的button点击事件*/
	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
	{
		public void onClick(DialogInterface dialog, int which)
		{
			switch (which)
			{
				case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
					//android.os.Process.killProcess(android.os.Process.myPid());
					//System.exit(0);
					ActivityCollector.finishAll();
					finish();
					break;
				case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
					break;
				default:
					break;
			}
		}
	};

}





