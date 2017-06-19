package com.example.materialtest.dataprocessing;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Xml;

import com.example.materialtest.model.UpdateInfo;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;


public class Update extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }



    public static UpdateInfo getUpdataInfo(InputStream is) throws Exception{
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        int type = parser.getEventType();
        UpdateInfo info = new UpdateInfo();
        while(type != XmlPullParser.END_DOCUMENT ){
            switch (type) {
                case XmlPullParser.START_TAG:
                    if("version".equals(parser.getName())){
                        info.setVersion(parser.nextText());
                    }else if ("url".equals(parser.getName())){
                        info.setUrl(parser.nextText());
                    }else if ("description".equals(parser.getName())){
                        info.setDescription(parser.nextText());
                    }
                    break;
            }
            type = parser.next();
        }
        return info;
    }


    /**
     * 获取软件版本号
     * @param context
     * @return
     */
    public static int getVerCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(
                    "com.example.materialtest", 0).versionCode;
        }catch(Exception e){
            e.printStackTrace();
        }
        return verCode;
    }

    /**
     * 获取版本名称
     * @param context
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(
                    "com.example.materialtest", 0).versionName;
        }catch(Exception e){
            e.printStackTrace();
        }
        return verName;
    }




}
