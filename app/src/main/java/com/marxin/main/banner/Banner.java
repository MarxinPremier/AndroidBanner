package com.marxin.main.banner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.marxin.main.R;

import java.util.ArrayList;

/**
 * @author premier
 *         <p>
 *         banner的封装
 *         采用viewpager加单张imageview的方案来实现
 *         1.多余一张图片的时候用viewpager 来实现无线轮滑的效果
 *         2.一张图片的时候不能轮滑
 *         3.零张图片的时候显示默认图片
 */
public class Banner extends RelativeLayout {

    //上下文
    private Context context;
    //基本view
    private View baseView;
    //viewpager 可被继承使用
    protected AutoScrollViewPager viewPager;
    //单张图片使用的控件 可被继承使用
    protected ImageView singlImg;
    //数据源
    private ArrayList<String> dataurl = new ArrayList<>();
    private BannerViewPagerAdapter adapter;

    public Banner(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        findView();

        initDefaultHeight();

        initSingleImg();

        initDataUrl();

        initBanner();
    }

    /**
     * 初始化url
     */
    private void initDataUrl() {
        dataurl.add("http://img06.tooopen.com/images/20160906/tooopen_sy_177992564428.jpg");
    }

    /**
     * 初始化单张图片
     */
    private void initSingleImg() {
        //默认隐藏
        singlImg.setVisibility(GONE);
    }

    /**
     * 初始化banner
     */
    private void initBanner() {
        //适配器
        adapter = new BannerViewPagerAdapter(context, dataurl, new BannerViewPagerAdapter.ClickCallBack() {
            @Override
            public void callback(Object object) {
                String url = (String) object;
                Toast.makeText(context, url, Toast.LENGTH_LONG).show();
            }
        });
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        //监听器
    }

    /**
     * 设置默认高度 可以被重写
     */
    protected void initDefaultHeight() {
        //因为是同一个高度。singleimg和viewpager使用用一个layoutparams就可以
        ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
        //默认设置为屏幕高度的4分之一
        layoutParams.height = getScreenHeight() / 4;
        viewPager.setLayoutParams(layoutParams);
        layoutParams.width = getScreenWidth();
        singlImg.setLayoutParams(layoutParams);
    }


    /**
     * 找到控件
     */
    private void findView() {
        //主布局
        baseView = LayoutInflater.from(context).inflate(R.layout.banner_layout, this);
        //viewpager
        viewPager = (AutoScrollViewPager) baseView.findViewById(R.id.banner_viewpager);
        //单张图片
        singlImg = (ImageView) baseView.findViewById(R.id.banner_single_img);
    }

    /**
     * 获取屏幕宽度的方法
     */
    protected int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext()

                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * 获取屏幕宽度的方法
     */
    protected int getScreenHeight() {
        WindowManager wm = (WindowManager) getContext()

                .getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * 更新数据
     *
     * @param dataurl
     */
    public void setDataurl(ArrayList<String> dataurl) {
        this.dataurl.clear();
        this.dataurl.addAll(dataurl);
        if (dataurl.size() == 1) {
            viewPager.stopAutoScroll();
            singlImg.setVisibility(VISIBLE);
            viewPager.setVisibility(GONE);
            adapter.singleImgLoader(singlImg, dataurl.get(0));
        } else if (dataurl.size() > 1) {
            viewPager.setVisibility(VISIBLE);
            adapter.notifyDataSetChanged();
            singlImg.setVisibility(GONE);
            startViewPagerScroll(1500);
        }
    }

    /**
     * Banner开始滚动
     **/
    public void startViewPagerScroll(int delayTimeInMills) {
        viewPager.startAutoScroll(delayTimeInMills); //延迟delayTimeInMills秒启动
        viewPager.setInterval(1500); //每隔5秒自动切换一次
        viewPager.setBorderAnimation(false); //最后一张无动画效果
    }

    /**
     * Banner停止滚动
     **/
    public void stopViewPagerScroll() {
        viewPager.stopAutoScroll();
    }
}
