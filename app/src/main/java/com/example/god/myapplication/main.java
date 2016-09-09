package com.example.god.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by GOD on 2016/9/9.
 */
public class main extends Activity {
    private ImageView glide_iv;
    private ListView glide_lv;
    private static final String URL ="http://ww4.sinaimg.cn/large/90bd89ffjw1eqvmd6o8r6j20go0p5ju2.jpg";
    private static final String URL1 ="http://192.168.1.117:8011/webnnn/1.jpg";
    private static final String URL2 ="http://192.168.1.117:8011/webnnn/2.jpg";
    private static final String URL3 ="http://192.168.1.117:8011/webnnn/3.jpg";
    private static final String URL4 ="http://192.168.1.117:8011/webnnn/4.jpg";
    private static final String URL5 ="http://192.168.1.117:8011/webnnn/5.jpg";

    private List<Pic> fruitList = new ArrayList<Pic>();
    private ArrayList<Pic> fruit_array = new ArrayList<Pic>();
    public List<String> urls = new ArrayList<String>();

    private ExecutorService executorService;

    private Handler mainHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if(msg.what == 2012){
                //只要在主线程就可以处理ui
                ((ImageView)main.this.findViewById(msg.arg1)).setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initDatas();
        glide_iv = (ImageView) findViewById(R.id.glide_iv);
        // 通过下面这句代码把网络图片加载到ImageView中，非常方便
        //Glide的with方法不仅接受Context，还接受Activity 和 Fragment等，Context会自动的从他们获取，使用很方便
        Glide.with(this).load(URL).into(glide_iv);
        glide_lv = (ListView) findViewById(R.id.glide_lv);

        executorService = Executors.newFixedThreadPool(5);

        for (int i = 0;i<urls.size();i++)
        {
            fruit_array.add(new Pic(urls.get(i)));
            fruitList.add(fruit_array.get(i));
        }

        Pic_Adapter adapter = new Pic_Adapter(main.this,R.layout.fruit_item, fruitList);
        ListView listView = (ListView) findViewById(R.id.glide_lv);
        listView.setAdapter(adapter);
//        glide_adatper();

    }

    public void glide_adatper(){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                glide_lv.setAdapter(new BaseAdapter() {
                    @Override
                    public View getView(int arg0, View contentView, ViewGroup arg2) {
                        ViewHolder holder=null;
                        View view;
                        if (contentView == null) {
                            holder=new ViewHolder();
                            contentView= LayoutInflater.from(main.this).inflate(R.layout.my_image_view,null);
                            holder.itemIv = (ImageView)contentView.findViewById(R.id.item_iv);
                            holder.itemTv = (TextView)contentView.findViewById(R.id.item_tv);
                            contentView.setTag(holder);
                        } else {
                            holder=(ViewHolder) contentView.getTag();
                        }
                        String url = urls.get(arg0);
                        //在ListView中加载列表图片
                        Glide.with(main.this).load(url).centerCrop()
                                .placeholder(R.mipmap.ic_launcher).crossFade()
                                .into(holder.itemIv);
                        holder.itemTv.setText(url);
                        return contentView;
                    }

                    @Override
                    public long getItemId(int arg0) {
                        // TODO Auto-generated method stub
                        return arg0;
                    }

                    @Override
                    public Object getItem(int arg0) {
                        // TODO Auto-generated method stub
                        return urls.get(arg0);
                    }

                    @Override
                    public int getCount() {
                        // TODO Auto-generated method stub
                        return urls.size();
                    }
                    class ViewHolder{
                        ImageView itemIv;
                        TextView itemTv;
                    }
                });

//                listview_text_1.getImageromSdk();
//                for(int i = 0;i<listview_text_1.getList_result().size();i++)
//                {
//                    test_mul_1.getImageromSdk(listview_text_1.getList_result().get(i).toString());
//                    bm_array.add(test_mul_1.onDecodeClicked(test_mul_1.List_result));
//                }
//
//                try {
//                    mainHandler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            for(int i = 0;i<listview_text_1.getList_result().size();i++){
//                                fruit_array.add(new Fruit(listview_text_1.getList_result().get(i).toString(),bm_array.get(i)));
//                                fruitList.add(fruit_array.get(i));
//                            }
//                            FruitAdapter adapter = new FruitAdapter(MainActivity.this,
//                                    R.layout.fruit_item, fruitList);
//                            ListView listView = (ListView) findViewById(R.id.list_view);
//                            listView.setAdapter(adapter);
//                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
//                                    Fruit fruit = fruitList.get(position);
//                                    Toast.makeText(MainActivity.this, fruit.getName(),Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//                    });
//                }
//                catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
            }
        });







    }
    /**
     * 添加数据
     */
    private void initDatas() {
        urls.add(URL);
        urls.add(URL1);
        urls.add(URL2);
        urls.add(URL3);
        urls.add(URL4);
        urls.add(URL5);


    }
}