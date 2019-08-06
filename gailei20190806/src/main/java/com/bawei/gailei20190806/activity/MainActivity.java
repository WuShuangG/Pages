package com.bawei.gailei20190806.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.gailei20190806.R;
import com.bawei.gailei20190806.adapter.MyBase;
import com.bawei.gailei20190806.bean.ListBean;
import com.bawei.gailei20190806.persenter.IView;
import com.bawei.gailei20190806.persenter.Persenter;
import com.bawei.gailei20190806.utils.HttpUtils;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IView{

    private ListView lv;
    private String path = "http://blog.zhaoliang5156.cn/api/chathistory.json";
    private SharedPreferences sp;
    private Persenter persenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);

        sp = getSharedPreferences("jsons",MODE_PRIVATE);

        boolean netWork = HttpUtils.isNetWork(this);
        if (netWork){
            persenter = new Persenter(this);
            persenter.getData(path);
        }else {
            Toast.makeText(this, "无网络连接", Toast.LENGTH_SHORT).show();
            String json = sp.getString("json","");
            Gson gson = new Gson();
            ListBean listBean = gson.fromJson(json, ListBean.class);
            List<ListBean.DataInfo> data = listBean.getData();
            lv.setAdapter(new MyBase(data,MainActivity.this));
        }



    }



    @Override
    public void onSuccess(Object obj) {
        String json = (String) obj;
        Gson gson = new Gson();
        ListBean listBean = gson.fromJson(json, ListBean.class);
        List<ListBean.DataInfo> data = listBean.getData();
        lv.setAdapter(new MyBase(data,MainActivity.this));
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("json",json);
        edit.commit();
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        persenter.onDesctory();
    }
}
