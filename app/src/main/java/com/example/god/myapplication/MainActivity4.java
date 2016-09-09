package com.example.god.myapplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Created by GOD on 2016/9/9.
 */
public class MainActivity4 extends Activity {
    private listview_text listview_text_1;
    private ImageCachceUitl imageCachceUitl;
    private List<String> urlList=new ArrayList<String>();
    private Runnable runnable;
    private Handler mainHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if(msg.what == 2012){
                //只要在主线程就可以处理ui
                ((ImageView)MainActivity4.this.findViewById(msg.arg1)).setImageBitmap((Bitmap) msg.obj);
            }
        }


    };
    private Handler handler =new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case ImageCachceUitl.SUCCSEE:
                    Bitmap bitmap=(Bitmap) msg.obj;
                    int psition=msg.arg1;
                    //通过TAg加载当前的limageview
                    ImageView imageView=(ImageView) listview.findViewWithTag(psition);
                    if (null!=bitmap&&null!=imageView) {
                        imageView.setImageBitmap(bitmap);
                    }
                    break;
                case ImageCachceUitl.FAIL:
                    Toast.makeText(getApplicationContext(), "下载错误", Toast.LENGTH_LONG).show();
                default:
                    break;
            }
        };
    };
    private ListView listview;
    private SoapObject request;
    private ExecutorService executorService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listview=(ListView) findViewById(R.id.glide_lv);
        imageCachceUitl=new ImageCachceUitl(getApplicationContext(), handler);
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");

        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");

        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg");


        executorService=Executors.newFixedThreadPool(5);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                listview_text_1.getImageromSdk();

                listview_text_1.getList_result().size();

                for(int i = 0;i<listview_text_1.getList_result().size();i++)
                {
                    urlList.add(listview_text_1.getList_result().get(i).toString());
                }
                try {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listview.setAdapter(new myListAdapt());
                        }
                    });

                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public String getImageFromAndroid(String arg0,String arg1, String arg2){

        return arg1;

    };

    @SuppressLint("SdCardPath")
    public  void getImageromSdk(){
        Log.i("进入获取图片方法", "进入获取图片方法");

    }
    class myListAdapt extends BaseAdapter{
        private LayoutInflater layoutInflater;
        ImageView list_imag;
        Button list_but;
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return urlList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return urlList.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @SuppressLint({ "InflateParams", "ViewHolder" })
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            layoutInflater=LayoutInflater.from(getApplication());
            //if (layoutInflater==null) {
            convertView = layoutInflater.inflate(R.layout.my_image_view, null);
            //  }
            list_but=(Button) convertView.findViewById(R.id.list_but);
            list_imag=(ImageView) convertView.findViewById(R.id.list_imag);

            list_imag.setTag(position);
            final Bitmap bitmap=imageCachceUitl.getBitmapFromUrl(urlList.get(position), position);
            if (null!=bitmap) {
                list_imag.setImageBitmap(bitmap);
            }
            list_imag.setVisibility(View.VISIBLE);

            list_but.setOnClickListener(new OnClickListener() {



                @Override
                public void onClick(View v) {
                    Log.i("获取点击焦点", "获取点击焦点");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            getImageromSdk();
                        }
                    }).start();
                }
            });
            return convertView;
        }

    }

}