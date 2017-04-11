package com.android.recycleviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> dataList = new ArrayList<>();
    private MyAadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));  // 类似listview布局
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

//        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL)); // StaggeredGridLayoutManager.HORIZONTAL 作调整时，item_layout中textView 长宽属性做相应调整
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator()); // 好像不加也有动画效果 ？？？

        adapter = new MyAadapter(MainActivity.this, dataList);
        adapter.setOnItemClickListener(new MyAadapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {
                Toast.makeText(MainActivity.this,position + " click",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClickListener(View view, int position) {
                Toast.makeText(MainActivity.this,position + " Long Click",Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 'A'; i < 'z'; i++) {
            dataList.add((char) i + " ---> " + i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                AddData(1); // 1为数据所对定的下标
                break;
            case R.id.action_delete:
                DaleteData(1);
                break;

        }
        return true;
    }

    // 增加数据 position为数据所对应下标
    public void AddData(int position) {
        dataList.add(position, "liurenyi");
        adapter.notifyItemInserted(position);
    }

    // 删除数据
    public void DaleteData(int positon) {
        dataList.remove(positon);
        adapter.notifyItemRemoved(positon);
    }
}

