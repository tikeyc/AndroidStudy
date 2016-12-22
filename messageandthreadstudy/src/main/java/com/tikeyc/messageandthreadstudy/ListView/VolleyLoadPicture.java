package com.tikeyc.messageandthreadstudy.ListView;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by public1 on 2016/12/21.
 */

/*
1.哪三大图片加载框架？
        1)    Picasso
        2)    Glide
        3)    Fresco

2.介绍:
    Picasso ：和Square的网络库一起能发挥最大作用，因为Picasso可以选择将网络请求的缓存部分交给了okhttp实现。

    Glide：模仿了Picasso的API，而且在他的基础上加了很多的扩展(比如gif等支持)，Glide默认的Bitmap格式是RGB_565，比    Picasso默认的ARGB_8888格式的内存开销要小一半；Picasso缓存的是全尺寸的(只缓存一种)，而Glide缓存的是跟ImageView尺寸相同的(即56*56和128*128是两个缓存) 。

    FB的图片加载框架Fresco：最大的优势在于5.0以下(最低2.3)的bitmap加载。在5.0以下系统，Fresco将图片放到一个特别的内存区域(Ashmem区)。当然，在图片不显示的时候，占用的内存会自动被释放。这会使得APP更加流畅，减少因图片内存占用而引发的OOM。为什么说是5.0以下，因为在5.0以后系统默认就是存储在Ashmem区了。

3.总结：
       Picasso所能实现的功能，Glide都能做，无非是所需的设置不同。但是Picasso体积比起Glide小太多如果项目中网络请求本身用的就是okhttp或者retrofit(本质还是okhttp)，那么建议用Picasso，体积会小很多(Square全家桶的干活)。Glide的好处是大型的图片流，比如gif、Video，如果你们是做美拍、爱拍这种视频类应用，建议使用。
Fresco在5.0以下的内存优化非常好，代价就是体积也非常的大，按体积算Fresco>Glide>Picasso
不过在使用起来也有些不便（小建议：他只能用内置的一个ImageView来实现这些功能，用起来比较麻烦，我们通常是根据Fresco自己改改，直接使用他的Bitmap层）
* */

/*用Volley加载图片
* */
public class VolleyLoadPicture {

    private ImageLoader mImageLoader = null;
    private BitmapCache mBitmapCache;


    private ImageLoader.ImageListener one_listener;

    public VolleyLoadPicture(Context context,ImageView imageView){
        one_listener = ImageLoader.getImageListener(imageView, 0, 0);

        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
        mBitmapCache = new BitmapCache();
        mImageLoader = new ImageLoader(mRequestQueue, mBitmapCache);
    }

    public ImageLoader getmImageLoader() {
        return mImageLoader;
    }

    public void setmImageLoader(ImageLoader mImageLoader) {
        this.mImageLoader = mImageLoader;
    }

    public ImageLoader.ImageListener getOne_listener() {
        return one_listener;
    }

    public void setOne_listener(ImageLoader.ImageListener one_listener) {
        this.one_listener = one_listener;
    }

    class BitmapCache implements ImageLoader.ImageCache {
        private LruCache<String, Bitmap> mCache;
        private int sizeValue;

        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    sizeValue = value.getRowBytes() * value.getHeight();
                    return sizeValue;
                }

            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }
    }
}


/*普通方式加载图片
public class NormalLoadPictrue {

    private String uri;
    private ImageView imageView;
    private byte[] picByte;


    public void getPicture(String uri,ImageView imageView){
        this.uri = uri;
        this.imageView = imageView;
        new Thread(runnable).start();
    }

    @SuppressLint("HandlerLeak")
    Handler handle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (picByte != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(picByte, 0, picByte.length);
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL(uri);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");
                conn.setReadTimeout(10000);

                if (conn.getResponseCode() == 200) {
                    InputStream fis =  conn.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] bytes = new byte[1024];
                    int length = -1;
                    while ((length = fis.read(bytes)) != -1) {
                        bos.write(bytes, 0, length);
                    }
                    picByte = bos.toByteArray();
                    bos.close();
                    fis.close();

                    Message message = new Message();
                    message.what = 1;
                    handle.sendMessage(message);
                }


            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

}
* */

/*用ImageLoader加载图片
public class ImageLoaderPicture {

    private DisplayImageOptions options;

    public ImageLoaderPicture(Context context) {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2)
        .denyCacheImageMultipleSizesInMemory()
        .discCacheFileNameGenerator(new Md5FileNameGenerator())
        .tasksProcessingOrder(QueueProcessingType.LIFO).enableLogging()
        .memoryCache(new WeakMemoryCache())
        .build();
        ImageLoader.getInstance().init(config);

        options = new DisplayImageOptions.Builder()
        .showStubImage(0)
        .showImageForEmptyUri(0)
        .showImageOnFail(0)
        .cacheInMemory().cacheOnDisc()
        .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
        .bitmapConfig(android.graphics.Bitmap.Config.RGB_565)
        .build();
    }

    public DisplayImageOptions getOptions() {
        return options;
    }

    public void setOptions(DisplayImageOptions options) {
        this.options = options;
    }


}
* */