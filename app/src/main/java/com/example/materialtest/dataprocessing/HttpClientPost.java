package com.example.materialtest.dataprocessing;

/**
 * Created by xzs on 2017/5/27.
 */

public class HttpClientPost {
    /*public void loginByHttpClientPost(String user_id, String user_password) {
		//1.创建 HttpClient 的实例
		HttpClient client = new DefaultHttpClient();
		//2. 创建某种连接方法的实例，在这里是HttpPost。在 HttpPost 的构造函数中传入待连接的地址
		HttpPost httpPost = new HttpPost(uri);
		try {
			//封装传递参数的集合
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			//往这个集合中添加你要传递的参数
			parameters.add(new BasicNameValuePair("user_id", user_id));
			parameters.add(new BasicNameValuePair("user_password", user_password));
			//创建传递参数封装 实体对象
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, "UTF-8");//设置传递参数的编码
			//把实体对象存入到httpPost对象中
			httpPost.setEntity(entity);
			//3. 调用第一步中创建好的实例的 execute 方法来执行第二步中创建好的 method 实例
			HttpResponse response = client.execute(httpPost); //HttpUriRequest的后代对象 //在浏览器中敲一下回车
			//4. 读 response
			System.out.println("response is  "+response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==200){//判断状态码
				InputStream is = response.getEntity().getContent();//获取内容
				final String result = StreamTools.streamToStr(is); // 通过工具类转换文本
				LoginActivity.this.runOnUiThread(new Runnable() {   //通过runOnUiThread方法处理
					@Override
					public void run() {
						ConcurrentHashMap<String,Object> map = GsonUtil.getMapFromJson(result);
						String message=(String)map.get("message");

						if(message.equals("false")){
							et_pass.setText("");
							Toast.makeText(LoginActivity.this, "密码错误，请重新输入", Toast.LENGTH_SHORT).show();
						}else{
							Intent intent= new Intent();
							intent.putExtra("map", (Serializable)map);
							intent.setClass(LoginActivity.this, IcActivity.class);
							startActivity(intent);
						}

					}
				});
			}else
			{
				Toast.makeText(LoginActivity.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//6. 释放连接。无论执行方法是否成功，都必须释放连接
			client.getConnectionManager().shutdown();
		}
	}*/
}
