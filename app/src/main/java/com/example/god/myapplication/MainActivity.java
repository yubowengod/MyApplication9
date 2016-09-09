package com.example.god.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.god.myapplication.ImageTask;
import com.example.god.myapplication.ImageTaskManager;
import com.example.god.myapplication.ImageTaskManagerThread;


import java.io.File;
import java.util.List;



public class MainActivity extends Activity {

    private List<Fruit> fruitList = new ArrayList<Fruit>();
    private ArrayList<Fruit> fruit_array = new ArrayList<Fruit>();
    private ExecutorService executorService;
//    private ExecutorService executorService1;

    private EditText txt;
    private Button btn;
    private ImageView imageView1,imageView2;
    Bitmap bm;
    ArrayList<Bitmap> bm_array = new ArrayList<Bitmap>();

    listview_pic picqqq;


    private TextView textView;
    public ProvinceThread myThread;
    public Handler handler;
    public String result;
    public String result_insetinfo;

    private test_mul test_mul_1;
    private listview_text listview_text_1;

    private List<String> main_List_result;

    private Handler mainHandler = new Handler(){

                          @Override
                  public void handleMessage(Message msg) {
                          // TODO Auto-generated method stub
                          super.handleMessage(msg);
                          if(msg.what == 2012){
                                  //只要在主线程就可以处理ui
                                  ((ImageView)MainActivity.this.findViewById(msg.arg1)).setImageBitmap((Bitmap) msg.obj);
                              }
                      }


                      };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        handler = new Handler();


        executorService = Executors.newFixedThreadPool(5);

        txt = (EditText) findViewById(R.id.txt);


        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synchronized (this) {
                    test_pool();
                }
            }
        });



        listview_download();




    }




    private void listview_download(){
        executorService.submit(new Runnable() {
            @Override
            public void run() {

                listview_text_1.getImageromSdk();

                for(int i = 0;i<listview_text_1.getList_result().size();i++)
                {
                    test_mul_1.getImageromSdk(listview_text_1.getList_result().get(i).toString());

                    bm_array.add(test_mul_1.onDecodeClicked(test_mul_1.List_result));
                }

                try {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            for(int i = 0;i<listview_text_1.getList_result().size();i++){
                                fruit_array.add(new Fruit(listview_text_1.getList_result().get(i).toString(),bm_array.get(i)));
                                fruitList.add(fruit_array.get(i));
                            }





//                            Fruit apple = new Fruit("Apple", bm);
//                            fruitList.add(apple);


                            FruitAdapter adapter = new FruitAdapter(MainActivity.this,
                                    R.layout.fruit_item, fruitList);
                            ListView listView = (ListView) findViewById(R.id.list_view);
                            listView.setAdapter(adapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                                    Fruit fruit = fruitList.get(position);
                                    Toast.makeText(MainActivity.this, fruit.getName(),Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }
    private void getInfo(){

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x123){    //更新UI或其他操作
                    result=myThread.getList_result();//有值
//                    bm = picqqq.getLoacalBitmap("/storage/sdcard0/qwe.png");
                    bm=myThread.onDecodeClicked(result);
                    imageView1.setImageBitmap(bm);
                }

            }
        };

        myThread=new ProvinceThread("ImgToBase64String",handler);
        myThread.start();
    }






    /**
     * 线程池
     */
    private void  test_pool(){
        ImageTaskManager imageTaskManager=ImageTaskManager.getInstance();
        ImageTaskManagerThread imageTaskManagerThread=new ImageTaskManagerThread();
        new Thread(imageTaskManagerThread).start();

//        File f= ImageUtil.getDirName();
//        List<String> list=ImageUtil.getPicName();

        String []items={"图1"
                ,"图2",
                "图3",
                "图4",
                "图5",
                "图6"};

        for(int i=0;i<items.length;i++){
            imageTaskManager.addImageTask(new ImageTask(items[i],this));//添加任务进线程池
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }




}
