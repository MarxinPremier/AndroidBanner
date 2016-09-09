package com.marxin.main.banner;

import android.content.Context;
import android.os.ParcelUuid;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.marxin.main.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * banner 的viewpager的适配器
 * Created by hejie on 2016/8/30.
 */
public class BannerViewPagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<String> dataUrl;
    private ArrayList<ImageView> imageViews = new ArrayList<>();
    private ClickCallBack clickCallBack;

    public BannerViewPagerAdapter(Context context, ArrayList<String> dataUrl, ClickCallBack clickCallBack) {
        this.context = context;
        this.dataUrl = dataUrl;
        this.clickCallBack = clickCallBack;
    }

    @Override
    public int getCount() {
        //使用无限大
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView;
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCallBack.callback(dataUrl.get(position % dataUrl.size()));
            }
        });
        //使用相应图片加载框架
        imageLoadMethod(imageView, dataUrl.get(position % dataUrl.size()), R.drawable.new_shop_nopic2x);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //移除imageview
        container.removeView((View) object);
    }


    /**
     * 图片加载使用的框架，可重写
     *
     * @param imageView  控件
     * @param url        图片url
     * @param defaultImg 默认图片
     */
    protected void imageLoadMethod(ImageView imageView, String url, int defaultImg) {
        //默认使用picasso
        Picasso.with(context).load(url).error(defaultImg).into(imageView);
    }

    /**
     * 单张图片加载图片
     * @param imageView
     * @param url
     */
    public  void  singleImgLoader(ImageView imageView,String url){
        imageLoadMethod(imageView,url,R.drawable.new_shop_nopic2x);
    }


    interface ClickCallBack {
        public void callback(Object object);
    }
}
