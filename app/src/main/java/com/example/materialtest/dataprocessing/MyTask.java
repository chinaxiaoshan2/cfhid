package com.example.materialtest.dataprocessing;

import android.app.Activity;
import android.os.Bundle;

import com.example.materialtest.R;
import com.example.materialtest.model.InterchangeNotice;
import com.example.materialtest.model.JointQueryInfo;
import com.example.materialtest.model.MinstorTaskView;
import com.example.materialtest.model.Pm_department;
import com.example.materialtest.model.Users;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MyTask extends Activity {

    private static List<InterchangeNotice> icnLst=new ArrayList<>() ;
    private static List<MinstorTaskView> mtLst=new ArrayList<>() ;
    private static List<Pm_department> depLst=new ArrayList<>() ;
    private static List<JointQueryInfo> icList=new ArrayList<>() ;
    private static List<Users> userLst=new ArrayList<>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    public  static List<Users> findUserLst(int keduan_id){ //项目系统 人员列表
        try {
            String path = MyApplication.getContext().getString(R.string.serverIp)+"pm/pm_ic/listUser.action";
            URL url = new URL(path);
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("keduan_id",String.valueOf(keduan_id))
                    .build();
            Request request = new Request.Builder()
                    .url(url).post(requestBody).build();
            Response response = client.newCall(request).execute();
            final String responseData = response.body().string();
            userLst= GsonUtil.getUserListFromJson(responseData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userLst;
    }


    public  static List<Pm_department> findDepLst(){ //项目系统 部门列表
        try {
            String path = MyApplication.getContext().getString(R.string.serverIp)+"pm/pm_ic/listAdd.action";
            URL url = new URL(path);
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .build();
            Request request = new Request.Builder()
                    .url(url).post(requestBody).build();
            Response response = client.newCall(request).execute();
            final String responseData = response.body().string();
            depLst = GsonUtil.getDepListFromJson(responseData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return depLst;
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


    public  static List<JointQueryInfo> findIc(String operType,String login_id){ //3领导审阅PM_IC
        try {
            String a=null;
            if(operType.equals("3")){
                a="pm/pm_ic/queryIcInfo3.action";
            }else if(operType.equals("1")) {
                a="pm/pm_ic/queryIcInfo.action";
            }else if(operType.equals("9")) {
                a="pm/pm_ic/queryIcInfo5.action";
            }
            String path = MyApplication.getContext().getString(R.string.serverIp)+a;
            URL url = new URL(path);
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("operType",operType)
                    .add("login_id",login_id)
                    .add("kdministor_status","0")
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
}
