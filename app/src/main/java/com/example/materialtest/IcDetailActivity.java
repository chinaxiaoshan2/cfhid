package com.example.materialtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.materialtest.model.JointQueryInfo;

public class IcDetailActivity extends AppCompatActivity {


    public JointQueryInfo jointQueryInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icdetail);
        Intent intent = getIntent();
        jointQueryInfo = (JointQueryInfo) intent.getSerializableExtra("jointQueryInfo");
        String fruitName =jointQueryInfo.getXiangmumc();
        int fruitImageId=jointQueryInfo.getImageId();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView = (ImageView) findViewById(R.id.fruit_image_view);
        TextView fruitContentText = (TextView) findViewById(R.id.xiangmumc_text);
        TextView yonghu_text = (TextView) findViewById(R.id.yonghu_text);
        TextView xinxijib_text=(TextView) findViewById(R.id.xinxijib_text);
        TextView jinzhanjd_text=(TextView) findViewById(R.id.jinzhanjd_text);
        TextView yujizb_text=(TextView) findViewById(R.id.yujizb_text);
        TextView suoshuly_text=(TextView) findViewById(R.id.suoshuly_text);
        TextView xinxilb_text=(TextView) findViewById(R.id.xinxilb_text);
        TextView fuzebm_text=(TextView) findViewById(R.id.fuzebm_text);
        TextView fuzery_text=(TextView) findViewById(R.id.fuzery_text);
        TextView shebeizl_text=(TextView) findViewById(R.id.shebeizl_text);
        TextView yujihete_text=(TextView) findViewById(R.id.yujihete_text);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        fruitContentText.setText(jointQueryInfo.getXiangmumc());
        yonghu_text.setText(jointQueryInfo.getYonghu());
        xinxijib_text.setText(jointQueryInfo.getXinxijib());
        jinzhanjd_text.setText(jointQueryInfo.getJinzhanjd());
        yujizb_text.setText(jointQueryInfo.getYujizb());
        suoshuly_text.setText(jointQueryInfo.getSuoshuly());
        xinxilb_text.setText(jointQueryInfo.getXinxilb());
        fuzebm_text.setText(jointQueryInfo.getFuzebm());
        fuzery_text.setText(jointQueryInfo.getFuzery());
        shebeizl_text.setText(jointQueryInfo.getShebeizl());
        yujihete_text.setText(jointQueryInfo.getYujihte());
       // xinxijib_text.setText(jointQueryInfo.getXinxijib());

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
}
