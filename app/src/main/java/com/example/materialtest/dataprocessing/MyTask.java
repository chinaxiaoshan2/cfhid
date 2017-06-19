package com.example.materialtest.dataprocessing;

import android.app.Activity;
import android.os.Bundle;

import com.example.materialtest.R;
import com.example.materialtest.model.InterchangeNotice;
import com.example.materialtest.model.JointQueryInfo;
import com.example.materialtest.model.MinstorTaskView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MyTask extends Activity {

    private static List<JointQueryInfo> list=new ArrayList<>();
    private static List<InterchangeNotice> icnLst=new ArrayList<>() ;
    private static List<MinstorTaskView> mtLst=new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
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
            list = GsonUtil.getIcListFromJson(responseData);
            for(int i=0;i<list.size();i++){
                list.get(i).setImageId(R.drawable.apple);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String taskToMinstor(String user_id,String noticeId,String subType){  //上报到部长
        String jsonMessage=null;
        try {
            String path = MyApplication.getContext().getString(R.string.serverIp)+"pm/interchange/taskToMinstor.action";
            URL url = new URL(path);
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("user_id",user_id)
                    .add("noticeId",noticeId)
                    .add("subType",subType)
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

    public static List<InterchangeNotice> listMyTask(String userId) { //我的任务
        try {
            String path = MyApplication.getContext().getString(R.string.serverIp) + "pm/interchange/taskQuery1.action";
            URL url = new URL(path);
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("user_id", userId)
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

    public  static List<MinstorTaskView> listDisTask(String serverIp, String userId) { //我的任务
        try {
            String path =  serverIp+ "pm/interchange/taskNotice.action";
            URL url = new URL(path);
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("user_id", userId)
                    .build();
            Request request = new Request.Builder()
                    .url(url).post(requestBody).build();
            Response response = client.newCall(request).execute();
            final String responseData = response.body().string();
            mtLst = GsonUtil.getmtListFromJson(responseData);
            for (int i = 0; i < mtLst.size(); i++) {
                mtLst.get(i).setImageId(R.drawable.grape);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mtLst;
    }

}
