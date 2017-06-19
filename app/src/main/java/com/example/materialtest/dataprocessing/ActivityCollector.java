package com.example.materialtest.dataprocessing;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by xzs on 2017/6/5.
 */

public class ActivityCollector {

    public static List<Activity> activities=new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActvity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for(Activity activity : activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
            activities.clear();
        }
    }
}
