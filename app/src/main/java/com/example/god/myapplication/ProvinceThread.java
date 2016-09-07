package com.example.god.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Handler;
import android.os.Message;


import android.util.Base64;
import android.widget.ListView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GOD on 2016/8/29.
 */

public class ProvinceThread extends Thread{


    public String SERVICE_NAMESPACE = "http://tempuri.org/";
    private String methodName = "ImgToBase64String";   //设置方法名
    private SoapObject result;
    private ListView listView;
    private MainActivity activity;
    public String List_result = null;


    private Handler handler; //设置消息，通知主线程进行相关操作

    public ProvinceThread(String methodName, Handler handler){   // 构造方法，传入方法名和消息
        super();
        this.methodName=methodName;
        this.handler=handler;
    }


    public void setListView(ListView listView) {
        this.listView = listView;
    }  //设置方法对应的参数


    public  String getProvinceList() {
        // 调用 的方法
        String methodName = "ImgToBase64String";
        // 创建HttpTransportSE传输对象
        HttpTransportSE ht = new HttpTransportSE(Data_up.getSERVICE_URL());
        try {
            ht.debug = true;
            // 使用SOAP1.1协议创建Envelop对象
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            // 实例化SoapObject对象
            SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE,methodName);

            soapObject.addProperty("Imagefilename","111");
            envelope.bodyOut = soapObject;
            // 设置与.NET提供的webservice保持较好的兼容性
            envelope.dotNet = true;

            // 调用webservice
            ht.call(SERVICE_NAMESPACE + methodName, envelope);

            if (envelope.getResponse() != null) {
                // 获取服务器响应返回的SOAP消息
                SoapObject result = (SoapObject) envelope.bodyIn;
                String resuly_back ;
                resuly_back = result.getProperty(0).toString();//true
                List_result = resuly_back;
            }
        } catch (SoapFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run(){
        getProvinceList();
        Message msg=new Message();
        msg.what=0x123;
        handler.sendMessage(msg);
    }

    public Bitmap onDecodeClicked(String string) {
        byte[] decode = Base64.decode(string,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
//        //save to image on sdcard
//        saveBitmap(bitmap);
        return bitmap;
    }
    public String getList_result(){
        return List_result;
    }


}