package com.marxin.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.marxin.main.banner.Banner;

import java.security.PrivateKey;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> moreData;
    private ArrayList<String> twoData;
    private ArrayList<String> oneData;
    private Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    /**
     * 初始化
     */
    private void init() {
        findView();

        initData();
    }

    /**
     * 找到控件
     */
    private void findView() {
        banner = (Banner) findViewById(R.id.banner);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        oneData = new ArrayList<>();
        oneData.add("http://img06.tooopen.com/images/20160817/tooopen_sy_175780144453.jpg");

        twoData = new ArrayList<>();
        twoData.addAll(oneData);
        twoData.add("http://img06.tooopen.com/images/20160906/tooopen_sy_177992564428.jpg");

        moreData = new ArrayList<>();
        moreData.addAll(twoData);
        moreData.add("http://img06.tooopen.com/images/20160810/tooopen_sy_175027421951.jpg");
        moreData.add("http://img06.tooopen.com/images/20160811/tooopen_sy_175085182258.jpg");
    }


    /**
     * 按钮监听
     *
     * @param view
     */
    public void onClick(View view) {
        Button button = (Button) view;
        int id = button.getId();
        switch (id) {
            case R.id.btn_one:
                banner.setDataurl(oneData);
                break;
            case R.id.btn_two:
                banner.setDataurl(twoData);
                break;
            case R.id.btn_more:
                banner.setDataurl(moreData);
                break;
        }
    }
}
