package com.example.materialtest.dataprocessing;

import com.example.materialtest.model.InterchangeNotice;
import com.example.materialtest.model.JointQueryInfo;
import com.example.materialtest.model.MinstorTaskView;
import com.example.materialtest.model.Pm_department;
import com.example.materialtest.model.Test;
import com.example.materialtest.model.Users;
import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class GsonUtil {  
       public static String getJson(String url){
             
           HttpClient client = new DefaultHttpClient();  
             
           HttpPost  request;  
           try {  
            request = new HttpPost(new URI(url));
            HttpResponse  response  =  client.execute(request);  


            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity  entity = response.getEntity();  
                if(entity!=null){  
                    String beanListToJson = EntityUtils.toString(entity,"GBK");
                    return beanListToJson;  
                }  
            }  
            
        } catch (Exception e) {
            // TODO: handle exception  
        }  
         return  null;  
       }  
       
       
   public  static ConcurrentHashMap<String,Object> getMapFromJson(String json){
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<ConcurrentHashMap<String,Object>>() {
        }.getType();  
        Gson gson = new Gson();  
        ConcurrentHashMap<String,Object> map= gson.fromJson(json, type);
        return map;  
   }

    public  static Test getTestFromJson(String json){
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Test>() {
        }.getType();
        Gson gson = new Gson();
        Test test= gson.fromJson(json, type);
        return test;
    }

    public  static List<InterchangeNotice> getIcnListFromJson(String json){
       java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<InterchangeNotice>>() {
       }.getType();  
       Gson gson = new Gson();  
       List<InterchangeNotice> list= gson.fromJson(json, type);
       return list;  
  }

    public  static String getMessageFromJson(String json){
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<String>() {
        }.getType();
        Gson gson = new Gson();
        String list= gson.fromJson(json, type);
        return list;
    }


    public  static List<JointQueryInfo> getIcListFromJson(String json){
       java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<JointQueryInfo>>() {
       }.getType();  
       Gson gson = new Gson();  
       List<JointQueryInfo> list= gson.fromJson(json, type);
       return list;  
  }

    public  static List<MinstorTaskView> getmtListFromJson(String json){
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<MinstorTaskView>>() {
        }.getType();
        Gson gson = new Gson();
        List<MinstorTaskView> list= gson.fromJson(json, type);
        return list;
    }

    public  static List<Pm_department> getDepListFromJson(String json){
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<Pm_department>>() {
        }.getType();
        Gson gson = new Gson();
        List<Pm_department> list= gson.fromJson(json, type);
        return list;
    }

    public  static List<Users> getUserListFromJson(String json){
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<Users>>() {
        }.getType();
        Gson gson = new Gson();
        List<Users> list= gson.fromJson(json, type);
        return list;
    }

}  
