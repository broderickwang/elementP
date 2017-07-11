package com.xuanqi.he.o2omvp.activity;

import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.se7en.utils.DeviceUtils;
import com.se7en.utils.SystemUtil;

/**
 * Created by He on 2017/5/12.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initLib();
//        initOkgo();

        SDKInitializer.initialize(this);
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    public static MyApplication getInstance(){
        return instance;
    }

    /*private void initOkgo() {
        OkGo.init(this);
        try {
            OkGo.getInstance()
                    .debug("OkGo", Level.INFO, true)
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //OkGo.DEFAULT_MILLISECONDS全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间
                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.NO_CACHE)
                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                    .setRetryCount(0)
                    .setCookieStore(new PersistentCookieStore())   //cookie持久化存储，如果cookie不过期，则一直有效
                    //可以设置https的证书,以下几种方案根据需要自己设置
                    .setCertificates();                               //方法一：信任所有证书,不安全有风险
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private void initLib() {
        SystemUtil.setContext(this);
        DeviceUtils.setContext(this);
        /*Glide从来都不会直接将图片的完整尺寸全部加载到内存中，
    而是用多少加载多少。Glide会自动判断ImageView的大小，
    然后只将这么大的图片像素加载到内存当中，帮助我们节省内存开支*/
        //with()方法可以接收Context、Activity或者Fragment类型的参数,
        // 注意with()方法中传入的实例会决定Glide加载图片的生命周期
        /*Glide.with(this)
                .load("url")
//            .asBitmap()//强制加载静态图，比如gif动图，加上这句就会加载成静态图片
                .asGif()//强制加载gif动图，要注意的是如果url是一张静态图，就会加载不出来，会加载出网络错误图
                .skipMemoryCache(true)//禁用内存缓存
                .placeholder(R.drawable.loading)//占位图
                .error(R.drawable.error)//网络错误图
                .diskCacheStrategy(DiskCacheStrategy.NONE)//不需要硬盘缓存
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//只缓存原始图片
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//只缓存转换后的图片（默认选项）
                .diskCacheStrategy(DiskCacheStrategy.ALL)//即缓存原始图片，也缓存转换过后的图片
                .transform(new GlideCircleTransform(this))//加载圆形图片
                .override(100, 100)//强制指定加载图片的大小，不过一般不需要，Glide会根据imageView的大小来对图片进行采样
                .into(imageView);*/
    }

    /*//改变字体颜色
    //先构造SpannableString
    SpannableString spanString = new SpannableString("我是动态创建的TextView");
    //再构造一个改变字体颜色的Span
*//*       ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.fblue));*//*
    //将这个Span应用于指定范围的字体
    spanString.setSpan(new ClickableSpan() {
        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            //设置文本的颜色
            ds.setColor(Color.BLUE);
            //超链接形式的下划线，false 表示不显示下划线，true表示显示下划线
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View view) {
            LogUtils.e("jhhh");
        }
    }, 0, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);//Spannable.SPAN_EXCLUSIVE_INCLUSIVE的原因，即（前面不应用特效，后面应用特效），
    textView.setText(spanString);
    textView.setMovementMethod(LinkMovementMethod.getInstance());//不设置 没有点击事件
    textView.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明*/


    //版本小于19关闭硬件加速
    /*if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
        mLoadingView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }*/

//    BottomSheetDialog
}
