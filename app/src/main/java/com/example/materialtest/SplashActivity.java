package com.example.materialtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.materialtest.dataprocessing.ActivityCollector;

public class SplashActivity extends Activity {

private Intent intent;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState); 
setContentView(R.layout.splash);
    ActivityCollector.addActivity(this);
// PushManager.startWork(getApplicationContext(),PushConstants.LOGIN_TYPE_API_KEY,"FQ6vwPps0SYLIWtlSuzLhow7");
    startMainAvtivity();
} 

private void startMainAvtivity() { 
new Handler().postDelayed(new Runnable() {
public void run() { 
intent=new Intent(SplashActivity.this,LoginActivity.class);
startActivity(intent); 
SplashActivity.this.finish();
} 
}, 2000);
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActvity(this);
    }
}