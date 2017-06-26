package com.example.materialtest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.materialtest.dataprocessing.MyApplication;
import com.example.materialtest.model.InterchangeNotice;
import com.example.materialtest.util.FileUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import net.tsz.afinal.FinalHttp;

import org.apache.http.Header;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class PhotoUploadActivity extends AppCompatActivity {
	private SwipeRefreshLayout swipeRefresh;
	private String actionUrl ;
	private TextView mText1;
	private Button mButton;
    private Button clearButton;
	private FinalHttp finalHttp;
	private final int FILE_SELECT_CODE=1;
	private static List<InterchangeNotice> icnLst=new ArrayList<>() ;
	private ProgressDialog pd;    //进度条对话框

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photoupload);

		Intent intent = getIntent();
		String uid =intent.getStringExtra("uid");
		final String userId= intent.getStringExtra("userId");
		String icu_id= intent.getStringExtra("icu_id");

        actionUrl = getResources().getString(R.string.serverIp)+"pm/UploadFileServlet?uid="+uid+"&userId="+userId+"&icu_id="+icu_id;
        mText1 = (TextView) findViewById(R.id.files_path);
          /* 设置mButton的onClick事件处理 */
        mButton = (Button) findViewById(R.id.myButton);
        clearButton=(Button) findViewById(R.id.myButton2);

		swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
		swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
		swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				refresh();
			}
		});



		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chooseFile();
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mText1.setText("");
				mButton.setText("选择文件");
            }
        });

		finalHttp = new FinalHttp();
		finalHttp.addHeader("Accept-Charset", "UTF-8");//配置http请求头
		finalHttp.configCharset("UTF-8");
		finalHttp.configTimeout(20 * 1000);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.uploadfile_fab);
        fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Snackbar.make(view, "确定上传吗", Snackbar.LENGTH_SHORT)
                    .setAction("确定", new View.OnClickListener() {
						@Override
                        public void onClick(View v) {
							upload();
							/*new Thread(new Runnable() {
								@Override
								public void run() {
									icnLst=MyTask.listMyTask(userId);
									Intent intent= new Intent();
									intent.putExtra("icnLst", (Serializable) (icnLst));
									intent.putExtra("user_id",userId);
									intent.setClass(PhotoUploadActivity.this, MyTaskActivity.class);
									startActivity(intent);
								}
							}).start();*/

                        }
                    })
                    .show();
        }
    });
    }

    //上传文件
    public void upload(){
        try {
            if(mText1.getText().toString().length()<1){
                Toast.makeText(MyApplication.getContext(),"请先添加文件",Toast.LENGTH_LONG).show();
            }else {
                AsyncHttpClient client =new AsyncHttpClient();
                RequestParams params =new RequestParams();
                String[] sourceStrArray = mText1.getText().toString().split(";");
                for (int i = 0; i < sourceStrArray.length; i++) {
                    File file = new File(sourceStrArray[i]);
                    params.put("file"+String.valueOf(i),file);
                }
                client.post(actionUrl, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        showToast("上传成功！");
						pd.dismiss();
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        showToast("上传失败！");
						pd.dismiss();
                    }

                    @Override
                    public void onStart() {
                        showToast("开始上传！");
						pd=new  ProgressDialog(PhotoUploadActivity.this);
						pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
						pd.setMessage("正在上传文件");
						pd.show();
                        super.onStart();
                    }

                    @Override
                    public void onProgress(int bytesWritten, int totalSize) {
						pd.setMax(totalSize);
						pd.setProgress(bytesWritten);
						super.onProgress(bytesWritten, totalSize);
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	/*//上传文件
	public void upload2(){
		try {
			if(mText1.getText().toString().length()<1){
				Toast.makeText(MyApplication.getContext(),"请先添加文件",Toast.LENGTH_LONG).show();
			}else {
				AjaxParams params = new AjaxParams();
				String[] sourceStrArray = mText1.getText().toString().split(";");
				for (int i = 0; i < sourceStrArray.length; i++) {
					File file = new File(sourceStrArray[i]);
					params.put("file"+String.valueOf(i),file);
				}
				String url = actionUrl;//请求的URL
				//post请求，三个参数分别是请求地址、请求参数、请求的回调接口
				finalHttp.post(url, params, new AjaxCallBack<String>() {
                    @Override
                    public void onLoading(long count, long current) { //每1秒钟自动被回调一次
                        *//*textView.setText(current+"/"+count);*//*
                    }

                    @Override
					public void onFailure(Throwable t, int errorNo, String strMsg) {
						super.onFailure(t, errorNo, strMsg);
						showToast("上传失败, msg=" + strMsg);
					}

					@Override
					public void onStart() {
						super.onStart();
						showToast("开始上传");
					}

					@Override
					public void onSuccess(String t) {
						super.onSuccess(t);
						//根据服务器返回的json数据，判断上传是否成功
						if(!TextUtils.isEmpty(t)){
							try {
								JSONObject obj = new JSONObject(t);
								if(obj.optInt("result") == 1){
									showToast("上传成功");
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

/*	打开文件选择器*/

	private void chooseFile() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("*/*");
		intent.addCategory(Intent.CATEGORY_OPENABLE);

		try {
			startActivityForResult( Intent.createChooser(intent, "请选择上传的文件"), FILE_SELECT_CODE);
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(this, "请建立文件管理器",  Toast.LENGTH_SHORT).show();
		}
	}

	/*选择的结果*/
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)  {
		switch (requestCode) {
			case FILE_SELECT_CODE:
				if (resultCode == RESULT_OK) {
					Uri uri = data.getData();
					String path = FileUtils.getPath(this, uri);
					String namepath=null;
					namepath=mText1.getText().toString();
					if(namepath!=null && namepath!="") {
						mText1.setText(namepath + ";" + path);
					}else{
						mText1.setText(path);
					}
					mButton.setText("再选");
				}
				break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}


	private void showToast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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

	private void refresh() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						swipeRefresh.setRefreshing(false);
					}
				});
			}
		}).start();
	}

}





