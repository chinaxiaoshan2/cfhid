package com.example.materialtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.materialtest.adapter.DisTaskAdapter;
import com.example.materialtest.model.MinstorTaskView;

import java.util.ArrayList;
import java.util.List;

    public class DistributeTaskActivity extends AppCompatActivity {


        private List<MinstorTaskView> mtList = new ArrayList<>();

        private DisTaskAdapter adapter;

        private SwipeRefreshLayout swipeRefresh;

        private String user_id;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_distask);
            Toolbar toolbar = (Toolbar) findViewById(R.id.distask_toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }


            Intent intent = getIntent();
            mtList= (List<MinstorTaskView>) intent.getSerializableExtra("mtLst");
            user_id=intent.getStringExtra("user_id");
            initFruits();
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new DisTaskAdapter(mtList,user_id);
            recyclerView.setAdapter(adapter);
            swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
            swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
            swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refreshFruits();
                }
            });
        }

        private void refreshFruits() {
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
                            initFruits();
                            adapter.notifyDataSetChanged();
                            swipeRefresh.setRefreshing(false);
                        }
                    });
                }
            }).start();
        }

        private void initFruits() {

        }

        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.toolbar, menu);
            return true;
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
