package com.example.materialtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.materialtest.dataprocessing.ActivityCollector;
import com.example.materialtest.dataprocessing.GsonUtil;

import java.io.Serializable;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity {

	// 声明控件
	private EditText et_name, et_pass;
	//final String uri=R.string.serverIp+"pm/login.action";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ActivityCollector.addActivity(this);
		// 获取控件对象
		et_name = (EditText) findViewById(R.id.et_name); //用户名控件
		et_pass = (EditText) findViewById(R.id.et_pass);//密码控件
	}




	/**
	 * 按钮点击事件处理
	 * @param v
	 */
	public void login(View v) {
		//获取控件id
		int id = v.getId();

		switch (id) {
			case R.id.btn_login:

				//暂时测试  删除下三行，下面注释解除

/*				Intent intent= new Intent();
				intent.setClass(LoginActivity.this, MainActivity.class);
				startActivity(intent);*/

           // 获取控件的文本内容
            final String user_id = et_name.getText().toString();// 用户名
            final String user_password = et_pass.getText().toString();// 用户密码
            //判断用户名和密码是否为空
            if (TextUtils.isEmpty(user_id.trim())  || TextUtils.isEmpty(user_password.trim())) {
                Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_LONG).show();
            } else {
               loginByOkHttpClient(user_id, user_password);
            }
				break;
			default:
				break;
		}
	}

	public void loginByOkHttpClient(final String user_id,final String user_password){
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String path = getResources().getString(R.string.serverIp)+"pm/login.action";
					URL uri = new URL(path);
					OkHttpClient client = new OkHttpClient();
					RequestBody requestBody = new FormBody.Builder()
							.add("user_id",user_id)
							.add("user_password",user_password).build();
					Request request = new Request.Builder()
							.url(uri).post(requestBody).build();
					Response response = client.newCall(request).execute();
					final String responseData = response.body().string();
					LoginActivity.this.runOnUiThread(new Runnable() {   //通过runOnUiThread方法处理
						@Override
						public void run() {
							ConcurrentHashMap<String,Object> map = GsonUtil.getMapFromJson(responseData);
							String message=(String)map.get("message");

							if(message.equals("false")){
								et_pass.setText("");
								Toast.makeText(LoginActivity.this, "密码错误，请重新输入", Toast.LENGTH_SHORT).show();
							}else{
								Intent intent= new Intent();
								intent.putExtra("map", (Serializable)map);
								intent.setClass(LoginActivity.this, MainActivity.class);
								startActivity(intent);
							}

						}
					});
				} catch (Exception e) {
					Toast.makeText(LoginActivity.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollector.removeActvity(this);
	}
}